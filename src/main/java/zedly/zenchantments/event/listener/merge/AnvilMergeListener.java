package zedly.zenchantments.event.listener.merge;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import zedly.zenchantments.Zenchantment;
import zedly.zenchantments.ZenchantmentsPlugin;
import zedly.zenchantments.configuration.WorldConfiguration;
import zedly.zenchantments.enchantments.Unrepairable;

import java.util.*;
import java.util.Map.Entry;

import static org.bukkit.Material.AIR;
import static org.bukkit.Material.ENCHANTED_BOOK;
import static org.bukkit.enchantments.Enchantment.DURABILITY;

public class AnvilMergeListener implements Listener {
    private final ZenchantmentsPlugin plugin;

    public AnvilMergeListener(@NotNull ZenchantmentsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    private void onClick(@NotNull InventoryClickEvent event) {
        if (event.getInventory().getType() != InventoryType.ANVIL || !event.getClick().isLeftClick()) {
            return;
        }

        if (event.getCurrentItem() == null || event.getCurrentItem().getType() != ENCHANTED_BOOK) {
            return;
        }

        EnchantmentStorageMeta bookMeta = (EnchantmentStorageMeta) event.getCurrentItem().getItemMeta();

        if (bookMeta.getStoredEnchants().containsKey(DURABILITY)
            && bookMeta.getStoredEnchants().get(DURABILITY) == 0
        ) {
            bookMeta.removeStoredEnchant(DURABILITY);
            event.getCurrentItem().setItemMeta(bookMeta);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    private void onClick(@NotNull PrepareAnvilEvent event) {
        if (event.getViewers().size() < 1) {
            return;
        }

        final AnvilInventory anvilInv = event.getInventory();
        final ItemStack item0 = anvilInv.getItem(0);

        if (item0 != null && item0.getType() == ENCHANTED_BOOK) {
            final EnchantmentStorageMeta bookMeta = (EnchantmentStorageMeta) item0.getItemMeta();

            if (!bookMeta.getStoredEnchants().containsKey(DURABILITY)) {
                bookMeta.addStoredEnchant(DURABILITY, 0, true);
                item0.setItemMeta(bookMeta);
            }
        }

        final ItemStack item1 = anvilInv.getItem(1);
        if (item1 != null && item1.getType() == ENCHANTED_BOOK) {
            final EnchantmentStorageMeta bookMeta = (EnchantmentStorageMeta) item1.getItemMeta();

            if (!bookMeta.getStoredEnchants().containsKey(DURABILITY)) {
                bookMeta.addStoredEnchant(DURABILITY, 0, true);
                item1.setItemMeta(bookMeta);
            }
        }

        this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, () -> {
            final ItemStack stack = this.doMerge(
                item0,
                item1,
                anvilInv.getItem(2),
                this.plugin.getWorldConfigurationProvider().getConfigurationForWorld(
                    event.getViewers().get(0).getWorld()
                )
            );

            if (stack != null) {
                anvilInv.setItem(2, stack);
            }
        }, 0);
    }

    @Nullable
    @Contract("null, _, _, _ -> null; _, null, _, _ -> null; _, _, null, _ -> null")
    private ItemStack doMerge(
        @Nullable ItemStack leftItem,
        @Nullable ItemStack rightItem,
        @Nullable ItemStack oldOutItem,
        @NotNull WorldConfiguration worldConfiguration
    ) {
        if (leftItem == null || rightItem == null || oldOutItem == null) {
            return null;
        }

        if (leftItem.getType() == AIR
            || rightItem.getType() == AIR
            || oldOutItem.getType() == AIR
        ) {
            return null;
        }

        if (!oldOutItem.hasItemMeta()) {
            return null;
        }

        final List<String> normalLeftLore = new ArrayList<>();
        final Map<Zenchantment, Integer> leftEnchantments = Zenchantment.getZenchantmentsOnItemStack(
            leftItem,
            true,
            this.plugin.getGlobalConfiguration(),
            worldConfiguration,
            normalLeftLore
        );
        final Map<Zenchantment, Integer> rightEnchantments = Zenchantment.getZenchantmentsOnItemStack(
            rightItem,
            true,
            this.plugin.getGlobalConfiguration(),
            worldConfiguration
        );

        final boolean isBookLeft = leftItem.getType() == ENCHANTED_BOOK;
        final boolean isBookRight = rightItem.getType() == ENCHANTED_BOOK;

        final Map<Enchantment, Integer> leftEnch = isBookLeft
            ? ((EnchantmentStorageMeta) leftItem.getItemMeta()).getStoredEnchants()
            : leftItem.getEnchantments();
        final Map<Enchantment, Integer> rightEnch = isBookRight
            ? ((EnchantmentStorageMeta) rightItem.getItemMeta()).getStoredEnchants()
            : rightItem.getEnchantments();

        int leftUnbreakingLevel = leftEnch.getOrDefault(DURABILITY, -1);
        int rightUnbreakingLevel = rightEnch.getOrDefault(DURABILITY, -1);

        for (final Zenchantment enchantment : leftEnchantments.keySet()) {
            if (enchantment.getKey().getKey().equals(Unrepairable.KEY)) {
                return new ItemStack(AIR);
            }
        }

        for (final Zenchantment enchantment : rightEnchantments.keySet()) {
            if (enchantment.getKey().getKey().equals(Unrepairable.KEY)) {
                return new ItemStack(AIR);
            }
        }

        if (leftEnchantments.isEmpty() && rightEnchantments.isEmpty()) {
            return oldOutItem;
        }

        final EnchantmentPool pool = new EnchantmentPool(oldOutItem, worldConfiguration.getMaxZenchantments());
        pool.addAll(leftEnchantments);

        final List<Entry<Zenchantment, Integer>> rightEnchantmentList = new ArrayList<>(rightEnchantments.entrySet());
        Collections.shuffle(rightEnchantmentList);
        pool.addAll(rightEnchantmentList);

        final Map<Zenchantment, Integer> outEnchantments = pool.getEnchantmentMap();
        final ItemStack newOutItem = new ItemStack(oldOutItem);

        final ItemMeta meta = oldOutItem.getItemMeta();
        meta.setLore(null);
        newOutItem.setItemMeta(meta);

        for (final Entry<Zenchantment, Integer> enchantEntry : outEnchantments.entrySet()) {
            enchantEntry.getKey().setForItemStack(newOutItem, enchantEntry.getValue(), worldConfiguration);
        }

        final ItemMeta newOutMeta = newOutItem.getItemMeta();
        final List<String> outLore = newOutMeta.hasLore() ? newOutMeta.getLore() : new ArrayList<>();
        outLore.addAll(normalLeftLore);

        if (leftUnbreakingLevel * rightUnbreakingLevel == 0
            && leftUnbreakingLevel < 1
            && rightUnbreakingLevel < 1
        ) {
            if (oldOutItem.getType() == ENCHANTED_BOOK) {
                ((EnchantmentStorageMeta) newOutMeta).removeStoredEnchant(DURABILITY);
            } else {
                newOutMeta.removeEnchant(DURABILITY);
            }

            newOutMeta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        newOutMeta.setLore(outLore);
        newOutItem.setItemMeta(newOutMeta);

        Zenchantment.updateEnchantmentGlowForItemStack(newOutItem, !outEnchantments.isEmpty(), worldConfiguration);

        return newOutItem;
    }

    private static class EnchantmentPool {
        private final Map<Zenchantment, Integer> enchantmentPool = new HashMap<>();
        private final ItemStack                  itemStack;
        private final int                        maxCapacity;

        public EnchantmentPool(@NotNull ItemStack itemStack, int maxCapacity) {
            this.itemStack = itemStack;
            this.maxCapacity = maxCapacity;
        }

        @Contract(mutates = "this")
        public void addAll(@NotNull Map<Zenchantment, Integer> enchantsToAdd) {
            this.addAll(enchantsToAdd.entrySet());
        }

        @Contract(mutates = "this")
        public void addAll(@NotNull Collection<Entry<Zenchantment, Integer>> enchantsToAdd) {
            for (Entry<Zenchantment, Integer> enchantEntry : enchantsToAdd) {
                this.addEnchant(enchantEntry);
            }
        }

        @Contract(mutates = "this")
        private void addEnchant(@NotNull Entry<Zenchantment, Integer> enchantEntry) {
            Zenchantment ench = enchantEntry.getKey();

            if (this.itemStack.getType() != ENCHANTED_BOOK && !ench.isValidMaterial(this.itemStack)) {
                return;
            }

            for (Zenchantment enchantment : this.enchantmentPool.keySet()) {
                if (ench.getConflicting().contains(enchantment.getClass())) {
                    return;
                }
            }

            if (this.enchantmentPool.containsKey(ench)) {
                int leftLevel = this.enchantmentPool.get(ench);
                int rightLevel = enchantEntry.getValue();

                if (leftLevel == rightLevel && leftLevel < ench.getMaxLevel()) {
                    this.enchantmentPool.put(ench, leftLevel + 1);
                } else if (rightLevel > leftLevel) {
                    this.enchantmentPool.put(ench, rightLevel);
                }
            } else if (this.enchantmentPool.size() < this.maxCapacity) {
                this.enchantmentPool.put(ench, enchantEntry.getValue());
            }
        }

        @NotNull
        public Map<Zenchantment, Integer> getEnchantmentMap() {
            return this.enchantmentPool;
        }
    }
}