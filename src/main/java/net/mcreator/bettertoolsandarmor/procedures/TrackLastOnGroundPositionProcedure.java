package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class TrackLastOnGroundPositionProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
		}
	}

	public static void execute(double x, double y, double z, Entity entity) {
		execute(null, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity.onGround()) {
			{
				BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.last_on_ground_x = Math.floor(x) + 0.5;
				_vars.syncPlayerVariables(entity);
			}
			{
				BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.last_on_ground_y = Math.floor(y) + 0.5;
				_vars.syncPlayerVariables(entity);
			}
			{
				BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.last_on_ground_z = Math.floor(z) + 0.5;
				_vars.syncPlayerVariables(entity);
			}
		}
		if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).save_from_void_cooldown > 0) {
			{
				BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.save_from_void_cooldown = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).save_from_void_cooldown - 1;
				_vars.syncPlayerVariables(entity);
			}
		}
		if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).ender_titanium_boots_cooldown > 0) {
			{
				BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.ender_titanium_boots_cooldown = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).ender_titanium_boots_cooldown - 1;
				_vars.syncPlayerVariables(entity);
			}
		}
	}
}
