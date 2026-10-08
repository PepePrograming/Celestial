package net.minecraft.client.renderer.rendertype;

import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;

public final class CelestialRenderTypeFactory {
    private CelestialRenderTypeFactory() {
    }

    public static RenderType textured(Identifier texture) {
        return RenderType.create(
                "celestial_textured",
                RenderSetup.builder(RenderPipelines.GUI_TEXTURED)
                        .withTexture("Sampler0", texture)
                        .createRenderSetup()
        );
    }
}
