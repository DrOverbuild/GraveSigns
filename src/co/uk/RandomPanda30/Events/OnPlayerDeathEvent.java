package co.uk.RandomPanda30.Events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import co.uk.RandomPanda30.GraveSigns.GraveSigns;
import co.uk.RandomPanda30.Methods.GraveSignsMethods;

public class OnPlayerDeathEvent implements Listener {

	public OnPlayerDeathEvent (GraveSigns plugin) {
		GraveSigns.plugin = plugin;
	}

	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event) {
		Player player;
		if (event.getEntity() instanceof Player) {
			player = event.getEntity();
			if (player.hasPermission("gravesigns.place")) {
				Location location = player.getLocation();
				GraveSignsMethods.placeGraveSign(location, player);
			}
		}
	}

}
