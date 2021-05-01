package multiteam.rethinkers.main.blocks.copper_can;

import multiteam.rethinkers.ReThinkersConstruct;
import multiteam.multicore_lib.setup.utilities.MathF;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import multiteam.rethinkers.main.blocks.ModBlocks;
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

        FluidRenderer.putTexturedQuad(builder, matrixStack.last().pose(), fluid_sprite, MathF.BlockToFloatScaleVector3f(4.0f, 10.0f, 4.0f), MathF.BlockToFloatScaleVector3f(12.0f, 10.0f, 12.0f), Direction.UP , fluidToRender.getAttributes().getColor() , 150, 0, false);

        matrixStack.popPose();

    }

    public static void register(){
        ClientRegistry.bindTileEntityRenderer(ModBlocks.COPPER_CAN_TILE_ENTITY.get(), CopperCanFluidRenderer::new);
    }

}
