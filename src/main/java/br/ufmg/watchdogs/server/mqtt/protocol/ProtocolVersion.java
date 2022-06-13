package br.ufmg.watchdogs.server.mqtt.protocol;

public enum ProtocolVersion {
    V1(0b0000);

    private final Integer version;

    ProtocolVersion(Integer version) {
        this.version = version;
    }

    public Integer getVersion() {
        return version;
    }
}
