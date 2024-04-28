package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.common.NeoForgeMod;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class GetSwimSpeedProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "" + new java.text.DecimalFormat("#.##").format(((LivingEntity) entity).getAttribute(NeoForgeMod.SWIM_SPEED.value()).getValue());
	}
}
