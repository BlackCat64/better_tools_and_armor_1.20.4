
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.bettertoolsandarmor.client.model.Modeltough_necklace;
import net.mcreator.bettertoolsandarmor.client.model.Modellightning_staff_projectile;
import net.mcreator.bettertoolsandarmor.client.model.Modelice_staff_projectile;
import net.mcreator.bettertoolsandarmor.client.model.Modelfire_staff_projectile;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class BetterToolsModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modelfire_staff_projectile.LAYER_LOCATION, Modelfire_staff_projectile::createBodyLayer);
		event.registerLayerDefinition(Modelice_staff_projectile.LAYER_LOCATION, Modelice_staff_projectile::createBodyLayer);
		event.registerLayerDefinition(Modeltough_necklace.LAYER_LOCATION, Modeltough_necklace::createBodyLayer);
		event.registerLayerDefinition(Modellightning_staff_projectile.LAYER_LOCATION, Modellightning_staff_projectile::createBodyLayer);
	}
}
