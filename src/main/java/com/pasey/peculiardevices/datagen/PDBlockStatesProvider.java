package com.pasey.peculiardevices.datagen;

import com.pasey.peculiardevices.PeculiarDevices;
import com.pasey.peculiardevices.blocks.additionals.GeoPipe;
import com.pasey.peculiardevices.registration.BlockRegistration;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class PDBlockStatesProvider extends BlockStateProvider {
    private final ExistingFileHelper existingFileHelper;

    public PDBlockStatesProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PeculiarDevices.MODID, exFileHelper);
        existingFileHelper = exFileHelper;
    }



    @Override
    protected void registerStatesAndModels() {
        stackableBlock(BlockRegistration.GEO_PIPE.get());

        simpleBlockWithItem(BlockRegistration.LITHIUM_ORE.get(), cubeAll(BlockRegistration.LITHIUM_ORE.get()));

    }

    private void stackableBlock(Block block) {
        getVariantBuilder(block).forAllStates(state -> {
            Boolean is_bottom = state.getValue(GeoPipe.BOTTOM);
            return ConfiguredModel.builder()
                   .modelFile(
                           new ModelFile.ExistingModelFile(modLoc(is_bottom ? "block/geo_pipe_end" : "block/geo_pipe"),
                           existingFileHelper)
                   )
                   .build();
        });
    }
}
