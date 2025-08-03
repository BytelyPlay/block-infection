package org.hyperoil.blockinfection.Blocks.BlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;
import org.hyperoil.blockinfection.Utils.BlockEntitiesHelper;
import org.hyperoil.blockinfection.Utils.BlocksHelper;

public class ChargedInfectionCoreBlockEntity extends BlockEntity implements IEnergyStorage {
    public ChargedInfectionCoreBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntitiesHelper.CHARGED_INFECTION_CORE_BLOCK_ENTITY.get(), pos, blockState);
    }

    @Override
    public int receiveEnergy(int i, boolean b) {
        return i;
    }

    @Override
    public int extractEnergy(int i, boolean b) {
        return i;
    }

    @Override
    public int getEnergyStored() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getMaxEnergyStored() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return false;
    }
}
