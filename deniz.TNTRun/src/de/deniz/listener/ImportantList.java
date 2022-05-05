
package de.deniz.listener;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import de.deniz.main.Main;

public class ImportantList implements Listener {
	public static int taskid;
	String Livee;
	FileConfiguration Range;
	static boolean isAir;
	static ArrayList<String> poisoned;

	static {
		ImportantList.taskid = 1;
		ImportantList.poisoned = new ArrayList<String>();
	}

	public ImportantList() {
		this.Livee = "§f§l[§3§lLIVE§f§l] ";
		this.Range = Main.getPlugin().getConfig();
	}

	@EventHandler
	public void onChatEvent(final AsyncPlayerChatEvent e) {
		final String message = e.getMessage();
		final Player p = e.getPlayer();
		if (Main.Dead.contains(p)) {
			if (!Main.stopped) {
				e.setFormat("§c[X] §7" + p.getName() + " §6» §7" + message);
			} else {
				e.setFormat("&c[X] §f" + p.getName() + " §6» §f" + message);
			}
		} else if (Main.Publisher.equals(p.getName()) || Main.Admin.equals(p.getName())) {

			e.setFormat("§c" + p.getName() + " §6» §c" + message);

		} else if (Main.Living.contains(p)) {

			e.setFormat("§f" + p.getName() + " §6» §f" + message);

		} else {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onCommandPreProcess(PlayerCommandPreprocessEvent event) {
		if (event.getMessage().toLowerCase().startsWith("/help")) {
			event.setCancelled(true);
			event.getPlayer().sendMessage("§e------Help-Commands Page 1------");
			event.getPlayer().sendMessage("/start [Countdown]: Starte das TNT Spiel mit allen Teilnehmern (Admins)");
			event.getPlayer().sendMessage("/remove: Bereite das Spielgebiet vor (Admins)");
			event.getPlayer().sendMessage("/anmelden [Spieler]: Füge einen User als Spieler hinzu (Admins)");
			event.getPlayer().sendMessage("§e------Help-Commands Page 1------");

		}
	}

	@EventHandler
	public void whenBlockDestroyed(final BlockBreakEvent e) {
		if (Main.Publisher.equals(e.getPlayer().getName()) || Main.Admin.equals(e.getPlayer().getName())) {

		} else {

			e.setCancelled(true);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void PlayerMoveBeforeStart(final PlayerMoveEvent e) {

		if (e.getPlayer().getLocation().getBlockY() < 0) {
			e.getPlayer().teleport(new Location(Bukkit.getWorld("World"), 325.5, 151, 325.5));
		}

		if (Main.stopped == false && Main.starting) {
			if (Main.Angemeldet.contains(e.getPlayer())) {

				if (e.getPlayer().getLocation().getBlockX() < 325 || e.getPlayer().getLocation().getBlockX() > 325
						|| e.getPlayer().getLocation().getBlockZ() < 325
						|| e.getPlayer().getLocation().getBlockZ() > 325) {
					e.getPlayer().teleport(new Location(Bukkit.getWorld("World"), 325.5, 151, 325.5));

				}
			}
		}

		if (Main.stopped == false && Main.starting == false) {

			if (e.getPlayer().getLocation().getBlockY() > 150) {
				for (Player alle : Bukkit.getOnlinePlayers()) {
					if (Main.Angemeldet.contains(alle)) {
						Bukkit.getWorld("World")
								.getBlockAt(alle.getLocation().getBlockX(), 149, alle.getLocation().getBlockZ())
								.setType(Material.AIR);
					}
				}
			} else if (e.getPlayer().getLocation().getBlockY() < 130) {
				Main.Angemeldet.remove(e.getPlayer());

				e.getPlayer().setGameMode(GameMode.SPECTATOR);
				e.getPlayer().teleport(new Location(Bukkit.getWorld("World"), 325.5, 151, 325.5));

				for (Player all : Main.OnlinePlayer) {
					all.sendMessage(Main.pr + "§7" + e.getPlayer().getName() + " §cIst ausgeschieden. Noch "
							+ Main.Angemeldet.size() + " Spieler verbleiben.");
				}
				if (Main.Angemeldet.size() == 1) {
					Main.stopped = true;
					Main.starting = false;
					for (Player all : Main.OnlinePlayer) {
						all.sendMessage(Main.pr + "§fDer Spieler §2" + Main.Angemeldet.get(0).getName()
								+ " §fhat das TNT-Run gewonnen!");

						all.sendTitle("§2" + Main.Angemeldet.get(0).getName(), "§fhat das Spiel gewonnen!");
						all.removePotionEffect(PotionEffectType.REGENERATION);
						Main.Angemeldet.clear();
					}
				}

			}
		}

	}

	@EventHandler
	public void PlayerDamageBeforeStart(final EntityDamageEvent e) {
		if (Main.stopped == false && Main.starting) {
			e.setCancelled(true);
		}
	}

}
