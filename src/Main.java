import service.ExpenseTracker;
import utils.Utils;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Application Expense Tracker stared");
        System.out.println("What would you like to do?");
        System.out.println("*********************************************************************************************");
        System.out.println("*                                              Menu                                         *");
        System.out.println("*********************************************************************************************");
        System.out.println("* Add an expense                    | Ex. add --description \"Lunch\" --amount 20             *");
        System.out.println("* Update an expense by id           | Ex. update --id 1 --description \"Lunch\" --amount 20   *");
        System.out.println("* Delete an expense                 | Ex. delete --id 2                                     *");
        System.out.println("* View all expenses                 | Ex. list                                              *");
        System.out.println("* View summary of all expenses      | Ex. summary                                           *");
        System.out.println("* View summary of expenses by month | Ex. summary --month 8                                 *");
        System.out.println("*********************************************************************************************");
        System.out.println("* Type \"exit\" to leave                                                                      *");
        System.out.println("*********************************************************************************************");

        while (true) {
            System.out.println("expense-tracker: ");
            String command = scanner.nextLine();

            // Split the command received to be read
            String actionCommand = Utils.findActionCommand(command.trim());
            String[] commands = Utils.splitCommand(command);
            System.out.println(Arrays.toString(commands));

            // Based on the first command we control the switch
            switch (actionCommand) {
                case "add":
                    ExpenseTracker.addExpense(command);
                    break;
                case "update":
                    ExpenseTracker.updateExpense(commands, command);
                    break;
                case "delete":
                    ExpenseTracker.deleteExpense(commands, command);
                    break;
                case "list":
                    ExpenseTracker.listExpenses();
                    break;
                case "summary":
                    ExpenseTracker.summary(commands);
                    break;
                case "exit":
                    System.out.println("Application Expense Tracker - finished ");
                    scanner.close();
                    return;
                default:
                    System.out.println("Unkown command ");
            }
        }
    }
}