package nl.manthos.labymodtab.util.labymod;

import com.google.gson.JsonElement;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;
import org.bukkit.entity.Player;

import java.nio.charset.StandardCharsets;

public interface Protocol {

    void sendLabyModMessage(Player player, String key, JsonElement messageContent );

    default byte[] getBytesToSend( String messageKey, String messageContents ) {
        // Getting an empty buffer
        ByteBuf byteBuf = Unpooled.buffer();

        // Writing the message-key to the buffer
        writeString( byteBuf, messageKey );

        // Writing the contents to the buffer
        writeString( byteBuf, messageContents );

        // Copying the buffer's bytes to the byte array
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes( bytes );

        // Release the buffer
        byteBuf.release();

        // Returning the byte array
        return bytes;
    }

    default void writeVarIntToBuffer(ByteBuf buf, int input ) {
        while ( (input & -128) != 0 ) {
            buf.writeByte( input & 127 | 128 );
            input >>>= 7;
        }

        buf.writeByte( input );
    }

    default void writeString( ByteBuf buf, String string ) {
        byte[] abyte = string.getBytes(StandardCharsets.UTF_8);

        if ( abyte.length > Short.MAX_VALUE ) {
            throw new EncoderException( "String too big (was " + string.length() + " bytes encoded, max " + Short.MAX_VALUE + ")" );
        } else {
            writeVarIntToBuffer( buf, abyte.length );
            buf.writeBytes( abyte );
        }
    }

    default int readVarIntFromBuffer(ByteBuf buf ) {
        int i = 0;
        int j = 0;

        byte b0;
        do {
            b0 = buf.readByte();
            i |= (b0 & 127) << j++ * 7;
            if ( j > 5 ) {
                throw new RuntimeException( "VarInt too big" );
            }
        } while ( (b0 & 128) == 128 );

        return i;
    }

    default String readString( ByteBuf buf, int maxLength ) {
        int i = readVarIntFromBuffer( buf );

        if ( i > maxLength * 4 ) {
            throw new DecoderException( "The received encoded string buffer length is longer than maximum allowed (" + i + " > " + maxLength * 4 + ")" );
        } else if ( i < 0 ) {
            throw new DecoderException( "The received encoded string buffer length is less than zero! Weird string!" );
        } else {
            byte[] bytes = new byte[i];
            buf.readBytes( bytes );

            String s = new String( bytes, StandardCharsets.UTF_8);
            if ( s.length() > maxLength ) {
                throw new DecoderException( "The received string length is longer than maximum allowed (" + i + " > " + maxLength + ")" );
            } else {
                return s;
            }
        }
    }
}
