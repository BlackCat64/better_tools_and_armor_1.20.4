package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.items.ItemHandlerHelper;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class LostSoulsPotionProcedureProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		boolean valid_spawn = false;
		valid_spawn = true;
		if ((new Object() {
			public String getValue() {
				CompoundTag dataIndex0 = new CompoundTag();
				entity.saveWithoutId(dataIndex0);
				return dataIndex0.getCompound("LastDeathLocation").getString("dimension");
			}
		}.getValue()).isEmpty()) {
			valid_spawn = false;
			{
				Entity _ent = entity;
				if (!_ent.level().isClientSide() && _ent.getServer() != null) {
					_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
							_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "title @s actionbar \"\u00A7cNo previous death location found\"");
				}
			}
		} else if (("ResourceKey[minecraft:dimension / " + (new Object() {
			public String getValue() {
				CompoundTag dataIndex2 = new CompoundTag();
				entity.saveWithoutId(dataIndex2);
				return dataIndex2.getCompound("LastDeathLocation").getString("dimension");
			}
		}.getValue()) + "]").equals("" + entity.level().dimension())) {
			if (world
					.getBlockState(BlockPos.containing(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_x, entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_y,
							entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_z))
					.canOcclude()
					&& world.getBlockState(BlockPos.containing(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_x, entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_y + 1,
							entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_z)).canOcclude()) {
				valid_spawn = false;
				{
					Entity _ent = entity;
					if (!_ent.level().isClientSide() && _ent.getServer() != null) {
						_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
								_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "title @s actionbar \"\u00A7cLast death location is obstructed\"");
					}
				}
			} else {
				if ((world.getFluidState(BlockPos.containing(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_x, entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_y,
						entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_z)).createLegacyBlock()).getBlock() == Blocks.LAVA
						|| (world.getFluidState(BlockPos.containing(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_x, entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_y,
								entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_z)).createLegacyBlock()).getBlock() == Blocks.LAVA) {
					valid_spawn = false;
					{
						Entity _ent = entity;
						if (!_ent.level().isClientSide() && _ent.getServer() != null) {
							_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
									_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "title @s actionbar \"\u00A7cLast death location is in lava\"");
						}
					}
				} else if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_y >= 0) {
					{
						Entity _ent = entity;
						_ent.teleportTo(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_x, entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_y,
								entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_z);
						if (_ent instanceof ServerPlayer _serverPlayer)
							_serverPlayer.connection.teleport(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_x, entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_y,
									entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_z, _ent.getYRot(), _ent.getXRot());
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_x, entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_y,
									entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_z), BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.enderman.teleport")), SoundSource.PLAYERS, 1, 1);
						} else {
							_level.playLocalSound(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_x, entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_y,
									entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_z, BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.enderman.teleport")), SoundSource.PLAYERS, 1, 1, false);
						}
					}
				} else {
					{
						Entity _ent = entity;
						_ent.teleportTo(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_x, 1, entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_z);
						if (_ent instanceof ServerPlayer _serverPlayer)
							_serverPlayer.connection.teleport(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_x, 1, entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_z, _ent.getYRot(), _ent.getXRot());
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_x, 1, entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_z),
									BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.enderman.teleport")), SoundSource.PLAYERS, 1, 1);
						} else {
							_level.playLocalSound(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_x, 1, entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_z,
									BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.enderman.teleport")), SoundSource.PLAYERS, 1, 1, false);
						}
					}
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(
								new CommandSourceStack(CommandSource.NULL, new Vec3(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_x, 0, entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_z), Vec2.ZERO, _level,
										4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								"fill ~-1 ~ ~-1 ~1 ~ ~1 obsidian destroy");
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(
								new CommandSourceStack(CommandSource.NULL, new Vec3(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_x, 1, entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_death_z), Vec2.ZERO, _level,
										4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								"fill ~-1 ~ ~-1 ~1 ~1 ~1 air destroy");
				}
			}
		} else {
			valid_spawn = false;
			{
				Entity _ent = entity;
				if (!_ent.level().isClientSide() && _ent.getServer() != null) {
					_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
							_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "title @s actionbar \"\u00A7cLast death location is in a different dimension\"");
				}
			}
		}
		if (!valid_spawn) {
			if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
				BetterToolsMod.queueServerWork(1, () -> {
					if (entity instanceof Player _player) {
						ItemStack _stktoremove = new ItemStack(Items.GLASS_BOTTLE);
						_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
					}
					if (entity instanceof Player _player) {
						ItemStack _setstack = new ItemStack(BetterToolsModItems.LOST_SOULS_POTION.get()).copy();
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
					}
				});
			}
		}
	}
}
