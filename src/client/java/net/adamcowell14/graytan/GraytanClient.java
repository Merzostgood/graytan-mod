package net.adamcowell14.graytan;

//import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.adamcowell14.graytan.config.ModConfig;
import net.adamcowell14.graytan.config.ModPoint;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import net.adamcowell14.graytan.util.KeybindRegistry;

//import net.adamcowell14.graytan.hud.RenderOverlay;
import net.adamcowell14.graytan.config.KeyBind;

public class GraytanClient implements ClientModInitializer {
    public static final String MOD_ID = "graytan";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final KeyBind DEFAULT_KEYBIND = KeyBind.of(ModifierKeyCode.unknown());
    public static final MinecraftClient MC = MinecraftClient.getInstance();
    public static ModConfig CONFIG;

    private static final KeyBinding ENABLED_BIND = new KeyBinding(
            "Enable anti teleport",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_O,
            "Graytan mod"
    );

    @Override
    public void onInitializeClient() {
        KeybindRegistry.registerType();
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        KeybindRegistry.registerEvent();
        KeyBindingHelper.registerKeyBinding(ENABLED_BIND);
//        HudRenderCallback.EVENT.register(RenderOverlay::RenderGameOverlayEvent);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            binds();
        });
    }

    private void binds () {
//        while (ENABLED_BIND.wasPressed()) {
//            assert MC.player != null;
//
//            if (CONFIG.anti_tp) {
//                CONFIG.anti_tp = false;
//                MC.player.sendMessage(Text.of("false"), true);
//                MC.inGameHud.setSubtitle(Text.of("mod enabled"));
//            } else {
//                CONFIG.anti_tp = true;
//                MC.player.sendMessage(Text.of("true"), true);
//                MC.inGameHud.setSubtitle(Text.of("mod disabled"));
//            }
//        }
    }

    public static void test(ModPoint point) {
        assert MC.player != null;

        MC.player.sendMessage(Text.translatable("mod.graytan.direction_set"), true);

        LOGGER.info(point.getName());

        MC.player.setYaw(point.getYaw());
        MC.player.setPitch(point.getPitch());
    }
}
