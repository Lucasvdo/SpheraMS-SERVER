package br.com.spherams.net.packet.out;

import br.com.spherams.net.opcodes.SendOpcode;
import br.com.spherams.net.packet.ByteBufOutPacket;

public final class SendNoteSuccessPacket extends ByteBufOutPacket {

    public SendNoteSuccessPacket() {
        super(SendOpcode.MEMO_RESULT);

        writeByte(4);
    }
}
