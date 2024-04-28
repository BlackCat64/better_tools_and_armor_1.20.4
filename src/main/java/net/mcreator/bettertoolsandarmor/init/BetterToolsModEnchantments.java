
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.core.registries.Registries;

import net.mcreator.bettertoolsandarmor.enchantment.ThunderShotEnchantment;
import net.mcreator.bettertoolsandarmor.enchantment.SmeltingTouchEnchantment;
import net.mcreator.bettertoolsandarmor.enchantment.LifeAuraEnchantment;
import net.mcreator.bettertoolsandarmor.enchantment.KarmaCurseEnchantment;
import net.mcreator.bettertoolsandarmor.enchantment.FreezeShotEnchantment;
import net.mcreator.bettertoolsandarmor.enchantment.ExperienceEnchEnchantment;
import net.mcreator.bettertoolsandarmor.enchantment.DesperationEnchantment;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class BetterToolsModEnchantments {
	public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(Registries.ENCHANTMENT, BetterToolsMod.MODID);
	public static final DeferredHolder<Enchantment, Enchantment> EXPERIENCE_ENCH = REGISTRY.register("experience_ench", () -> new ExperienceEnchEnchantment());
	public static final DeferredHolder<Enchantment, Enchantment> KARMA_CURSE = REGISTRY.register("karma_curse", () -> new KarmaCurseEnchantment());
	public static final DeferredHolder<Enchantment, Enchantment> THUNDER_SHOT = REGISTRY.register("thunder_shot", () -> new ThunderShotEnchantment());
	public static final DeferredHolder<Enchantment, Enchantment> FREEZE_SHOT = REGISTRY.register("freeze_shot", () -> new FreezeShotEnchantment());
	public static final DeferredHolder<Enchantment, Enchantment> LIFE_AURA = REGISTRY.register("life_aura", () -> new LifeAuraEnchantment());
	public static final DeferredHolder<Enchantment, Enchantment> DESPERATION = REGISTRY.register("desperation", () -> new DesperationEnchantment());
	public static final DeferredHolder<Enchantment, Enchantment> SMELTING_TOUCH = REGISTRY.register("smelting_touch", () -> new SmeltingTouchEnchantment());
}
