package io.github.randommcsomethin.randomsearlyprogression;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.impl.tag.convention.TagRegistration;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagBuilder;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomsEarlyProgression implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MODID = "randoms-early-progression";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	public static final TagKey<Block> NEEDS_FLINT_TOOL = TagKey.of(RegistryKeys.BLOCK, new Identifier(MODID, "needs_flint_tool"));
	public static final TagKey<Item> FLINT_TOOLS = TagKey.of(RegistryKeys.ITEM, new Identifier(MODID, "flint_tools"));

	public static final TagKey<Biome> HAS_ROCKS = TagKey.of(RegistryKeys.BIOME, new Identifier(MODID, "has_rocks"));
	public static final RegistryKey<PlacedFeature> FLINT_ROCKS = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(MODID, "flint_rocks"));

	public static final Block FLINT_ROCK = new RockBlock(FabricBlockSettings.copyOf(Blocks.STONE_BUTTON));

	static {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(Items.GRAVEL.getDefaultStack(),
				new Block[]{
						FLINT_ROCK
				}
		));
	}

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		FuelRegistry.INSTANCE.remove(Items.WOODEN_AXE);
		FuelRegistry.INSTANCE.remove(Items.WOODEN_PICKAXE);
		FuelRegistry.INSTANCE.remove(Items.WOODEN_SHOVEL);
		FuelRegistry.INSTANCE.remove(Items.WOODEN_HOE);
		FuelRegistry.INSTANCE.remove(Items.WOODEN_SWORD);

		BiomeModifications.addFeature(
				BiomeSelectors.tag(HAS_ROCKS), GenerationStep.Feature.VEGETAL_DECORATION, FLINT_ROCKS
		);

		Registry.register(Registries.BLOCK, new Identifier(MODID, "flint_rock"), FLINT_ROCK);
		Registry.register(Registries.ITEM, new Identifier(MODID, "flint_rock"), new BlockItem(FLINT_ROCK, new FabricItemSettings()));

		LOGGER.info("Hello Fabric world!");
	}
}