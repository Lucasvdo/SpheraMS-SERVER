package br.com.spherams.net.encryption;

import br.com.spherams.net.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {
    private final MapleAESOFB sendCypher;

    public PacketEncoder(MapleAESOFB sendCypher) {
        this.sendCypher = sendCypher;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet in, ByteBuf out) {
        byte[] packet = in.getBytes();
        out.writeBytes(getEncodedHeader(packet.length));

        MapleCustomEncryption.encryptData(packet);
        sendCypher.crypt(packet);
        out.writeBytes(packet);
    }

    private byte[] getEncodedHeader(int length) {
        return sendCypher.getPacketHeader(length);
    }
}
