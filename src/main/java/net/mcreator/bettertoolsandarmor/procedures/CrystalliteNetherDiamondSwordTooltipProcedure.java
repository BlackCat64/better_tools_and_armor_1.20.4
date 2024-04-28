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
public class CrystalliteNetherDiamondSwordTooltipProcedure {
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
		if (itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_SWORD_NETHER_DIAMOND.get() || itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_AXE_NETHER_DIAMOND.get()
				|| itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_DAGGER_NETHER_DIAMOND.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal("\u00A77Weapon Effects:"));
				tooltip.add(Component.literal("\u00A795s \u00A74of fire"));
				tooltip.add(Component.literal("\u00A77When in the Nether:"));
				tooltip.add(Component.literal("\u00A7910s \u00A74of fire"));
			} else {
				tooltip.add(Component.literal("\u00A78Press Shift for details"));
			}
		}
	}
}
