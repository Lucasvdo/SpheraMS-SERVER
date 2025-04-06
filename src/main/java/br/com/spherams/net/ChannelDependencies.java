package br.com.spherams.net;

import br.com.spherams.client.processor.npc.FredrickProcessor;
import br.com.spherams.service.NoteService;

import java.util.Objects;

public record ChannelDependencies(NoteService noteService, FredrickProcessor fredrickProcessor) {

    public ChannelDependencies {
        Objects.requireNonNull(noteService);
        Objects.requireNonNull(fredrickProcessor);
    }
}
