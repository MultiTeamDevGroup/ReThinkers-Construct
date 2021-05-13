package multiteam.rethinkers.main.world;

import com.mojang.serialization.Codec;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockPileFeature;
import net.minecraft.world.gen.feature.BlockStateProvidingFeatureConfig;

public class WorldGenOreberry extends BlockPileFeature {

    //I have 0 idea how to make oreberries generate

    public WorldGenOreberry(Codec<BlockStateProvidingFeatureConfig> codec) {
        super(codec);
    }

}
