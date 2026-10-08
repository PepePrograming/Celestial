package fishcute.celestial.mixin;

import fishcute.celestialmain.api.minecraft.wrappers.ICameraWrapper;
import fishcute.celestialmain.api.minecraft.wrappers.ILevelWrapper;
import fishcute.celestialmain.api.minecraft.wrappers.IPoseStackWrapper;
import fishcute.celestialmain.api.minecraft.wrappers.IShaderInstanceWrapper;
import fishcute.celestialmain.api.minecraft.wrappers.IVertexBufferWrapper;
import fishcute.celestialmain.sky.CelestialSky;
import fishcute.celestialmain.version.independent.VersionLevelRenderer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.SkyRenderer;
import net.minecraft.world.level.MoonPhase;
import com.mojang.blaze3d.vertex.PoseStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SkyRenderer.class)
public class SkyRendererMixin {
    private static final IVertexBufferWrapper NOOP_BUFFER = new IVertexBufferWrapper() {
        @Override
        public void celestial$bind() {
        }

        @Override
        public void celestial$drawWithShader(Object matrix, Object projectionMatrix, IShaderInstanceWrapper shader) {
        }
    };

    @Inject(method = "renderSunMoonAndStars", at = @At("HEAD"), cancellable = true)
    private void renderCustomSky(PoseStack poseStack, float starBrightness, float skyAngle, float moonAngle,
                                 MoonPhase moonPhase, float rainStrength, float timeOfDay, CallbackInfo info) {
        if (!CelestialSky.doesDimensionHaveCustomSky()) {
            return;
        }

        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel level = minecraft.level;
        Camera camera = minecraft.gameRenderer.getMainCamera();
        if (level == null || camera == null) {
            return;
        }

        info.cancel();
        float tickDelta = minecraft.getDeltaTracker().getGameTimeDeltaPartialTick(false);
        VersionLevelRenderer.renderLevel(
                null,
                (IPoseStackWrapper) (Object) poseStack,
                NOOP_BUFFER,
                NOOP_BUFFER,
                (ICameraWrapper) (Object) camera,
                (ILevelWrapper) (Object) level,
                tickDelta,
                null
        );
    }
}
