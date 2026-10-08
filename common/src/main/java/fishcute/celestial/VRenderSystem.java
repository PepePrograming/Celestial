package fishcute.celestial;

import fishcute.celestialmain.api.minecraft.IRenderSystem;
import fishcute.celestialmain.api.minecraft.wrappers.IResourceLocationWrapper;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.biome.Biome;

public class VRenderSystem implements IRenderSystem {
    public void setShaderFogStart(float start) {
    }
    public void setShaderFogEnd(float end) {
    }
    public int getBiomeFogColor(Biome biome) {
        return 0xC0D8FF;
    }
    public void levelFogColor() {
    }
    public void setupNoFog() {
    }
    public void defaultBlendFunc() {
    }

    public void depthMask(boolean enable) {
    }
    public void setShaderColor(float f, float g, float h, float a) {
    }
    public void clearColor(float f, float g, float h, float a) {
    }
    public void unbindVertexBuffer() {
    }
    public void toggleBlend(boolean enable) {
    }
    public void defaultBlendFunction() {
    }
    public void setShaderPositionColor() {
    }
    public void setShaderPositionTex() {
    }

    public void toggleTexture(boolean texture) {
        // Apparently unused in 1.19.4+
    }
    public void blendFuncSeparate() {
    }

    public void setShaderTexture(int i, IResourceLocationWrapper j) {
        VInstances.texture = (Identifier) (Object) j;
    }

    @Override
    public void shadeModel(int i) {
        // Unused in 1.18+
    }
}
