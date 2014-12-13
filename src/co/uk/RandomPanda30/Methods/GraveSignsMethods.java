package co.uk.RandomPanda30.Methods;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Files.Config;
import co.uk.RandomPanda30.GraveSigns.GraveSigns;

public class GraveSignsMethods {

	@SuppressWarnings("unchecked")
	public static void placeGraveSign(Location signLocation, Player player) {
		World world = signLocation.getWorld();
		Block block = world.getBlockAt(signLocation);
		if (block.isLiquid()) {
			sendPlayerMessage(player, (String) Config.CRITICAL_INLIQUID.value);
			return;
		}

		if (player.isFlying()) {
			sendPlayerMessage(player, (String) Config.CRITICAL_INFLYING.value);
			return;
		}

		if (player.getGameMode() == GameMode.CREATIVE) {
			sendPlayerMessage(player, (String) Config.CRITICAL_INCREATIVE.value);
			return;
		}

		block.setType(Material.SIGN_POST);
		GraveSigns.signLocations.add(signLocation);
		sendPlayerMessage(player, (String) Config.GRAVESIGN_PLACED.value);
		Sign sign = (Sign) block.getState();
		List<String> lines = (ArrayList<String>) Config.SIGN_TEXT.value;
		startDespawnTimer(signLocation, player);
		int lineCount = 0;
		for (String line : lines) {
			if (lineCount <= 3) {
				sign.setLine(lineCount, GraveSigns.formatMessage(line)
						.replaceAll("#", player.getName()));
			}
			lineCount = lineCount + 1;
		}
		sign.update();
	}

	public static void startDespawnTimer(final Location signLocation,
			final Player player) {
		Integer rawTime = (Integer) Config.SIGN_DESPAWN_TIMER.value * 20;
		Long time = new Long(rawTime);
		GraveSigns.plugin.getServer().getScheduler()
				.scheduleSyncDelayedTask(GraveSigns.plugin, new Runnable() {

					@Override
					public void run() {
						Block block = signLocation.getWorld().getBlockAt(
								signLocation);
						if (block.isEmpty()) {
							return;
						} else {
							block.setType(Material.AIR);
							GraveSigns.signLocations.remove(signLocation);
							sendPlayerMessage(player,
									(String) Config.GRAVESIGN_REMOVED.value);
						}
					}

				}, time);
	}

	public static void sendPlayerMessage(Player player, String message) {
		player.sendMessage(GraveSigns.formatMessage(message));
	}

	public static void sendConsoleMessage(String message) {
		Bukkit.getConsoleSender()
				.sendMessage(GraveSigns.formatMessage(message));
	}

}
