package nl.manthos.labymodtab.util.labymod.methods;

import com.google.gson.JsonObject;
import nl.manthos.labymodtab.util.labymod.LabyModProtocol;
import org.bukkit.entity.Player;

public class LabyModBanner {

    public static void sendServerBanner(Player player, String imageUrl) {
        JsonObject object = new JsonObject();
        object.addProperty("url", imageUrl);
        LabyModProtocol.sendLabyModMessage(player, "server_banner", object);
    }
}
