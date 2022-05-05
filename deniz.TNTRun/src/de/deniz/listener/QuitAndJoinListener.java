
package de.deniz.listener;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.deniz.main.Main;

public class QuitAndJoinListener implements Listener {
	public static int taskid;
	String Livee;
	FileConfiguration Range;

	public QuitAndJoinListener() {
		this.Range = Main.getPlugin().getConfig();
	}

	@EventHandler
	public void whenPlayerJoin(final PlayerJoinEvent event) {

		event.setJoinMessage("§f[§a+§f] §7" + event.getPlayer().getName());
		Main.OnlinePlayer.add(event.getPlayer());

	}

	@EventHandler
	public void whenPlayerQuit(final PlayerQuitEvent event) {

		event.setQuitMessage("§f[§c-§f] §4" + event.getPlayer().getName());
		Main.OnlinePlayer.remove(event.getPlayer());

	}
}
