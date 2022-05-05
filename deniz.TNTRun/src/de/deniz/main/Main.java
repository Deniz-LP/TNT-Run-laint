
package de.deniz.main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.deniz.command.Anmelden;
import de.deniz.command.Remove;
import de.deniz.command.Start;
import de.deniz.listener.ImportantList;
import de.deniz.listener.QuitAndJoinListener;

public class Main extends JavaPlugin {
	private static Main plugin;
	public static String pr;
	public static String np;
	public static String nf;
	public static String Publisher;
	public static ArrayList<Player> Living;
	public static ArrayList<Player> Dead;
	public static ArrayList<Player> OnlinePlayer;
	public static ArrayList<Player> Angemeldet;
	public static int taskid;
	public static boolean stopped;
	public static boolean starting;
	public static String noPerms;
	public static int countdownTime;

	static {
		Main.pr = "§d§l[§4§lT§e§lN§4§lT§f§l-Run§d§l] ";
		Main.np = String.valueOf(String.valueOf(Main.pr)) + "§4§lDazu hast du keine Rechte.";
		Main.nf = String.valueOf(String.valueOf(Main.pr)) + "§c§lSpieler wurde nicht gefunden.";
		Main.Publisher = "Deniz_LP";
		Main.Living = new ArrayList<Player>();
		Main.Dead = new ArrayList<Player>();
		Main.OnlinePlayer = new ArrayList<Player>();
		Main.Angemeldet = new ArrayList<Player>();
		Main.taskid = 0;
		Main.noPerms = "NoPerms";
		Main.countdownTime = 3600;
	}

	public void onEnable() {

		for (Player p : Bukkit.getOnlinePlayers()) {
			Main.OnlinePlayer.add(p);
		}

		this.getConfig().set("Anzahl.Spieler", (Object) 0);
		this.saveConfig();
		Main.stopped = true;
		Main.starting = false;

		Main.plugin = this;
		final PluginManager pm = Bukkit.getPluginManager();
		System.out.println("Das ist ein Test!");

		this.getCommand("start").setExecutor((CommandExecutor) new Start());
		this.getCommand("anmelden").setExecutor((CommandExecutor) new Anmelden());
		this.getCommand("remove").setExecutor((CommandExecutor) new Remove());

		pm.registerEvents((Listener) new ImportantList(), (Plugin) this);
		pm.registerEvents((Listener) new QuitAndJoinListener(), (Plugin) this);

	}

	public static Main getPlugin() {
		return Main.plugin;
	}

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Ok.");
	}
}
