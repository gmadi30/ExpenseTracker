package validate;

import utils.Utils;

public class CommandValidator {

    public static boolean addCommandValidator(String command) {

        // Validates the command has description and amount options
        if (!command.contains("--description") || !command.contains("--amount") ) {
            System.out.println("Invalid command syntax: " + command);
            System.out.println("Command should be like the following: " + "Ex. add --description \"Lunch\" --amount 20");
            return false;
        }

        // Validates the description option is not empty or blank
        if (Utils.isDescriptionEmpty(command)) {
            System.out.println("The description for the Expense cannot be empty: " + command);
            System.out.println("Command should be like the following: " + "Ex. add --description \"Lunch\" --amount 20");
        }

        // Validates the amount option is not empty or blank
        if (Utils.isAmountEmptyOrBlank(command)){
            System.out.println("The amount for the Expense cannot be empty: " + command);
            System.out.println("Command should be like the following: " + "Ex. add --description \"Lunch\" --amount 20");
            return false;
        }

        // Validates the amount option is > 0
        if (!Utils.isAmountValid(command)){
            System.out.println("The amount for the Expense cannot be empty: " + command);
            System.out.println("Command should be like the following: " + "Ex. add --description \"Lunch\" --amount 20");
            return false;
        }
        return true;
    }

    public static boolean deleteCommandValidator(String[] commands, String command) {
        if (commands.length != 3) {
            System.out.println("Invalid command syntax: " + command);
            System.out.println("Command should be like the following: " + "Ex. delete --id 2");
            return false;
        }

        if (!"--id".equals(commands[1])) {
            System.out.println("Invalid optional command syntax: " + commands[1]);
            System.out.println("Optional command should be like the following: " + "--id");
            return false;
        }

        return true;
    }

    public static boolean updateCommandValidator(String[] commands, String command) {
        if (commands.length != 7) {
            System.out.println("Invalid command syntax: " + command);
            System.out.println("Command should be like the following: " + "update --id 1 --description \"Lunch\" --amount 20");
            return false;
        }

        if (!"--id".equals(commands[1])) {
            System.out.println("Invalid optional command syntax: " + commands[1]);
            System.out.println("Optional command should be like the following: " + "--id");
            return false;
        }

        if (!"--description".equals(commands[3])) {
            System.out.println("Invalid optional command syntax: " + commands[3]);
            System.out.println("Optional command should be like the following: " + "--description");
            return false;
        }

        if (!"--amount".equals(commands[5])) {
            System.out.println("Invalid optional command syntax: " + commands[5]);
            System.out.println("Optional command should be like the following: " + "--amount");
            return false;
        }

        if (Double.parseDouble(commands[6]) < 0) {
            System.out.println("Invalid amount, it cannot be less than 0: " + commands[6]);
            return false;
        }
        return true;
    }
}
