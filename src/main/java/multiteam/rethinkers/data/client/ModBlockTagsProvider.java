package multiteam.rethinkers.data.client;

import multiteam.rethinkers.ReThinkersConstruct;
import multiteam.rethinkers.main.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.DyeColor;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import slimeknights.tconstruct.common.registration.MetalItemObject;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.gadgets.TinkerGadgets;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.shared.TinkerMaterials;
import slimeknights.tconstruct.shared.block.SlimeType;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.smeltery.data.SmelteryCompat;
import slimeknights.tconstruct.tables.TinkerTables;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.world.TinkerWorld;
import slimeknights.tconstruct.world.block.SlimeGrassBlock;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.function.Consumer;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, ReThinkersConstruct.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.addCommon();
        this.addGadgets();
        this.addTools();
        this.addWorld();
        this.addSmeltery();
        this.addFluids();
    }

    private void addCommon() {
        //this.addMetalTags(ModBlocks.TIN);
        //this.addMetalTags(ModBlocks.ALUMINIUM);
    }

    private void addTools() {

    }

    private void addWorld() {

    }

    private void addGadgets() {
    }

    private void addSmeltery() {
    }

    private void addFluids() {
       // this.tag(BlockTags.STRIDER_WARM_BLOCKS).add(new Block[]{TinkerFluids.magmaCream.getBlock(), TinkerFluids.moltenBlaze.getBlock()});
    }

    public String getName() {
        return "ReThinkers Construct Block Tags";
    }

    private void addColored(Consumer<Block> consumer, ITag.INamedTag<Block> group, String pattern) {
        String prefix = group.getName().getPath().toUpperCase(Locale.ENGLISH) + '_';
        DyeColor[] var5 = DyeColor.values();
        int var6 = var5.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            DyeColor color = var5[var7];
            ResourceLocation key = new ResourceLocation(ReThinkersConstruct.MOD_ID, pattern.replace("{color}", color.getName()));
            ITag.INamedTag<Block> tag = this.getForgeTag(prefix + color.getName());
            Block block = (Block) ForgeRegistries.BLOCKS.getValue(key);
            if (block == null || block == net.minecraft.block.Blocks.AIR) {
                throw new IllegalStateException("Unknown "+ReThinkersConstruct.MOD_ID+" block: " + key.toString());
            }

            this.tag(tag).add(new Block[]{block});
            consumer.accept(block);
        }

    }

    private void addMetalTags(MetalItemObject metal) {
        this.tag(metal.getBlockTag()).add(new Block[]{(Block)metal.get()});
        this.tag(BlockTags.BEACON_BASE_BLOCKS).addTag(metal.getBlockTag());
        this.tag(Tags.Blocks.STORAGE_BLOCKS).addTag(metal.getBlockTag());
    }

    private ITag.INamedTag<Block> getForgeTag(String name) {
        try {
            name = name.toUpperCase(Locale.ENGLISH);
            return (ITag.INamedTag) Tags.Blocks.class.getDeclaredField(name).get((Object)null);
        } catch (IllegalAccessException | NoSuchFieldException | SecurityException | IllegalArgumentException var3) {
            throw new IllegalStateException(Tags.Blocks.class.getName() + " is missing tag name: " + name);
        }
    }
}
