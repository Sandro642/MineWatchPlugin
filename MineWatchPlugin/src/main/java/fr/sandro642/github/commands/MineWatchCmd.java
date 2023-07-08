package fr.sandro642.github.commands;

import fr.sandro642.github.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MineWatchCmd implements CommandExecutor {

    public final Main main;
    public MineWatchCmd(Main main) {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if (label.equalsIgnoreCase("minewatch")) {
            if (args[0].equalsIgnoreCase("start")) {
                main.getConfig().set("SQL.Enabled", true);
                player.sendMessage("§aVous avez activé le plugin Minewatch");
                return true;
            }

            if (args[0].equalsIgnoreCase("stop")) {
                main.getConfig().set("SQL.Enabled", false);
                player.sendMessage("§aVous avez désactivé le plugin Minewatch");
                return true;
            }
        }

        return false;
    }
}
