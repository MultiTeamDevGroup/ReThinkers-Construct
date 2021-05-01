package multiteam.rethinkers.main.blocks;

import multiteam.rethinkers.ReThinkersConstruct;
import multiteam.rethinkers.main.Registration;
import multiteam.rethinkers.main.blocks.copper_can.CopperCanBlock;
import multiteam.rethinkers.main.blocks.copper_can.CopperCanTileEntity;
import multiteam.rethinkers.main.items.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import slimeknights.mantle.item.BlockTooltipItem;
import slimeknights.tconstruct.common.registration.MetalItemObject;

import javax.annotation.Nullable;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {

    //Properties
    public static final Material MATERIAL_OREBERRY = (new Material(MaterialColor.STONE, false, false, false, true, false, false, Material.CACTUS.getPushReaction()));
    public static final AbstractBlock.Properties GENERIC_METAL_BLOCK = builder(Material.METAL, ToolType.PICKAXE, SoundType.METAL).requiresCorrectToolForDrops().strength(5.0F);
    protected static final net.minecraft.item.Item.Properties GENERAL_PROPS = (new net.minecraft.item.Item.Properties()).tab(ReThinkersConstruct.RETHINKERS_GENERAL_TAB);
    public static final Function<Block, ? extends BlockItem> GENERAL_TOOLTIP_BLOCK_ITEM= (b) -> { return new BlockTooltipItem(b, GENERAL_PROPS); };


    //Utility
    public static final RegistryObject<Block> COPPER_CAN_BLOCK = registerNoItem("copper_can", () -> new CopperCanBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_BROWN).strength(0,0).harvestLevel(0).sound(SoundType.LANTERN)));
    public static final RegistryObject<TileEntityType<CopperCanTileEntity>> COPPER_CAN_TILE_ENTITY = Registration.TILE_ENTITY_TYPES.register("copper_can", () -> TileEntityType.Builder.of(CopperCanTileEntity::new, COPPER_CAN_BLOCK.get()).build(null));
                                                                                                                                                                                  //Hardness and resistance
    //Utility
    public static final RegistryObject<Block> SILKY_CLOTH_BLOCK = registerWithItem("silky_cloth_block", () -> new Block(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.GOLD).strength(0.8f, 0.8f).sound(SoundType.WOOL)), new Item.Properties().tab(ReThinkersConstruct.RETHINKERS_GENERAL_TAB));

    //Oreberrys
    public static final RegistryObject<Block> ESSENCE_BERRY_BUSH = registerWithItem("essence_berry_bush", () -> new OreberryBushBlock(AbstractBlock.Properties.of(MATERIAL_OREBERRY).sound(SoundType.GRASS).strength(2.0f, 6.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).dynamicShape()), new Item.Properties().tab(ReThinkersConstruct.RETHINKERS_GENERAL_TAB).rarity(Rarity.UNCOMMON));
    public static final RegistryObject<Block> IRON_OREBERRY_BUSH = registerWithItem("iron_oreberry_bush", () -> new OreberryBushBlock(AbstractBlock.Properties.of(MATERIAL_OREBERRY).sound(SoundType.GRASS).strength(2.0f, 6.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).dynamicShape()), new Item.Properties().tab(ReThinkersConstruct.RETHINKERS_GENERAL_TAB).rarity(Rarity.UNCOMMON));
    public static final RegistryObject<Block> GOLD_OREBERRY_BUSH = registerWithItem("gold_oreberry_bush", () -> new OreberryBushBlock(AbstractBlock.Properties.of(MATERIAL_OREBERRY).sound(SoundType.GRASS).strength(2.0f, 6.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).dynamicShape()), new Item.Properties().tab(ReThinkersConstruct.RETHINKERS_GENERAL_TAB).rarity(Rarity.UNCOMMON));
    public static final RegistryObject<Block> COPPER_OREBERRY_BUSH = registerWithItem("copper_oreberry_bush", () -> new OreberryBushBlock(AbstractBlock.Properties.of(MATERIAL_OREBERRY).sound(SoundType.GRASS).strength(2.0f, 6.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).dynamicShape()), new Item.Properties().tab(ReThinkersConstruct.RETHINKERS_GENERAL_TAB).rarity(Rarity.UNCOMMON));
    public static final RegistryObject<Block> TIN_OREBERRY_BUSH = registerWithItem("tin_oreberry_bush", () -> new OreberryBushBlock(AbstractBlock.Properties.of(MATERIAL_OREBERRY).sound(SoundType.GRASS).strength(2.0f, 6.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).dynamicShape()), new Item.Properties().tab(ReThinkersConstruct.RETHINKERS_GENERAL_TAB).rarity(Rarity.UNCOMMON));
    public static final RegistryObject<Block> ALUMINIUM_OREBERRY_BUSH = registerWithItem("aluminium_oreberry_bush", () -> new OreberryBushBlock(AbstractBlock.Properties.of(MATERIAL_OREBERRY).sound(SoundType.GRASS).strength(2.0f, 6.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).dynamicShape()), new Item.Properties().tab(ReThinkersConstruct.RETHINKERS_GENERAL_TAB).rarity(Rarity.UNCOMMON));


    //Metals
    public static final MetalItemObject TIN = Registration.TICON_BLOCKS.registerMetal("tin", GENERIC_METAL_BLOCK, GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);
    public static final MetalItemObject ALUMINIUM = Registration.TICON_BLOCKS.registerMetal("aluminium", GENERIC_METAL_BLOCK, GENERAL_TOOLTIP_BLOCK_ITEM, GENERAL_PROPS);




    public static void register(){

    }

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block){
        return Registration.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> registerWithItem(String name, Supplier<T> block, Item.Properties itemProperties){
        RegistryObject<T> ret = registerNoItem(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(ret.get(), itemProperties));
        return ret;
    }

    protected static AbstractBlock.Properties builder(Material material, @Nullable ToolType toolType, SoundType soundType) {
        return AbstractBlock.Properties.of(material).harvestTool(toolType).sound(soundType);
    }
}
