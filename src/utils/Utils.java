package utils;

import model.Expense;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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

    public static boolean isIdValid(String command, String id) {
        boolean isAmountValid = false;
        try {
            int amount = Integer.parseInt(id);
            if (amount >= 0) {
                isAmountValid = true;
            }
        } catch (Exception e) {
            System.out.println("The input id is not valid: " + id);
        }
        return isAmountValid;
    }

    public static String getIdFromUpdateCommand(String command) {
       return command.substring(command.indexOf("--id") + "--id".length() + 1, command.indexOf("--description")).trim();
    }

    public static String getMonthFromCommand(String command) {
        return command.substring(command.indexOf("--month") + "--month".length() + 1).trim();
    }

    public static boolean isMonthValid(String command) {
        String month = getMonthFromCommand(command);
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

    public static void writeFile(String timestamp,  List<Expense> expenses) throws IOException {
        String fileName = "C:\\Users\\georg\\Desktop\\ " + timestamp +"expenses.txt";
        FileWriter fw = new FileWriter(fileName);
        fw.write("# \tID\tDate\tDescription\tAmount \n");
        for (Expense expense : expenses) {
            fw.write("#"
                    + "\t" +expense.getId()
                    + "\t" + expense.getDate()
                    + "\t" + expense.getDescription()
                    + "\t" + expense.getAmount()+"\t" + "\n");
        }
        fw.close();
    }
}
