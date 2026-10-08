package fishcute.celestial.mixin.api;

import fishcute.celestial.Vector;
import fishcute.celestialmain.api.minecraft.IMcVector;
import fishcute.celestialmain.api.minecraft.wrappers.ILevelWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ClientLevel.class)
public class ClientLevelMixin implements ILevelWrapper {
    @Override
    public IMcVector celestial$getSkyColor(float tickDelta) {
        var level = (ClientLevel)(Object) this;
        int color = level.environmentAttributes().getValue(EnvironmentAttributes.SKY_COLOR, Minecraft.getInstance().gameRenderer.getMainCamera().position());
        return Vector.fromVec(rgb(color));
    }

    @Override
    public float[] celestial$getSunriseColor(float tickDelta) {
        return null;
    }

    @Override
    public float celestial$getTimeOfDay(float tickDelta) {
        var self = (ClientLevel)(Object) this;
        return (float) Math.floorMod(self.getDayTime(), 24000L) / 24000.0F;
    }

    @Override
    public float celestial$getSunAngle(float tickDelta) {
        var self = (ClientLevel)(Object) this;
        return self.environmentAttributes().getValue(EnvironmentAttributes.SUN_ANGLE, Minecraft.getInstance().gameRenderer.getMainCamera().position());
    }

    @Override
    public double celestial$getHorizonHeight() {
        var self = (ClientLevel)(Object) this;
        return self.getLevelData().getHorizonHeight(self);
    }

    @Override
    public boolean celestial$hasGround() {
        var self = (ClientLevel)(Object) this;
        return !self.dimensionType().hasCeiling();
    }

    private static Vec3 rgb(int color) {
        return new Vec3((color >> 16 & 255) / 255.0, (color >> 8 & 255) / 255.0, (color & 255) / 255.0);
    }
}
