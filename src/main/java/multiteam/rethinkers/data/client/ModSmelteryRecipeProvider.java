package multiteam.rethinkers.data.client;

import multiteam.rethinkers.main.blocks.ModFluids;
import multiteam.rethinkers.main.items.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.TrueCondition;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.mantle.recipe.EntityIngredient;
import slimeknights.mantle.recipe.ingredient.IngredientWithout;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.common.conditions.ConfigEnabledCondition;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.common.registration.MetalItemObject;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.gadgets.TinkerGadgets;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.entitymelting.EntityMeltingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.fuel.MeltingFuelBuilder;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.molding.MoldingRecipeBuilder;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.shared.block.SlimeType;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.smeltery.block.component.SearedTankBlock;
import slimeknights.tconstruct.smeltery.data.SmelteryCompat;
import slimeknights.tconstruct.smeltery.data.SmelteryRecipeProvider;
import slimeknights.tconstruct.world.TinkerWorld;
import slimeknights.tconstruct.world.block.SlimeGrassBlock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModSmelteryRecipeProvider extends SmelteryRecipeProvider {

    public ModSmelteryRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        this.addBaseRecipes(consumer);
        this.addMeltingRecipes(consumer);
        this.addCastingRecipes(consumer);
        this.addAlloyRecipes(consumer);
        this.addEntityMeltingRecipes(consumer);
    }

    private void addBaseRecipes(Consumer<IFinishedRecipe> consumer) {
    }

    private void addCastingRecipes(Consumer<IFinishedRecipe> consumer) {
    }

    private void addMeltingRecipes(Consumer<IFinishedRecipe> consumer) {
        String folder = "smeltery/melting/";
        String metalFolder = folder + "metal/";
        this.addMetalMelting(consumer, TinkerFluids.moltenAluminum.get(), "aluminium", false, metalFolder, false);
        MeltingRecipeBuilder.melting(Ingredient.of(ModItems.ESSENCE_BERRY.get()), ModFluids.LIQUID_ESSENCE.get(), 16, 1.5F).build(consumer, location(folder + "oreberry/essence"));
        MeltingRecipeBuilder.melting(Ingredient.of(ModItems.IRON_OREBERRY.get()), TinkerFluids.moltenIron.get(), 16, 1.5F).build(consumer, location(folder + "oreberry/iron"));
        MeltingRecipeBuilder.melting(Ingredient.of(ModItems.GOLD_OREBERRY.get()), TinkerFluids.moltenGold.get(), 16, 1.5F).build(consumer, location(folder + "oreberry/gold"));
        MeltingRecipeBuilder.melting(Ingredient.of(ModItems.COPPER_OREBERRY.get()), TinkerFluids.moltenCopper.get(), 16, 1.5F).build(consumer, location(folder + "oreberry/copper"));
        MeltingRecipeBuilder.melting(Ingredient.of(ModItems.TIN_OREBERRY.get()), TinkerFluids.moltenTin.get(), 16, 1.5F).build(consumer, location(folder + "oreberry/tin"));
        MeltingRecipeBuilder.melting(Ingredient.of(ModItems.ALUMINIUM_OREBERRY.get()), TinkerFluids.moltenAluminum.get(), 16, 1.5F).build(consumer, location(folder + "oreberry/aluminium"));
        this.addMetalMelting(consumer, ModFluids.MOLTEN_ALUMITE.get(), "alumite", false, metalFolder, false);

    }

    private void addAlloyRecipes(Consumer<IFinishedRecipe> consumer) {

    }

    private void addEntityMeltingRecipes(Consumer<IFinishedRecipe> consumer) {

    }

    private void addSearedStonecutter(@Nonnull Consumer<IFinishedRecipe> consumer, IItemProvider output, String folder) {

    }

    private static void addMetalBase(Consumer<IFinishedRecipe> consumer, Fluid fluid, int amount, boolean isOre, String tagName, float factor, String recipePath, boolean isOptional) {
        Consumer<IFinishedRecipe> wrapped = isOptional ? withCondition(consumer, new ICondition[]{tagCondition(tagName)}) : consumer;
        MeltingRecipeBuilder builder = MeltingRecipeBuilder.melting(Ingredient.of(getTag("forge", tagName)), fluid, amount, factor);
        if (isOre) {
            builder.setOre();
        }

        builder.build(wrapped, location(recipePath));
    }

    private void addMetalMelting(Consumer<IFinishedRecipe> consumer, Fluid fluid, String name, boolean hasOre, String folder, boolean isOptional) {
        String prefix = folder + "/" + name + "/";
        addMetalBase(consumer, fluid, 1296, false, "storage_blocks/" + name, 3.0F, prefix + "block", isOptional);
        addMetalBase(consumer, fluid, 144, false, "ingots/" + name, 1.0F, prefix + "ingot", isOptional);
        addMetalBase(consumer, fluid, 16, false, "nuggets/" + name, 0.33333334F, prefix + "nugget", isOptional);
        if (hasOre) {
            addMetalBase(consumer, fluid, 144, true, "ores/" + name, 1.5F, prefix + "ore", isOptional);
        }

        addMetalBase(consumer, fluid, 144, false, "dusts/" + name, 0.75F, prefix + "dust", true);
        addMetalBase(consumer, fluid, 144, false, "plates/" + name, 1.0F, prefix + "plates", true);
        addMetalBase(consumer, fluid, 576, false, "gears/" + name, 2.0F, prefix + "gear", true);
        addMetalBase(consumer, fluid, 48, false, "coins/" + name, 0.6666667F, prefix + "coin", true);
        addMetalBase(consumer, fluid, 72, false, "rods/" + name, 0.2F, prefix + "rod", true);
        addMetalBase(consumer, fluid, 144, false, "sheetmetals/" + name, 1.0F, prefix + "sheetmetal", true);
    }

    private int getTemperature(Supplier<? extends Fluid> supplier) {
        return ((Fluid)supplier.get()).getAttributes().getTemperature() - 300;
    }

    private void addBlockCastingRecipe(Consumer<IFinishedRecipe> consumer, Supplier<? extends Fluid> fluid, int amount, IItemProvider block, String location) {
        ItemCastingRecipeBuilder.basinRecipe(block).setFluidAndTime(new FluidStack((Fluid)fluid.get(), amount)).build(consumer, location(location));
    }

    private void addBlockCastingRecipe(Consumer<IFinishedRecipe> consumer, ITag<Fluid> fluid, int temperature, int amount, IItemProvider block, String location) {
        ItemCastingRecipeBuilder.basinRecipe(block).setFluidAndTime(temperature, fluid, amount).build(consumer, location(location));
    }

    private static void addSearedCastingRecipe(Consumer<IFinishedRecipe> consumer, IItemProvider block, Ingredient cast, String location) {
        addSearedCastingRecipe(consumer, block, cast, 500, location);
    }

    private static void addSearedSlabCastingRecipe(Consumer<IFinishedRecipe> consumer, IItemProvider block, Ingredient cast, String location) {
        addSearedCastingRecipe(consumer, block, cast, 250, location);
    }

    private static void addSearedCastingRecipe(Consumer<IFinishedRecipe> consumer, IItemProvider block, Ingredient cast, int amount, String location) {
        ItemCastingRecipeBuilder.basinRecipe(block).setFluidAndTime(new FluidStack(TinkerFluids.moltenClay.get(), amount)).setCast(cast, true).build(consumer, location(location));
    }

    private void addCastingWithCastRecipe(Consumer<IFinishedRecipe> consumer, Supplier<? extends Fluid> fluid, int amount, CastItemObject cast, IItemProvider output, String location) {
        FluidStack fluidStack = new FluidStack((Fluid)fluid.get(), amount);
        ItemCastingRecipeBuilder.tableRecipe(output).setFluidAndTime(fluidStack).setCast(cast.getMultiUseTag(), false).build(consumer, location(location + "_gold_cast"));
        ItemCastingRecipeBuilder.tableRecipe(output).setFluidAndTime(fluidStack).setCast(cast.getSingleUseTag(), true).build(consumer, location(location + "_sand_cast"));
    }

    private void addIngotCastingRecipe(Consumer<IFinishedRecipe> consumer, Supplier<? extends Fluid> fluid, int amount, IItemProvider ingot, String location) {
        this.addCastingWithCastRecipe(consumer, fluid, amount, TinkerSmeltery.ingotCast, ingot, location);
    }

    private void addIngotCastingRecipe(Consumer<IFinishedRecipe> consumer, Supplier<? extends Fluid> fluid, IItemProvider ingot, String location) {
        this.addIngotCastingRecipe(consumer, fluid, 144, ingot, location);
    }

    private void addGemCastingRecipe(Consumer<IFinishedRecipe> consumer, Supplier<? extends Fluid> fluid, IItemProvider gem, String location) {
        this.addCastingWithCastRecipe(consumer, fluid, 250, TinkerSmeltery.gemCast, gem, location);
    }

    private void addNuggetCastingRecipe(Consumer<IFinishedRecipe> consumer, Supplier<? extends Fluid> fluid, IItemProvider nugget, String location) {
        this.addCastingWithCastRecipe(consumer, fluid, 16, TinkerSmeltery.nuggetCast, nugget, location);
    }

    private void addSlimeMeltingRecipe(Consumer<IFinishedRecipe> consumer, Supplier<? extends Fluid> fluidSupplier, SlimeType type, ITag<Item> tag, String folder) {
        String slimeFolder = folder + type.getSerializedName() + "/";
        MeltingRecipeBuilder.melting(Ingredient.of(tag), (Fluid)fluidSupplier.get(), 250, 1.0F).build(consumer, location(slimeFolder + "ball"));
        IItemProvider item = (IItemProvider)TinkerWorld.congealedSlime.get(type);
        MeltingRecipeBuilder.melting(Ingredient.of(new IItemProvider[]{item}), (Fluid)fluidSupplier.get(), 1000, 2.0F).build(consumer, location(slimeFolder + "congealed"));
        item = (IItemProvider)TinkerWorld.slime.get(type);
        MeltingRecipeBuilder.melting(Ingredient.of(new IItemProvider[]{item}), (Fluid)fluidSupplier.get(), 2250, 3.0F).build(consumer, location(slimeFolder + "block"));
    }

    private void addSlimeCastingRecipe(Consumer<IFinishedRecipe> consumer, ITag<Fluid> fluid, int temperature, SlimeType slimeType, String folder) {
        String colorFolder = folder + slimeType.getSerializedName() + "/";
        this.addBlockCastingRecipe(consumer, fluid, temperature, 1000, (IItemProvider)TinkerWorld.congealedSlime.get(slimeType), colorFolder + "congealed");
        ItemCastingRecipeBuilder.basinRecipe((IItemProvider)TinkerWorld.slime.get(slimeType)).setFluidAndTime(temperature, fluid, 1250).setCast((IItemProvider)TinkerWorld.congealedSlime.get(slimeType), true).build(consumer, location(colorFolder + "block"));
        ItemCastingRecipeBuilder.tableRecipe((IItemProvider)TinkerCommons.slimeball.get(slimeType)).setFluidAndTime(temperature, fluid, 250).build(consumer, location(colorFolder + "slimeball"));
    }

    private void addMetalCastingRecipe(Consumer<IFinishedRecipe> consumer, Supplier<? extends Fluid> fluid, @Nullable IItemProvider block, @Nullable IItemProvider ingot, @Nullable IItemProvider nugget, String folder, String metal) {
        String metalFolder = folder + metal + "/";
        if (block != null) {
            this.addBlockCastingRecipe(consumer, fluid, 1296, block, metalFolder + "block");
        }

        if (ingot != null) {
            this.addIngotCastingRecipe(consumer, fluid, ingot, metalFolder + "ingot");
        }

        if (nugget != null) {
            this.addNuggetCastingRecipe(consumer, fluid, nugget, metalFolder + "nugget");
        }

        this.addOptionalCastingWithCast(consumer, (Fluid)fluid.get(), 144, TinkerSmeltery.plateCast, "plates", "plate", metal, folder);
        this.addOptionalCastingWithCast(consumer, (Fluid)fluid.get(), 576, TinkerSmeltery.gearCast, "gears", "gear", metal, folder);
        this.addOptionalCastingWithCast(consumer, (Fluid)fluid.get(), 48, TinkerSmeltery.coinCast, "coins", "coin", metal, folder);
        this.addOptionalCastingWithCast(consumer, (Fluid)fluid.get(), 72, TinkerSmeltery.rodCast, "rods", "rod", metal, folder);
    }

    private void addMetalCastingRecipe(Consumer<IFinishedRecipe> consumer, Supplier<? extends Fluid> fluid, MetalItemObject metal, String folder, String name) {
        this.addMetalCastingRecipe(consumer, fluid, (IItemProvider)metal.get(), metal.getIngot(), metal.getNugget(), folder, name);
    }

    private void addOptionalCastingWithCast(Consumer<IFinishedRecipe> consumer, Fluid fluid, int amount, CastItemObject cast, String tagPrefix, String recipeName, String name, String folder) {
        String tagName = tagPrefix + "/" + name;
        ITag<Item> tag = getTag("forge", tagName);
        Consumer<IFinishedRecipe> wrapped = withCondition(consumer, new ICondition[]{tagCondition(tagName)});
        ItemCastingRecipeBuilder.tableRecipe(tag).setFluidAndTime(new FluidStack(fluid, amount)).setCast(cast.getMultiUseTag(), false).build(wrapped, location(folder + name + "/" + recipeName + "_gold_cast"));
        ItemCastingRecipeBuilder.tableRecipe(tag).setFluidAndTime(new FluidStack(fluid, amount)).setCast(cast.getSingleUseTag(), true).build(wrapped, location(folder + name + "/" + recipeName + "_sand_cast"));
    }

    private void addMetalOptionalCasting(Consumer<IFinishedRecipe> consumer, Fluid fluid, String name, String folder) {
        this.addOptionalCastingWithCast(consumer, fluid, 16, TinkerSmeltery.nuggetCast, "nuggets", "nugget", name, folder);
        this.addOptionalCastingWithCast(consumer, fluid, 144, TinkerSmeltery.ingotCast, "ingots", "ingot", name, folder);
        this.addOptionalCastingWithCast(consumer, fluid, 144, TinkerSmeltery.plateCast, "plates", "plate", name, folder);
        this.addOptionalCastingWithCast(consumer, fluid, 576, TinkerSmeltery.gearCast, "gears", "gear", name, folder);
        this.addOptionalCastingWithCast(consumer, fluid, 48, TinkerSmeltery.coinCast, "coins", "coin", name, folder);
        this.addOptionalCastingWithCast(consumer, fluid, 72, TinkerSmeltery.rodCast, "rods", "rod", name, folder);
        ITag<Item> block = getTag("forge", "storage_blocks/" + name);
        Consumer<IFinishedRecipe> wrapped = withCondition(consumer, new ICondition[]{tagCondition("storage_blocks/" + name)});
        ItemCastingRecipeBuilder.basinRecipe(block).setFluidAndTime(new FluidStack(fluid, 1296)).build(wrapped, location(folder + name + "/block"));
    }

    private void addCastCastingRecipe(Consumer<IFinishedRecipe> consumer, ITag.INamedTag<Item> input, CastItemObject cast, String folder) {
        String path = input.getName().getPath();
        ItemCastingRecipeBuilder.tableRecipe(cast).setFluidAndTime(new FluidStack(TinkerFluids.moltenGold.get(), 144)).setCast(input, true).setSwitchSlots().build(consumer, location(folder + "casts/" + path));
        MoldingRecipeBuilder.moldingTable(cast.getSand()).setMaterial(TinkerSmeltery.blankCast.getSand()).setPattern(input, false).build(consumer, location(folder + "sand_casts/" + path));
        MoldingRecipeBuilder.moldingTable(cast.getRedSand()).setMaterial(TinkerSmeltery.blankCast.getRedSand()).setPattern(input, false).build(consumer, location(folder + "red_sand_casts/" + path));
    }
}
