package br.com.spherams.net.packet.logging;

import br.com.spherams.net.packet.Packet;

public interface PacketLogger {
    void log(Packet packet);
}
