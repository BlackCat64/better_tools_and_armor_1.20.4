package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.common.NeoForgeMod;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class GetSwimSpeedModifierProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		double modifier = 0;
		modifier = ((LivingEntity) entity).getAttribute(NeoForgeMod.SWIM_SPEED.value()).getValue() - ((LivingEntity) entity).getAttribute(NeoForgeMod.SWIM_SPEED.value()).getBaseValue();
		if (modifier > 0) {
			return "+" + new java.text.DecimalFormat("#.##").format(modifier);
		}
		return "" + new java.text.DecimalFormat("#.##").format(modifier);
	}
}
