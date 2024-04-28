package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;

public class DoubleJumpProcedureProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.onGround() && entity.getDeltaMovement().y() >= -0.5) {
			{
				BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.extra_jumps = (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(BetterToolsModMobEffects.DOUBLE_JUMP.get()) ? _livEnt.getEffect(BetterToolsModMobEffects.DOUBLE_JUMP.get()).getAmplifier() : 0) + 1;
				_vars.syncPlayerVariables(entity);
			}
		}
		if (entity instanceof ServerPlayer _player) {
			AdvancementHolder _adv = _player.server.getAdvancements().get(new ResourceLocation("better_tools:double_jump_adv"));
			if (_adv != null) {
				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
				if (!_ap.isDone()) {
					for (String criteria : _ap.getRemainingCriteria())
						_player.getAdvancements().award(_adv, criteria);
				}
			}
		}
	}
}
