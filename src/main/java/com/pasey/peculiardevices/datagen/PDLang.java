package com.pasey.peculiardevices.datagen;

import com.pasey.peculiardevices.PeculiarDevices;
import com.pasey.peculiardevices.registration.BlockRegistration;
import com.pasey.peculiardevices.registration.ItemRegistration;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class PDLang extends LanguageProvider {
    public PDLang(PackOutput output, String locale) {
        super(output, PeculiarDevices.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add(BlockRegistration.LITHIUM_ORE.get(), "Lithium Ore");

        add(ItemRegistration.RAW_LITHIUM.get(), "Raw Lithium");
    }
}
