package com.pasey.peculiardevices.datagen;

import com.pasey.peculiardevices.PeculiarDevices;
import com.pasey.peculiardevices.registration.PDBlocks;
import com.pasey.peculiardevices.registration.PDCreativeTabs;
import com.pasey.peculiardevices.registration.PDItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class PDLangProvider extends LanguageProvider {
    public PDLangProvider(PackOutput output, String locale) {
        super(output, PeculiarDevices.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        // Blocks
        add(PDBlocks.GEO_GENERATOR.get(), "Geothermal Generator");
        add(PDBlocks.GEO_PIPE.get(), "Geothermal Pipe");
        add(PDBlocks.LITHIUM_ORE.get(), "Lithium Ore");

        // Items
        add(PDItems.LITHIUM_INGOT.get(), "Lithium Ingot");
        add(PDItems.RAW_LITHIUM.get(), "Raw Lithium");

        // Creative Tab
        add(PDCreativeTabs.PD_TAB.get().getDisplayName().getString(), "Peculiar Devices");
    }
}
