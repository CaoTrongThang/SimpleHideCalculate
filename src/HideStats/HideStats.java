package src.HideStats;

import src.main;

public class HideStats {
    private double hide_Price = 0;
    private double hide_Needed = 0;
    private double fee = 0;

    public HideStats(double hide_Needed, double fee) {
        this.hide_Needed = hide_Needed;
        this.fee = fee;
    }

    public HideStats(double price) {
        this.hide_Price = price;
    }

    public double getHideNeeded() {
        return hide_Needed;
    }

    public void setHideNeeded(double hide) {
        this.hide_Needed = hide;
    }

    public int getCraftFee() {
        return (int) fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getHidePrice() {
        return hide_Price;
    }

    public int getTotalHideCost() {
        if (main.hideCon.isBuyOrder()) {
            double cost = hide_Needed * hide_Price;
            double costAfterBuyOrder = cost + (cost * 2.5 / 100);

            return (int) costAfterBuyOrder;
        } else {
            return (int) (hide_Needed * hide_Price);
        }
    }
}
