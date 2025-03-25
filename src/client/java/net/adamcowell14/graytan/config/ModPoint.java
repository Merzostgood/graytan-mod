package net.adamcowell14.graytan.config;

import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import net.adamcowell14.graytan.GraytanClient;

public class ModPoint {
    @ConfigEntry.Gui.Tooltip
    private String name;

    @ConfigEntry.Gui.Tooltip
    private float yaw;

    @ConfigEntry.Gui.Tooltip
    private float pitch;

    private KeyBind keyBind = GraytanClient.DEFAULT_KEYBIND;

    public float getYaw() {
        return yaw;
    }
    public float getPitch() {
        return pitch;
    }
    public String getName() {
        return name;
    }
    public KeyBind getKeyBind() {
        return keyBind;
    }

    public void setName(String NewName) {
        name = NewName;
    }

    public void setKeyBind(ModifierKeyCode modifierKeyCode) {
        this.keyBind = KeyBind.of(modifierKeyCode);
    }
}