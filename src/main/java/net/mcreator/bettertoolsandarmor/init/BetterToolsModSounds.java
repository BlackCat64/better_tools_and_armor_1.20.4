
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class BetterToolsModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, BetterToolsMod.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> CRYSTALLITE_BREAK = REGISTRY.register("crystallite_break", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("better_tools", "crystallite_break")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CRYSTALLITE_PLACE = REGISTRY.register("crystallite_place", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("better_tools", "crystallite_place")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CRYSTALLITE_STEP = REGISTRY.register("crystallite_step", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("better_tools", "crystallite_step")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CRYSTALLITE_SHIMMER = REGISTRY.register("crystallite_shimmer", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("better_tools", "crystallite_shimmer")));
}
