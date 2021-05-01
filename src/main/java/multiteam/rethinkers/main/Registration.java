package multiteam.rethinkers.main;

import multiteam.rethinkers.ReThinkersConstruct;
import multiteam.rethinkers.main.blocks.ModBlocks;
import multiteam.rethinkers.main.blocks.ModFluids;
import multiteam.rethinkers.main.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.tconstruct.common.registration.BlockDeferredRegisterExtension;

public class Registration {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ReThinkersConstruct.MOD_ID);
    public static final BlockDeferredRegisterExtension TICON_BLOCKS = new BlockDeferredRegisterExtension(ReThinkersConstruct.MOD_ID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ReThinkersConstruct.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ReThinkersConstruct.MOD_ID);
    public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(ReThinkersConstruct.MOD_ID);

    public static void register(){
        IEventBus modeEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCKS.register(modeEventBus);
        TICON_BLOCKS.register(modeEventBus);
        TILE_ENTITY_TYPES.register(modeEventBus);
        ITEMS.register(modeEventBus);
        FLUIDS.register(modeEventBus);

        ModBlocks.register();
        ModItems.register();
        ModFluids.register();
    }
}
