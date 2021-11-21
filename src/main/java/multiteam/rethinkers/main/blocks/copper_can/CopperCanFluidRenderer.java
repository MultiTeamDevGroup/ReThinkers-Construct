package multiteam.rethinkers.main.blocks.copper_can;

import com.google.common.collect.ImmutableList;
import multiteam.rethinkers.ReThinkersConstruct;
import multiteam.multicore_lib.setup.utilities.MathF;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import multiteam.rethinkers.main.blocks.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import slimeknights.mantle.client.render.FluidRenderer;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class CopperCanFluidRenderer extends TileEntityRenderer<CopperCanTileEntity> {

    public static final ResourceLocation EMPTY_FLUID_TEXTURE = new ResourceLocation(ReThinkersConstruct.MOD_ID,"empty");
    private static final List<RenderType> RENDER_TYPES = IntStream.range(0, 16).mapToObj((p_228882_0_) -> {
        return RenderType.endPortal(p_228882_0_ + 1);
    }).collect(ImmutableList.toImmutableList());

    private static final Random RANDOM = new Random(31100L);

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

        double d0 = copperCanTileEntity.getBlockPos().distSqr(this.renderer.camera.getPosition(), true);
        int i = this.getPasses(d0);
        float f = 0.75F;
        Matrix4f matrix4f = matrixStack.last().pose();
        this.renderCube(copperCanTileEntity, f, 0.15F, matrix4f, buffer.getBuffer(RENDER_TYPES.get(0)));

        matrixStack.popPose();

    }

    public static void register(){
        ClientRegistry.bindTileEntityRenderer(ModBlocks.COPPER_CAN_TILE_ENTITY.get(), CopperCanFluidRenderer::new);
    }

    protected int getPasses(double p_191286_1_) {
        if (p_191286_1_ > 36864.0D) {
            return 1;
        } else if (p_191286_1_ > 25600.0D) {
            return 3;
        } else if (p_191286_1_ > 16384.0D) {
            return 5;
        } else if (p_191286_1_ > 9216.0D) {
            return 7;
        } else if (p_191286_1_ > 4096.0D) {
            return 9;
        } else if (p_191286_1_ > 1024.0D) {
            return 11;
        } else if (p_191286_1_ > 576.0D) {
            return 13;
        } else {
            return p_191286_1_ > 256.0D ? 14 : 15;
        }
    }

    private void renderCube(CopperCanTileEntity p_228883_1_, float p_228883_2_, float p_228883_3_, Matrix4f p_228883_4_, IVertexBuilder p_228883_5_) {
        float f = (RANDOM.nextFloat() * 0.5F + 0.1F) * p_228883_3_;
        float f1 = (RANDOM.nextFloat() * 0.5F + 0.4F) * p_228883_3_;
        float f2 = (RANDOM.nextFloat() * 0.5F + 0.5F) * p_228883_3_;
        this.renderFace(p_228883_1_, p_228883_4_, p_228883_5_, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, f, f1, f2, Direction.SOUTH);
        this.renderFace(p_228883_1_, p_228883_4_, p_228883_5_, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, f, f1, f2, Direction.NORTH);
        this.renderFace(p_228883_1_, p_228883_4_, p_228883_5_, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, f, f1, f2, Direction.EAST);
        this.renderFace(p_228883_1_, p_228883_4_, p_228883_5_, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, f, f1, f2, Direction.WEST);
        this.renderFace(p_228883_1_, p_228883_4_, p_228883_5_, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f, f1, f2, Direction.DOWN);
        this.renderFace(p_228883_1_, p_228883_4_, p_228883_5_, 0.0F, 1.0F, p_228883_2_, p_228883_2_, 1.0F, 1.0F, 0.0F, 0.0F, f, f1, f2, Direction.UP);
    }

    private void renderFace(CopperCanTileEntity p_228884_1_, Matrix4f p_228884_2_, IVertexBuilder p_228884_3_, float p_228884_4_, float p_228884_5_, float p_228884_6_, float p_228884_7_, float p_228884_8_, float p_228884_9_, float p_228884_10_, float p_228884_11_, float p_228884_12_, float p_228884_13_, float p_228884_14_, Direction p_228884_15_) {
            p_228884_3_.vertex(p_228884_2_, p_228884_4_, p_228884_6_, p_228884_8_).color(p_228884_12_, p_228884_13_, p_228884_14_, 1.0F).endVertex();
            p_228884_3_.vertex(p_228884_2_, p_228884_5_, p_228884_6_, p_228884_9_).color(p_228884_12_, p_228884_13_, p_228884_14_, 1.0F).endVertex();
            p_228884_3_.vertex(p_228884_2_, p_228884_5_, p_228884_7_, p_228884_10_).color(p_228884_12_, p_228884_13_, p_228884_14_, 1.0F).endVertex();
            p_228884_3_.vertex(p_228884_2_, p_228884_4_, p_228884_7_, p_228884_11_).color(p_228884_12_, p_228884_13_, p_228884_14_, 1.0F).endVertex();

    }

}
