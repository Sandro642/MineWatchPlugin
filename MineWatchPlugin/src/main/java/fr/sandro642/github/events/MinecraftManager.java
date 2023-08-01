package fr.sandro642.github.events;

import fr.sandro642.github.MineWatch;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import static fr.sandro642.github.db.DBManagerSQL.addElement;

public class MinecraftManager implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        if (MineWatch.getInstance().getConfig().getBoolean("SetAccessDataInput.onPlayerJoin") == true) {
            addElement("PlayerJoinEvent", player.getName(), world.getName(), System.currentTimeMillis());
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        if (MineWatch.getInstance().getConfig().getBoolean("SetAccessDataInput.onPlayerQuit") == true) {
            addElement("PlayerQuitEvent", player.getName(), world.getName(), System.currentTimeMillis());
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        if (MineWatch.getInstance().getConfig().getBoolean("SetAccessDataInput.onBlockBreak") == true) {
            addElement("BlockBreakEvent", player.getName(), world.getName(), System.currentTimeMillis());
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        if (MineWatch.getInstance().getConfig().getBoolean("SetAccessDataInput.onBlockPlace") == true) {
            addElement("BlockPlaceEvent", player.getName(), world.getName(), System.currentTimeMillis());
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        World world = player.getWorld();
        if (MineWatch.getInstance().getConfig().getBoolean("SetAccessDataInput.onPlayerDeath") == true) {
            addElement("PlayerDeathEvent", player.getName(), world.getName(), System.currentTimeMillis());
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        if (MineWatch.getInstance().getConfig().getBoolean("SetAccessDataInput.onPlayerRespawn") == true) {
            addElement("PlayerRespawnEvent", player.getName(), world.getName(), System.currentTimeMillis());
        }
    }

}
