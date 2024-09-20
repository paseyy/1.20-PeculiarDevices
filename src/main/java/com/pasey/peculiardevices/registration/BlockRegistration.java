package com.pasey.peculiardevices.registration;

import com.pasey.peculiardevices.PeculiarDevices;
import com.pasey.peculiardevices.blocks.materials.LithiumOre;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistration {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PeculiarDevices.MODID);

    public static final RegistryObject<LithiumOre> LITHIUM_ORE = BLOCKS.register("lithium_ore", LithiumOre::new);

    public static class CreativeModeTabs {
        public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
                DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PeculiarDevices.MODID);

        public static final RegistryObject<CreativeModeTab> PD_TAB = CREATIVE_MODE_TABS.register("pd_tab",
                () -> CreativeModeTab.builder()
                        .icon(() -> new ItemStack(ItemRegistration.LITHIUM_ORE_ITEM.get()))
                        .title(Component.translatable("creativetab.pd_tab"))
                        .displayItems((displayParameters, output) -> {
                            output.accept(ItemRegistration.LITHIUM_ORE_ITEM.get());
                            output.accept(ItemRegistration.RAW_LITHIUM.get());
                        })
                        .build()
        );

        public static void register(IEventBus eventBus) {
            CREATIVE_MODE_TABS.register(eventBus);
        }
    }
}
