package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

public class NatureRingEquippedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
			_vars.nature_ring_equipped = true;
			_vars.syncPlayerVariables(entity);
		}
		CharmCounterIncrementProcedure.execute(entity);
	}
}
