package multiteam.rethinkers.main.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import slimeknights.tconstruct.library.tinkering.IndestructibleEntityItem;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.smeltery.item.CopperCanItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;


public class CopperCanBlock extends Block {

    protected static final VoxelShape SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 12.0D, 13.0D);

    public CopperCanBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, IBlockReader iBlockReader, BlockPos pos, ISelectionContext iSelectionContext) {
        return SHAPE;
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        ItemStack stack = new ItemStack(TinkerSmeltery.copperCan.get());
        TileEntity copperCanTe = world.getBlockEntity(pos);
        CompoundNBT nbt = copperCanTe.getTileData();
        if(nbt != null){
            CopperCanItem.setFluid(stack, CopperCanTileEntity.getContainedFluid(nbt));
        }
        return stack;
    }

    @Override
    public boolean hasTileEntity(BlockState state){
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world){
        return ModBlocks.COPPER_CAN_TILE_ENTITY.get().create();
    }

    public void playerDestroy(World world, PlayerEntity playerEntity, BlockPos pos, BlockState state, @Nullable TileEntity tileEntity, ItemStack handItem) {
        ItemStack stack = new ItemStack(TinkerSmeltery.copperCan.get());
        TileEntity copperCanTe = tileEntity;
        if(copperCanTe != null){
            CompoundNBT nbt = copperCanTe.getTileData();
            if(nbt != null){
                CopperCanItem.setFluid(stack, CopperCanTileEntity.getContainedFluid(nbt));
            }
            IndestructibleEntityItem dropstack = new IndestructibleEntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
            world.addFreshEntity(dropstack);
            dropstack.setPos(pos.getX(), pos.getY(), pos.getZ());
        }

    }

}
