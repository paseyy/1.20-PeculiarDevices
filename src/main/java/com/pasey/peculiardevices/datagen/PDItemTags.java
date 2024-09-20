package com.pasey.peculiardevices.datagen;

import com.pasey.peculiardevices.PeculiarDevices;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class PDItemTags extends ItemTagsProvider {
    public PDItemTags(PackOutput packOutput,
                      CompletableFuture<HolderLookup.Provider> lookupProvider,
                      BlockTagsProvider blockTags,
                      ExistingFileHelper helper) {
        super(packOutput, lookupProvider, blockTags.contentsGetter(), PeculiarDevices.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {

    }
}
