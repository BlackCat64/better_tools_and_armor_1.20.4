
package net.mcreator.bettertoolsandarmor.item.extension;

import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.Direction;

import net.mcreator.bettertoolsandarmor.procedures.FireStaffDispensedProcedure;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class FireStaffInDispenserItemExtension {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> DispenserBlock.registerBehavior(BetterToolsModItems.FIRE_STAFF.get(), new OptionalDispenseItemBehavior() {
			public ItemStack execute(BlockSource blockSource, ItemStack stack) {
				ItemStack itemstack = stack.copy();
				Level world = blockSource.level();
				Direction direction = blockSource.state().getValue(DispenserBlock.FACING);
				int x = blockSource.pos().getX();
				int y = blockSource.pos().getY();
				int z = blockSource.pos().getZ();
				boolean success = this.isSuccess();
				return FireStaffDispensedProcedure.execute(world, x, y, z, direction, itemstack, success);
			}
		}));
	}
}
