package net.adamcowell14.graytan;

import net.adamcowell14.graytan.util.CommandRegistry;
import net.adamcowell14.graytan.util.KeybindRegistry;
import net.adamcowell14.graytan.config.ModConfig;
import net.adamcowell14.graytan.config.ModPoint;
import net.adamcowell14.graytan.config.KeyBind;
import net.adamcowell14.graytan.util.Utils;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.AutoConfig;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Objects;

public class GraytanClient implements ClientModInitializer {
    public static final String MOD_ID = "graytan";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final KeyBind DEFAULT_KEYBIND = KeyBind.of(ModifierKeyCode.unknown());
    public static final MinecraftClient MC = MinecraftClient.getInstance();
    public static ModConfig CONFIG;

    @Override
    public void onInitializeClient() {
        KeybindRegistry.registerType();
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        ConfigHolder<ModConfig> CONFIGHolder = AutoConfig.getConfigHolder(ModConfig.class);
        CONFIG = CONFIGHolder.getConfig();

        CONFIGHolder.registerSaveListener((configHolder, config) -> {
            for(ModPoint point : config.places) {
                if (Utils.regexString(point.getName()) || Objects.equals(point.getName(), "")) {
                    point.setName("Point_" + Utils.getSaltString());
                }
            }
            return null;
        });

        for(ModPoint point : CONFIG.places) {
            if (Utils.regexString(point.getName()) || Objects.equals(point.getName(), "")) {
                point.setName("Point_" + Utils.getSaltString());
                LOGGER.info(point.getName());
            }
        }

        CommandRegistry.register();
        KeybindRegistry.registerEvent();
//        HudRenderCallback.EVENT.register(RenderOverlay::RenderGameOverlayEvent);
    }

    public static boolean hasCompassInHand(PlayerEntity player) {
        ItemStack mainHandStack = player.getMainHandStack();
        if (mainHandStack.getItem() == Items.COMPASS) {
            return true;
        }

        ItemStack offHandStack = player.getOffHandStack();
        return offHandStack.getItem() == Items.COMPASS;
    }

    public static void direction(ModPoint point) {
        assert MC.player != null;

        if (CONFIG.ms_lore_mode && hasCompassInHand(MC.player)) {
            direction_update(point);
        } else if (!CONFIG.ms_lore_mode) {
            direction_update(point);
        } else {
            MC.player.sendMessage(Text.translatable("mod.graytan.ms_mode_error"), false);
        }
    }

    private static void direction_update(ModPoint point) {
        assert MC.player != null;

        MC.player.sendMessage(Text.translatable("mod.graytan.direction_set"), true);

        LOGGER.info(point.getName());

        MC.player.setYaw(point.getYaw());
        MC.player.setPitch(point.getPitch());
        if (CONFIG.time != -1) {
            new UpdateRotate().start();
        }
    }

    public static ModPoint getPoint(String name) {
        for (ModPoint point : CONFIG.places) {
            if (point.getName().equals(name)) {
                return point;
            }
        }
        return null;
    }
}

class UpdateRotate extends Thread{
    @Override
    public void run(){
        MinecraftClient mc = MinecraftClient.getInstance();

        assert mc.player != null;

        try {
            Thread.sleep(GraytanClient.CONFIG.time);
            mc.player.setPitch(-90);
            mc.player.setYaw(-90);
        } catch (InterruptedException ignored) {}
    }
}