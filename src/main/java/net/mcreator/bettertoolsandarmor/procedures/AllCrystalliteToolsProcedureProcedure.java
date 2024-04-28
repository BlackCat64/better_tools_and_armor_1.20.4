package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class AllCrystalliteToolsProcedureProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player);
		}
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		boolean sword = false;
		boolean pickaxe = false;
		boolean axe = false;
		boolean shovel = false;
		boolean hoe = false;
		boolean dagger = false;
		if (!(entity instanceof ServerPlayer _plr0 && _plr0.level() instanceof ServerLevel && _plr0.getAdvancements().getOrStartProgress(_plr0.server.getAdvancements().get(new ResourceLocation("better_tools:all_crystallite_tools_adv"))).isDone())) {
			if (entity.getCapability(Capabilities.ItemHandler.ENTITY, null) instanceof IItemHandlerModifiable _modHandler) {
				for (int _idx = 0; _idx < _modHandler.getSlots(); _idx++) {
					ItemStack itemstackiterator = _modHandler.getStackInSlot(_idx).copy();
					if (itemstackiterator.is(ItemTags.create(new ResourceLocation("better_tools:crystallite_swords")))) {
						sword = true;
					} else if (itemstackiterator.is(ItemTags.create(new ResourceLocation("better_tools:crystallite_pickaxes")))) {
						pickaxe = true;
					} else if (itemstackiterator.is(ItemTags.create(new ResourceLocation("better_tools:crystallite_axes")))) {
						axe = true;
					} else if (itemstackiterator.is(ItemTags.create(new ResourceLocation("better_tools:crystallite_shovels")))) {
						shovel = true;
					} else if (itemstackiterator.is(ItemTags.create(new ResourceLocation("better_tools:crystallite_hoes")))) {
						hoe = true;
					} else if (itemstackiterator.is(ItemTags.create(new ResourceLocation("better_tools:crystallite_daggers")))) {
						dagger = true;
					}
				}
			}
			if (sword == true && pickaxe == true && axe == true && shovel == true && hoe == true && dagger == true) {
				if (entity instanceof ServerPlayer _player) {
					AdvancementHolder _adv = _player.server.getAdvancements().get(new ResourceLocation("better_tools:all_crystallite_tools_adv"));
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
