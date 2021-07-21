package nl.manthos.labymodflags.util.labymod.methods;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import nl.manthos.labymodflags.util.labymod.LabyModProtocol;
import org.bukkit.entity.Player;

import java.util.UUID;

public class LabyModFlags {

    public void sendFlag(Player receiver, UUID uuid, String countryCode) {
        JsonObject flagPacket = new JsonObject();

        // Create array
        JsonArray users = new JsonArray();

        // Add user to array
        JsonObject userObject = new JsonObject();
        userObject.addProperty("uuid", uuid.toString()); // UUID of the flag player
        userObject.addProperty("code", countryCode); // The country code (e.g. "us", "de")
        users.add(userObject);

        // Add array to flag object packet
        flagPacket.add("users", users);

        LabyModProtocol.sendLabyModMessage(receiver, "language_flag", flagPacket);
    }
}
