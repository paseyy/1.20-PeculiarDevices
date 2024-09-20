package com.pasey.peculiardevices.datagen;

import com.pasey.peculiardevices.PeculiarDevices;
import com.pasey.peculiardevices.registration.BlockRegistration;
import com.pasey.peculiardevices.registration.Registration;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class PDBlockStates extends BlockStateProvider {
    public PDBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PeculiarDevices.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(BlockRegistration.LITHIUM_ORE.get());
    }
}
