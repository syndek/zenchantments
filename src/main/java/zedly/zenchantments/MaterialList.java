package zedly.zenchantments;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.AbstractList;
import java.util.concurrent.ThreadLocalRandom;

import static org.bukkit.Material.*;

public final class MaterialList extends AbstractList<Material> {
    public static final MaterialList BEDS = new MaterialList(
        WHITE_BED,
        ORANGE_BED,
        MAGENTA_BED,
        LIGHT_BLUE_BED,
        YELLOW_BED,
        LIME_BED,
        PINK_BED,
        GRAY_BED,
        LIGHT_GRAY_BED,
        CYAN_BED,
        PURPLE_BED,
        BLUE_BED,
        BROWN_BED,
        GREEN_BED,
        RED_BED,
        BLACK_BED
    );

    public static final MaterialList WOOL = new MaterialList(
        WHITE_WOOL,
        ORANGE_WOOL,
        MAGENTA_WOOL,
        LIGHT_BLUE_WOOL,
        YELLOW_WOOL,
        LIME_WOOL,
        PINK_WOOL,
        GRAY_WOOL,
        LIGHT_GRAY_WOOL,
        CYAN_WOOL,
        PURPLE_WOOL,
        BLUE_WOOL,
        BROWN_WOOL,
        GREEN_WOOL,
        RED_WOOL,
        BLACK_WOOL
    );

    public static final MaterialList SHULKER_BOXES = new MaterialList(
        WHITE_SHULKER_BOX,
        ORANGE_SHULKER_BOX,
        MAGENTA_SHULKER_BOX,
        LIGHT_BLUE_SHULKER_BOX,
        YELLOW_SHULKER_BOX,
        LIME_SHULKER_BOX,
        PINK_SHULKER_BOX,
        GRAY_SHULKER_BOX,
        LIGHT_GRAY_SHULKER_BOX,
        CYAN_SHULKER_BOX,
        PURPLE_SHULKER_BOX,
        BLUE_SHULKER_BOX,
        BROWN_SHULKER_BOX,
        GREEN_SHULKER_BOX,
        RED_SHULKER_BOX,
        BLACK_SHULKER_BOX,
        SHULKER_BOX
    );

    public static final MaterialList CONCRETE_POWDER = new MaterialList(
        WHITE_CONCRETE_POWDER,
        ORANGE_CONCRETE_POWDER,
        MAGENTA_CONCRETE_POWDER,
        LIGHT_BLUE_CONCRETE_POWDER,
        YELLOW_CONCRETE_POWDER,
        LIME_CONCRETE_POWDER,
        PINK_CONCRETE_POWDER,
        GRAY_CONCRETE_POWDER,
        LIGHT_GRAY_CONCRETE_POWDER,
        CYAN_CONCRETE_POWDER,
        PURPLE_CONCRETE_POWDER,
        BLUE_CONCRETE_POWDER,
        BROWN_CONCRETE_POWDER,
        GREEN_CONCRETE_POWDER,
        RED_CONCRETE_POWDER,
        BLACK_CONCRETE_POWDER
    );

    public static final MaterialList CONCRETE = new MaterialList(
        WHITE_CONCRETE,
        ORANGE_CONCRETE,
        MAGENTA_CONCRETE,
        LIGHT_BLUE_CONCRETE,
        YELLOW_CONCRETE,
        LIME_CONCRETE,
        PINK_CONCRETE,
        GRAY_CONCRETE,
        LIGHT_GRAY_CONCRETE,
        CYAN_CONCRETE,
        PURPLE_CONCRETE,
        BLUE_CONCRETE,
        BROWN_CONCRETE,
        GREEN_CONCRETE,
        RED_CONCRETE,
        BLACK_CONCRETE
    );

    public static final MaterialList GLAZED_TERRACOTTA = new MaterialList(
        WHITE_GLAZED_TERRACOTTA,
        ORANGE_GLAZED_TERRACOTTA,
        MAGENTA_GLAZED_TERRACOTTA,
        LIGHT_BLUE_GLAZED_TERRACOTTA,
        YELLOW_GLAZED_TERRACOTTA,
        LIME_GLAZED_TERRACOTTA,
        PINK_GLAZED_TERRACOTTA,
        GRAY_GLAZED_TERRACOTTA,
        LIGHT_GRAY_GLAZED_TERRACOTTA,
        CYAN_GLAZED_TERRACOTTA,
        PURPLE_GLAZED_TERRACOTTA,
        BLUE_GLAZED_TERRACOTTA,
        BROWN_GLAZED_TERRACOTTA,
        GREEN_GLAZED_TERRACOTTA,
        RED_GLAZED_TERRACOTTA,
        BLACK_GLAZED_TERRACOTTA
    );

    public static final MaterialList TERRACOTTA = new MaterialList(
        WHITE_TERRACOTTA,
        ORANGE_TERRACOTTA,
        MAGENTA_TERRACOTTA,
        LIGHT_BLUE_TERRACOTTA,
        YELLOW_TERRACOTTA,
        LIME_TERRACOTTA,
        PINK_TERRACOTTA,
        GRAY_TERRACOTTA,
        LIGHT_GRAY_TERRACOTTA,
        CYAN_TERRACOTTA,
        PURPLE_TERRACOTTA,
        BLUE_TERRACOTTA,
        BROWN_TERRACOTTA,
        GREEN_TERRACOTTA,
        RED_TERRACOTTA,
        BLACK_TERRACOTTA
    );

    public static final MaterialList CARPETS = new MaterialList(
        WHITE_CARPET,
        ORANGE_CARPET,
        MAGENTA_CARPET,
        LIGHT_BLUE_CARPET,
        YELLOW_CARPET,
        LIME_CARPET,
        PINK_CARPET,
        GRAY_CARPET,
        LIGHT_GRAY_CARPET,
        CYAN_CARPET,
        PURPLE_CARPET,
        BLUE_CARPET,
        BROWN_CARPET,
        GREEN_CARPET,
        RED_CARPET,
        BLACK_CARPET
    );

    public static final MaterialList STAINED_GLASS = new MaterialList(
        WHITE_STAINED_GLASS,
        ORANGE_STAINED_GLASS,
        MAGENTA_STAINED_GLASS,
        LIGHT_BLUE_STAINED_GLASS,
        YELLOW_STAINED_GLASS,
        LIME_STAINED_GLASS,
        PINK_STAINED_GLASS,
        GRAY_STAINED_GLASS,
        LIGHT_GRAY_STAINED_GLASS,
        CYAN_STAINED_GLASS,
        PURPLE_STAINED_GLASS,
        BLUE_STAINED_GLASS,
        BROWN_STAINED_GLASS,
        GREEN_STAINED_GLASS,
        RED_STAINED_GLASS,
        BLACK_STAINED_GLASS
    );

    public static final MaterialList STAINED_GLASS_PANES = new MaterialList(
        WHITE_STAINED_GLASS_PANE,
        ORANGE_STAINED_GLASS_PANE,
        MAGENTA_STAINED_GLASS_PANE,
        LIGHT_BLUE_STAINED_GLASS_PANE,
        YELLOW_STAINED_GLASS_PANE,
        LIME_STAINED_GLASS_PANE,
        PINK_STAINED_GLASS_PANE,
        GRAY_STAINED_GLASS_PANE,
        LIGHT_GRAY_STAINED_GLASS_PANE,
        CYAN_STAINED_GLASS_PANE,
        PURPLE_STAINED_GLASS_PANE,
        BLUE_STAINED_GLASS_PANE,
        BROWN_STAINED_GLASS_PANE,
        GREEN_STAINED_GLASS_PANE,
        RED_STAINED_GLASS_PANE,
        BLACK_STAINED_GLASS_PANE
    );

    public static final MaterialList DYES = new MaterialList(
        BONE_MEAL,
        ORANGE_DYE,
        MAGENTA_DYE,
        LIGHT_BLUE_DYE,
        YELLOW_DYE,
        LIME_DYE,
        PINK_DYE,
        GRAY_DYE,
        LIGHT_GRAY_DYE,
        CYAN_DYE,
        PURPLE_DYE,
        LAPIS_LAZULI,
        COCOA_BEANS,
        GREEN_DYE,
        RED_DYE,
        INK_SAC
    );

    public static final MaterialList WOODEN_BUTTONS = new MaterialList(
        OAK_BUTTON,
        BIRCH_BUTTON,
        SPRUCE_BUTTON,
        ACACIA_BUTTON,
        DARK_OAK_BUTTON,
        JUNGLE_BUTTON,
        CRIMSON_BUTTON,
        WARPED_BUTTON
    );

    public static final MaterialList WOODEN_DOORS = new MaterialList(
        OAK_DOOR,
        BIRCH_DOOR,
        SPRUCE_DOOR,
        ACACIA_DOOR,
        DARK_OAK_DOOR,
        JUNGLE_DOOR,
        CRIMSON_DOOR,
        WARPED_DOOR
    );

    public static final MaterialList WOODEN_FENCES = new MaterialList(
        OAK_FENCE,
        BIRCH_FENCE,
        SPRUCE_FENCE,
        ACACIA_FENCE,
        DARK_OAK_FENCE,
        JUNGLE_FENCE,
        CRIMSON_FENCE,
        WARPED_FENCE
    );

    public static final MaterialList FENCE_GATES = new MaterialList(
        OAK_FENCE_GATE,
        BIRCH_FENCE_GATE,
        SPRUCE_FENCE_GATE,
        ACACIA_FENCE_GATE,
        DARK_OAK_FENCE_GATE,
        JUNGLE_FENCE_GATE,
        CRIMSON_FENCE_GATE,
        WARPED_FENCE_GATE
    );

    public static final MaterialList LEAVES = new MaterialList(
        OAK_LEAVES,
        BIRCH_LEAVES,
        SPRUCE_LEAVES,
        ACACIA_LEAVES,
        DARK_OAK_LEAVES,
        JUNGLE_LEAVES
    );

    public static final MaterialList LOGS = new MaterialList(
        OAK_LOG,
        BIRCH_LOG,
        SPRUCE_LOG,
        ACACIA_LOG,
        DARK_OAK_LOG,
        JUNGLE_LOG,
        CRIMSON_STEM,
        WARPED_STEM
    );

    public static final MaterialList WOODEN_PLANKS = new MaterialList(
        OAK_PLANKS,
        BIRCH_PLANKS,
        SPRUCE_PLANKS,
        ACACIA_PLANKS,
        DARK_OAK_PLANKS,
        JUNGLE_PLANKS,
        CRIMSON_PLANKS,
        WARPED_PLANKS
    );

    public static final MaterialList WOODEN_PRESSURE_PLATES = new MaterialList(
        OAK_PRESSURE_PLATE,
        BIRCH_PRESSURE_PLATE,
        SPRUCE_PRESSURE_PLATE,
        ACACIA_PRESSURE_PLATE,
        DARK_OAK_PRESSURE_PLATE,
        JUNGLE_PRESSURE_PLATE,
        CRIMSON_PRESSURE_PLATE,
        WARPED_PRESSURE_PLATE
    );

    public static final MaterialList SAPLINGS = new MaterialList(
        ACACIA_SAPLING,
        BIRCH_SAPLING,
        DARK_OAK_SAPLING,
        JUNGLE_SAPLING,
        OAK_SAPLING,
        SPRUCE_SAPLING
    );

    public static final MaterialList WOODEN_SLABS = new MaterialList(
        OAK_SLAB,
        BIRCH_SLAB,
        SPRUCE_SLAB,
        ACACIA_SLAB,
        DARK_OAK_SLAB,
        JUNGLE_SLAB,
        CRIMSON_SLAB,
        WARPED_SLAB
    );

    public static final MaterialList WOODEN_STAIRS = new MaterialList(
        OAK_STAIRS,
        BIRCH_STAIRS,
        SPRUCE_STAIRS,
        ACACIA_STAIRS,
        DARK_OAK_STAIRS,
        JUNGLE_STAIRS,
        CRIMSON_STAIRS,
        WARPED_STAIRS
    );

    public static final MaterialList WOODEN_TRAPDOORS = new MaterialList(
        OAK_TRAPDOOR,
        BIRCH_TRAPDOOR,
        SPRUCE_TRAPDOOR,
        ACACIA_TRAPDOOR,
        DARK_OAK_TRAPDOOR,
        JUNGLE_TRAPDOOR,
        CRIMSON_TRAPDOOR,
        WARPED_SIGN
    );

    public static final MaterialList WOOD = new MaterialList(
        OAK_WOOD,
        BIRCH_WOOD,
        SPRUCE_WOOD,
        ACACIA_WOOD,
        DARK_OAK_WOOD,
        JUNGLE_WOOD,
        CRIMSON_HYPHAE,
        WARPED_HYPHAE
    );

    public static final MaterialList STRIPPED_LOGS = new MaterialList(
        STRIPPED_OAK_LOG,
        STRIPPED_BIRCH_LOG,
        STRIPPED_SPRUCE_LOG,
        STRIPPED_ACACIA_LOG,
        STRIPPED_DARK_OAK_LOG,
        STRIPPED_JUNGLE_LOG,
        STRIPPED_CRIMSON_STEM,
        STRIPPED_WARPED_STEM
    );

    public static final MaterialList STRIPPED_WOOD = new MaterialList(
        STRIPPED_OAK_WOOD,
        STRIPPED_BIRCH_WOOD,
        STRIPPED_SPRUCE_WOOD,
        STRIPPED_ACACIA_WOOD,
        STRIPPED_DARK_OAK_WOOD,
        STRIPPED_JUNGLE_WOOD,
        STRIPPED_CRIMSON_HYPHAE,
        STRIPPED_WARPED_HYPHAE
    );

    public static final MaterialList DEADLY_PLANTS = new MaterialList(WITHER_ROSE);

    public static final MaterialList SMALL_FLOWERS = new MaterialList(
        DANDELION,
        POPPY,
        BLUE_ORCHID,
        ALLIUM,
        AZURE_BLUET,
        RED_TULIP,
        ORANGE_TULIP,
        WHITE_TULIP,
        PINK_TULIP,
        OXEYE_DAISY
    );

    public static final MaterialList LARGE_FLOWERS = new MaterialList(
        SUNFLOWER,
        LILAC,
        TALL_GRASS,
        LARGE_FERN,
        ROSE_BUSH,
        PEONY
    );

    public static final MaterialList GROWN_CROPS = new MaterialList(
        WHEAT,
        POTATOES,
        CARROTS,
        COCOA,
        BEETROOTS,
        NETHER_WART,
        SWEET_BERRY_BUSH
    );

    public static final MaterialList GROWN_CROP_BLOCKS = new MaterialList(MELON, PUMPKIN);

    private final Material[] values;

    private MaterialList(final @NotNull Material... values) {
        this.values = values;
    }

    @Override
    public Material get(int index) {
        return this.values[index];
    }

    @Override
    public int size() {
        return this.values.length;
    }

    @Override
    public int indexOf(final @NotNull Object object) {
        if (!(object instanceof Material)) {
            throw new IllegalArgumentException("Object must be a Material.");
        }

        final Material search = (Material) object;

        for (int i = 0; i < this.values.length; i++) {
            if (this.values[i] == search) {
                return i;
            }
        }

        return -1;
    }

    @NotNull
    public Material getNext(final @NotNull Material material) {
        final int index = this.indexOf(material);

        if (index == -1) {
            throw new IllegalArgumentException("Material is not contained in the MaterialList.");
        }

        return this.values[(index + 1) % this.values.length];
    }

    @NotNull
    public Material getRandom() {
        return this.values[ThreadLocalRandom.current().nextInt(this.values.length)];
    }
}
