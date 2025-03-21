package net.adamcowell14.graytan.config;

import lombok.*;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import net.adamcowell14.graytan.GraytanClient;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModPoint {
    @ConfigEntry.Gui.Tooltip
    private String name;

    @ConfigEntry.Gui.Tooltip
    private float yaw;

    @ConfigEntry.Gui.Tooltip
    private float pitch;

    private KeyBind keyBind = GraytanClient.DEFAULT_KEYBIND;

    public ModPoint(String name, float yaw, float pitch) {
        this(name, yaw, pitch, GraytanClient.DEFAULT_KEYBIND);
    }

    public void setKeyBind(ModifierKeyCode modifierKeyCode) {
        this.keyBind = KeyBind.of(modifierKeyCode);
    }
}