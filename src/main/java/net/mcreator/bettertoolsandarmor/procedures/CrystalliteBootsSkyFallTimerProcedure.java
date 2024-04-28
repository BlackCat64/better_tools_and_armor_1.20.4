package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CrystalliteBootsSkyFallTimerProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.getY(), event.player);
		}
	}

	public static void execute(double y, Entity entity) {
		execute(null, y, entity);
	}

	private static void execute(@Nullable Event event, double y, Entity entity) {
		if (entity == null)
			return;
		if (entity.getDeltaMovement().y() >= 0 || entity.onGround()) {
			{
				BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.fall_start_y = y;
				_vars.syncPlayerVariables(entity);
			}
		} else {
			if (!entity.onGround()) {
				if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SKY_BOOTS.get()) {
					if (Math.abs(y - entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).fall_start_y) < 5) {
						entity.fallDistance = (float) 0.1;
					}
					if (((LivingEntity) entity).getAttribute(NeoForgeMod.ENTITY_GRAVITY.value()).getValue() < ((LivingEntity) entity).getAttribute(NeoForgeMod.ENTITY_GRAVITY.value()).getBaseValue()) {
						if (entity instanceof ServerPlayer _player) {
							AdvancementHolder _adv = _player.server.getAdvancements().get(new ResourceLocation("better_tools:low_gravity_adv"));
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
