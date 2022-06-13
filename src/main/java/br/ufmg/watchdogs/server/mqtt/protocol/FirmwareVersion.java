package br.ufmg.watchdogs.server.mqtt.protocol;

public enum FirmwareVersion {
    V1(0b0000);

    private final Integer version;

    FirmwareVersion(Integer version) {
        this.version = version;
    }

    public Integer getVersion() {
        return version;
    }
}
