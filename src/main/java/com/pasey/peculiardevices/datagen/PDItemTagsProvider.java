package com.pasey.peculiardevices.datagen;

import com.pasey.peculiardevices.PeculiarDevices;
import com.pasey.peculiardevices.registration.PDItems;
import com.pasey.peculiardevices.util.PDTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class PDItemTagsProvider extends ItemTagsProvider {
    public PDItemTagsProvider(PackOutput packOutput,
                              CompletableFuture<HolderLookup.Provider> lookupProvider,
                              BlockTagsProvider blockTags,
                              ExistingFileHelper helper) {
        super(packOutput, lookupProvider, blockTags.contentsGetter(), PeculiarDevices.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        copy(PDTags.Blocks.PD_MACHINES, PDTags.Items.PD_MACHINES);
        copy(PDTags.Blocks.PD_ORES, Tags.Items.ORES);

        tag(Tags.Items.INGOTS)
                .add(PDItems.LITHIUM_INGOT.get());

        tag(Tags.Items.RAW_MATERIALS)
                .add(PDItems.RAW_LITHIUM.get());
    }

}

