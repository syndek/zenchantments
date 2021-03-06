package zedly.zenchantments.enchantments;

import com.google.common.collect.ImmutableSet;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.jetbrains.annotations.NotNull;
import zedly.zenchantments.Hand;
import zedly.zenchantments.Tool;
import zedly.zenchantments.Zenchantment;
import zedly.zenchantments.ZenchantmentsPlugin;
import zedly.zenchantments.arrows.ApocalypseArrow;
import zedly.zenchantments.arrows.ZenchantedArrow;

import java.util.Set;

public final class Apocalypse extends Zenchantment {
    public static final String KEY = "apocalypse";

    private static final String                             NAME        = "Apocalypse";
    private static final String                             DESCRIPTION = "Unleashes hell";
    private static final Set<Class<? extends Zenchantment>> CONFLICTING = ImmutableSet.of();
    private static final Hand                               HAND_USE    = Hand.RIGHT;

    private final NamespacedKey key;

    public Apocalypse(
        final @NotNull ZenchantmentsPlugin plugin,
        final @NotNull Set<Tool> enchantable,
        final int maxLevel,
        final int cooldown,
        final double probability,
        final float power
    ) {
        super(plugin, enchantable, maxLevel, cooldown, probability, power);
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
        final ApocalypseArrow arrow = new ApocalypseArrow(this.getPlugin(), (Arrow) event.getProjectile());
        ZenchantedArrow.putArrow((Arrow) event.getProjectile(), arrow, (Player) event.getEntity());
        return true;
    }
}
