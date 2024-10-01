package com.pasey.peculiardevices.registration;

import com.pasey.peculiardevices.PeculiarDevices;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ItemRegistration {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PeculiarDevices.MODID);


    public static final RegistryObject<Item> GEO_PIPE_ITEM =
            ITEMS.register("geo_pipe", () -> new BlockItem(BlockRegistration.GEO_PIPE.get(), new Item.Properties()));

    public static final RegistryObject<Item> LITHIUM_INGOT =
            ITEMS.register("lithium_ingot", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LITHIUM_ORE_ITEM =
            ITEMS.register("lithium_ore", () -> new BlockItem(BlockRegistration.LITHIUM_ORE.get(), new Item.Properties()));

    public static final RegistryObject<Item> RAW_LITHIUM =
            ITEMS.register("raw_lithium", () -> new Item(new Item.Properties()));
}
