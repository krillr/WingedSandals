package io.quintus.wingedsandals;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by akrill on 3/27/15.
 */
public class WingedSandals extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {}
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerLoginEvent(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        boolean canFly = event.getPlayer().hasPermission("wingedsandals.allow." + player.getLocation().getWorld().getName());
        boolean shouldFly = event.getPlayer().hasPermission("wingedsandals.force." + player.getLocation().getWorld().getName());
        boolean isFlying = event.getPlayer().isFlying();

        if (player.hasPermission("wingedsandals.override." + player.getLocation().getWorld().getName()) || player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

            if (canFly) {
            player.setAllowFlight(true);
        } else {
            player.setAllowFlight(false);
        }

        if (isFlying && !canFly) {
            player.setFlying(false);
        }

        if (shouldFly && !isFlying) {
            player.setFlying(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerTeleportEvent(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        boolean canFly = event.getPlayer().hasPermission("wingedsandals.allow." + event.getTo().getWorld().getName());
        boolean shouldFly = event.getPlayer().hasPermission("wingedsandals.force." + event.getTo().getWorld().getName());
            boolean isFlying = event.getPlayer().isFlying();

        if (player.hasPermission("wingedsandals.override." + event.getTo().getWorld().getName()) || player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

            if (canFly) {
            player.setAllowFlight(true);
        } else {
            player.setAllowFlight(false);
        }

        if (isFlying && !canFly) {
            player.setFlying(false);
        }

        if (shouldFly && !isFlying) {
            player.setFlying(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        boolean canFly = event.getPlayer().hasPermission("wingedsandals.allow." + player.getLocation().getWorld().getName());
        boolean shouldFly = event.getPlayer().hasPermission("wingedsandals.force." + player.getLocation().getWorld().getName());
        boolean isFlying = event.getPlayer().isFlying();

        if (player.hasPermission("wingedsandals.override." + player.getLocation().getWorld().getName()) || player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        if (canFly) {
            player.setAllowFlight(true);
        } else {
            player.setAllowFlight(false);
        }

        if (isFlying && !canFly) {
            player.setFlying(false);
            event.setCancelled(true);
        }

        if (shouldFly && !isFlying) {
            player.setFlying(true);
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        boolean canFly = event.getPlayer().hasPermission("wingedsandals.allow." + player.getLocation().getWorld().getName());
        boolean shouldFly = event.getPlayer().hasPermission("wingedsandals.force." + player.getLocation().getWorld().getName());
        boolean isFlying = event.getPlayer().isFlying();

        if (player.hasPermission("wingedsandals.override." + player.getLocation().getWorld().getName()) || player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        if (canFly) {
            player.setAllowFlight(true);
        } else {
            player.setAllowFlight(false);
        }

        if (isFlying && !canFly) {
            player.setFlying(false);
        }

        if (shouldFly && !isFlying) {
            player.setFlying(true);
        }
    }
}

