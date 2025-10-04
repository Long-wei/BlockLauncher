package org.bkl.modloader;

public enum ModLoaderType {
    FORGE("Forge"),
    FABRIC("Fabric"),
    QUILT("Quilt"),
    RIFT("Rift"),
    UNKNOWN("Unknown");

    private final String displayName;

    ModLoaderType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
