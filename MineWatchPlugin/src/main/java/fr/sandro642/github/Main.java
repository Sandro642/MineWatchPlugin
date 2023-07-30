package fr.sandro642.github;

import fr.sandro642.github.commands.MineWatchCmd;
import fr.sandro642.github.events.GetEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.*;
import java.util.Objects;

public class Main extends JavaPlugin {

    private static Main instance;
    private static Connection connection;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        Bukkit.getConsoleSender().sendMessage("§8+------------------------------------+");
        Bukkit.getConsoleSender().sendMessage("          §dMineWatch§5Plugin");
        Bukkit.getConsoleSender().sendMessage("               §aEnabled");
        Bukkit.getConsoleSender().sendMessage("§8");

        Bukkit.getConsoleSender().sendMessage("§f-> §cLoading core:");
        checkEvents();
        checkSqlDB();
        loadCommands();

        Bukkit.getConsoleSender().sendMessage("§9");
        Bukkit.getConsoleSender().sendMessage("§8+------------------------------------+");

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§8+------------------------------------+");
        Bukkit.getConsoleSender().sendMessage("          §dMineWatch§5Plugin");
        Bukkit.getConsoleSender().sendMessage("               §aDisabled");
        Bukkit.getConsoleSender().sendMessage("§8");

        Bukkit.getConsoleSender().sendMessage("§f-> §cDisabling core:");
        try {
            disabledSqlDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Bukkit.getConsoleSender().sendMessage("§9");
        Bukkit.getConsoleSender().sendMessage("§8+------------------------------------+");
    }

    public void disabledSqlDB() throws SQLException {
        try {
            Bukkit.getConsoleSender().sendMessage("   - §fSqlDB: §aDisabling");
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
            Bukkit.getConsoleSender().sendMessage("-> §dError Disabling...");
        }
    }

    public void loadCommands() {
        try {
            MineWatchCmd mineWatchCmd = new MineWatchCmd();
            Objects.requireNonNull(getCommand("minewatch")).setExecutor(mineWatchCmd);
            Bukkit.getConsoleSender().sendMessage("   - §fCommands: §aLoaded");
        } catch (NullPointerException e) {
            Bukkit.getConsoleSender().sendMessage("   - §fCommands: §4Error");
            e.printStackTrace();
        }
    }

    public boolean tableExists(String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet resultSet = metaData.getTables(null, null, tableName, null)) {
            return resultSet.next();
        }
    }
   
    public void createTableIfNotExists(String tableName) throws SQLException {
    String query = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
            + "id INT AUTO_INCREMENT PRIMARY KEY,"
            + "World VARCHAR(255),"
            + "Player VARCHAR(255),"
            + "Action VARCHAR(255),"
            + "Timestamp DATETIME"
            + ")";

    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.executeUpdate();
    }
}



    public void checkSqlDB() {
        try {
            String host = getConfig().getString("SQL.Host");
            String user = getConfig().getString("SQL.User");
            String password = getConfig().getString("SQL.Password");
            String database = getConfig().getString("SQL.Database");
            int port = getConfig().getInt("SQL.Port", 3306);

            // Vérification de l'existence de la table MineWatchLog
            if (!tableExists("MineWatchLog")) {
                createTableIfNotExists("MineWatchLog");
            }

            String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
            connection = DriverManager.getConnection(url, user, password);
            GetEvents.getConnectionInformation(host, port, database, user, password);

            Bukkit.getConsoleSender().sendMessage("   - §fSqlDB: §aLoaded");
            Bukkit.getConsoleSender().sendMessage("      - §fConnected to MySQL: " + url);
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("Failed to connect to MySQL database: " + e.getMessage());
            Bukkit.getConsoleSender().sendMessage("-> §dError charging...");

            // Open Circuit

            getServer().getPluginManager().disablePlugin(this);
        }
    }

    public void checkEvents() {
        try {
            getServer().getPluginManager().registerEvents(new GetEvents(), this);
            Bukkit.getConsoleSender().sendMessage("   - §fEvents: §aLoaded");
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
            Bukkit.getConsoleSender().sendMessage("-> §dError charging...");
        }
    }

    public static Main getInstance() {
        return instance;
    }
}
