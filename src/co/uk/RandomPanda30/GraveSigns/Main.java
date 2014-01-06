package co.uk.RandomPanda30.GraveSigns;

import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import co.uk.stats.Metrics;

public class Main extends JavaPlugin {

	public static GraveSignsListener GraveSignsListener;

	public static String tag = "§5[§6GraveSigns§5]";

	public static Main plugin;
	public final GraveSignsListener listener = new GraveSignsListener(this);

	public void onEnable() {

		getServer().getPluginManager().registerEvents(this.listener, this);

		FileConfiguration config = this.getConfig();

		config.addDefault("Line1", null);
		config.addDefault("Line2", null);
		config.addDefault("Line3", null);

		config.options().copyDefaults(true);
		this.saveConfig();

		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
			FileConfiguration metrics_fc = new YamlConfiguration();
			metrics_fc.load(metrics.getConfigFile());
			if (!metrics_fc.getBoolean("opt-out", false)) {
				Bukkit.getConsoleSender().sendMessage(
						"Sending MCStats to their servers!");
			} else {
				Bukkit.getConsoleSender()
						.sendMessage(
								"Error sending MCStats to their servers. Is MCStats disabled?");
			}
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage(
					"Could not send stats MCStats to their servers!");
		} catch (InvalidConfigurationException e) {
			Bukkit.getConsoleSender().sendMessage(
					"Could not send stats MCStats to their servers!");
		}

		Bukkit.getConsoleSender().sendMessage(
				tag + " GraveSigns is being enabled");
	}

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("GraveSigns is being disabled");
	}

}