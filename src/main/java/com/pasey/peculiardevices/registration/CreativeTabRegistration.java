package com.pasey.peculiardevices.registration;

import com.pasey.peculiardevices.PeculiarDevices;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTabRegistration {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PeculiarDevices.MODID);

    public static final RegistryObject<CreativeModeTab> PD_TAB = CREATIVE_MODE_TABS.register("pd_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ItemRegistration.LITHIUM_ORE_ITEM.get()))
                    .title(Component.translatable("creativetab.pd_tab"))
                    .displayItems((displayParameters, output) -> {
                        output.accept(ItemRegistration.LITHIUM_ORE_ITEM.get());
                        output.accept(ItemRegistration.RAW_LITHIUM.get());
                        output.accept(ItemRegistration.LITHIUM_INGOT.get());

                        output.accept(ItemRegistration.GEO_PIPE_ITEM.get());
                    })
                    .build()
    );
}
