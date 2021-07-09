package dev.maiky.labymodhud.util.labymod.methods;

import com.google.gson.JsonObject;
import dev.maiky.labymodhud.Main;
import dev.maiky.labymodhud.util.config.Settings;
import dev.maiky.labymodhud.util.labymod.LabyModProtocol;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.UUID;

/**
 * This project is owned by Maiky Perlee - © 2021
 */

public class LabyModBalance {

    private static @Getter HashMap<UUID, Double> cache = new HashMap<>();

    public static void updateBalanceDisplay(Player player, EnumBalanceType type, boolean visible, double balance ) {
        if (cache.containsKey(player.getUniqueId()) && cache.get(player.getUniqueId()) == balance)
            return;
        cache.put(player.getUniqueId(), balance);

        JsonObject economyObject = new JsonObject();
        JsonObject cashObject = new JsonObject();

        cashObject.addProperty( "visible", visible );

        cashObject.addProperty( "balance", balance );

        final String iconUrl = Settings.CUSTOM_ICON.getKeyAsString();
        if (!iconUrl.equals("")) {
            cashObject.addProperty("icon", iconUrl);
        }

        final String decimalFormat = Settings.DECIMAL_FORMAT.getKeyAsString();
        boolean failedSyntax = false;

        try {
            new DecimalFormat(decimalFormat).format(1);
        } catch (Exception exception) {
            failedSyntax = true;
        }

        JsonObject decimalObject = new JsonObject();
        if (!failedSyntax) {
            decimalObject.addProperty("format", decimalFormat);
            decimalObject.addProperty("divisor", 1);
            cashObject.add( "decimal", decimalObject );
        } else {
            decimalObject.addProperty("format", "€#,##0.00");
            decimalObject.addProperty("divisor", 1);
            cashObject.add( "decimal", decimalObject );

            Main.getInstance().getLogger().warning("De decimal format die je op dit moment gebruikt is fout! Pas deze zo snel mogelijk aan in je config of verwijder je config voor de standaard instellingen.");
        }

        economyObject.add(type.getKey(), cashObject);

        LabyModProtocol.sendLabyModMessage( player, "economy", economyObject );
    }

    public enum EnumBalanceType {
        CASH("cash"),
        BANK("bank");

        private final String key;

        EnumBalanceType(String key) {
            this.key = key;
        }

        public String getKey() {
            return this.key;
        }
    }

}
