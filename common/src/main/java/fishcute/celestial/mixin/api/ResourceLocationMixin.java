package fishcute.celestial.mixin.api;

import fishcute.celestialmain.api.minecraft.wrappers.IResourceLocationWrapper;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Identifier.class)
public class ResourceLocationMixin implements IResourceLocationWrapper {
}
