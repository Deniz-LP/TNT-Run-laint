
package de.deniz.util;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.deniz.main.Main;

public class Starting {
	static int taskid;
	static int time;
	static FileConfiguration Range;

	static {
		Starting.taskid = 0;
		Starting.Range = Main.getPlugin().getConfig();
	}

	public static void startTimer(final int time1) {
		Starting.time = time1;
		Bukkit.getScheduler().cancelTask(Starting.taskid);
		Starting.taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) Main.getPlugin(),
				(Runnable) new Runnable() {
					@SuppressWarnings({ "deprecation" })
					@Override
					public void run() {

						if (Starting.time == 0) {
							Starting.time = Main.countdownTime;
							for (final Player player : Bukkit.getOnlinePlayers()) {
								Main.stopped = false;
								Main.starting = false;
								player.sendMessage(Main.pr + "§6GO!");
								player.setGameMode(GameMode.ADVENTURE);

								if (Main.Angemeldet.contains(player)) {
									Main.Living.add(player);
								}

							}
							Bukkit.getScheduler().cancelTask(Starting.taskid);
						} else if (Starting.time == 10) {
							for (final Player player : Bukkit.getOnlinePlayers()) {
								player.sendMessage(Main.pr + "§fStart in §2" + time);
								for (Player p : Main.Angemeldet) {
									p.teleport(new Location(Bukkit.getWorld("World"), 325.5, 151, 325.5));
									p.setGameMode(GameMode.ADVENTURE);
									p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 999999, 9999999),
											true);

								}
							}
						} else if (Starting.time == 5 || Starting.time == 4) {
							for (final Player player : Bukkit.getOnlinePlayers()) {
								player.sendMessage(Main.pr + "§fStart in §2" + time);
							}
						} else if (Starting.time == 3) {
							for (final Player player : Bukkit.getOnlinePlayers()) {
								player.sendMessage(Main.pr + "§fStart in §a3");
								player.sendTitle(" ", "§a3");

							}
						} else if (Starting.time == 2) {
							for (final Player player : Bukkit.getOnlinePlayers()) {
								player.sendMessage(Main.pr + "§fStart in §c2");
								player.sendTitle(" ", "§c2");

							}
						} else if (Starting.time == 1) {
							for (final Player player : Bukkit.getOnlinePlayers()) {
								player.sendMessage(Main.pr + "§fStart in §41");
								player.sendTitle(" ", "§41");

							}
						} else if (Starting.time == 11) {
							for (int x = 300; x < 350; x++) {
								for (int y = 300; y < 350; y++) {
									Random ran = new Random();
									int rand = ran.nextInt(2) + 1;
									if (rand == 1) {
										Bukkit.getWorld("World").getBlockAt(x, 150, y).setType(Material.SAND);

									} else {
										Bukkit.getWorld("World").getBlockAt(x, 150, y).setType(Material.GRAVEL);

									}
									Bukkit.getWorld("World").getBlockAt(x, 149, y).setType(Material.TNT);
								}
							}

						}

						--Starting.time;

					}
				}, 0L, 20L);
	}

	public static int getTime() {
		return Starting.time;
	}

	public static void cancelTask() {
		Bukkit.getScheduler().cancelTask(Starting.taskid);
	}
}
