package com.pasey.peculiardevices.datagen;

import com.pasey.peculiardevices.PeculiarDevices;
import com.pasey.peculiardevices.registration.ItemRegistration;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class PDItemModelsProvider extends ItemModelProvider {
    private final ExistingFileHelper existingFileHelper;
    public PDItemModelsProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PeculiarDevices.MODID, existingFileHelper);
        this.existingFileHelper = existingFileHelper;
    }

    @Override
    protected void registerModels() {
        basicItem(ItemRegistration.LITHIUM_INGOT.get());
        basicItem(ItemRegistration.RAW_LITHIUM.get());

        blockItem(ItemRegistration.GEO_PIPE_ITEM.get());
    }

    private void blockItem(Item item) {
        if (item instanceof BlockItem blockItem) {
            getBuilder(item.toString())
                .parent(new ModelFile.ExistingModelFile(
                        modLoc("block/" + Objects.requireNonNull(
                            ForgeRegistries.BLOCKS.getKey(blockItem.getBlock())).getPath()
                        ),
                        existingFileHelper
                    )
                )
                .transforms()
                .transform(ItemDisplayContext.GUI)
                    .rotation(-150, 10, 0)
                    .scale(0.75f, 0.75f, 0.75f)
                    .end();
        }
    }
}


