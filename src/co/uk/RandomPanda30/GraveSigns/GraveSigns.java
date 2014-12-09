package co.uk.RandomPanda30.GraveSigns;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.Events.OnPlayerDeathEvent;
import co.uk.RandomPanda30.Files.Config;
import co.uk.RandomPanda30.Methods.ConfigMethods;
import co.uk.stats.Metrics;

public class GraveSigns extends JavaPlugin {

	public static GraveSigns plugin;
	public static PluginDescriptionFile pdfFile;

	public static File config;
	public static FileConfiguration configC;
	public static ConfigurationSection configCS;

	public static ArrayList<Location> signLocations = new ArrayList<Location>();

	public final OnPlayerDeathEvent pde = new OnPlayerDeathEvent(this);

	public void onEnable() {
		plugin = this;
		pdfFile = this.getDescription();

		ConfigMethods.initConfig();

		getServer().getPluginManager().registerEvents(pde, plugin);

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
	}

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("GraveSigns is being disabled");
	}

	public static String formatMessage(String string) {
		return string.replaceAll("%TAG", (String) Config.TAG.value)
				.replaceAll("%N", (String) Config.NORMAL.value)
				.replaceAll("%W", (String) Config.WARNING.value)
				.replaceAll("%E", (String) Config.ERROR.value)
				.replaceAll("%A", (String) Config.ARG.value)
				.replaceAll("%H", (String) Config.HEADER.value)
				.replaceAll("(&([a-fk-or0-9]))", "\u00A7$2")
				.replaceAll("&u", "\n");
	}

}