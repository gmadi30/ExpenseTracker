package utils;

import java.time.Month;

public class Utils {

    private Utils() {}

    public static String getDescriptionFromCommand(String command) {
        int index1 = command.indexOf("\"");
        int lastIndex = command.lastIndexOf("\"");
        return command.substring(index1+1, lastIndex);
    }

    public static double amountToDouble(String amount) {
        return Double.parseDouble(amount);
    }

    public static String[] splitCommand(String command) {
        return command.split(" ");
    }

    public static String findActionCommand(String command) {
        int indexFirstSpace = command.indexOf(" ");
        String actionCommand = command;
        if (indexFirstSpace != -1) {
            actionCommand = command.substring(0, indexFirstSpace).trim();
        }
        System.out.println(actionCommand);
        return actionCommand;
    }

    public static boolean isDescriptionEmpty(String command) {
        return getDescriptionFromCommand(command).isBlank();
    }

    public static boolean isAmountEmptyOrBlank(String command) {
        return getAmountFromCommand(command).isBlank();
    }

    public static String getAmountFromCommand(String command) {
        return command.substring(command.indexOf("--amount") + "--amount".length() + 1).trim();
    }

    public static String getIdFromCommand(String command) {
        return command.substring(command.indexOf("--id") + "--id".length() + 1).trim();
    }

    public static boolean isAmountValid(String command) {
        boolean isAmountValid = false;
        String amountFromCommand = getAmountFromCommand(command);
        try {
            double amount = amountToDouble(amountFromCommand);
            if (amount >= 0.0) {
                isAmountValid = true;
            }
        } catch (Exception e) {
            System.out.println("The input amount is not valid: " + amountFromCommand);
        }
        return isAmountValid;
    }

    public static boolean isIdValid(String command) {
        boolean isAmountValid = false;
        String idFromCommand = getIdFromUpdateCommand(command);
        try {
            int amount = Integer.parseInt(idFromCommand);
            if (amount >= 0) {
                isAmountValid = true;
            }
        } catch (Exception e) {
            System.out.println("The input id is not valid: " + idFromCommand);
        }
        return isAmountValid;
    }

    public static String getIdFromUpdateCommand(String command) {
       return command.substring(command.indexOf("--id") + "--id".length() + 1, command.indexOf("--description")).trim();
    }

    public static String getMontFromCommand(String command) {
        return command.substring(command.indexOf("--month") + "--month".length() + 1).trim();
    }

    public static boolean isMonthValid(String command) {
        String month = getMontFromCommand(command);
        int intMonth = 0;
        boolean isMonthValid = false;
        try {
            intMonth = Integer.parseInt(month);
            if (intMonth >= 1 && intMonth <= 12) {
                isMonthValid = true;
            }
        } catch (Exception e) {
            System.out.println("The input month is not valid");
        }

        return isMonthValid;
    }
}
