package fr.sandro642.github;

import de.tallerik.MySQL;
import fr.sandro642.github.commands.MineWatchCmd;
import fr.sandro642.github.events.GetEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static void checkStatus() {
        if (!Main.getInstance().getConfig().getBoolean("SQL.Enabled") == true) {
            Bukkit.getConsoleSender().sendMessage("Plugin MineWatch is not enabled. Execute /minewatch start");
            return;
        }
    }

    public static Main instance;

    public static MySQL mySQL = new MySQL();

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§8+------------------------------------+");
        Bukkit.getConsoleSender().sendMessage("          §dMineWatch§5Plugin");
        Bukkit.getConsoleSender().sendMessage("               §aEnabled");
        Bukkit.getConsoleSender().sendMessage("§8");

        Bukkit.getConsoleSender().sendMessage("§f-> §cLoading core:");
        checkEvents();
        checkSqlDB();

        Bukkit.getConsoleSender().sendMessage("§9");
        Bukkit.getConsoleSender().sendMessage("§8+------------------------------------+");

    }

    @Override
    public void onDisable() {
        // Default launch
        instance = this;


        Bukkit.getConsoleSender().sendMessage("§8+------------------------------------+");
        Bukkit.getConsoleSender().sendMessage("          §dMineWatch§5Plugin");
        Bukkit.getConsoleSender().sendMessage("               §aDisabled");
        Bukkit.getConsoleSender().sendMessage("§8");

        Bukkit.getConsoleSender().sendMessage("§f-> §cDisabling core:");

        disabledSqlDB();

        Bukkit.getConsoleSender().sendMessage("§9");
        Bukkit.getConsoleSender().sendMessage("§8+------------------------------------+");
    }

    public void disabledSqlDB() {
        try {
            Main.mySQL.close();
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
            Bukkit.getConsoleSender().sendMessage("-> §dError Disabling...");
            return;
        }
        Bukkit.getConsoleSender().sendMessage("   - §fSqlDB: §aDisabling");
    }

    public void loadCommands() {
        try {
            getCommand("minewatch").setExecutor(new MineWatchCmd(this));
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
            Bukkit.getConsoleSender().sendMessage("-> §dError load Commands...");
            return;
        }
        Bukkit.getConsoleSender().sendMessage("   - §fCommands: §aLoaded");
    }

    public void checkSqlDB() {
        try {
            mySQL.setHost(Main.getInstance().getConfig().getString("SQL.Host"));
            mySQL.setUser(Main.getInstance().getConfig().getString("SQL.User"));
            mySQL.setPassword(Main.getInstance().getConfig().getString("SQL.Password"));
            mySQL.setDb(Main.getInstance().getConfig().getString("SQL.Database"));

            mySQL.setPort(Main.getInstance().getConfig().getInt("SQL.Port")); // Optional. Default: 3306
            mySQL.setDebug(Main.getInstance().getConfig().getBoolean("SQL.Debug")); // Optional. Default: false

            Main.mySQL.connect();

            Main.mySQL.isConnected();

            if (Main.getInstance().getConfig().getBoolean("SQL.Debug") == true) {
                Main.mySQL.isDebug();
            }
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
            Bukkit.getConsoleSender().sendMessage("-> §dError charging...");
            return;
        }
        Bukkit.getConsoleSender().sendMessage("   - §fSqlDB: §aLoaded");
    }

    public void checkEvents() {
        try {
            getServer().getPluginManager().registerEvents(new GetEvents(), this);
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
            Bukkit.getConsoleSender().sendMessage("-> §dError charging...");
            return;
        }
        Bukkit.getConsoleSender().sendMessage("   - §fEvents: §aLoaded");
    }

    public static Main getInstance() {
        return instance;
    }
}