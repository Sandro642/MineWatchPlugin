package fr.sandro642.github.events;

import de.tallerik.utils.Insert;
import fr.sandro642.github.Main;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GetEvents implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Main.checkStatus();

        // sql.tableInsert();
        Insert ins = new Insert();
        ins.setTable("LogsMineWatch");
        ins.setColumns("player, Action");
        ins.setData(e.getPlayer().getName(), "Join server");
        Main.mySQL.tableInsert(ins);

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Main.checkStatus();

        Insert ins = new Insert();

        ins.setData(e.getPlayer().getName(), "Quit server");
        Main.mySQL.tableInsert(ins);

    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Main.checkStatus();

        Insert ins = new Insert();

        Item item = e.getItemDrop();

        ins.setData(e.getPlayer().getName(), "Drop item: " + item);
        Main.mySQL.tableInsert(ins);
    }

    @EventHandler
    public void onPickUp(PlayerPickupItemEvent e) {
        Main.checkStatus();

        Insert ins = new Insert();

        Item item = e.getItem();

        ins.setData(e.getPlayer().getName(), "Pick up item: " + item);
        Main.mySQL.tableInsert(ins);
    }

}
