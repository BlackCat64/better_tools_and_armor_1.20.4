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
public class SapphireSwordTooltipProcedure {
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
		if (itemstack.getItem() == BetterToolsModItems.SAPPHIRE_SWORD.get() || itemstack.getItem() == BetterToolsModItems.SAPPHIRE_DAGGER.get() || itemstack.getItem() == BetterToolsModItems.SAPPHIRE_AXE.get()) {
			if (Screen.hasShiftDown()) {
				tooltip.add(Component.literal("\u00A77Weapon Effects:"));
				tooltip.add(Component.literal("\u00A7910% \u00A7bchance for \u00A795s \u00A7bfreeze"));
				tooltip.add(Component.literal("\u00A77When in a cold biome:"));
				tooltip.add(Component.literal("\u00A7920% \u00A7bchance for \u00A7910s \u00A7bfreeze"));
			} else {
				tooltip.add(Component.literal("\u00A78Press Shift for details"));
			}
		}
	}
}
