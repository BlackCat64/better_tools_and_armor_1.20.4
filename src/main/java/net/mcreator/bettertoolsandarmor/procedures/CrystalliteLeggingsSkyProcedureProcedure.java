package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.UUID;

@Mod.EventBusSubscriber
public class CrystalliteLeggingsSkyProcedureProcedure {
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
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SKY_LEGGINGS.get()) {
			if (!(((LivingEntity) entity).getAttribute(NeoForgeMod.ENTITY_GRAVITY.value())
					.hasModifier((new AttributeModifier(UUID.fromString("eab4ed7b-1527-4afa-82ae-7e1dc9f37c7d"), "crystallite_leggings_sky", (-0.04), AttributeModifier.Operation.ADDITION)))))
				((LivingEntity) entity).getAttribute(NeoForgeMod.ENTITY_GRAVITY.value())
						.addTransientModifier((new AttributeModifier(UUID.fromString("eab4ed7b-1527-4afa-82ae-7e1dc9f37c7d"), "crystallite_leggings_sky", (-0.04), AttributeModifier.Operation.ADDITION)));
		} else {
			((LivingEntity) entity).getAttribute(NeoForgeMod.ENTITY_GRAVITY.value()).removeModifier(UUID.fromString("eab4ed7b-1527-4afa-82ae-7e1dc9f37c7d"));
		}
	}
}
