package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class AllDiamondEffectArmorProcedureProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player);
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof ServerPlayer _plr0 && _plr0.level() instanceof ServerLevel
				&& _plr0.getAdvancements().getOrStartProgress(_plr0.server.getAdvancements().get(new ResourceLocation("better_tools:all_diamond_effect_armor_adv"))).isDone())) {
			if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.DIAMOND_SUGAR_BOOTS.get())) : false) {
				if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.DIAMOND_RABBIT_BOOTS.get())) : false) {
					if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.DIAMOND_PHANTOM_BOOTS.get())) : false) {
						if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.DIAMOND_CACTUS_CHESTPLATE.get())) : false) {
							if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.DIAMOND_HEARTY_CHESTPLATE.get())) : false) {
								if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.DIAMOND_RUBY_LEGGINGS.get())) : false) {
									if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.DIAMOND_CRYSTAL_LEGGINGS.get())) : false) {
										if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.DIAMOND_GOLD_CARROT_HELMET.get())) : false) {
											if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.DIAMOND_BLACKSTONE_LEGGINGS.get())) : false) {
												if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.DIAMOND_CRYING_OBSIDIAN_HELMET.get())) : false) {
													if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.DIAMOND_MAGMA_CHESTPLATE.get())) : false) {
														if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.DIAMOND_GLASS_ARMOR_HELMET.get())) : false) {
															if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.DIAMOND_GLASS_ARMOR_CHESTPLATE.get())) : false) {
																if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.DIAMOND_GLASS_ARMOR_LEGGINGS.get())) : false) {
																	if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(BetterToolsModItems.DIAMOND_GLASS_ARMOR_BOOTS.get())) : false) {
																		if (entity instanceof ServerPlayer _player) {
																			AdvancementHolder _adv = _player.server.getAdvancements().get(new ResourceLocation("better_tools:all_diamond_effect_armor_adv"));
																			if (_adv != null) {
																				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
																				if (!_ap.isDone()) {
																					for (String criteria : _ap.getRemainingCriteria())
																						_player.getAdvancements().award(_adv, criteria);
																				}
																			}
																		}
																		if (entity instanceof ServerPlayer _player) {
																			AdvancementHolder _adv = _player.server.getAdvancements().get(new ResourceLocation("better_tools:all_iron_effect_armor_adv"));
																			if (_adv != null) {
																				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
																				if (!_ap.isDone()) {
																					for (String criteria : _ap.getRemainingCriteria())
																						_player.getAdvancements().award(_adv, criteria);
																				}
																			}
																		}
																		if (entity instanceof ServerPlayer _player) {
																			AdvancementHolder _adv = _player.server.getAdvancements().get(new ResourceLocation("better_tools:all_effect_armor_adv"));
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
