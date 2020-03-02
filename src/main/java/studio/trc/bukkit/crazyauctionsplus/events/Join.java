package studio.trc.bukkit.crazyauctionsplus.events;

import java.util.logging.Level;
import java.util.logging.Logger;

import studio.trc.bukkit.crazyauctionsplus.utils.FileManager.*;
import studio.trc.bukkit.crazyauctionsplus.utils.enums.Messages;
import studio.trc.bukkit.crazyauctionsplus.utils.enums.Category;
import studio.trc.bukkit.crazyauctionsplus.utils.enums.ShopType;
import studio.trc.bukkit.crazyauctionsplus.database.Storage;

import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join
    implements Listener
{
    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        GUIAction.setCategory(player, Category.NONE);
        GUIAction.setShopType(player, ShopType.ANY);
        if (!Files.CONFIG.getFile().getBoolean("Settings.Join-Message")) return;
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Join.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (player == null) return;
            Storage data = Storage.getPlayer(player);
            if (data.getMailNumber() > 0) {
                player.sendMessage(Messages.getMessage("Email-of-player-owned-items"));
            }
        }).start();
    }
}