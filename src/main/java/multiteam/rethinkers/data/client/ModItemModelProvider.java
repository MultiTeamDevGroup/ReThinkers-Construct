package multiteam.rethinkers.data.client;

import multiteam.rethinkers.ReThinkersConstruct;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ReThinkersConstruct.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //Blockitems
        //this might work as replacement for the name: ModBlocks.SILKY_CLOTH_BLOCK.getId().toString()
        withExistingParent("silky_cloth_block", modLoc("block/silky_cloth_block"));

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        //Regular Items
        //builder(itemGenerated, "angel_wings");
    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/"+name);
    }
}