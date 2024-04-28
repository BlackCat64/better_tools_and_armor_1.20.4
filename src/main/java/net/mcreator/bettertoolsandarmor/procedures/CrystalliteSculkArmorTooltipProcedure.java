package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.Screen;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class CrystalliteSculkArmorTooltipProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, event.getItemStack(), event.getToolTip());
	}

	public static void execute(ItemStack itemstack, List<Component> tooltip) {
		execute(null, itemstack, tooltip);
	}

	private static void execute(@Nullable Event event, ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		if (itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_HELMET.get() || itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_CHESTPLATE.get()
				|| itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_LEGGINGS.get() || itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_BOOTS.get()) {
			if (Screen.hasShiftDown()) {
				if (itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_SCULK_CHESTPLATE.get()) {
					tooltip.add(Component.literal("\u00A77When attacked at range:"));
					tooltip.add(Component.literal("\u00A7920% chance to launch a sonic boom"));
				}
				tooltip.add(Component.literal("\u00A77For each armor piece worn:"));
				tooltip.add(Component.literal("\u00A79-10% Detection Range for all nearby mobs"));
				tooltip.add(Component.literal("\u00A77When full set worn:"));
				tooltip.add(Component.literal("\u00A79-50% Detection Range"));
			} else {
				tooltip.add(Component.literal("\u00A78Press Shift for details"));
			}
		}
	}
}
