package de.deniz.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.deniz.main.Main;

public class Anmelden implements CommandExecutor
{
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
           
            	Player target = Main.getPlugin().getServer().getPlayer(args[0]);
                if (target != null) {
                  //if(!Main.Angemeldet.contains(target)) {
                	  if(!(Main.stopped == false && Main.starting == false)) {
                		  Main.Angemeldet.add(target);
                    	  p.sendMessage(Main.pr + "§cSpieler hinzugefügt: §f"+target.getName());
                	  }else {

                    	  p.sendMessage(Main.pr + "§cEs läuft grade eine Runde.!");
                	  }
                  //}else {
                //	  p.sendMessage(Main.pr + "§cDer Spieler ist schon angemeldet.");
                 // }
                }
                
                else {
                    p.sendMessage(Main.nf);
                }
            
            
        }
        else {
            System.out.println("Der Command ist nur als Spieler nutzbar!");
        }
        return false;
    }
}
