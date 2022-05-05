
package de.deniz.command;

import de.deniz.util.Starting;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.deniz.main.Main;
import org.bukkit.command.CommandExecutor;

public class Start implements CommandExecutor {

	public boolean onCommand(final CommandSender sender, final Command command, final String label,
			final String[] args) {
		if (sender instanceof Player) {
			final Player p = (Player) sender;
			if (Main.Publisher.equals(p.getName())) {
				if (Main.starting == false && Main.stopped == false) {
					p.sendMessage(Main.pr + "§cEs läuft grade eine Runde!");
				}
				if (Main.Angemeldet.size() > 1) {
					Main.stopped = false;
					Main.starting = true;
					final int starttimer = Integer.parseInt(args[0]);
					if (starttimer < 12) {

						p.sendMessage(Main.pr + "&cBitte geben sie einen Starttimer über 11 Sekunden an!");
						return false;
					}
					Starting.startTimer(starttimer);
					p.sendMessage("Starting...");
				} else {
					p.sendMessage(Main.pr + "§cNicht genug Spieler");
				}
			} else {
				p.sendMessage(Main.np);
			}
		} else {
			System.out.println("Der Command ist nur als Spieler nutzbar!");
		}
		return false;
	}
}
