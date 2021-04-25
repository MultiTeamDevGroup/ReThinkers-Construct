package multiteam.rethinkers.main.blocks;

import multiteam.rethinkers.main.Registration;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import slimeknights.tconstruct.shared.TinkerMaterials;

import java.util.function.Supplier;

public class ModBlocks {

    //public static Material TinkerCopperMaterial = TinkerMaterials.copper.get().defaultBlockState().getMaterial();

    public static final RegistryObject<Block> COPPER_CAN_BLOCK = registerNoItem("copper_can", () -> new CopperCanBlock(AbstractBlock.Properties.of(Material.METAL).strength(0,0).harvestLevel(0).sound(SoundType.LANTERN)));
    public static final RegistryObject<TileEntityType<CopperCanTileEntity>> COPPER_CAN_TILE_ENTITY = Registration.TILE_ENTITY_TYPES.register("copper_can", () -> TileEntityType.Builder.of(CopperCanTileEntity::new, COPPER_CAN_BLOCK.get()).build(null));



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
