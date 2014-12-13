package co.uk.RandomPanda30.Commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Files.Config;
import co.uk.RandomPanda30.Methods.GraveSignsMethods;

public class CommandHandler implements CommandExecutor {

	private static HashMap<String, CommandInterface> commands = new HashMap<String, CommandInterface>();

	public void register(String name, CommandInterface cmd) {
		commands.put(name, cmd);
	}

	public boolean exists(String name) {
		return commands.containsKey(name);
	}

	public CommandInterface getExecutor(String name) {
		return commands.get(name);
	}

	@Override
	public boolean onCommand(CommandSender cmdSender, Command cmd,
			String label, String[] args) {
		Player player = null;
		if (cmdSender instanceof Player) {
			player = (Player) cmdSender;
			if (args.length == 0) {
				getExecutor("gravesigns").onCommand(cmdSender, cmd, label, args);
				return true;
			}

			if (args.length > 0) {
				if (exists(args[0])) {
					getExecutor(args[0]).onCommand(cmdSender, cmd, label, args);
					return true;
				} else {
					GraveSignsMethods.sendPlayerMessage(player,
							(String) Config.CRITICAL_COMMANDNULL.value);
					return true;
				}
			}
		} else {
			GraveSignsMethods
					.sendConsoleMessage((String) Config.CRITICAL_PLAYERONLY.value);
		}
		return false;
	}

}
