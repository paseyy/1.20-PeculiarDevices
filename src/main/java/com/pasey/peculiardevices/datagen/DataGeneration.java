package com.pasey.peculiardevices.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DataGeneration {
    public static void generate(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new PDBlockStates(packOutput, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new PDItemModels(packOutput, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new PDLang(packOutput, "en_us"));

        PDBlockTags pdBlockTags = new PDBlockTags(packOutput, lookupProvider, event.getExistingFileHelper());

        generator.addProvider(event.includeServer(), pdBlockTags);
        generator.addProvider(event.includeServer(), new PDRecipes(packOutput));
        generator.addProvider(event.includeServer(),
                new PDItemTags(packOutput, lookupProvider, pdBlockTags, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(PDLootTables::new, LootContextParamSets.BLOCK))));
    }
}
