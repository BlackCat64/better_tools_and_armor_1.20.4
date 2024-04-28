
package net.mcreator.bettertoolsandarmor.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

public class RubyToolsShovelItem extends ShovelItem {
	public RubyToolsShovelItem() {
		super(new Tier() {
			public int getUses() {
				return 300;
			}

			public float getSpeed() {
				return 12f;
			}

			public float getAttackDamageBonus() {
				return 1.5f;
			}

			public int getLevel() {
				return 1;
			}

			public int getEnchantmentValue() {
				return 12;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(BetterToolsModItems.RUBY.get()));
			}
		}, 1, -2.6f, new Item.Properties());
	}
}
