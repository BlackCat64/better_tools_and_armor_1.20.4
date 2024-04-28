package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.common.NeoForgeMod;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

public class GildedBraceletEquippedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(((LivingEntity) entity).getAttribute(NeoForgeMod.BLOCK_REACH.value()).hasModifier((new AttributeModifier(UUID.fromString("690220ea-a287-43e1-8ba3-ddc831edb69c"), "gilded_bracelet", 1, AttributeModifier.Operation.ADDITION)))))
			((LivingEntity) entity).getAttribute(NeoForgeMod.BLOCK_REACH.value()).addTransientModifier((new AttributeModifier(UUID.fromString("690220ea-a287-43e1-8ba3-ddc831edb69c"), "gilded_bracelet", 1, AttributeModifier.Operation.ADDITION)));
		CharmCounterIncrementProcedure.execute(entity);
	}
}
