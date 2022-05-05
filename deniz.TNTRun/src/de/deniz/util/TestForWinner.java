
package de.deniz.util;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;

import de.deniz.main.Main;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;

public class TestForWinner {
	public static int taskid;

	static {
		TestForWinner.taskid = 1;
	}

	public static void WInningPLayer(final Player winner) {
		TestForWinner.taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) Main.getPlugin(),
				(Runnable) new Runnable() {
					int time = 10;

					@SuppressWarnings({ "deprecation", "rawtypes" })
					@Override
					public void run() {
						if (this.time == 0) {
							Bukkit.getScheduler().cancelTask(TestForWinner.taskid);
						}
						for (int a = 1; a < 50; ++a) {
							for (final Player player : Bukkit.getOnlinePlayers()) {
								player.sendMessage(String.valueOf(String.valueOf(Main.pr))
										+ "§c§lDAS SPIEL IST VORBEI! Damit ist §8§l" + winner.getName()
										+ "§c§l der Gewinner!");
								player.sendMessage("§7Jeder kann nun schreiben.");
								player.sendTitle("§4§l" + winner.getName(), "§aist der Gewinner! ");
								final PacketPlayOutTitle length = new PacketPlayOutTitle(20, 180, 20);
								((CraftPlayer) player).getHandle().playerConnection.sendPacket((Packet) length);
							}
							final Location loc = new Location(winner.getWorld(), winner.getLocation().getX(),
									winner.getLocation().getY(), winner.getLocation().getZ());
							final Firework firework = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
							final FireworkMeta fireworkmeta = firework.getFireworkMeta();
							fireworkmeta.addEffects(new FireworkEffect[] { FireworkEffect.builder()
									.withColor(Color.GREEN).with(FireworkEffect.Type.BALL_LARGE).build() });
							fireworkmeta.setPower(1);
							firework.setFireworkMeta(fireworkmeta);
						}
						--this.time;
					}
				}, 0L, 20L);
	}

	public static boolean testForWinBoolean() {
		return Main.getPlugin().getConfig().getInt("Anzahl.Spieler") == 1;
	}
}
