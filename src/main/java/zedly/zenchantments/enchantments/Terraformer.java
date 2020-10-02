package zedly.zenchantments.enchantments;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;
import zedly.zenchantments.Zenchantment;
import zedly.zenchantments.Storage;
import zedly.zenchantments.Utilities;
import zedly.zenchantments.compatibility.EnumStorage;
import zedly.zenchantments.enums.Hand;
import zedly.zenchantments.enums.Tool;

import static org.bukkit.Material.*;
import static org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK;
import static zedly.zenchantments.enums.Tool.SHOVEL;

public class Terraformer extends Zenchantment {

	public static int[][] SEARCH_FACES = new int[][]{new int[]{-1, 0, 0}, new int[]{1, 0, 0}, new int[]{0, -1, 0}, new int[]{0, 0, -1}, new int[]{0, 0, 1}};

	private static final int MAX_BLOCKS = 64;

	public static final int ID = 61;

	@Override
	public Builder<Terraformer> defaults() {
		return new Builder<>(Terraformer::new, ID)
			.maxLevel(1)
			.loreName("Terraformer")
			.probability(0)
			.enchantable(new Tool[]{SHOVEL})
			.conflicting(new Class[]{})
			.description("Places the leftmost blocks in the players inventory within a 7 block radius")
			.cooldown(0)
			.power(-1.0)
			.handUse(Hand.RIGHT);
	}

	@Override
	public boolean onBlockInteract(PlayerInteractEvent evt, int level, boolean usedHand) {
		if (evt.getPlayer().isSneaking()) {
			if (evt.getAction().equals(RIGHT_CLICK_BLOCK)) {
				Block start = evt.getClickedBlock().getRelative(evt.getBlockFace());
				Material mat = AIR;

				for (int i = 0; i < 9; i++) {
					if (evt.getPlayer().getInventory().getItem(i) != null) {
						if (evt.getPlayer().getInventory().getItem(i).getType().isBlock() &&
							Storage.COMPATIBILITY_ADAPTER.TerraformerMaterials().contains(
								evt.getPlayer().getInventory().getItem(i).getType())) {
							mat = evt.getPlayer().getInventory().getItem(i).getType();
							break;
						}
					}
				}

				for (Block b : Utilities.bfs(start, MAX_BLOCKS, false, 5.f, SEARCH_FACES,
					Storage.COMPATIBILITY_ADAPTER.Airs(), new EnumStorage<>(new Material[]{}), false, true)) {
					if (b.getType().equals(AIR)) {
						if (Utilities.hasItem(evt.getPlayer(), mat, 1)) {
							if (Storage.COMPATIBILITY_ADAPTER.placeBlock(b, evt.getPlayer(), mat, null)) {
								Utilities.removeItem(evt.getPlayer(), mat, 1);
								if (Storage.rnd.nextInt(10) == 5) {
									Utilities.damageTool(evt.getPlayer(), 1, usedHand);
								}
							}
						}
					}
				}
				return true;
			}
		}
		return false;
	}


}
