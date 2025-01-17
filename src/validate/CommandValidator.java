package validate;

public class CommandValidator {

    public static boolean addCommandValidator(String[] commands, String command) {
        if (commands.length != 5) {
            System.out.println("Invalid command syntax: " + command);
            System.out.println("Command should be like the following: " + "Ex. add --description \"Lunch\" --amount 20");
            return false;
        }

        if (!"--description".equals(commands[1])) {
            System.out.println("Invalid optional command syntax: " + commands[1]);
            System.out.println("Optional command should be like the following: " + "--description");
            return false;
        }

        if (!"--amount".equals(commands[3])) {
            System.out.println("Invalid optional command syntax: " + commands[3]);
            System.out.println("Optional command should be like the following: " + "--amount");
            return false;
        }

        if (Double.parseDouble(commands[4]) < 0) {
            System.out.println("Invalid amount cannot be less than 0: " + commands[3]);
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
}
