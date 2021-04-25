package multiteam.rethinkers.main.blocks;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import multiteam.rethinkers.ReThinkersConstruct;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class CopperCanFluidRenderer extends TileEntityRenderer<CopperCanTileEntity> {

    public static final ResourceLocation EMPTY_FLUID_TEXTURE = new ResourceLocation(ReThinkersConstruct.MOD_ID,"empty");

    public CopperCanFluidRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(CopperCanTileEntity copperCanTileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int combinedLight, int combinedOverlay) {
        Fluid fluidToRender = CopperCanTileEntity.getContainedFluid(copperCanTileEntity.getTileData());
        TextureAtlasSprite fluid_sprite;
        if(fluidToRender == Fluids.EMPTY){
            fluid_sprite = Minecraft.getInstance().getTextureAtlas(AtlasTexture.LOCATION_BLOCKS).apply(EMPTY_FLUID_TEXTURE);
        }else{
            ResourceLocation stillLocation = fluidToRender.getAttributes().getStillTexture();
            fluid_sprite = Minecraft.getInstance().getTextureAtlas(AtlasTexture.LOCATION_BLOCKS).apply(stillLocation);
        }

        IVertexBuilder builder = iRenderTypeBuffer.getBuffer(RenderType.translucent());

        matrixStack.pushPose();

        matrixStack.translate(.5, .5, .5);
        matrixStack.mulPose(Quaternion.ONE);
        matrixStack.scale(1, 1, 1);
        matrixStack.translate(-.5, -.5, -.5);

        add(builder, matrixStack, 0, 0.5f, 0, fluid_sprite.getU0(), fluid_sprite.getV0());
        add(builder, matrixStack, 1, 0.5f, 0, fluid_sprite.getU0(), fluid_sprite.getV0());
        add(builder, matrixStack, 1, 0.5f, 1, fluid_sprite.getU0(), fluid_sprite.getV0());
        add(builder, matrixStack, 0, 0.5f, 1, fluid_sprite.getU0(), fluid_sprite.getV0());

        matrixStack.popPose();

    }

    private void add(IVertexBuilder renderer, MatrixStack stack, float x, float y, float z, float u, float v){
        renderer.normal(stack.last().normal(), x, y, z)
                .color(1.0f, 1.0f, 1.0f, 1.0f)
                .uv(u, v)
                .uv2(0, 240)
                .normal(1,0,0)
                .endVertex();
    }

    public static void register(){
        ClientRegistry.bindTileEntityRenderer(ModBlocks.COPPER_CAN_TILE_ENTITY.get(), CopperCanFluidRenderer::new);
    }

}
