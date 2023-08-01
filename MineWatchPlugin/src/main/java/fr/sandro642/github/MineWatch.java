package fr.sandro642.github;

import fr.sandro642.github.db.DBManagerSQL;
import fr.sandro642.github.db.DatabaseManager;
import fr.sandro642.github.events.MinecraftManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MineWatch extends JavaPlugin {

    private static MineWatch instance;

    private static final DatabaseManager dbManager = new DatabaseManager();

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        DBManagerSQL.initConnection();
        Bukkit.getLogger().info("                                                                               ");
        Bukkit.getLogger().info("§2 ███▄ ▄███▓ ██▓ ███▄    █ ▓█████  █     █░ ▄▄▄     ▄▄▄█████▓ ▄████▄   ██░ ██ ");
        Bukkit.getLogger().info("§2▓██▒▀█▀ ██▒▓██▒ ██ ▀█   █ ▓█   ▀ ▓█░ █ ░█░▒████▄   ▓  ██▒ ▓▒▒██▀ ▀█  ▓██░ ██▒");
        Bukkit.getLogger().info("§2▓██    ▓██░▒██▒▓██  ▀█ ██▒▒███   ▒█░ █ ░█ ▒██  ▀█▄ ▒ ▓██░ ▒░▒▓█    ▄ ▒██▀▀██░");
        Bukkit.getLogger().info("§2▒██    ▒██ ░██░▓██▒  ▐▌██▒▒▓█  ▄ ░█░ █ ░█ ░██▄▄▄▄██░ ▓██▓ ░ ▒▓▓▄ ▄██▒░▓█ ░██ ");
        Bukkit.getLogger().info("§2▒██▒   ░██▒░██░▒██░   ▓██░░▒████▒░░██▒██▓  ▓█   ▓██▒ ▒██▒ ░ ▒ ▓███▀ ░░▓█▒░██▓");
        Bukkit.getLogger().info("§2░ ▒░   ░  ░░▓  ░ ▒░   ▒ ▒ ░░ ▒░ ░░ ▓░▒ ▒   ▒▒   ▓▒█░ ▒ ░░   ░ ░▒ ▒  ░ ▒ ░░▒░▒");
        Bukkit.getLogger().info("§2░  ░      ░ ▒ ░░ ░░   ░ ▒░ ░ ░  ░  ▒ ░ ░    ▒   ▒▒ ░   ░      ░  ▒    ▒ ░▒░ ░");
        Bukkit.getLogger().info("§2░      ░    ▒ ░   ░   ░ ░    ░     ░   ░    ░   ▒    ░      ░         ░  ░░ ░");
        Bukkit.getLogger().info("§2       ░    ░           ░    ░  ░    ░          ░  ░            ░ ░       ░  ░  ");
        Bukkit.getLogger().info("§f[§2Alpha§f] §bV:1.0.0                                                       §2░ ");
        Bukkit.getLogger().info("                                                                                      ");
        getServer().getPluginManager().registerEvents(new MinecraftManager(), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("§4MineWatch is disabled !");
    }




    public static MineWatch getInstance() {
        return instance;
    }
}
