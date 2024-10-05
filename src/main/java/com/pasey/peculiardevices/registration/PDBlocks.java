package com.pasey.peculiardevices.registration;

import com.pasey.peculiardevices.PeculiarDevices;
import com.pasey.peculiardevices.blocks.machines.GeoGenerator;
import com.pasey.peculiardevices.blocks.machines.GeoPipe;
import com.pasey.peculiardevices.blocks.machines.VibratoryMill;
import com.pasey.peculiardevices.blocks.materials.LithiumOre;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PDBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PeculiarDevices.MODID);

    public static final RegistryObject<LithiumOre> LITHIUM_ORE = BLOCKS.register("lithium_ore", LithiumOre::new);
    public static final RegistryObject<GeoGenerator> GEO_GENERATOR = BLOCKS.register("geo_generator", GeoGenerator::new);
    public static final RegistryObject<GeoPipe> GEO_PIPE = BLOCKS.register("geo_pipe", GeoPipe::new);
    public static final RegistryObject<VibratoryMill> VIBRATORY_MILL = BLOCKS.register("vibratory_mill", VibratoryMill::new);
}
