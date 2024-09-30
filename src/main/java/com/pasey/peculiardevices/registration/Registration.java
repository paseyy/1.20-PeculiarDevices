package com.pasey.peculiardevices.registration;

import net.minecraftforge.eventbus.api.IEventBus;

public class Registration {
    public static void init(IEventBus modEventBus) {
        CreativeTabRegistration.CREATIVE_MODE_TABS.register(modEventBus);
        ItemRegistration.ITEMS.register(modEventBus);
        BlockRegistration.BLOCKS.register(modEventBus);
    }
}
