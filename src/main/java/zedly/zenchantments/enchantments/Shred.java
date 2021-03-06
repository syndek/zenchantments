package zedly.zenchantments.enchantments;

import com.google.common.collect.ImmutableSet;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import zedly.zenchantments.*;
import zedly.zenchantments.configuration.WorldConfiguration;
import zedly.zenchantments.event.BlockShredEvent;

import java.util.HashSet;
import java.util.Set;

import static org.bukkit.Material.*;
import static zedly.zenchantments.MaterialList.SHRED_PICKS;
import static zedly.zenchantments.MaterialList.SHRED_SHOVELS;

public final class Shred extends Zenchantment {
    public static final String KEY = "shred";

    private static final String                             NAME        = "Shred";
    private static final String                             DESCRIPTION = "Breaks the blocks within a radius of the original block mined";
    private static final Set<Class<? extends Zenchantment>> CONFLICTING = ImmutableSet.of(Pierce.class, Switch.class);
    private static final Hand                               HAND_USE    = Hand.LEFT;

    private final NamespacedKey key;

    public Shred(
        final @NotNull ZenchantmentsPlugin plugin,
        final @NotNull Set<Tool> enchantable,
        final int maxLevel,
        final int cooldown,
        final double power,
        final float probability
    ) {
        super(plugin, enchantable, maxLevel, cooldown, power, probability);
        this.key = new NamespacedKey(plugin, KEY);
    }

    @Override
    @NotNull
    public NamespacedKey getKey() {
        return this.key;
    }

    @Override
    @NotNull
    public String getName() {
        return NAME;
    }

    @Override
    @NotNull
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    @NotNull
    public Set<Class<? extends Zenchantment>> getConflicting() {
        return CONFLICTING;
    }

    @Override
    @NotNull
    public Hand getHandUse() {
        return HAND_USE;
    }

    @Override
    public boolean onBlockBreak(final @NotNull BlockBreakEvent event, final int level, final boolean usedHand) {
        final Block block = event.getBlock();

        if (!SHRED_PICKS.contains(block.getType()) && !SHRED_SHOVELS.contains(block.getType())) {
            return false;
        }

        final ItemStack hand = Utilities.getUsedItemStack(event.getPlayer(), usedHand);

        this.shred(
            block,
            block,
            new int[] { level + 3, level + 3, level + 3 },
            0,
            4.6 + (level * 0.22),
            new HashSet<>(),
            event.getPlayer(),
            this.getPlugin().getWorldConfigurationProvider().getConfigurationForWorld(block.getWorld()),
            hand.getType(),
            usedHand
        );

        return true;
    }

    private void shred(
        final @NotNull Block centerBlock,
        final @NotNull Block relativeBlock,
        final int[] coordinates,
        final int time,
        final double size,
        final @NotNull Set<Block> used,
        final @NotNull Player player,
        final @NotNull WorldConfiguration config,
        final @NotNull Material itemType,
        final boolean usedHand
    ) {
        if (MaterialList.AIR.contains(relativeBlock.getType()) || used.contains(relativeBlock)) {
            return;
        }

        final Material originalType = relativeBlock.getType();

        if ((Tool.PICKAXE.contains(itemType) && !SHRED_PICKS.contains(relativeBlock.getType()))
            || (Tool.SHOVEL.contains(itemType) && !SHRED_SHOVELS.contains(relativeBlock.getType()))
        ) {
            return;
        }

        if (config.areShredDropsEnabled() == 0) {
            this.getPlugin().getCompatibilityAdapter().breakBlock(relativeBlock, player);
        } else {
            final BlockShredEvent event = new BlockShredEvent(relativeBlock, player);
            this.getPlugin().getServer().getPluginManager().callEvent(event);

            if (event.isCancelled()) {
                return;
            }

            if (config.areShredDropsEnabled() == 1) {
                if (relativeBlock.getType() == NETHER_QUARTZ_ORE) {
                    relativeBlock.setType(NETHERRACK);
                } else if (MaterialList.ORES.contains(relativeBlock.getType())) {
                    relativeBlock.setType(STONE);
                }

                if (event.isCancelled() || event.getBlock().getType() == Material.AIR) {
                    return;
                }

                Zenchantment.applyForTool(
                    player,
                    this.getPlugin().getPlayerDataProvider(),
                    this.getPlugin().getGlobalConfiguration(),
                    this.getPlugin().getWorldConfigurationProvider(),
                    Utilities.getUsedItemStack(player, true),
                    (ench, level) -> ench.onBlockBreak(event, level, true)
                );

                if (event.isCancelled()) {
                    return;
                }

                relativeBlock.breakNaturally();
            } else {
                relativeBlock.setType(Material.AIR);
            }
        }

        Sound sound = null;
        switch (originalType) {
            case GRASS_BLOCK:
                sound = Sound.BLOCK_GRASS_BREAK;
                break;
            case DIRT:
            case GRAVEL:
            case CLAY:
                sound = Sound.BLOCK_GRAVEL_BREAK;
                break;
            case SAND:
                sound = Sound.BLOCK_SAND_BREAK;
                break;
            case AIR:
                break;
            default:
                sound = Sound.BLOCK_STONE_BREAK;
                break;
        }

        if (sound != null) {
            relativeBlock
                .getLocation()
                .getWorld()
                .playSound(relativeBlock.getLocation(), sound, 1, 1);
        }

        Utilities.damageItemStack(player, 1, usedHand);

        used.add(relativeBlock);

        for (int i = 0; i < 3; i++) {
            if (coordinates[i] > 0) {
                coordinates[i] -= 1;

                final Block block1 = relativeBlock.getRelative(i == 0 ? -1 : 0, i == 1 ? -1 : 0, i == 2 ? -1 : 0);
                final Block block2 = relativeBlock.getRelative(i == 0 ? 1 : 0, i == 1 ? 1 : 0, i == 2 ? 1 : 0);

                if (block1.getLocation().distanceSquared(centerBlock.getLocation()) < size + (-1 + 2 * Math.random())) {
                    this.shred(centerBlock, block1, coordinates, time + 2, size, used, player, config, itemType, usedHand);
                }

                if (block2.getLocation().distanceSquared(centerBlock.getLocation()) < size + (-1 + 2 * Math.random())) {
                    this.shred(centerBlock, block2, coordinates, time + 2, size, used, player, config, itemType, usedHand);
                }

                coordinates[i] += 1;
            }
        }
    }
}
