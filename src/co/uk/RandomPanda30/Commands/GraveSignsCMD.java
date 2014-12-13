package co.uk.RandomPanda30.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Files.Config;
import co.uk.RandomPanda30.Methods.GraveSignsMethods;

public class GraveSignsCMD implements CommandInterface {

	@Override
	public boolean onCommand(CommandSender cmdSender, Command cmd,
			String label, String[] args) {
		Player player = (Player) cmdSender;
		GraveSignsMethods.sendPlayerMessage(player,
				(String) Config.GRAVESIGN_GRAVESIGNS.value);
		return true;
	}

}
