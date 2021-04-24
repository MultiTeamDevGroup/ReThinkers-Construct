package multiteam.rethinkers.main;

import multiteam.rethinkers.ReThinkersConstruct;
import multiteam.rethinkers.main.blocks.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;


@Mod.EventBusSubscriber(modid = ReThinkersConstruct.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

    @SubscribeEvent
    public static void onPlayerRightClickWithItem(PlayerInteractEvent event){
        World worldIn = event.getWorld();
        ItemStack usedItem = event.getItemStack();
        BlockPos clickedPos = event.getPos();
        BlockState clickedBlock = worldIn.getBlockState(clickedPos);
        PlayerEntity playerEntity = event.getPlayer();

        if(event.getSide().isClient()){
            //yes.
        }

        if(!worldIn.isClientSide){

            if(usedItem.getItem() == TinkerSmeltery.copperCan.get() && clickedBlock.getBlock() != Blocks.AIR){
                if(clickedBlock.getBlockState().isCollisionShapeFullBlock(worldIn.getChunkForCollisions(ChunkPos.getX((long)clickedPos.getX()), ChunkPos.getZ((long)clickedPos.getZ())), clickedPos)){

                    if(event.getFace() == Direction.UP){
                        worldIn.setBlockAndUpdate(clickedPos.above(), ModBlocks.COPPER_CAN_BLOCK.get().defaultBlockState());

                    }else if(event.getFace() != Direction.DOWN && event.getFace() != Direction.UP){
                        worldIn.setBlockAndUpdate(clickedPos.relative(event.getFace()), ModBlocks.COPPER_CAN_BLOCK.get().defaultBlockState());
                    }


                }
            }

        }
    }

}
