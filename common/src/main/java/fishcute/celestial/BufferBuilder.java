package fishcute.celestial;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import fishcute.celestialmain.api.minecraft.wrappers.IBufferBuilderWrapper;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.rendertype.CelestialRenderTypeFactory;
import org.joml.Matrix4f;

import static fishcute.celestial.VInstances.bufferBuilder;

public class BufferBuilder implements IBufferBuilderWrapper {
    private enum Kind { TRIANGLE_FAN, COLOR, TEXTURED }

    private Kind kind;

    @Override
    public void celestial$beginTriangleFan() {
        kind = Kind.TRIANGLE_FAN;
        bufferBuilder = Tesselator.getInstance().begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
    }

    @Override
    public void celestial$beginObject() {
        kind = Kind.TEXTURED;
        bufferBuilder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
    }

    @Override
    public void celestial$beginColorObject() {
        kind = Kind.COLOR;
        bufferBuilder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
    }

    @Override
    public void celestial$vertex(Object matrix4f, float x, float y, float z, float r, float g, float b, float a) {
        bufferBuilder.addVertex((Matrix4f) matrix4f, x, y, z).setColor(r, g, b, a);
    }

    @Override
    public void celestial$vertexUv(Object matrix4f, float x, float y, float z, float u, float v, float r, float g, float b, float a) {
        bufferBuilder.addVertex((Matrix4f) matrix4f, x, y, z).setUv(u, v).setColor(r, g, b, a);
    }

    @Override
    public void celestial$upload() {
        if (bufferBuilder != null) {
            var mesh = bufferBuilder.buildOrThrow();
            if (kind == Kind.TRIANGLE_FAN) {
                RenderTypes.debugTriangleFan().draw(mesh);
            } else if (kind == Kind.COLOR) {
                RenderTypes.debugFilledBox().draw(mesh);
            } else if (VInstances.texture != null) {
                CelestialRenderTypeFactory.textured(VInstances.texture).draw(mesh);
            } else {
                mesh.close();
            }
            bufferBuilder = null;
        }
    }
}
