package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CrystalliteSwordSkyCritTrackerProcedure {
	@SubscribeEvent
	public static void onPlayerCriticalHit(CriticalHitEvent event) {
		execute(event, event.getEntity().level(), event.getEntity(), event.isVanillaCritical());
	}

	public static void execute(LevelAccessor world, Entity sourceentity, boolean isvanillacritical) {
		execute(null, world, sourceentity, isvanillacritical);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity sourceentity, boolean isvanillacritical) {
		if (sourceentity == null)
			return;
		if (isvanillacritical) {
			{
				BetterToolsModVariables.PlayerVariables _vars = sourceentity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.critical_hit = true;
				_vars.syncPlayerVariables(sourceentity);
			}
			if (((LivingEntity) sourceentity).getAttribute(BetterToolsModAttributes.CRITICALHITMULTIPLIER.get()).getValue() != 1.5) {
				if (event != null && event.hasResult()) {
					event.setResult(Event.Result.DENY);
				}
			}
			BetterToolsMod.queueServerWork(10, () -> {
				{
					BetterToolsModVariables.PlayerVariables _vars = sourceentity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
					_vars.critical_hit = false;
					_vars.syncPlayerVariables(sourceentity);
				}
			});
		}
	}
}
