package com.pasey.peculiardevices.datagen;

import com.pasey.peculiardevices.PeculiarDevices;
import com.pasey.peculiardevices.registration.BlockRegistration;
import com.pasey.peculiardevices.registration.CreativeTabRegistration;
import com.pasey.peculiardevices.registration.ItemRegistration;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class PDLangProvider extends LanguageProvider {
    public PDLangProvider(PackOutput output, String locale) {
        super(output, PeculiarDevices.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add(BlockRegistration.GEO_PIPE.get(), "Geothermal Pipe");
        add(BlockRegistration.LITHIUM_ORE.get(), "Lithium Ore");

        add(ItemRegistration.LITHIUM_INGOT.get(), "Lithium Ingot");
        add(ItemRegistration.RAW_LITHIUM.get(), "Raw Lithium");

        add(CreativeTabRegistration.PD_TAB.get().getDisplayName().getString(), "Peculiar Devices");
    }
}
