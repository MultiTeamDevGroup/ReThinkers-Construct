package multiteam.rethinkers.main.blocks;

import multiteam.rethinkers.ReThinkersConstruct;
import multiteam.rethinkers.main.Registration;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.fluids.FluidIcons;
import slimeknights.tconstruct.library.Util;

public class ModFluids {

    public static final ResourceLocation LIQUID_ESSENCE_STILL = new ResourceLocation(ReThinkersConstruct.MOD_ID, "block/fluid/liquid_essence");

    public static final FluidObject<ForgeFlowingFluid> LIQUID_ESSENCE = Registration.FLUIDS.register("liquid_essence", coolBuilder(LIQUID_ESSENCE_STILL, FluidIcons.LIQUID_FLOWING).color(-9325008).density(1200).viscosity(1200).temperature(336), Material.WATER, 0);
    public static final FluidObject<ForgeFlowingFluid> MOLTEN_ALUMITE = Registration.FLUIDS.register("molten_alumite", moltenBuilder().color(16744419).temperature(1200), Material.LAVA, 12);
    //public static final FluidObject<ForgeFlowingFluid> MOLTEN_TIN = Registration.FLUIDS.register("molten_tin", moltenBuilder().color(9263209).temperature(500), Material.LAVA, 11);


    public static void register(){}

    private static FluidAttributes.Builder coolBuilder(ResourceLocation stillTexture, ResourceLocation flowingTexture) {
        return FluidAttributes.builder(stillTexture, flowingTexture).sound(SoundEvents.BUCKET_FILL, SoundEvents.BUCKET_EMPTY);
    }

    private static FluidAttributes.Builder coolBuilder() {
        return FluidAttributes.builder(FluidIcons.LIQUID_STILL, FluidIcons.LIQUID_FLOWING).sound(SoundEvents.BUCKET_FILL, SoundEvents.BUCKET_EMPTY);
    }

    private static FluidAttributes.Builder hotBuilder(ResourceLocation stillTexture, ResourceLocation flowingTexture) {
        return FluidAttributes.builder(stillTexture, flowingTexture).density(2000).viscosity(10000).temperature(1000).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA);
    }

    private static FluidAttributes.Builder stoneBuilder() {
        return hotBuilder(FluidIcons.STONE_STILL, FluidIcons.STONE_FLOWING);
    }

    private static FluidAttributes.Builder moltenBuilder() {
        return hotBuilder(FluidIcons.MOLTEN_STILL, FluidIcons.MOLTEN_FLOWING);
    }
}
