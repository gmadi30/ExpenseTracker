package utils;

public class Utils {

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
        String actionCommand = "unknown";
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
}
