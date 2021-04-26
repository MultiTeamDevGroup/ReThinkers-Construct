package multiteam.rethinkers.data.client;

import multiteam.rethinkers.ReThinkersConstruct;
import multiteam.rethinkers.main.blocks.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider  extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ReThinkersConstruct.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.SILKY_CLOTH_BLOCK.get());
    }
}
