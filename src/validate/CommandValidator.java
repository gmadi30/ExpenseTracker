package validate;

import utils.Utils;

public class CommandValidator {

    public static final String UPDATE_VALID_COMMAND_MESSAGE = "Command should be like the following: " + "Ex. update --id 1 --description \"Lunch\" --amount 20";
    public static final String DELETE_VALID_COMMAND_MESSAGE = "Command should be like the following: " + "Ex. delete --id 2";
    public static final String ADD_VALID_COMMAND_MESSAGE = "Command should be like the following: " + "Ex. add --description \"Lunch\" --amount 20";

    public static boolean addCommandValidator(String command) {

        // Validates the command has description and amount options
        if (!command.contains("--description") || !command.contains("--amount") ) {
            System.out.println("Invalid command syntax: " + command);
            System.out.println(ADD_VALID_COMMAND_MESSAGE);
            return false;
        }

        // Validates the description option is not empty or blank
        if (Utils.isDescriptionEmpty(command)) {
            System.out.println("The description for the Expense cannot be empty: " + command);
            System.out.println(ADD_VALID_COMMAND_MESSAGE);
        }

        // Validates the amount option is not empty or blank
        if (Utils.isAmountEmptyOrBlank(command)){
            System.out.println("The amount for the Expense cannot be empty: " + command);
            System.out.println(ADD_VALID_COMMAND_MESSAGE);
            return false;
        }

        // Validates the amount option is > 0
        if (!Utils.isAmountValid(command)){
            System.out.println("The amount for the Expense cannot be empty or less than 0: " + command);
            System.out.println(ADD_VALID_COMMAND_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean deleteCommandValidator(String command) {

        // Validates the command has id option
        if (!command.contains("--id")) {
            System.out.println("Invalid command syntax: " + command);
            System.out.println(DELETE_VALID_COMMAND_MESSAGE);
            return false;
        }

        // Validates the id is valid
        if (!Utils.isIdValid(command)){
            System.out.println("Invalid id syntax: " + command);
            System.out.println(DELETE_VALID_COMMAND_MESSAGE);
            return false;
        }

        return true;
    }

    public static boolean updateCommandValidator(String command) {
        if (!command.contains("--id") || !command.contains("--description") || !command.contains("--amount")  ) {
            System.out.println("Invalid optional command syntax: " + command);
            System.out.println(UPDATE_VALID_COMMAND_MESSAGE);
            return false;
        }

        // Validates the description option is not empty or blank
        if (Utils.isDescriptionEmpty(command)) {
            System.out.println("The description for the Expense cannot be empty: " + command);
            System.out.println(UPDATE_VALID_COMMAND_MESSAGE);
        }

        // Validates the id is valid
        if (!Utils.isIdValid(command)){
            System.out.println("Invalid id syntax: " + command);
            System.out.println(UPDATE_VALID_COMMAND_MESSAGE);
            return false;
        }

        // Validates the amount option is > 0
        if (!Utils.isAmountValid(command)){
            System.out.println("The amount for the Expense cannot be empty or less than 0: " + command);
            System.out.println(UPDATE_VALID_COMMAND_MESSAGE);
            return false;
        }
        return true;
    }
}
