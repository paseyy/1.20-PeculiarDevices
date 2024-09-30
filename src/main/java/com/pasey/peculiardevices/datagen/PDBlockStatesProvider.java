package com.pasey.peculiardevices.datagen;

import com.pasey.peculiardevices.PeculiarDevices;
import com.pasey.peculiardevices.registration.BlockRegistration;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class PDBlockStatesProvider extends BlockStateProvider {
    public PDBlockStatesProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PeculiarDevices.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(BlockRegistration.LITHIUM_ORE.get(), cubeAll(BlockRegistration.LITHIUM_ORE.get()));
    }
}
