package multiteam.rethinkers.data;

import multiteam.rethinkers.ReThinkersConstruct;
import multiteam.rethinkers.data.client.ModBlockStateProvider;
import multiteam.rethinkers.data.client.ModBlockTagsProvider;
import multiteam.rethinkers.data.client.ModItemModelProvider;
import multiteam.rethinkers.data.client.ModItemTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import slimeknights.tconstruct.common.data.tags.TConstructBlockTagsProvider;

@Mod.EventBusSubscriber(modid = ReThinkersConstruct.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    private DataGenerators() {}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        ModBlockTagsProvider blockTags = new ModBlockTagsProvider(gen, existingFileHelper);

        gen.addProvider(new ModBlockStateProvider(gen, existingFileHelper));
        gen.addProvider(new ModItemModelProvider(gen, existingFileHelper));
        gen.addProvider(blockTags);
        gen.addProvider(new ModItemTagsProvider(gen, blockTags, existingFileHelper));
    }
}
