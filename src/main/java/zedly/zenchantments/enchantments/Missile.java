package zedly.zenchantments.enchantments;

import com.google.common.collect.ImmutableSet;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.jetbrains.annotations.NotNull;
import zedly.zenchantments.*;
import zedly.zenchantments.arrows.MissileArrow;
import zedly.zenchantments.arrows.ZenchantedArrow;

import java.util.Set;

public final class Missile extends Zenchantment {
    public static final String KEY = "missile";

    private static final String                             NAME        = "Missile";
    private static final String                             DESCRIPTION = "Shoots a missile from the bow";
    private static final Set<Class<? extends Zenchantment>> CONFLICTING = ImmutableSet.of();
    private static final Hand                               HAND_USE    = Hand.RIGHT;

    private final NamespacedKey key;

    public Missile(
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
    public boolean onEntityShootBow(final @NotNull EntityShootBowEvent event, final int level, final boolean usedHand) {
        final MissileArrow arrow = new MissileArrow(this.getPlugin(), (Arrow) event.getProjectile());
        final Player player = (Player) event.getEntity();

        ZenchantedArrow.putArrow((Arrow) event.getProjectile(), arrow, player);

        event.setCancelled(true);

        Utilities.damageItemStack(player, 1, usedHand);
        Utilities.removeMaterialsFromPlayer(player, Material.ARROW, 1);
        return true;
    }
}
