package com.pasey.peculiardevices.util;

import com.pasey.peculiardevices.PeculiarDevices;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import net.minecraft.tags.BlockTags;

public class PDTags {
    public static class Blocks {
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(PeculiarDevices.MODID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> PD_MACHINES = tag("pd_machines");


        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(PeculiarDevices.MODID, name));
        }
    }
}
