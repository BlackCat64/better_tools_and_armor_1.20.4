
package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import java.util.List;

public abstract class CrystalliteArmorSapphireItem extends ArmorItem {
	public CrystalliteArmorSapphireItem(ArmorItem.Type type, Item.Properties properties) {
		super(new ArmorMaterial() {
			@Override
			public int getDurabilityForType(ArmorItem.Type type) {
				return new int[]{13, 15, 16, 11}[type.getSlot().getIndex()] * 35;
			}

			@Override
			public int getDefenseForType(ArmorItem.Type type) {
				return new int[]{4, 6, 8, 4}[type.getSlot().getIndex()];
			}

			@Override
			public int getEnchantmentValue() {
				return 20;
			}

			@Override
			public SoundEvent getEquipSound() {
				return BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("better_tools:crystallite_step"));
			}

			@Override
			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(BetterToolsModItems.CRYSTALLITE_SHARDS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_GEM.get()), new ItemStack(BetterToolsModItems.SAPPHIRE.get()));
			}

			@Override
			public String getName() {
				return "crystallite_armor_sapphire";
			}

			@Override
			public float getToughness() {
				return 2.5f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0.125f;
			}
		}, type, properties);
	}

	public static class Helmet extends CrystalliteArmorSapphireItem {
		public Helmet() {
			super(ArmorItem.Type.HELMET, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.literal("\u00A77Upgrade: \u00A7bSapphire"));
			list.add(Component.literal("\u00A77Ability:"));
			list.add(Component.literal("\u00A7bFreezing - Can freeze attackers"));
			list.add(Component.literal("\u00A77Full-set bonus:"));
			list.add(Component.literal("\u00A7bFurther increases chances for freeze"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/crystallite_sapphire__layer_1.png";
		}
	}

	public static class Chestplate extends CrystalliteArmorSapphireItem {
		public Chestplate() {
			super(ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.literal("\u00A77Upgrade: \u00A7bSapphire"));
			list.add(Component.literal("\u00A77Ability:"));
			list.add(Component.literal("\u00A7bFreezing - Can freeze attackers"));
			list.add(Component.literal("\u00A77Full-set bonus:"));
			list.add(Component.literal("\u00A7bFurther increases chances for freeze"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/crystallite_sapphire__layer_1.png";
		}
	}

	public static class Leggings extends CrystalliteArmorSapphireItem {
		public Leggings() {
			super(ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.literal("\u00A77Upgrade: \u00A7bSapphire"));
			list.add(Component.literal("\u00A77Ability:"));
			list.add(Component.literal("\u00A7bFreezing - Can freeze attackers"));
			list.add(Component.literal("\u00A77Full-set bonus:"));
			list.add(Component.literal("\u00A7bFurther increases chances for freeze"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/crystallite_sapphire__layer_2.png";
		}
	}

	public static class Boots extends CrystalliteArmorSapphireItem {
		public Boots() {
			super(ArmorItem.Type.BOOTS, new Item.Properties().fireResistant());
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, level, list, flag);
			list.add(Component.literal("\u00A77Upgrade: \u00A7bSapphire"));
			list.add(Component.literal("\u00A77Ability:"));
			list.add(Component.literal("\u00A7bFreezing - Can freeze attackers"));
			list.add(Component.literal("\u00A77Full-set bonus:"));
			list.add(Component.literal("\u00A7bFurther increases chances for freeze"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "better_tools:textures/models/armor/crystallite_sapphire__layer_1.png";
		}
	}
}
