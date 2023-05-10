package src.Enums;

public enum Leather {
    TIER2(2, 1, 0, 18),
    TIER3(3, 2, 8, 31),
    TIER4(4, 2, 16, 54),
    TIER5(5, 3, 32, 94),
    TIER6(6, 4, 64, 164),
    TIER7(7, 5, 128, 287),
    TIER8(8, 5, 256, 503);

    private int hideNeeded;
    private int leatherTier;
    private int itemValue;
    private int baseFocusCost;

    private Leather(int hideTier, int hideNeeded, int itemValue, int baseFocusCost) {
        this.leatherTier = hideTier;
        this.hideNeeded = hideNeeded;
        this.itemValue = itemValue;
        this.baseFocusCost = baseFocusCost;
    }

    public int getHideNeeded() {
        return hideNeeded;
    }

    public int getLeatherTier() {
        return leatherTier;
    }

    public int getItemValue() {
        return itemValue;
    }

    public int getBaseFocusCost() {
        return baseFocusCost;
    }
}
