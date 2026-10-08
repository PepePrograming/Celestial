package fishcute.celestialfabric;


import com.mojang.blaze3d.platform.InputConstants;
import fishcute.celestial.Celestial;
import fishcute.celestialmain.util.ClientTick;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;

public class CelestialFabric implements ClientModInitializer {
    public static KeyMapping reloadSky;
    @Override
    public void onInitializeClient() {
        Celestial.init();

        reloadSky = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.reload_sky",
                InputConstants.KEY_F10,
                KeyMapping.Category.MISC
        ));

        ClientTickEvents.END_CLIENT_TICK.register((endTick -> {
            while (reloadSky.consumeClick()) {
                ClientTick.onReloadKey();
            }
        }));
    }
}
