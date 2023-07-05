package fr.sandro642.github;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§8+------------------------------------+");
        Bukkit.getConsoleSender().sendMessage("          §dMineWatch§5Plugin");
        Bukkit.getConsoleSender().sendMessage("             §aEnabled §d");
        Bukkit.getConsoleSender().sendMessage("§8");

        Bukkit.getConsoleSender().sendMessage("§f-> §cLoading core:");
        checkEvents();
        checkMongoDB();

        Bukkit.getConsoleSender().sendMessage("§9");
        Bukkit.getConsoleSender().sendMessage("§8+------------------------------------+");

    }


    public void checkMongoDB() {
        try {

        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
            Bukkit.getConsoleSender().sendMessage("-> §dError charging...");
            return;
        }
        Bukkit.getConsoleSender().sendMessage("   - §fMongoDB: §aLoaded");
    }

    public void checkEvents() {
        try {

        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
            Bukkit.getConsoleSender().sendMessage("-> §dError charging...");
            return;
        }
        Bukkit.getConsoleSender().sendMessage("   - §fEvents: §aLoaded");
    }
}