package net.adamcowell14.graytan.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.minecraft.client.util.InputUtil;

import java.util.ArrayList;
import java.util.List;

@Config(name = "graytan")
public class ModConfig implements ConfigData {
    public boolean anti_tp = false;
    public int fault_max = 10;
    public List<ModPoint> places = new ArrayList<>();
}