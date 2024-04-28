package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

public class CharmCounterIncrementProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).charms_equipped < 4) {
			{
				BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.charms_equipped = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).charms_equipped + 1;
				_vars.syncPlayerVariables(entity);
			}
		} else {
			{
				BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.charms_equipped = 4;
				_vars.syncPlayerVariables(entity);
			}
		}
	}
}
