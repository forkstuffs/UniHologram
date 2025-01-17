package me.hsgamer.unihologram.spigot.test.command;

import me.hsgamer.hscore.bukkit.command.sub.SubCommandManager;
import me.hsgamer.unihologram.spigot.test.UniHologramPlugin;
import me.hsgamer.unihologram.spigot.test.command.subcommand.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainCommand extends Command {
    private final SubCommandManager subCommandManager;

    public MainCommand(UniHologramPlugin plugin) {
        super("unihologram");
        subCommandManager = new SubCommandManager() {
            @Override
            public void sendHelpMessage(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String... strings) {
                subCommandManager.getSubcommands().values().forEach(subCommand -> commandSender.sendMessage(subCommand.getUsage()));
            }

            @Override
            public void sendArgNotFoundMessage(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String... strings) {
                commandSender.sendMessage("Arg not found");
            }
        };
        subCommandManager.registerSubcommand(new CreateCommand(plugin));
        subCommandManager.registerSubcommand(new DeleteCommand(plugin));
        subCommandManager.registerSubcommand(new AddLineCommand(plugin));
        subCommandManager.registerSubcommand(new DeleteLineCommand(plugin));
        subCommandManager.registerSubcommand(new SetLineCommand(plugin));
        subCommandManager.registerSubcommand(new InsertLineCommand(plugin));
        subCommandManager.registerSubcommand(new CountLineCommand(plugin));
        subCommandManager.registerSubcommand(new MoveCommand(plugin));
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        return subCommandManager.onCommand(commandSender, s, strings);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return subCommandManager.onTabComplete(sender, alias, args);
    }
}
