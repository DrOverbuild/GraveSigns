package co.uk.RandomPanda30.Commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Files.Config;
import co.uk.RandomPanda30.GraveSigns.GraveSigns;
import co.uk.RandomPanda30.Methods.GraveSignsMethods;

public class ClearCMD implements CommandInterface {

	public boolean onCommand(CommandSender cmdSender, Command cmd,
			String label, String[] args) {
		Player player = (Player) cmdSender;
		if ((Boolean) Config.COMMANDS_CLEAR_ENABLE.value) {
			if (player.hasPermission("gravesigns.clear")) {
				if (args.length > 1) {
					GraveSignsMethods.sendPlayerMessage(player,
							(String) Config.COMMANDS_CLEAR_SYNTAX.value);
					return true;
				}
				GraveSignsMethods.sendPlayerMessage(player,
						(String) Config.GRAVESIGNS_CLEARED.value);
				for (Location loc : GraveSigns.signLocations) {
					if (loc.getBlock().getState().getType()
							.equals(Material.SIGN_POST)
							|| loc.getBlock().getState().getType()
									.equals(Material.SIGN)) {
						loc.getBlock().setType(Material.AIR);
					}
				}
			} else {
				GraveSignsMethods.sendPlayerMessage(player,
						(String) Config.CRITICAL_NOPERM.value);
			}
		} else {
			GraveSignsMethods.sendPlayerMessage(player,
					(String) Config.CRITICAL_CMDNOTENABLED.value.toString()
							.replaceAll("cmd", cmd.toString()));
		}
		return true;
	}

}
