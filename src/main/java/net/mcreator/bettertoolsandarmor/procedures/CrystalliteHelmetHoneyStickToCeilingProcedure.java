package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.UUID;

@Mod.EventBusSubscriber
public class CrystalliteHelmetHoneyStickToCeilingProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).stick_to_ceiling) {
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_HONEY_HELMET.get()) {
				if (entity.getDeltaMovement().y() >= 0) {
					if (world.getBlockState(BlockPos.containing(x, y + 2, z)).canOcclude()) {
						if (!(((LivingEntity) entity).getAttribute(NeoForgeMod.ENTITY_GRAVITY.value()).getModifier(UUID.fromString("df433bbf-6612-471a-8caf-44e463ed594e")) != null)) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y + 2, z), BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.honey_block.place")), SoundSource.PLAYERS, (float) 0.75, 1);
								} else {
									_level.playLocalSound(x, (y + 2), z, BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.honey_block.place")), SoundSource.PLAYERS, (float) 0.75, 1, false);
								}
							}
						}
						if (!(((LivingEntity) entity).getAttribute(NeoForgeMod.ENTITY_GRAVITY.value())
								.hasModifier((new AttributeModifier(UUID.fromString("df433bbf-6612-471a-8caf-44e463ed594e"), "stick_to_ceiling", (-1.5), AttributeModifier.Operation.MULTIPLY_TOTAL)))))
							((LivingEntity) entity).getAttribute(NeoForgeMod.ENTITY_GRAVITY.value())
									.addTransientModifier((new AttributeModifier(UUID.fromString("df433bbf-6612-471a-8caf-44e463ed594e"), "stick_to_ceiling", (-1.5), AttributeModifier.Operation.MULTIPLY_TOTAL)));
						if (world.dayTime() % 5 == 0) {
							if (world instanceof ServerLevel _level)
								_level.sendParticles(ParticleTypes.FALLING_HONEY, x, (y + 2), z, 1, 0.5, 0.5, 0.5, 0.025);
						}
					} else {
						((LivingEntity) entity).getAttribute(NeoForgeMod.ENTITY_GRAVITY.value()).removeModifier(UUID.fromString("df433bbf-6612-471a-8caf-44e463ed594e"));
					}
				} else {
					((LivingEntity) entity).getAttribute(NeoForgeMod.ENTITY_GRAVITY.value()).removeModifier(UUID.fromString("df433bbf-6612-471a-8caf-44e463ed594e"));
				}
			} else {
				((LivingEntity) entity).getAttribute(NeoForgeMod.ENTITY_GRAVITY.value()).removeModifier(UUID.fromString("df433bbf-6612-471a-8caf-44e463ed594e"));
			}
		} else {
			((LivingEntity) entity).getAttribute(NeoForgeMod.ENTITY_GRAVITY.value()).removeModifier(UUID.fromString("df433bbf-6612-471a-8caf-44e463ed594e"));
		}
	}
}
