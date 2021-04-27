package multiteam.rethinkers.main;

import multiteam.rethinkers.ReThinkersConstruct;
import multiteam.rethinkers.main.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.smeltery.item.CopperCanItem;

import java.util.Objects;


@Mod.EventBusSubscriber(modid = ReThinkersConstruct.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

    @SubscribeEvent
    public static void onPlayerRightClickWithItem(PlayerInteractEvent event){
        World worldIn = event.getWorld();
        ItemStack usedItem = event.getItemStack();
        BlockPos clickedPos = event.getPos();
        BlockState clickedBlock = worldIn.getBlockState(clickedPos);
        PlayerEntity playerEntity = event.getPlayer();

        if(event.getClass() == PlayerInteractEvent.RightClickItem.class || event.getClass() == PlayerInteractEvent.RightClickBlock.class){
            //Placing Copper Cans
            if(usedItem.getItem() == TinkerSmeltery.copperCan.get() && clickedBlock.getBlock() != Blocks.AIR){
                IBlockReader iBlockReader = worldIn.getChunkForCollisions(ChunkPos.getX((long)clickedPos.getX()), ChunkPos.getZ((long)clickedPos.getZ()));
                if(clickedBlock.getBlockState().isCollisionShapeFullBlock(iBlockReader, clickedPos)){
                    BlockPos placepos = null;
                    SoundEvent placeSound = ModBlocks.COPPER_CAN_BLOCK.get().getSoundType(ModBlocks.COPPER_CAN_BLOCK.get().defaultBlockState()).getPlaceSound();
                    Block copperCan = ModBlocks.COPPER_CAN_BLOCK.get();
                    if(event.getFace() == Direction.UP){
                        placepos = clickedPos.above();
                        if(worldIn.getBlockState(placepos) == Blocks.AIR.defaultBlockState()  && worldIn.getFluidState(placepos).isEmpty()){
                            worldIn.playSound((PlayerEntity)null, placepos.getX(), placepos.getY(), placepos.getZ(), placeSound, SoundCategory.BLOCKS, 1f, 1f);
                            worldIn.setBlockAndUpdate(placepos, copperCan.defaultBlockState());
                            consumeItemIfIsNotCreative(playerEntity, usedItem);
                        }else{
                            placepos = null;
                        }
                    }else if(event.getFace() != Direction.DOWN && event.getFace() != Direction.UP){
                        placepos = clickedPos.relative(event.getFace());
                        if(worldIn.getBlockState(placepos) == Blocks.AIR.defaultBlockState() && worldIn.getFluidState(placepos).isEmpty()){
                            worldIn.playSound((PlayerEntity)null, placepos.getX(), placepos.getY(), placepos.getZ(), placeSound, SoundCategory.BLOCKS, 1f, 1f);
                            worldIn.setBlockAndUpdate(placepos, copperCan.defaultBlockState());
                            consumeItemIfIsNotCreative(playerEntity, usedItem);
                        }else{
                            placepos = null;
                        }
                    }

                    if(placepos != null){
                        TileEntity copperCanTe = worldIn.getBlockEntity(placepos);
                        if(copperCanTe != null){
                            CompoundNBT nbt = copperCanTe.getTileData();
                            Fluid fluid = CopperCanItem.getFluid(usedItem);
                            String string = ((ResourceLocation) Objects.requireNonNull(fluid.getRegistryName())).toString();
                            nbt.putString("ContainedFluid", string);
                        }
                    }


                }
            }

        }

    }

    public static void consumeItemIfIsNotCreative(PlayerEntity playerEntity, ItemStack stack){
        if(!playerEntity.isCreative()){
            stack.shrink(1);
            if(stack.isEmpty()){
                playerEntity.inventory.removeItem(stack);
            }
        }
    }

}
