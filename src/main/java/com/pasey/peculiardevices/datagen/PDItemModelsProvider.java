package com.pasey.peculiardevices.datagen;

import com.pasey.peculiardevices.PeculiarDevices;
import com.pasey.peculiardevices.registration.ItemRegistration;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class PDItemModelsProvider extends ItemModelProvider {
    public PDItemModelsProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PeculiarDevices.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ItemRegistration.LITHIUM_INGOT.get());
        basicItem(ItemRegistration.RAW_LITHIUM.get());
    }
}
