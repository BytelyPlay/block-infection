package org.hyperoil.blockinfection.Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.hyperoil.blockinfection.Utils.BlocksHelper;
import org.hyperoil.blockinfection.Utils.InfectionManager;

public class InfectionBlockEntity extends BlockEntity {
    private int infectionID;
    public InfectionBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlocksHelper.INFECTION_BLOCK_ENTITY.get(), pos, blockState);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.infectionID = input.getIntOr("infectionID", InfectionManager.DEFAULT_INFECTION_ID);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("infectionID", infectionID);
    }

    public int getInfectionID() {
        return infectionID;
    }

    public void setInfectionID(int infectionID) {
        this.infectionID = infectionID;
        setChanged();
    }
}
