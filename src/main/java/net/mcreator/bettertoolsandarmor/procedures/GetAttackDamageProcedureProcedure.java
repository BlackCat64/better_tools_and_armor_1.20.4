package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

public class GetAttackDamageProcedureProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).attack_damage;
	}
}
