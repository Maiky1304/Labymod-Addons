package nl.manthos.labymodflags.util.storage;

import com.google.gson.Gson;
import nl.manthos.labymodflags.Main;
import nl.manthos.labymodflags.models.Country;
import org.bukkit.Note;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.UUID;

public class CountryStorage {

    private static Main main = Main.getInstance();
    private static ArrayList<Country> countries = new ArrayList<>();

    public static Country setCountry(UUID uuid, String countryCode) throws IOException {

        Country country = new Country(uuid, countryCode);
        countries.add(country);
        saveCountries();
        return country;
    }

    public static Country getCountry(UUID uuid) {
        for (Country country : countries) {
            if (country.getPlayerUUID().equals(uuid)) {
                return country;
            }
        }
        return null;
    }

    public static void deleteCountry(UUID uuid, String countryCode) throws IOException {
        for (Country country : countries) {
            if (country.getPlayerUUID().equals(uuid)) {
                countries.remove(uuid);
            }
        }
        saveCountries();
    }

    public static Country updateCountry(UUID uuid, Country counTry) throws IOException {
        for (Country country : countries) {
            if (country.getPlayerUUID().equals(uuid)) {
                country.setPlayerUUID(counTry.getPlayerUUID());
                country.setCountry(counTry.getCountry());
                saveCountries();
                return country;
            }
        }
        return null;
    }

    public static void saveCountries() throws IOException {

        Gson gson = new Gson();
        File file = new File(main.getDataFolder().getAbsolutePath() + "/countries.json");
        file.getParentFile().mkdir();
        file.createNewFile();

        Writer writer = new FileWriter(file, false);
        gson.toJson(countries, writer);
        writer.flush();
        writer.close();
        main.getLogger().info("Countries has been saved!");
    }
}
