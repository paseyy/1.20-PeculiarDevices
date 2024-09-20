package com.pasey.peculiardevices.registration;

import com.pasey.peculiardevices.PeculiarDevices;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.pasey.peculiardevices.registration.BlockRegistration.LITHIUM_ORE;

public class ItemRegistration {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PeculiarDevices.MODID);


    public static final RegistryObject<Item> LITHIUM_ORE_ITEM =
            ITEMS.register("lithium_ore", () -> new BlockItem(LITHIUM_ORE.get(), new Item.Properties()));

    public static final RegistryObject<Item> RAW_LITHIUM =
            ITEMS.register("raw_lithium", () -> new Item(new Item.Properties()));
}
