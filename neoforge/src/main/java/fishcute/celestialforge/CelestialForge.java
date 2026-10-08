package fishcute.celestialforge;

import com.mojang.blaze3d.platform.InputConstants;
import fishcute.celestial.Celestial;
import fishcute.celestialmain.sky.CelestialSky;
import fishcute.celestialmain.util.ClientTick;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(Celestial.MOD_ID)
public class CelestialForge {
    public static KeyMapping reloadSky = new KeyMapping(
            "key.reload_sky",
            InputConstants.KEY_F10,
            KeyMapping.Category.MISC
    );

    public CelestialForge(IEventBus modEventBus, ModContainer modContainer) {
        Celestial.init();
        modEventBus.register(CelestialForge.class);
    }

    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(reloadSky);
    }

    @EventBusSubscriber(modid = Celestial.MOD_ID, value = Dist.CLIENT)
    static class ClientEventHandler {
        @SubscribeEvent
        public static void clientTickEvent(ClientTickEvent.Post event) {
            while (reloadSky.consumeClick()) {
                ClientTick.onReloadKey();
            }
        }
    }
}
