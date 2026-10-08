package fishcute.celestial;

import com.mojang.blaze3d.vertex.BufferBuilder;
import fishcute.celestialmain.api.minecraft.IMcVector;
import fishcute.celestialmain.api.minecraft.wrappers.IBufferBuilderWrapper;
import fishcute.celestialmain.api.minecraft.wrappers.IResourceLocationWrapper;
import fishcute.celestialmain.api.minecraft.wrappers.IShaderInstanceWrapper;
import fishcute.celestialmain.version.independent.Instances;
import net.minecraft.resources.Identifier;

public class VInstances {
    public static void setInstances() {
        Instances.minecraft = new VMinecraftInstance();
        Instances.renderSystem = new VRenderSystem();

        Instances.bufferBuilderFactory = new Factories.BufferBuilder();
        Instances.shaderInstanceFactory = new Factories.ShaderInstance();
        Instances.resourceLocationFactory = new Factories.ResourceLocation();
        Instances.vectorFactory = new Factories.Vector();

        builder = new fishcute.celestial.BufferBuilder();
    }

    public static fishcute.celestial.BufferBuilder builder;
    public static BufferBuilder bufferBuilder = null;
    public static Identifier texture = null;

    protected interface Factories {
        class BufferBuilder implements IBufferBuilderWrapper.Factory {
            @Override
            public IBufferBuilderWrapper build() {
                return (IBufferBuilderWrapper) VInstances.builder;
            }
        }

        class ShaderInstance implements IShaderInstanceWrapper.Factory {
            @Override
            public IShaderInstanceWrapper build() {
                return new IShaderInstanceWrapper() {
                };
            }
        }

        class ResourceLocation implements IResourceLocationWrapper.Factory {
            @Override
            public IResourceLocationWrapper build(String name) {
                return (IResourceLocationWrapper) (Object) net.minecraft.resources.Identifier.parse(name);
            }
        }

        class Vector implements IMcVector.Factory {

            @Override
            public IMcVector build(float x, float y, float z) {
            return new fishcute.celestial.Vector(x, y, z);
            }
        }
    }
}
