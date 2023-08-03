//MIT License
//
//Copyright (c) 2023 Sandro Soria
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all
//copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//SOFTWARE.

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


/**
 * @Author: Sandro642
 * @Date: 2020/6/3 15:00
 * @File: MineWatch
 * @Version: 1.0
 * @Description: Main class of the plugin
 * @JDK: 1.8
 * @Software: IntelliJ IDEA
 * @Github: https://github.com/Sandro642/MineWatch
 * @Contact: https://sandro642.me/
 * @License: MIT License
 * @Project: MineWatch
 * @Server: Minecraft
 * @PluginTag: MineWatch
 * @PluginMainClass: fr.sandro642.github.MineWatch
 * @PluginVersion: 1.0.0
 * @PluginDescription: A plugin to watch your server
 */

public class MinecraftManager implements Listener {


    /**
     * @Description : When a player join
     * @param event
     */

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        if (MineWatch.getInstance().getConfig().getBoolean("SetAccessDataInput.onPlayerJoin") == true) {
            addElement("PlayerJoinEvent", player.getName(), world.getName(), System.currentTimeMillis());
        }
    }


    /**
     * @Description : When a player quit
     * @param event
     */

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        if (MineWatch.getInstance().getConfig().getBoolean("SetAccessDataInput.onPlayerQuit") == true) {
            addElement("PlayerQuitEvent", player.getName(), world.getName(), System.currentTimeMillis());
        }
    }


    /**
     * @Description : When a player break a block
     * @param event
     */

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        if (MineWatch.getInstance().getConfig().getBoolean("SetAccessDataInput.onBlockBreak") == true) {
            addElement("BlockBreakEvent", player.getName(), world.getName(), System.currentTimeMillis());
        }
    }


    /**
     * @Description : When a player place a block
     * @param event
     */

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        if (MineWatch.getInstance().getConfig().getBoolean("SetAccessDataInput.onBlockPlace") == true) {
            addElement("BlockPlaceEvent", player.getName(), world.getName(), System.currentTimeMillis());
        }
    }


    /**
     * @Description : When a player die
     * @param event
     */

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        World world = player.getWorld();
        if (MineWatch.getInstance().getConfig().getBoolean("SetAccessDataInput.onPlayerDeath") == true) {
            addElement("PlayerDeathEvent", player.getName(), world.getName(), System.currentTimeMillis());
        }
    }


    /**
     * @Description : When a player respawn
     * @param event
     */

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        if (MineWatch.getInstance().getConfig().getBoolean("SetAccessDataInput.onPlayerRespawn") == true) {
            addElement("PlayerRespawnEvent", player.getName(), world.getName(), System.currentTimeMillis());
        }
    }

}
