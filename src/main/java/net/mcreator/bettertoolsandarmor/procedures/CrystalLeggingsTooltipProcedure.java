package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class CrystalLeggingsTooltipProcedure {
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
		if (itemstack.getItem() == BetterToolsModItems.CRYSTAL_LEGGINGS.get()) {
			tooltip.add(Component.literal("\u00A77Effect Applied:"));
			tooltip.add(Component.literal("\u00A79Swift Swim II (0:05)"));
			tooltip.add(Component.literal("\u00A7cDurability Cost: 1"));
		} else if (itemstack.getItem() == BetterToolsModItems.DIAMOND_CRYSTAL_LEGGINGS.get()) {
			tooltip.add(Component.literal("\u00A77Effect Applied:"));
			tooltip.add(Component.literal("\u00A79Swift Swim III (0:08)"));
			tooltip.add(Component.literal("\u00A7cDurability Cost: 2"));
		}
	}
}
