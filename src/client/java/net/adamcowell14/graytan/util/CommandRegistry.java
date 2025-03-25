package net.adamcowell14.graytan.util;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.adamcowell14.graytan.commands.*;
import net.minecraft.command.CommandRegistryAccess;

import java.util.List;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class CommandRegistry {
    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) -> {
            var subs = generateSubcommands();

            registerCommand(literal("gtp").build(), dispatcher, subs);
            registerCommand(literal("graytantp").build(), dispatcher, subs);
            registerCommand(literal("gteleport").build(), dispatcher, subs);
            registerCommand(literal("graytan").build(), dispatcher, subs);
        });
    }

    private static void registerCommand(LiteralCommandNode<FabricClientCommandSource> mainCommand, CommandDispatcher<FabricClientCommandSource> dispatcher, List<LiteralCommandNode<FabricClientCommandSource>> subs) {
        dispatcher.getRoot().addChild(mainCommand);

        for(var node : subs) {
            mainCommand.addChild(node);
        }
    }

    private static List<LiteralCommandNode<FabricClientCommandSource>> generateSubcommands() {
        // List SubCommand
        var listNode = literal("list").executes(ListCommand::run).build();

        // TP SubCommand
        var tpNode = literal("tp").build();
        tpNode.addChild(argument("name", StringArgumentType.word())
                .suggests(TpCommand::suggest).executes(TpCommand::run).build());

        return List.of(listNode, tpNode);
    }
}