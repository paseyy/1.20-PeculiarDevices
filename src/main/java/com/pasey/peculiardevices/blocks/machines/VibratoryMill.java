package com.pasey.peculiardevices.blocks.machines;

import com.pasey.peculiardevices.blockentities.VibratoryMillBlockEntity;
import com.pasey.peculiardevices.blocks.base.BaseMachineBlock;
import com.pasey.peculiardevices.registration.PDBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

public class VibratoryMill extends BaseMachineBlock {
    public VibratoryMill() {
        super(Properties.of()
                .mapColor(MapColor.COLOR_ORANGE)
                .requiresCorrectToolForDrops()
                .strength(1.5f, 3.0f)
                .sound(SoundType.COPPER)
                .noOcclusion());
    }

    @Override
    @Nullable
    @ParametersAreNonnullByDefault
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return PDBlockEntities.VIBRATORY_MILL_BE.get().create(pPos, pState);
    }

    @Override
    @NotNull
    @ParametersAreNonnullByDefault
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pLevel.isClientSide()) {
            BlockEntity be = pLevel.getBlockEntity(pPos);
            if (be instanceof VibratoryMillBlockEntity vibratoryMillBE) {
                ItemStack stack = pPlayer.getItemInHand(pHand);
                if(stack.isEmpty()) { // Player has empty hand
                    if(vibratoryMillBE.peek().isEmpty()) {
                        pPlayer.sendSystemMessage(Component.literal("No item present"));
                        return InteractionResult.SUCCESS;
                    }
                    else { // There are items in the mill!
                        ItemStack extracted = vibratoryMillBE.pop();
                        pPlayer.setItemInHand(pHand, extracted);
                    }
                }
                else { // Player has something in hand
                    if(vibratoryMillBE.isFull()) {
                        pPlayer.sendSystemMessage(Component.literal("Inventory full"));
                        return InteractionResult.SUCCESS;
                    }
                    else {
                        ItemStack toInsert = stack.copy();
                        ItemStack remainder = vibratoryMillBE.push(toInsert);
                        pPlayer.setItemInHand(pHand, remainder);
                    }
                }

                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }
}
