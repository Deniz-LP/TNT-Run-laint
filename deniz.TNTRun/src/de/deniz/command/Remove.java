
package de.deniz.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.deniz.main.Main;

public class Remove implements CommandExecutor {

	public boolean onCommand(final CommandSender sender, final Command command, final String label,
			final String[] args) {
		if (sender instanceof Player) {
			final Player p = (Player) sender;
			if (Main.Publisher.equals(p.getName()) || Main.Admin.equals(p.getName())) {

				for (int x = 280; x < 370; x++) {
					for (int y = 0; y < 148; y++) {
						for (int z = 280; z < 370; z++) {

							Bukkit.getWorld("World").getBlockAt(x, y, z).setType(Material.AIR);
						}
					}
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

//fill 250 92 400 400 92 250 air