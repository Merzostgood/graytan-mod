package net.adamcowell14.graytan.config;

import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.annotation.Config;
import net.adamcowell14.graytan.GraytanClient;
import me.shedaniel.autoconfig.ConfigData;
import java.util.ArrayList;
import java.util.List;

@Config(name = GraytanClient.MOD_ID)
public class ModConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    public int time = 3000;

    public List<ModPoint> places = new ArrayList<>();
}