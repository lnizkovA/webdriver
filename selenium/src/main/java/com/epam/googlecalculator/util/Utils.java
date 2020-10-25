package com.epam.googlecalculator.util;

import java.util.Locale;
import java.util.Scanner;

public class Utils {

    public static double convertStringToDouble(String line){
        Scanner scanner = new Scanner(line);
        scanner.useLocale(Locale.US);
        double value =0;
        while (scanner.hasNext()){
            if(scanner.hasNextDouble()){
                value = scanner.nextDouble();
                System.out.println("Found Actual cost =" + value);
                break;
            }
            scanner.next();
        }
        scanner.close();
        return value;
    }
}
