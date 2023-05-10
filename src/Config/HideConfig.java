package src.Config;

public class HideConfig {
    boolean isFocus;
    boolean isBonus;

    public boolean isBonus() {
        return isBonus;
    }

    boolean calculateAll = false;
    boolean buyOrder = false;

    public boolean isBuyOrder() {
        return buyOrder;
    }

    int fee = 800;

    int TIER4_leather_spec = 100;
    int TIER5_leather_spec = 100;
    int TIER6_leather_spec = 100;
    int TIER7_leather_spec = 1;
    int TIER8_leather_spec = 1;

    int TIER_2_hide_price = 0;
    int TIER_3_hide_price = 0;
    int TIER_4_hide_price = 0;
    int TIER_5_hide_price = 0;
    int TIER_6_hide_price = 0;
    int TIER_7_hide_price = 0;
    int TIER_8_hide_price = 0;

    public boolean isCalculateAll() {
        return calculateAll;
    }

    public void setCalculateAll(boolean calculateAll) {
        this.calculateAll = calculateAll;
    }

    public int getTIER_2_hide_price() {
        return TIER_2_hide_price;
    }

    public void setTIER_2_hide_price(int tIER_2_hide_price) {
        TIER_2_hide_price = tIER_2_hide_price;
    }

    public int getTIER_3_hide_price() {
        return TIER_3_hide_price;
    }

    public void setTIER_3_hide_price(int tIER_3_hide_price) {
        TIER_3_hide_price = tIER_3_hide_price;
    }

    public int getTIER_4_hide_price() {
        return TIER_4_hide_price;
    }

    public void setTIER_4_hide_price(int tIER_4_hide_price) {
        TIER_4_hide_price = tIER_4_hide_price;
    }

    public int getTIER_5_hide_price() {
        return TIER_5_hide_price;
    }

    public void setTIER_5_hide_price(int tIER_5_hide_price) {
        TIER_5_hide_price = tIER_5_hide_price;
    }

    public int getTIER_6_hide_price() {
        return TIER_6_hide_price;
    }

    public void setTIER_6_hide_price(int tIER_6_hide_price) {
        TIER_6_hide_price = tIER_6_hide_price;
    }

    public int getTIER_7_hide_price() {
        return TIER_7_hide_price;
    }

    public void setTIER_7_hide_price(int tIER_7_hide_price) {
        TIER_7_hide_price = tIER_7_hide_price;
    }

    public int getTIER_8_hide_price() {
        return TIER_8_hide_price;
    }

    public void setTIER_8_hide_price(int tIER_8_hide_price) {
        TIER_8_hide_price = tIER_8_hide_price;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public boolean getIsFocus() {
        return isFocus;
    }
}
