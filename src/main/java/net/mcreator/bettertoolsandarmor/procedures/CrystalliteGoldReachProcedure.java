package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

import java.util.UUID;

@Mod.EventBusSubscriber
public class CrystalliteGoldReachProcedure {
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
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:increased_reach_tools")))) {
			if (!(((LivingEntity) entity).getAttribute(NeoForgeMod.BLOCK_REACH.value()).hasModifier((new AttributeModifier(UUID.fromString("5e4c476e-fefb-45d9-ac4d-5642ad234de6"), "crystallite_gold_tool", 3, AttributeModifier.Operation.ADDITION)))))
				((LivingEntity) entity).getAttribute(NeoForgeMod.BLOCK_REACH.value())
						.addTransientModifier((new AttributeModifier(UUID.fromString("5e4c476e-fefb-45d9-ac4d-5642ad234de6"), "crystallite_gold_tool", 3, AttributeModifier.Operation.ADDITION)));
		} else {
			((LivingEntity) entity).getAttribute(NeoForgeMod.BLOCK_REACH.value()).removeModifier(UUID.fromString("5e4c476e-fefb-45d9-ac4d-5642ad234de6"));
		}
	}
}
