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
        //Utility
        withExistingParent("silky_cloth_block", modLoc("block/silky_cloth_block"));

        //Oreberrys
        withExistingParent("essence_berry_bush", modLoc("block/essence_berrybush_stage3"));
        withExistingParent("iron_oreberry_bush", modLoc("block/iron_berrybush_stage3"));
        withExistingParent("gold_oreberry_bush", modLoc("block/gold_berrybush_stage3"));
        withExistingParent("tin_oreberry_bush", modLoc("block/tin_berrybush_stage3"));
        withExistingParent("copper_oreberry_bush", modLoc("block/copper_berrybush_stage3"));
        withExistingParent("aluminium_oreberry_bush", modLoc("block/aluminium_berrybush_stage3"));



    //Items
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        //Regular Items
        builder(itemGenerated, "essence_berry");
        builder(itemGenerated, "iron_oreberry");
        builder(itemGenerated, "gold_oreberry");
        builder(itemGenerated, "copper_oreberry");
        builder(itemGenerated, "tin_oreberry");
        builder(itemGenerated, "aluminium_oreberry");

    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/"+name);
    }

}