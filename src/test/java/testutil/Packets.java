package testutil;

import io.netty.buffer.Unpooled;
import br.com.spherams.net.packet.ByteBufInPacket;
import br.com.spherams.net.packet.ByteBufOutPacket;
import br.com.spherams.net.packet.InPacket;
import br.com.spherams.net.packet.OutPacket;

import java.util.function.Consumer;

public class Packets {

    public static InPacket buildInPacket(Consumer<OutPacket> contentProvider) {
        OutPacket builderInput = new ByteBufOutPacket();
        contentProvider.accept(builderInput);
        return new ByteBufInPacket(Unpooled.wrappedBuffer(builderInput.getBytes()));
    }
}
