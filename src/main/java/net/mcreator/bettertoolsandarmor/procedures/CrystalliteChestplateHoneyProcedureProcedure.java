package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.CompoundTag;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.UUID;

@Mod.EventBusSubscriber
public class CrystalliteChestplateHoneyProcedureProcedure {
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
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_HONEY_CHESTPLATE.get()) {
			if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).time_since_last_hurt > 200) {
				if (!(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getModifier(UUID.fromString("6368cc90-6174-4148-9846-438a1fe698f6")) != null)) {
					CompoundTag dataIndex4 = new CompoundTag();
					entity.saveWithoutId(dataIndex4);
					dataIndex4.putDouble("AbsorptionAmount", ((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) / 2));
					entity.load(dataIndex4);
					if (!(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
							.hasModifier((new AttributeModifier(UUID.fromString("6368cc90-6174-4148-9846-438a1fe698f6"), "crystallite_chestplate_honey", (-0.5), AttributeModifier.Operation.MULTIPLY_TOTAL)))))
						((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
								.addTransientModifier((new AttributeModifier(UUID.fromString("6368cc90-6174-4148-9846-438a1fe698f6"), "crystallite_chestplate_honey", (-0.5), AttributeModifier.Operation.MULTIPLY_TOTAL)));
				}
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) >= (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1)) {
					if ((entity instanceof Player _plr ? _plr.getAbsorptionAmount() : 0) < (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) * 3) {
						if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).crystallite_honey_absorption_timer <= 0) {
							CompoundTag dataIndex12 = new CompoundTag();
							entity.saveWithoutId(dataIndex12);
							dataIndex12.putDouble("AbsorptionAmount", (new Object() {
								public double getValue() {
									CompoundTag dataIndex11 = new CompoundTag();
									entity.saveWithoutId(dataIndex11);
									return dataIndex11.getDouble("AbsorptionAmount");
								}
							}.getValue() + 2));
							entity.load(dataIndex12);
							{
								BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
								_vars.crystallite_honey_absorption_timer = 400;
								_vars.syncPlayerVariables(entity);
							}
						}
					}
				}
			}
		} else {
			if (((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getModifier(UUID.fromString("6368cc90-6174-4148-9846-438a1fe698f6")) != null) {
				((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).removeModifier(UUID.fromString("6368cc90-6174-4148-9846-438a1fe698f6"));
				if (entity instanceof LivingEntity _entity)
					_entity.setHealth((float) ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1)
							+ Math.min(entity instanceof Player _plr ? _plr.getAbsorptionAmount() : 0, entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1)));
				CompoundTag dataIndex19 = new CompoundTag();
				entity.saveWithoutId(dataIndex19);
				dataIndex19.putDouble("AbsorptionAmount", 0);
				entity.load(dataIndex19);
			}
		}
		{
			BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
			_vars.crystallite_honey_absorption_timer = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).crystallite_honey_absorption_timer - 1;
			_vars.syncPlayerVariables(entity);
		}
	}
}
