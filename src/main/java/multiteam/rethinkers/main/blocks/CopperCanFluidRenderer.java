package multiteam.rethinkers.main.blocks;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import multiteam.rethinkers.ReThinkersConstruct;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import slimeknights.mantle.client.render.FluidRenderer;

public class CopperCanFluidRenderer extends TileEntityRenderer<CopperCanTileEntity> {

    public static final ResourceLocation EMPTY_FLUID_TEXTURE = new ResourceLocation(ReThinkersConstruct.MOD_ID,"empty");

    public CopperCanFluidRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(CopperCanTileEntity copperCanTileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        Fluid fluidToRender = CopperCanTileEntity.getContainedFluid(copperCanTileEntity.getTileData());
        TextureAtlasSprite fluid_sprite;
        if(fluidToRender == Fluids.EMPTY){
            fluid_sprite = Minecraft.getInstance().getTextureAtlas(AtlasTexture.LOCATION_BLOCKS).apply(EMPTY_FLUID_TEXTURE);
        }else{
            ResourceLocation stillLocation = fluidToRender.getAttributes().getStillTexture();
            fluid_sprite = Minecraft.getInstance().getTextureAtlas(AtlasTexture.LOCATION_BLOCKS).apply(stillLocation);
        }

        IVertexBuilder builder = buffer.getBuffer(FluidRenderer.RENDER_TYPE);

        matrixStack.pushPose();

        //This is how you get blocklight from the world on clientside :D how nice
        //Minecraft.getInstance().level.getBlockState(copperCanTileEntity.getBlockPos()).getLightValue(Minecraft.getInstance().level,copperCanTileEntity.getBlockPos())

        FluidRenderer.putTexturedQuad(builder, matrixStack.last().pose(), fluid_sprite, new Vector3f(BlockToFloatScale(4.0f), BlockToFloatScale(10.0f), BlockToFloatScale(4.0f)), new Vector3f(BlockToFloatScale(12.0f), BlockToFloatScale(10.0f), BlockToFloatScale(12.0f)), Direction.UP , fluidToRender.getAttributes().getColor() , 150, 0, false);

        matrixStack.popPose();
        //TankModel.BakedModel<?> model =
        //RenderUtils.renderFluidTank(matrixStack, iRenderTypeBuffer, fluidToRender, FluidTankAnimated tank, int light, float partialTicks, boolean flipGas)
        //BakedModel<?> model = (TankModel.BakedModel) ModelHelper.getBakedModel(copperCanTileEntity.getBlockState(), TankModel.BakedModel.class);
        //RenderUtils.renderFluidTank(matrixStack, buffer, model.getFluid(), ((CopperCanTileEntity)copperCanTileEntity).getTank(), combinedLightIn, partialTicks, true);

    }

    //Dunno why but the MathF class of MultiCoreLib doesnt work, so until it works:
    public float BlockToFloatScale(float value){
        return rescaleValues(0.0f, 16.0f, 0.0f, 1.0f, value);
    }

    public float rescaleValues(float minFrom, float maxFrom, float minTo, float maxTo, float valueToScale){
        float OldRange = (maxFrom - minFrom);
        float NewRange = (maxTo - minTo);

        return (((valueToScale - minFrom) * NewRange) / OldRange) + minTo;
    }

    public static void register(){
        ClientRegistry.bindTileEntityRenderer(ModBlocks.COPPER_CAN_TILE_ENTITY.get(), CopperCanFluidRenderer::new);
    }

}
