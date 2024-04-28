
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class BetterToolsModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(Registries.PARTICLE_TYPE, BetterToolsMod.MODID);
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> CRYSTALLITE_SPARKLE = REGISTRY.register("crystallite_sparkle", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ICE_PARTICLE = REGISTRY.register("ice_particle", () -> new SimpleParticleType(true));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ORE_LOCATION_PARTICLE = REGISTRY.register("ore_location_particle", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GUARDIAN_STAFF_BEAM = REGISTRY.register("guardian_staff_beam", () -> new SimpleParticleType(true));
}
