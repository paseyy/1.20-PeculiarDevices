package com.pasey.peculiardevices.datagen;

import com.pasey.peculiardevices.PeculiarDevices;
import com.pasey.peculiardevices.registration.BlockRegistration;
import com.pasey.peculiardevices.registration.ItemRegistration;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.stream.Collectors;

public class PDLootTablesProvider extends VanillaBlockLoot {

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getEntries().stream()
                .filter(e -> e.getKey().location().getNamespace().equals(PeculiarDevices.MODID))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    @Override
    protected void generate() {
        dropSelf(BlockRegistration.GEO_GENERATOR.get());
        dropSelf(BlockRegistration.GEO_PIPE.get());

        add(BlockRegistration.LITHIUM_ORE.get(),
                block -> createOreDrop(BlockRegistration.LITHIUM_ORE.get(), ItemRegistration.RAW_LITHIUM.get()));
    }

}
