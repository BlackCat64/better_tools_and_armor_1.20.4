package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ComboMiningProcedureProcedure {
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		execute(event, event.getState(), event.getPlayer());
	}

	public static void execute(BlockState blockstate, Entity entity) {
		execute(null, blockstate, entity);
	}

	private static void execute(@Nullable Event event, BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(new ResourceLocation("better_tools:combo_mining_tools")))) {
			if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).time_since_last_mined <= 20) {
				if (blockstate.getBlock() == entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_mined_block.getBlock()) {
					{
						BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
						_vars.block_mining_combo = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).block_mining_combo + 1;
						_vars.syncPlayerVariables(entity);
					}
				} else {
					{
						BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
						_vars.block_mining_combo = 0;
						_vars.syncPlayerVariables(entity);
					}
				}
			} else {
				{
					BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
					_vars.block_mining_combo = 0;
					_vars.syncPlayerVariables(entity);
				}
			}
		} else {
			{
				BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.block_mining_combo = 0;
				_vars.syncPlayerVariables(entity);
			}
		}
		{
			BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
			_vars.last_mined_block = blockstate;
			_vars.syncPlayerVariables(entity);
		}
		{
			BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
			_vars.time_since_last_mined = 0;
			_vars.syncPlayerVariables(entity);
		}
	}
}
