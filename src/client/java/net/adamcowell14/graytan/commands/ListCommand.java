package net.adamcowell14.graytan.commands;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.adamcowell14.graytan.GraytanClient;
import net.adamcowell14.graytan.config.ModPoint;
import net.minecraft.text.Text;


public class ListCommand {
    public static int run(CommandContext<FabricClientCommandSource> ctx) {
        StringBuilder message = new StringBuilder("§5Graytan | List of all places:§f\n");
        for (ModPoint point : GraytanClient.CONFIG.places) {
            message.append(point.getName()).append(" - yaw: ").append(point.getYaw()).append(", pitch: ").append(point.getPitch()).append("\n");
        }
        ctx.getSource().sendFeedback(Text.of(message.toString()));
        return 1;
    }
}