package nl.manthos.labymodtab.util.labymod;

import com.google.gson.JsonElement;
import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutCustomPayload;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class LabyModProtocol_18 implements Protocol {

    /**
     * Send a message to LabyMod
     * @param player Minecraft Client
     * @param key LabyMod message key
     * @param messageContent json object content
     */

    @Override
    public void sendLabyModMessage(Player player, String key, JsonElement messageContent ) {
        byte[] bytes = getBytesToSend( key, messageContent.toString() );

        PacketDataSerializer pds = new PacketDataSerializer( Unpooled.wrappedBuffer( bytes ) );
        PacketPlayOutCustomPayload payloadPacket = new PacketPlayOutCustomPayload( "labymod3:main", pds );
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket( payloadPacket );
    }
}
