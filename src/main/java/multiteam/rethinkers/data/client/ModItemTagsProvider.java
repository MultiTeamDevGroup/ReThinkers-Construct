package multiteam.rethinkers.data.client;

import multiteam.rethinkers.ReThinkersConstruct;
import multiteam.rethinkers.main.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.common.data.tags.TConstructItemTagsProvider;
import slimeknights.tconstruct.common.registration.MetalItemObject;

import java.util.Locale;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(DataGenerator generatorIn, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(generatorIn, blockTagProvider, ReThinkersConstruct.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.addCommon();
    }


    private void addCommon(){
        this.addMetalTags(ModBlocks.TIN);
        this.addMetalTags(ModBlocks.ALUMINIUM);
        this.addMetalTags(ModBlocks.ALUMITE);
    }

    public String getName() {
        return "ReThinkers Construct Item Tags";
    }

    private void addMetalTags(MetalItemObject metal) {
        this.tag(metal.getIngotTag()).add(new Item[]{metal.getIngot()});
        this.tag(Tags.Items.INGOTS).addTag(metal.getIngotTag());
        this.tag(metal.getNuggetTag()).add(new Item[]{metal.getNugget()});
        this.tag(Tags.Items.NUGGETS).addTag(metal.getNuggetTag());
        this.copy(metal.getBlockTag(), metal.getBlockItemTag());
    }

    private void copyColored(ITag.INamedTag<Block> blockGroup, ITag.INamedTag<Item> itemGroup) {
        String blockPre = blockGroup.getName().getPath().toUpperCase(Locale.ENGLISH) + '_';
        String itemPre = itemGroup.getName().getPath().toUpperCase(Locale.ENGLISH) + '_';
        DyeColor[] var5 = DyeColor.values();
        int var6 = var5.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            DyeColor color = var5[var7];
            ITag.INamedTag<Block> from = this.getForgeBlockTag(blockPre + color.getName());
            ITag.INamedTag<Item> to = this.getForgeItemTag(itemPre + color.getName());
            this.copy(from, to);
        }

        this.copy(this.getForgeBlockTag(blockPre + "colorless"), this.getForgeItemTag(itemPre + "colorless"));
    }

    private ITag.INamedTag<Block> getForgeBlockTag(String name) {
        try {
            name = name.toUpperCase(Locale.ENGLISH);
            return (ITag.INamedTag) Tags.Blocks.class.getDeclaredField(name).get((Object)null);
        } catch (IllegalAccessException | NoSuchFieldException | SecurityException | IllegalArgumentException var3) {
            throw new IllegalStateException(Tags.Blocks.class.getName() + " is missing tag name: " + name);
        }
    }

    private ITag.INamedTag<Item> getForgeItemTag(String name) {
        try {
            name = name.toUpperCase(Locale.ENGLISH);
            return (ITag.INamedTag) Tags.Items.class.getDeclaredField(name).get((Object)null);
        } catch (IllegalAccessException | NoSuchFieldException | SecurityException | IllegalArgumentException var3) {
            throw new IllegalStateException(Tags.Items.class.getName() + " is missing tag name: " + name);
        }
    }
}
