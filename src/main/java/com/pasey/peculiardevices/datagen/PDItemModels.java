package com.pasey.peculiardevices.datagen;

import com.pasey.peculiardevices.PeculiarDevices;
import com.pasey.peculiardevices.registration.BlockRegistration;
import com.pasey.peculiardevices.registration.ItemRegistration;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class PDItemModels extends ItemModelProvider {
    public PDItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PeculiarDevices.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(BlockRegistration.LITHIUM_ORE.getId().getPath(), modLoc("block/lithium_ore"));

        singleTexture("raw_lithium", new ResourceLocation("minecraft:item/generated"),
                "layer0", modLoc("item/raw_lithium"));
    }
}
