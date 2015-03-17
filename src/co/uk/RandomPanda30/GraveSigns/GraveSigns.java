package co.uk.RandomPanda30.GraveSigns;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.Commands.ClearCMD;
import co.uk.RandomPanda30.Commands.CommandHandler;
import co.uk.RandomPanda30.Commands.GraveSignsCMD;
import co.uk.RandomPanda30.Events.OnPlayerDeathEvent;
import co.uk.RandomPanda30.Files.Config;
import co.uk.RandomPanda30.Methods.ConfigMethods;
import co.uk.RandomPanda30.Methods.GraveSignsMethods;
import co.uk.stats.Metrics;

public class GraveSigns extends JavaPlugin {
	
	// Check block break. If the sign is in the list!
	// Add effects

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

		registerCommands();

		GraveSignsMethods.sendConsoleMessage((String) Config.TAG.value);

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
		GraveSignsMethods.sendConsoleMessage((String) Config.TAG.value);

		for (Location loc : GraveSigns.signLocations) {
			if (loc.getBlock().getState().getType().equals(Material.SIGN_POST)
					|| loc.getBlock().getState().getType()
							.equals(Material.SIGN)) {
				loc.getBlock().setType(Material.AIR);
			}
		}
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

	public void registerCommands() {
		CommandHandler handler = new CommandHandler();
		handler.register("gravesigns", new GraveSignsCMD());
		handler.register("gs", new GraveSignsCMD());
		
		handler.register("clear", new ClearCMD());
		getCommand("gravesigns").setExecutor(handler);
	}

}