package src;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import src.Config.HideConfig;
import src.Enums.Leather;
import src.ResourcesCalculate.Hide;

public class main {
    public static HideConfig hideCon = new HideConfig();

    public static void main(String[] args) {

        String result = "";
        Gson gson = new Gson();

        File file = new File("./src/Config/Hide.json");

        try {
            result = Files.readString(file.toPath());
            hideCon = gson.fromJson(result, HideConfig.class);

        } catch (Exception e) {

        }
        Scanner scan = new Scanner(System.in);
        Hide hide;

        int leatherTier;
        int leatherAmount;
        int leatherPrice;

        int counter = 2;

        if (!hideCon.isCalculateAll()) {
            System.out.println("Want tier of leather you wanna craft?");
            for (Leather a : Leather.values()) {
                System.out.println(counter + "." + " " + a);
                counter++;
            }
            leatherTier = scan.nextInt();
            if (leatherTier > counter || leatherTier < 1) {
                return;
            }
            System.out.println("Amount of leather you wanna craft?");
            leatherAmount = scan.nextInt();

            System.out.println("Price of the " + Leather.values()[leatherTier -
                    2].toString() + " leather:");
            leatherPrice = scan.nextInt();

            hide = new Hide(Leather.valueOf(Leather.values()[leatherTier -
                    2].toString()), leatherAmount, leatherPrice, hideCon.getFee());

            hide.Get_Hide_Price();
            hide.Calculate_Hide();
        } else {

        }
    }
}
