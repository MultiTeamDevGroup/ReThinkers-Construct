package multiteam.rethinkers.main.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class CopperCanTileEntity extends TileEntity implements ITickableTileEntity {

    private String containedFluid = "empty";

    public CopperCanTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public CopperCanTileEntity(){
        this(ModBlocks.COPPER_CAN_TILE_ENTITY.get());
    }

    @Override
    public void tick() {
        CompoundNBT compoundNBT = this.getTileData();
        if(compoundNBT == null){
            compoundNBT = new CompoundNBT();
        }

    }

    @Override
    public void load(BlockState state, CompoundNBT compoundNBT) {
        if(compoundNBT == null){
            compoundNBT = new CompoundNBT();
        }
        super.load(state, compoundNBT);
        this.containedFluid = compoundNBT.getString("ContainedFluid");
    }

    @Override
    public CompoundNBT save(CompoundNBT compoundNBT) {
        if(compoundNBT == null){
            compoundNBT = new CompoundNBT();
        }
        super.save(compoundNBT);
        compoundNBT.putString("ContainedFluid", this.containedFluid);
        return compoundNBT;
    }

    public void setContainedFluid(Fluid fluid){
        CompoundNBT nbt = this.getTileData();
        if(nbt != null){
            String string = ((ResourceLocation) Objects.requireNonNull(fluid.getRegistryName())).toString();
            nbt.putString("ContainedFluid", string);
        }
    }

    public static Fluid getContainedFluid(CompoundNBT nbt){
        if(nbt != null){
            ResourceLocation location = ResourceLocation.tryParse(nbt.getString("ContainedFluid"));
            if (location != null && ForgeRegistries.FLUIDS.containsKey(location)) {
                Fluid fluid = (Fluid)ForgeRegistries.FLUIDS.getValue(location);
                if (fluid != null) {
                    return fluid;
                }
            }
        }
        return Fluids.EMPTY;
    }

}
