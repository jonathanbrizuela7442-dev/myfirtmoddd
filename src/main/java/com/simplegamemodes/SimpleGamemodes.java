package com.simplegamemodes;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class SimpleGamemodes implements ModInitializer {
    public static final String MOD_ID = "simplegamemodes";

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            register(dispatcher, "gms", "survival");
            register(dispatcher, "gmc", "creative");
            register(dispatcher, "gma", "adventure");
            register(dispatcher, "gmsp", "spectator");
        });
    }

    private static void register(CommandDispatcher<ServerCommandSource> dispatcher, String alias, String mode) {
        dispatcher.register(CommandManager.literal(alias).executes(context -> {
            dispatcher.execute("gamemode " + mode, context.getSource());
            return 1;
        }));
    }
}
