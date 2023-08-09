package me.johnnyht.skyitems;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public final class SkyItems extends JavaPlugin implements Listener {
    private final Glowing glowing = new Glowing();
    private Scoreboard scoreboard;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);

    }
    @EventHandler
    public void useGlowItem(PlayerInteractEvent event){
        ItemStack item = event.getItem();
        if(item == null)return;
        ItemMeta meta = item.getItemMeta();
        if (item.getType() == Material.YELLOW_DYE && meta.getCustomModelData() == 10) {
                applyGlowEffectToNearbyPlayers(event.getPlayer());

        }
    }

    private void applyGlowEffectToNearbyPlayers(Player player) {
        double radiusSquared = 30 * 30;
        scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

        Team playerTeam = scoreboard.getEntryTeam(player.getName());

        if (playerTeam == null) return; // Player is not in a team

        for (Player nearbyPlayer : Bukkit.getOnlinePlayers()) {
            if (nearbyPlayer.getLocation().distanceSquared(player.getLocation()) <= radiusSquared) {
                Team nearbyPlayerTeam = scoreboard.getEntryTeam(nearbyPlayer.getName());
                Bukkit.getLogger().info("3");
                if (nearbyPlayerTeam == null || !nearbyPlayerTeam.equals(playerTeam)) {
                    Bukkit.getLogger().info("1");
                    glowing.glow(player,nearbyPlayer);
                    // You can also modify the player's model data here
                }
            }
        }
    }
}
