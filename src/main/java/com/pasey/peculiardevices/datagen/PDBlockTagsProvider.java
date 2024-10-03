package com.pasey.peculiardevices.datagen;

import com.pasey.peculiardevices.PeculiarDevices;
import com.pasey.peculiardevices.registration.BlockRegistration;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class PDBlockTagsProvider extends BlockTagsProvider {
    public PDBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, PeculiarDevices.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BlockRegistration.GEO_PIPE.get())
                .add(BlockRegistration.LITHIUM_ORE.get())
        ;

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(BlockRegistration.GEO_PIPE.get())
                .add(BlockRegistration.LITHIUM_ORE.get())
        ;

        tag(Tags.Blocks.ORES)
                .add(BlockRegistration.LITHIUM_ORE.get())
        ;
    }
}

