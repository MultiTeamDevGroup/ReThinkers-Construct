package multiteam.rethinkers.main.blocks;

import multiteam.rethinkers.main.items.ModItems;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class OreberryBushBlock extends BushBlock implements IGrowable {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape SHAPE_STAGE_SMALL = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
    private static final VoxelShape SHAPE_STAGE_MEDIUM = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);

    public OreberryBushBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));
    }

    public ItemStack getCloneItemStack(IBlockReader iBlockReader, BlockPos pos, BlockState state) {
        return new ItemStack(ModItems.ESSENCE_BERRY.get());
    }

    protected boolean mayPlaceOn(BlockState p_200014_1_, IBlockReader p_200014_2_, BlockPos p_200014_3_) {
        return p_200014_1_.is(Blocks.GRASS_BLOCK) || p_200014_1_.is(Blocks.DIRT) || p_200014_1_.is(Blocks.COARSE_DIRT) || p_200014_1_.is(Blocks.PODZOL) || p_200014_1_.is(Blocks.FARMLAND);
    }

    public VoxelShape getShape(BlockState state, IBlockReader iBlockReader, BlockPos pos, ISelectionContext selectionContext) {
        if (state.getValue(AGE) == 0) {
            return SHAPE_STAGE_SMALL;
        } else if(state.getValue(AGE) == 1){
            return SHAPE_STAGE_MEDIUM;
        } else { //AGE == 2
            return super.getShape(state, iBlockReader, pos, selectionContext);
        }
    }

    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    public void randomTick(BlockState state, ServerWorld serverWorld, BlockPos pos, Random random) {
        int age = state.getValue(AGE);
        if (age <= 2 && serverWorld.getRawBrightness(pos.above(), 0) <= 10 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(serverWorld, pos, state,random.nextInt(5) == 0)) {
            serverWorld.setBlock(pos, state.setValue(AGE, Integer.valueOf(age + 1)), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(serverWorld, pos, state);
        }

    }

    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult hitResult) {
        int age = state.getValue(AGE);
        boolean flag = age == 3;
        if (age == 3) {
            int j = 1 + world.random.nextInt(2);
            popResource(world, pos, new ItemStack(getBerry(world, pos).getItem(), j));
            world.playSound((PlayerEntity)null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
            world.setBlock(pos, state.setValue(AGE, Integer.valueOf(2)), 2);
            return ActionResultType.sidedSuccess(world.isClientSide);
        } else {
            return super.use(state, world, pos, playerEntity, hand, hitResult);
        }
    }

    public static ItemStack getBerry(World worldIn, BlockPos pos){
        Block bushBlock = worldIn.getBlockState(pos).getBlock();
        if (bushBlock == ModBlocks.ESSENCE_BERRY_BUSH.get()){
            return new ItemStack(ModItems.ESSENCE_BERRY.get());
        }
        return new ItemStack(bushBlock.asItem());
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    public boolean isValidBonemealTarget(IBlockReader blockReader, BlockPos pos, BlockState state, boolean bool) { return false; }

    public boolean isBonemealSuccess(World world, Random random, BlockPos pos, BlockState state) { return false; }

    public void performBonemeal(ServerWorld serverWorldIn, Random random, BlockPos pos, BlockState state) { return; }
}