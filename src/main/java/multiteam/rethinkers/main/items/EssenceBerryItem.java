package multiteam.rethinkers.main.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class EssenceBerryItem extends Item {

    public EssenceBerryItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag tooltipFlag) {
        super.appendHoverText(stack, worldIn, tooltip, tooltipFlag);
        tooltip.add(new TranslationTextComponent("tooltip.rethinkers.essence_berry_item"));
    }

    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerEntity, Hand hand) {
        playerEntity.giveExperiencePoints(10);
        worldIn.playSound(playerEntity, playerEntity.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1f, 1f);
        ItemStack itemstack = playerEntity.getItemInHand(hand);
        if(!playerEntity.isCreative()){
            itemstack.shrink(1);
            if(itemstack.isEmpty()){
                playerEntity.inventory.removeItem(itemstack);
            }
        }
        return ActionResult.consume(itemstack);
    }
}
