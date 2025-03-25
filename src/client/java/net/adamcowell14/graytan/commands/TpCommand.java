package net.adamcowell14.graytan.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.adamcowell14.graytan.GraytanClient;
import net.adamcowell14.graytan.config.ModPoint;
import net.minecraft.text.Text;

import java.util.concurrent.CompletableFuture;

public class TpCommand {
    public static CompletableFuture<Suggestions> suggest(CommandContext<FabricClientCommandSource> ignoredCtx, SuggestionsBuilder builder) {
        for (ModPoint point : GraytanClient.CONFIG.places) {
            builder.suggest(point.getName());
        }
        return builder.buildFuture();
    }

    public static int run(CommandContext<FabricClientCommandSource> ctx) {
        String pointName = ctx.getArgument("name", String.class);
        ModPoint point = GraytanClient.getPoint(pointName);
        if (point == null) {
            ctx.getSource().sendFeedback(Text.of("§5§lGraytan | §fPoint doesn't exist"));
            return 0;
        }

        GraytanClient.direction(point);
        return 1;
    }
}