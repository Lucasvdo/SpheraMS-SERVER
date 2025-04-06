package br.com.spherams.net.server.coordinator.session;

import br.com.spherams.net.server.Server;

import java.time.Instant;

import static java.util.concurrent.TimeUnit.DAYS;

record HostHwid(Hwid hwid, Instant expiry) {
    static HostHwid createWithDefaultExpiry(Hwid hwid) {
        return new HostHwid(hwid, getDefaultExpiry());
    }

    private static Instant getDefaultExpiry() {
        return Instant.ofEpochMilli(Server.getInstance().getCurrentTime() + DAYS.toMillis(7));
    }
}
