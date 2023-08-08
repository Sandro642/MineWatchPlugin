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

package fr.sandro642.github;

import fr.sandro642.github.db.DBManagerSQL;
import fr.sandro642.github.events.MinecraftManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


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

public class MineWatch extends JavaPlugin {


    /**
     * INSTANCE of class MineWatch
     */

    private static MineWatch instance;


    /**
     * onEnable() method
     *
     * @return void
     */

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


    /**
     * onDisable() method
     *
     * @return void
     */

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("§4MineWatch is disabled !");
    }


    /**
     * getInstance() method
     *
     * @return MineWatch
     */

    public static MineWatch getInstance() {
        return instance;
    }
}
