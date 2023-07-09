package fr.sandro642.github.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetEvents implements Listener {

    private static Connection connection;

    public static void getConnectionInformation(String host, int port, String database, String user, String password) {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

        try {
            connection = DriverManager.getConnection(url, user, password);
            Bukkit.getConsoleSender().sendMessage("Connected to MySQL database!");
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("Failed to connect to MySQL database: " + e.getMessage());
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String world = event.getPlayer().getWorld().getName();
        String player = event.getPlayer().getName();
        String action = "Join server";

        insertLogEntry(world, player, action);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        String world = event.getPlayer().getWorld().getName();
        String player = event.getPlayer().getName();
        String action = "Quit server";

        insertLogEntry(world, player, action);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        String world = event.getPlayer().getWorld().getName();
        String player = event.getPlayer().getName();
        String action = "Drop item";

        insertLogEntry(world, player, action);
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        String world = event.getPlayer().getWorld().getName();
        String player = event.getPlayer().getName();
        String action = "Pick up item";

        insertLogEntry(world, player, action);
    }

    private void insertLogEntry(String world, String player, String action) {
        String query = "INSERT INTO MineWatchLog (World, Player, Action) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, world);
            statement.setString(2, player);
            statement.setString(3, action);
            statement.executeUpdate();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("Failed to insert log entry: " + e.getMessage());
        }
    }
}
