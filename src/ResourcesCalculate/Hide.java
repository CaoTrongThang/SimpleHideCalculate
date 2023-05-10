package src.ResourcesCalculate;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.main;
import src.Config.HideConfig;
import src.Enums.Leather;
import src.HideStats.*;;

public class Hide {
    public static final double RRR_WITHOUT_FOCUS = 0.367;
    public static final double RRR_WITH_FOCUS = 0.539;
    public static final double RRR_WITH_BONUS = 0.404;
    public static final double RRR_WITH_BONUS_AND_FOCUS = 0.559;

    public static final double TAX_WITHOUT_PREMIUM = 8;
    public static final double TAX_WITH_PREMIUM = 4;

    public static final double SETUP_FEE = 2.5;

    public static final int FOCUS_EFFECIENCY_HIDES = 30;
    public static final int FOCUS_EFFECIENCY_SPECIALIST = 250;

    private Leather leather_Tier;
    private long craft_Amount;
    private long leather_Price;
    private double fee;

    List<HideStats> hideList = new ArrayList<>();

    public Hide(Leather Hide_Tier, long Craft_Amount, long leather_Price, long fee) {
        this.leather_Tier = Hide_Tier;
        this.craft_Amount = Craft_Amount;
        this.leather_Price = leather_Price;
        this.fee = fee;
    }

    public Hide(Leather Hide_Tier, long Craft_Amount, long leather_Price, long fee, boolean focus) {
        this.leather_Tier = Hide_Tier;
        this.craft_Amount = Craft_Amount;
        this.leather_Price = leather_Price;
        this.fee = fee;
    }

    public void Get_Hide_Price() {
        for (Field f : main.hideCon.getClass().getDeclaredFields()) {
            try {
                if (f.getName().contains("hide_price")) {
                    Field t = main.hideCon.getClass().getDeclaredField(f.getName());
                    t.setAccessible(true);
                    hideList.add(new HideStats(t.getInt(main.hideCon)));
                }
            } catch (Exception e) {

            }
        }
    }

    public double Calculate_Hide() {
        DecimalFormat df = new DecimalFormat("###,###.##");

        int counter = 0;
        int totalCost = 0;
        int totalFocusEffeciency = 0;

        for (Field f : main.hideCon.getClass().getDeclaredFields()) {
            if (f.getName().contains("spec")) {
                if (f.getName().contains(leather_Tier.toString())) {
                    try {
                        Field t = main.hideCon.getClass().getDeclaredField(f.getName());
                        t.setAccessible(true);
                        totalFocusEffeciency = totalFocusEffeciency
                                + (t.getInt(main.hideCon) * FOCUS_EFFECIENCY_SPECIALIST)
                                + (t.getInt(main.hideCon) * FOCUS_EFFECIENCY_HIDES);
                        continue;

                    } catch (Exception e) {

                    }
                    break;
                }

                try {
                    Field t = main.hideCon.getClass().getDeclaredField(f.getName());
                    t.setAccessible(true);
                    totalFocusEffeciency += (t.getInt(main.hideCon) * FOCUS_EFFECIENCY_HIDES);

                } catch (Exception e) {

                }
            }
        }

        for (Leather l : Leather.values()) {
            if (l == leather_Tier) {
                if (main.hideCon.getIsFocus()) {
                    if (main.hideCon.isBonus()) {
                        hideList.get(counter).setHideNeeded(craft_Amount * l.getHideNeeded());
                        hideList.get(counter)
                                .setFee(craft_Amount / (1 - RRR_WITH_BONUS_AND_FOCUS)
                                        * (l.getItemValue() * 0.1125)
                                        * (fee / 100));
                        break;
                    } else {
                        hideList.get(counter).setHideNeeded(craft_Amount * l.getHideNeeded());
                        hideList.get(counter)
                                .setFee(craft_Amount / (1 - RRR_WITH_FOCUS) * (l.getItemValue() * 0.1125)
                                        * (fee / 100));
                        break;
                    }
                } else {
                    if (main.hideCon.isBonus()) {
                        hideList.get(counter).setHideNeeded(craft_Amount * l.getHideNeeded());
                        hideList.get(counter)
                                .setFee(craft_Amount / (1 - RRR_WITH_BONUS)
                                        * (l.getItemValue() * 0.1125)
                                        * (fee / 100));
                        break;
                    } else {
                        hideList.get(counter).setHideNeeded(craft_Amount * l.getHideNeeded());
                        hideList.get(counter)
                                .setFee(craft_Amount / (1 - RRR_WITHOUT_FOCUS) * (l.getItemValue() * 0.1125)
                                        * (fee / 100));
                        break;
                    }
                }
            }
            if (main.hideCon.isBonus()) {
                hideList.get(counter).setHideNeeded((craft_Amount * (1 * RRR_WITH_BONUS)) * l.getHideNeeded());
                hideList.get(counter)
                        .setFee(craft_Amount / (1 - RRR_WITH_BONUS) * (l.getItemValue() * 0.1125)
                                * (fee / 100));
                counter++;
            } else {
                hideList.get(counter).setHideNeeded((craft_Amount - (craft_Amount * 36.7 / 100)) * l.getHideNeeded());
                hideList.get(counter)
                        .setFee(craft_Amount / (1 - RRR_WITHOUT_FOCUS) * (l.getItemValue() * 0.1125) * (fee / 100));
                counter++;
            }
        }

        counter = 0;
        for (Leather l : Leather.values()) {
            if (counter < hideList.size()) {
                HideStats holder = hideList.get(counter);
                if (!(holder.getHideNeeded() == 0)) {
                    System.out.println();
                    System.out.println(l.toString() + " HIDE PRICE: " + holder.getHidePrice());
                    System.out.println(l.toString() + " HIDE NEEDED: " + holder.getHideNeeded());
                    System.out.println("TOTAL COST TO BUY " + holder.getHideNeeded() + " HIDE: "
                            + holder.getTotalHideCost());
                    if (main.hideCon.isBonus()) {
                        System.out.println(
                                "TOTAL FEE COST TO CRAFT " + Math.round(craft_Amount / (1 - RRR_WITH_BONUS))
                                        + " LEATHER: "
                                        + holder.getCraftFee());
                    } else {
                        System.out.println(
                                "TOTAL FEE COST TO CRAFT " + Math.round(craft_Amount / (1 - RRR_WITHOUT_FOCUS))
                                        + " LEATHER: "
                                        + holder.getCraftFee());
                    }
                    System.out.println(
                            "TOTAL COST (HIDE BUY + FEE): " + (holder.getTotalHideCost() + holder.getCraftFee()));
                }
            } else {
                break;
            }
            counter++;
        }

        for (HideStats h : hideList) {
            if (main.hideCon.isBuyOrder()) {
                totalCost += (h.getTotalHideCost()) + h.getCraftFee();
            } else {
                totalCost += h.getTotalHideCost() + h.getCraftFee();
            }

        }

        long totalCrafted;
        long totalProfitAfterRRR;
        double totalSetUpAndTaxCost;
        double totalProfitAfterSetupFeeAndTax;

        double priceCanSell;
        double totalProfitWithLowestPriceCanSell;

        if (!main.hideCon.getIsFocus()) {
            if (main.hideCon.isBonus()) {
                totalCrafted = (int) (craft_Amount / (1 - RRR_WITH_BONUS));
            } else {
                totalCrafted = (int) (craft_Amount / (1 - RRR_WITHOUT_FOCUS));
            }
            totalProfitAfterRRR = totalCrafted * leather_Price;
            totalProfitAfterSetupFeeAndTax = (totalProfitAfterRRR
                    - (totalProfitAfterRRR * (SETUP_FEE + TAX_WITH_PREMIUM) / 100)) - totalCost;
            totalSetUpAndTaxCost = totalProfitAfterRRR * (SETUP_FEE + TAX_WITH_PREMIUM) / 100;

            priceCanSell = (totalCost / totalCrafted);
            totalProfitWithLowestPriceCanSell = (priceCanSell + 1) * totalCrafted
                    + -(totalCost);

            System.out.println();

            System.out.println("TOTAL COST = -" + df.format(totalCost));
            System.out.println("LEATHER CRAFTED (BASE) = " + craft_Amount);
            System.out.println(
                    "LEATHER FROM RRR (RESOURCE RETURN RATE) (WITHOUT FOCUS) = " + (totalCrafted - craft_Amount));
            System.out.println("TOTAL LEATHER CRAFTED (WITHOUT FOCUS) = " + totalCrafted);
            System.out.println("PROFIT FROM " + totalCrafted + " " + leather_Tier.toString()
                    + " LEATHER (" + leather_Price + "/EACH): "
                    + df.format(totalProfitAfterRRR));
            System.out
                    .println("TOTAL SETUP AND TAX COST = -"
                            + df.format(Math.round(totalSetUpAndTaxCost)) + " SILVER");
            System.out.println("PROFIT PER " + leather_Tier + " LEATHER: "
                    + (leather_Price - (leather_Price * TAX_WITH_PREMIUM / 100)));
            System.out
                    .println("TOTAL PROFIT (TOTAL LEATHER PRICE - TOTAL COST - TAX & SETUP FEE) = "
                            + df.format(Math.round(totalProfitAfterSetupFeeAndTax)) + " SILVER");
            if (totalProfitAfterSetupFeeAndTax > 0) {
                System.out.println("YOU CAN SELL YOUR LEATHER FOR "
                        + priceCanSell + " SILVER AND STILL PROFIT " + totalProfitWithLowestPriceCanSell
                        + " SILVER FROM "
                        + totalCrafted + " " + leather_Tier + " LEATHER");
                return totalProfitAfterSetupFeeAndTax;
            }
        } else {
            if (main.hideCon.isBonus()) {
                totalCrafted = (int) (craft_Amount / (1 - RRR_WITH_BONUS_AND_FOCUS));
            } else {
                totalCrafted = (int) (craft_Amount / (1 - RRR_WITH_FOCUS));
            }
            totalProfitAfterRRR = totalCrafted * leather_Price;
            totalProfitAfterSetupFeeAndTax = (totalProfitAfterRRR
                    - (totalProfitAfterRRR * (SETUP_FEE + TAX_WITH_PREMIUM) / 100)) - totalCost;
            totalSetUpAndTaxCost = totalProfitAfterRRR * (SETUP_FEE + TAX_WITH_PREMIUM) / 100;

            priceCanSell = (totalCost / totalCrafted);
            totalProfitWithLowestPriceCanSell = (priceCanSell + 1) * totalCrafted
                    + -(totalCost);

            double totalFocusCostBase = craft_Amount
                    * (leather_Tier.getBaseFocusCost() / Math.pow(2, ((double) totalFocusEffeciency / 10000)));

            double totalFocusCost = totalCrafted
                    * (leather_Tier.getBaseFocusCost() / Math.pow(2, ((double) totalFocusEffeciency / 10000)));

            System.out.println();

            System.out.println("TOTAL COST = -" + df.format(totalCost));
            System.out.println("LEATHER CRAFTED (BASE) = " + craft_Amount);
            System.out.println(
                    "LEATHER FROM RRR (RESOURCE RETURN RATE) (WITH FOCUS) = " + (totalCrafted - craft_Amount));
            System.out.println(
                    "TOTAL FOCUS COST TO CRAFT EACH = " + ((double) totalFocusEffeciency / totalCrafted) + " FOCUS");
            System.out.println(
                    "TOTAL FOCUS COST TO CRAFT " + craft_Amount + " (BASE) = " + totalFocusCostBase + " FOCUS");
            System.out.println("TOTAL FOCUS COST TO CRAFT " + totalCrafted + " = " + totalFocusCost + " FOCUS");
            System.out.println("TOTAL LEATHER CRAFTED (WITH FOCUS)= " + totalCrafted);
            System.out.println("PROFIT FROM " + totalCrafted + " " + leather_Tier.toString()
                    + " LEATHER (" + leather_Price + "/EACH): "
                    + df.format(totalProfitAfterRRR));
            System.out
                    .println("TOTAL SETUP AND TAX COST = -"
                            + df.format(Math.round(totalSetUpAndTaxCost)) + " SILVER");
            System.out.println("PROFIT PER " + leather_Tier + " LEATHER: "
                    + (leather_Price - (leather_Price * TAX_WITH_PREMIUM / 100)));
            System.out
                    .println("TOTAL PROFIT (TOTAL LEATHER PRICE - TOTAL COST - TAX & SETUP FEE) = "
                            + df.format(Math.round(totalProfitAfterSetupFeeAndTax)) + " SILVER");
            if (totalProfitAfterSetupFeeAndTax > 0) {
                System.out.println("YOU CAN SELL YOUR LEATHER FOR "
                        + priceCanSell + " SILVER AND STILL PROFIT " + totalProfitWithLowestPriceCanSell
                        + " SILVER FROM "
                        + totalCrafted + " " + leather_Tier + " LEATHER");
            }
            return totalProfitAfterSetupFeeAndTax;

        }
        return 0;

    }
}
