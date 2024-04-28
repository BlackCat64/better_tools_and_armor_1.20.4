package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.living.LivingAttackEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModEnchantments;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class FreezeShatterProcedureProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity(), event.getSource().getDirectEntity(), event.getSource().getEntity(), event.getAmount());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity immediatesourceentity, Entity sourceentity, double amount) {
		execute(null, world, x, y, z, entity, immediatesourceentity, sourceentity, amount);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, Entity immediatesourceentity, Entity sourceentity, double amount) {
		if (entity == null || immediatesourceentity == null || sourceentity == null)
			return;
		if (!BetterToolsModVariables.being_damaged_flag) {
			if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(BetterToolsModMobEffects.FROZEN.get())) {
				if (!(EnchantmentHelper.getItemEnchantmentLevel(BetterToolsModEnchantments.FREEZE_SHOT.get(), (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)) != 0
						|| EnchantmentHelper.getItemEnchantmentLevel(BetterToolsModEnchantments.FREEZE_SHOT.get(), (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)) != 0)) {
					BetterToolsModVariables.being_damaged_flag = true;
					entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("better_tools:freeze_shatter_damage"))),
							immediatesourceentity, sourceentity), (float) (amount + 3));
					if (entity instanceof LivingEntity _entity)
						_entity.removeEffect(BetterToolsModMobEffects.FROZEN.get());
					if (event instanceof ICancellableEvent _cancellable) {
						_cancellable.setCanceled(true);
					}
					BetterToolsModVariables.being_damaged_flag = false;
					if (sourceentity.getData(BetterToolsModVariables.PLAYER_VARIABLES).critical_hit) {
						if (world instanceof ServerLevel _level)
							_level.sendParticles(ParticleTypes.CRIT, (entity.getX()), (entity.getY() + 1), (entity.getZ()), 10, 0.5, 1, 0.5, 0.05);
						if (world instanceof ServerLevel _level)
							_level.sendParticles(ParticleTypes.DAMAGE_INDICATOR, (entity.getX()), (entity.getY() + 1), (entity.getZ()), (int) (amount / 2), 0.4, 0.6, 0.4, 0.025);
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.player.attack.crit")), SoundSource.PLAYERS, 1, 1);
							} else {
								_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.player.attack.crit")), SoundSource.PLAYERS, 1, 1, false);
							}
						}
						{
							BetterToolsModVariables.PlayerVariables _vars = immediatesourceentity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
							_vars.critical_hit = false;
							_vars.syncPlayerVariables(immediatesourceentity);
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.glass.break")), SoundSource.NEUTRAL, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.glass.break")), SoundSource.NEUTRAL, 1, 1, false);
						}
					}
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.SNOWFLAKE, x, (y + entity.getBbHeight() / 2), z, 5, 0.6, 0.6, 0.6, 0.1);
					if (sourceentity instanceof ServerPlayer _player) {
						AdvancementHolder _adv = _player.server.getAdvancements().get(new ResourceLocation("better_tools:freeze_shatter_adv"));
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
		}
	}
}
