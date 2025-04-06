package br.com.spherams.net.packet.out;

import br.com.spherams.model.Note;
import br.com.spherams.net.opcodes.SendOpcode;
import br.com.spherams.net.packet.ByteBufOutPacket;

import java.util.List;
import java.util.Objects;

import static br.com.spherams.tools.PacketCreator.getTime;

public final class ShowNotesPacket extends ByteBufOutPacket {

    public ShowNotesPacket(List<Note> notes) {
        super(SendOpcode.MEMO_RESULT);
        Objects.requireNonNull(notes);

        writeByte(3);
        writeByte(notes.size());
        notes.forEach(this::writeNote);
    }

    private void writeNote(Note note) {
        writeInt(note.id());
        writeString(note.from() + " "); //Stupid nexon forgot space lol
        writeString(note.message());
        writeLong(getTime(note.timestamp()));
        writeByte(note.fame());
    }
}
