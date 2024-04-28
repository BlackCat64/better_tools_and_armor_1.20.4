package net.mcreator.bettertoolsandarmor.network;

import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.client.Minecraft;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BetterToolsModVariables {
	public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, BetterToolsMod.MODID);
	public static final Supplier<AttachmentType<PlayerVariables>> PLAYER_VARIABLES = ATTACHMENT_TYPES.register("player_variables", () -> AttachmentType.serializable(() -> new PlayerVariables()).build());
	public static boolean being_damaged_flag = false;

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		BetterToolsMod.addNetworkMessage(PlayerVariablesSyncMessage.ID, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handleData);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (event.getEntity() instanceof ServerPlayer player)
				player.getData(PLAYER_VARIABLES).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (event.getEntity() instanceof ServerPlayer player)
				player.getData(PLAYER_VARIABLES).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (event.getEntity() instanceof ServerPlayer player)
				player.getData(PLAYER_VARIABLES).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			PlayerVariables original = event.getOriginal().getData(PLAYER_VARIABLES);
			PlayerVariables clone = new PlayerVariables();
			clone.respawn_xp = original.respawn_xp;
			clone.extra_jumps = original.extra_jumps;
			clone.charms_equipped = original.charms_equipped;
			clone.last_on_ground_x = original.last_on_ground_x;
			clone.last_on_ground_y = original.last_on_ground_y;
			clone.last_on_ground_z = original.last_on_ground_z;
			clone.save_from_void_cooldown = original.save_from_void_cooldown;
			clone.attack_damage = original.attack_damage;
			clone.attack_damage_modifier = original.attack_damage_modifier;
			clone.knockback_resistance = original.knockback_resistance;
			clone.knockback_resistance_modifier = original.knockback_resistance_modifier;
			clone.last_death_x = original.last_death_x;
			clone.last_death_y = original.last_death_y;
			clone.last_death_z = original.last_death_z;
			clone.last_food_eaten = original.last_food_eaten;
			clone.last_food_was_carbonated = original.last_food_was_carbonated;
			if (!event.isWasDeath()) {
				clone.time_since_last_hurt = original.time_since_last_hurt;
				clone.slow_falling_cooldown = original.slow_falling_cooldown;
				clone.crystallite_emerald_heal_timer = original.crystallite_emerald_heal_timer;
				clone.blocks_broken_with_sculk_crystallite_pickaxe = original.blocks_broken_with_sculk_crystallite_pickaxe;
				clone.critical_hit = original.critical_hit;
				clone.fall_time = original.fall_time;
				clone.crystallite_amethyst_absorption_timer = original.crystallite_amethyst_absorption_timer;
				clone.flaming_circlet_cooldown = original.flaming_circlet_cooldown;
				clone.time_since_last_attacked = original.time_since_last_attacked;
				clone.time_since_last_mined = original.time_since_last_mined;
				clone.last_mined_block = original.last_mined_block;
				clone.block_mining_combo = original.block_mining_combo;
				clone.stick_to_ceiling = original.stick_to_ceiling;
				clone.crystallite_honey_absorption_timer = original.crystallite_honey_absorption_timer;
				clone.time_since_last_jumped = original.time_since_last_jumped;
				clone.smelting_touch_item_to_smelt = original.smelting_touch_item_to_smelt;
				clone.smelting_touch_item_to_drop = original.smelting_touch_item_to_drop;
				clone.ender_titanium_boots_cooldown = original.ender_titanium_boots_cooldown;
				clone.time_since_non_carbonated_food_eaten = original.time_since_non_carbonated_food_eaten;
				clone.fall_start_y = original.fall_start_y;
				clone.nature_ring_equipped = original.nature_ring_equipped;
			}
			event.getEntity().setData(PLAYER_VARIABLES, clone);
		}
	}

	public static class PlayerVariables implements INBTSerializable<CompoundTag> {
		public double respawn_xp = 0.0;
		public double extra_jumps = 0.0;
		public double charms_equipped = 0.0;
		public double time_since_last_hurt = 0;
		public boolean slow_falling_cooldown = false;
		public double crystallite_emerald_heal_timer = 0.0;
		public double blocks_broken_with_sculk_crystallite_pickaxe = 0;
		public boolean critical_hit = false;
		public double fall_time = 0.0;
		public double crystallite_amethyst_absorption_timer = 300.0;
		public double last_on_ground_x = 0;
		public double last_on_ground_y = 0;
		public double last_on_ground_z = 0;
		public double save_from_void_cooldown = 0.0;
		public String attack_damage = "\"\"";
		public String attack_damage_modifier = "\"\"";
		public String knockback_resistance = "\"\"";
		public String knockback_resistance_modifier = "\"\"";
		public double flaming_circlet_cooldown = 0;
		public double time_since_last_attacked = 0;
		public double time_since_last_mined = 0;
		public BlockState last_mined_block = Blocks.AIR.defaultBlockState();
		public double block_mining_combo = 0;
		public boolean stick_to_ceiling = false;
		public double crystallite_honey_absorption_timer = 0;
		public double time_since_last_jumped = 0;
		public ItemStack smelting_touch_item_to_smelt = ItemStack.EMPTY;
		public ItemStack smelting_touch_item_to_drop = ItemStack.EMPTY;
		public double ender_titanium_boots_cooldown = 0;
		public double last_death_x = 0;
		public double last_death_y = 0;
		public double last_death_z = 0;
		public double time_since_non_carbonated_food_eaten = 0;
		public ItemStack last_food_eaten = ItemStack.EMPTY;
		public boolean last_food_was_carbonated = false;
		public double fall_start_y = 0;
		public boolean nature_ring_equipped = false;

		@Override
		public CompoundTag serializeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putDouble("respawn_xp", respawn_xp);
			nbt.putDouble("extra_jumps", extra_jumps);
			nbt.putDouble("charms_equipped", charms_equipped);
			nbt.putDouble("time_since_last_hurt", time_since_last_hurt);
			nbt.putBoolean("slow_falling_cooldown", slow_falling_cooldown);
			nbt.putDouble("crystallite_emerald_heal_timer", crystallite_emerald_heal_timer);
			nbt.putDouble("blocks_broken_with_sculk_crystallite_pickaxe", blocks_broken_with_sculk_crystallite_pickaxe);
			nbt.putBoolean("critical_hit", critical_hit);
			nbt.putDouble("fall_time", fall_time);
			nbt.putDouble("crystallite_amethyst_absorption_timer", crystallite_amethyst_absorption_timer);
			nbt.putDouble("last_on_ground_x", last_on_ground_x);
			nbt.putDouble("last_on_ground_y", last_on_ground_y);
			nbt.putDouble("last_on_ground_z", last_on_ground_z);
			nbt.putDouble("save_from_void_cooldown", save_from_void_cooldown);
			nbt.putString("attack_damage", attack_damage);
			nbt.putString("attack_damage_modifier", attack_damage_modifier);
			nbt.putString("knockback_resistance", knockback_resistance);
			nbt.putString("knockback_resistance_modifier", knockback_resistance_modifier);
			nbt.putDouble("flaming_circlet_cooldown", flaming_circlet_cooldown);
			nbt.putDouble("time_since_last_attacked", time_since_last_attacked);
			nbt.putDouble("time_since_last_mined", time_since_last_mined);
			nbt.put("last_mined_block", NbtUtils.writeBlockState(last_mined_block));
			nbt.putDouble("block_mining_combo", block_mining_combo);
			nbt.putBoolean("stick_to_ceiling", stick_to_ceiling);
			nbt.putDouble("crystallite_honey_absorption_timer", crystallite_honey_absorption_timer);
			nbt.putDouble("time_since_last_jumped", time_since_last_jumped);
			nbt.put("smelting_touch_item_to_smelt", smelting_touch_item_to_smelt.save(new CompoundTag()));
			nbt.put("smelting_touch_item_to_drop", smelting_touch_item_to_drop.save(new CompoundTag()));
			nbt.putDouble("ender_titanium_boots_cooldown", ender_titanium_boots_cooldown);
			nbt.putDouble("last_death_x", last_death_x);
			nbt.putDouble("last_death_y", last_death_y);
			nbt.putDouble("last_death_z", last_death_z);
			nbt.putDouble("time_since_non_carbonated_food_eaten", time_since_non_carbonated_food_eaten);
			nbt.put("last_food_eaten", last_food_eaten.save(new CompoundTag()));
			nbt.putBoolean("last_food_was_carbonated", last_food_was_carbonated);
			nbt.putDouble("fall_start_y", fall_start_y);
			nbt.putBoolean("nature_ring_equipped", nature_ring_equipped);
			return nbt;
		}

		@Override
		public void deserializeNBT(CompoundTag nbt) {
			respawn_xp = nbt.getDouble("respawn_xp");
			extra_jumps = nbt.getDouble("extra_jumps");
			charms_equipped = nbt.getDouble("charms_equipped");
			time_since_last_hurt = nbt.getDouble("time_since_last_hurt");
			slow_falling_cooldown = nbt.getBoolean("slow_falling_cooldown");
			crystallite_emerald_heal_timer = nbt.getDouble("crystallite_emerald_heal_timer");
			blocks_broken_with_sculk_crystallite_pickaxe = nbt.getDouble("blocks_broken_with_sculk_crystallite_pickaxe");
			critical_hit = nbt.getBoolean("critical_hit");
			fall_time = nbt.getDouble("fall_time");
			crystallite_amethyst_absorption_timer = nbt.getDouble("crystallite_amethyst_absorption_timer");
			last_on_ground_x = nbt.getDouble("last_on_ground_x");
			last_on_ground_y = nbt.getDouble("last_on_ground_y");
			last_on_ground_z = nbt.getDouble("last_on_ground_z");
			save_from_void_cooldown = nbt.getDouble("save_from_void_cooldown");
			attack_damage = nbt.getString("attack_damage");
			attack_damage_modifier = nbt.getString("attack_damage_modifier");
			knockback_resistance = nbt.getString("knockback_resistance");
			knockback_resistance_modifier = nbt.getString("knockback_resistance_modifier");
			flaming_circlet_cooldown = nbt.getDouble("flaming_circlet_cooldown");
			time_since_last_attacked = nbt.getDouble("time_since_last_attacked");
			time_since_last_mined = nbt.getDouble("time_since_last_mined");
			last_mined_block = NbtUtils.readBlockState(BuiltInRegistries.BLOCK.asLookup(), nbt.getCompound("last_mined_block"));
			block_mining_combo = nbt.getDouble("block_mining_combo");
			stick_to_ceiling = nbt.getBoolean("stick_to_ceiling");
			crystallite_honey_absorption_timer = nbt.getDouble("crystallite_honey_absorption_timer");
			time_since_last_jumped = nbt.getDouble("time_since_last_jumped");
			smelting_touch_item_to_smelt = ItemStack.of(nbt.getCompound("smelting_touch_item_to_smelt"));
			smelting_touch_item_to_drop = ItemStack.of(nbt.getCompound("smelting_touch_item_to_drop"));
			ender_titanium_boots_cooldown = nbt.getDouble("ender_titanium_boots_cooldown");
			last_death_x = nbt.getDouble("last_death_x");
			last_death_y = nbt.getDouble("last_death_y");
			last_death_z = nbt.getDouble("last_death_z");
			time_since_non_carbonated_food_eaten = nbt.getDouble("time_since_non_carbonated_food_eaten");
			last_food_eaten = ItemStack.of(nbt.getCompound("last_food_eaten"));
			last_food_was_carbonated = nbt.getBoolean("last_food_was_carbonated");
			fall_start_y = nbt.getDouble("fall_start_y");
			nature_ring_equipped = nbt.getBoolean("nature_ring_equipped");
		}

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				PacketDistributor.PLAYER.with(serverPlayer).send(new PlayerVariablesSyncMessage(this));
		}
	}

	public record PlayerVariablesSyncMessage(PlayerVariables data) implements CustomPacketPayload {
		public static final ResourceLocation ID = new ResourceLocation(BetterToolsMod.MODID, "player_variables_sync");

		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this(new PlayerVariables());
			this.data.deserializeNBT(buffer.readNbt());
		}

		@Override
		public void write(final FriendlyByteBuf buffer) {
			buffer.writeNbt(data.serializeNBT());
		}

		@Override
		public ResourceLocation id() {
			return ID;
		}

		public static void handleData(final PlayerVariablesSyncMessage message, final PlayPayloadContext context) {
			if (context.flow() == PacketFlow.CLIENTBOUND && message.data != null) {
				context.workHandler().submitAsync(() -> Minecraft.getInstance().player.getData(PLAYER_VARIABLES).deserializeNBT(message.data.serializeNBT())).exceptionally(e -> {
					context.packetHandler().disconnect(Component.literal(e.getMessage()));
					return null;
				});
			}
		}
	}
}
