package co.uk.RandomPanda30.GraveSigns;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class GraveSignsListener implements Listener {

	public GraveSignsListener (Main plugin) {
		Main.plugin = plugin;

	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		final Player p = e.getEntity();
		if (p.hasPermission("gravesigns.place")) {
			Location l = p.getLocation();
			l.setY(l.getY());
			World newBlock = l.getWorld();
			final Block nb = newBlock.getBlockAt(l);

			if (nb.isLiquid()) {
				p.sendMessage("\u00A73[GraveSigns]"
						+ "\u00A75 Could not place your GraveSign as you are in a liquid!");
				return;
			}
			if (p.isFlying()) {
				p.sendMessage("\u00A73[GraveSigns]"
						+ "\u00A75 Could not place your GraveSign as you are flying!");
				return;
			}

			nb.setType(Material.SIGN_POST);
			Sign s = (Sign) nb.getState();

			String player = p.getName();

			String Line1 = Main.plugin.getConfig().getString("Line1");
			String Line2 = Main.plugin.getConfig().getString("Line2");
			String Line3 = Main.plugin.getConfig().getString("Line3");

			s.setLine(0, Line1);
			s.setLine(1, Line2);
			s.setLine(2, Line3);
			s.setLine(3, player);
			s.update();

			Main.plugin.getServer().getScheduler()
					.scheduleSyncDelayedTask(Main.plugin, new Runnable() {

						@Override
						public void run() {
							if (nb.isEmpty()) {
								return;
							} else {
								nb.setType(Material.AIR);
								p.sendMessage("\u00A73[GraveSigns] "
										+ "\u00A75Your Grave Sign has despawned");
							}
						}

					}, 6000L);
		}
	}
}