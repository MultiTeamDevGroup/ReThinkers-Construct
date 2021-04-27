package multiteam.rethinkers.main.blocks;

import multiteam.rethinkers.ReThinkersConstruct;
import multiteam.rethinkers.main.Registration;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import slimeknights.tconstruct.shared.TinkerMaterials;

import java.util.function.Supplier;

public class ModBlocks {

    public static final Material MATERIAL_OREBERRY = (new Material(MaterialColor.STONE, false, false, false, true, false, false, Material.CACTUS.getPushReaction()));

    //public static Material TinkerCopperMaterial = TinkerMaterials.copper.get().defaultBlockState().getMaterial();

    public static final RegistryObject<Block> COPPER_CAN_BLOCK = registerNoItem("copper_can", () -> new CopperCanBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_BROWN).strength(0,0).harvestLevel(0).sound(SoundType.LANTERN)));
    public static final RegistryObject<TileEntityType<CopperCanTileEntity>> COPPER_CAN_TILE_ENTITY = Registration.TILE_ENTITY_TYPES.register("copper_can", () -> TileEntityType.Builder.of(CopperCanTileEntity::new, COPPER_CAN_BLOCK.get()).build(null));
                                                                                                                                                                                  //Hardness and resistance
    public static final RegistryObject<Block> SILKY_CLOTH_BLOCK = registerWithItem("silky_cloth_block", () -> new Block(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.GOLD).strength(0.8f, 0.8f).sound(SoundType.WOOL)), new Item.Properties().tab(ReThinkersConstruct.RETHINKERS_GENERAL_TAB));


    public static final RegistryObject<Block> ESSENCE_BERRY_BUSH = registerWithItem("essence_berry_bush", () -> new OreberryBushBlock(AbstractBlock.Properties.of(MATERIAL_OREBERRY).sound(SoundType.GRASS).strength(2.0f, 6.0f).dynamicShape()), new Item.Properties().tab(ReThinkersConstruct.RETHINKERS_GENERAL_TAB).rarity(Rarity.UNCOMMON));

    public static void register(){}

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block){
        return Registration.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> registerWithItem(String name, Supplier<T> block, Item.Properties itemProperties){
        RegistryObject<T> ret = registerNoItem(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(ret.get(), itemProperties));
        return ret;
    }
}
