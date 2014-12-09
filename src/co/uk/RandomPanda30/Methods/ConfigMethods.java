package co.uk.RandomPanda30.Methods;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.Files.Config;
import co.uk.RandomPanda30.GraveSigns.GraveSigns;

public class ConfigMethods {

	public static void initConfig() {
		try {
			GraveSigns.config = new File("plugins/"
					+ GraveSigns.pdfFile.getName(), "config.yml");
			if (!GraveSigns.config.exists()) {
				GraveSigns.config.getParentFile().mkdirs();
				GraveSigns.config.createNewFile();
			}

			GraveSigns.configC = new YamlConfiguration();
			GraveSigns.configCS = GraveSigns.configC
					.getConfigurationSection("");
			GraveSigns.configC.load(GraveSigns.config);

			for (Config value : Config.values()) {
				if (GraveSigns.configCS.get(value.name().replaceAll("_", ".")) == null) {
					GraveSigns.configCS.set(value.name().replaceAll("_", "."),
							value.value);
					GraveSigns.configC.save(GraveSigns.config);
				}

				value.value = GraveSigns.configCS.get(value.name().replaceAll(
						"_", "."));
			}

			GraveSigns.configC.load(GraveSigns.config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
