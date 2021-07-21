package nl.manthos.labymodflags.models;

import java.util.UUID;

public class Country  {

    private UUID playerUUID;
    private String country;

    public Country(UUID playerUUID, String country) {
        this.playerUUID = playerUUID;
        this.country = country;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

        public String getCountry() {
        return country;
    }

    public void setPlayerUUID(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
