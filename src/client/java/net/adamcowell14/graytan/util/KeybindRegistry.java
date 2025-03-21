package net.adamcowell14.graytan.util;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.util.Utils;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.adamcowell14.graytan.GraytanClient;
import net.adamcowell14.graytan.config.KeyBind;
import net.adamcowell14.graytan.config.ModConfig;
import net.adamcowell14.graytan.config.ModPoint;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class KeybindRegistry {
    public static void registerType() {
        AutoConfig.getGuiRegistry(ModConfig.class).registerTypeProvider((i18n, field, config, defaults, registry) -> {
            ModPoint point = (ModPoint) config;
            KeyBind keyBind = Utils.getUnsafely(field, point, GraytanClient.DEFAULT_KEYBIND);

            List<AbstractConfigListEntry> list = new ArrayList<>();
            ConfigEntryBuilder builder = ConfigEntryBuilder.create();
            list.add(builder.startModifierKeyCodeField(Text.translatable(i18n), keyBind.toModifierKeyCode())
                    .setModifierSaveConsumer(point::setKeyBind)
                    .setAllowMouse(true)
                    .build());

            return list;
        }, KeyBind.class);
    }

    public static void registerEvent() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(client.currentScreen != null) return;

            for(ModPoint point : GraytanClient.CONFIG.places) {
                if(point.getKeyBind().toModifierKeyCode().matchesCurrentKey() || point.getKeyBind().toModifierKeyCode().matchesCurrentMouse()) {
                    GraytanClient.test(point);
                }
            }
        });
    }
}