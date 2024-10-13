package com.pasey.peculiardevices.registration;

import com.pasey.peculiardevices.PeculiarDevices;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class PDItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PeculiarDevices.MODID);


    public static final RegistryObject<Item> BARBERTONITE_ORE_ITEM =
            ITEMS.register("barbertonite_ore", () -> new BlockItem(PDBlocks.BARBERTONITE_ORE.get(), new Item.Properties()));

    public static final RegistryObject<Item> GEO_GENERATOR_ITEM =
            ITEMS.register("geo_generator", () -> new BlockItem(PDBlocks.GEO_GENERATOR.get(), new Item.Properties()));

    public static final RegistryObject<Item> GEO_PIPE_ITEM =
            ITEMS.register("geo_pipe", () -> new BlockItem(PDBlocks.GEO_PIPE.get(), new Item.Properties()));

    public static final RegistryObject<Item> HEAT_EXCHANGE_UNIT =
            ITEMS.register("head_exchange_unit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LITHIUM_BATTERY =
            ITEMS.register("lithium_battery", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LITHIUM_INGOT =
            ITEMS.register("lithium_ingot", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LITHIUM_MILLINGS =
            ITEMS.register("lithium_millings", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LITHIUM_ORE_ITEM =
            ITEMS.register("lithium_ore", () -> new BlockItem(PDBlocks.LITHIUM_ORE.get(), new Item.Properties()));

    public static final RegistryObject<Item> RAW_LITHIUM =
            ITEMS.register("raw_lithium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> VIBRATORY_MILL_ITEM =
            ITEMS.register("vibratory_mill", () -> new BlockItem(PDBlocks.VIBRATORY_MILL.get(), new Item.Properties()));
}
