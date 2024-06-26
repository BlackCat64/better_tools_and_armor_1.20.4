package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

public class NetherDiamondToolsProcedureProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof ServerPlayer _plr0 && _plr0.level() instanceof ServerLevel && _plr0.getAdvancements().getOrStartProgress(_plr0.server.getAdvancements().get(new ResourceLocation("better_tools:nether_diamond_tools_adv"))).isDone())) {
			if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.NETHER_DIAMOND_PICKAXE.get())) : false) {
				if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.NETHER_DIAMOND_AXE.get())) : false) {
					if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.NETHER_DIAMOND_SHOVEL.get())) : false) {
						if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.NETHER_DIAMOND_HOE.get())) : false) {
							if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.NETHER_DIAMOND_DAGGER.get())) : false) {
								if (entity instanceof ServerPlayer _player) {
									AdvancementHolder _adv = _player.server.getAdvancements().get(new ResourceLocation("better_tools:nether_diamond_tools_adv"));
									if (_adv != null) {
										AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
										if (!_ap.isDone()) {
											for (String criteria : _ap.getRemainingCriteria())
												_player.getAdvancements().award(_adv, criteria);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
