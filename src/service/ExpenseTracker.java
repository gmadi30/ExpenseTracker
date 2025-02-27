package service;

import model.Expense;
import utils.Utils;
import validate.CommandValidator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ExpenseTracker {

   public static List<Expense> expenses = new ArrayList<>();

    public static void addExpense(String command) {
        if(CommandValidator.addCommandValidator(command)) {
            Expense expense = new Expense(
                    expenses.size() +1,
                    LocalDate.now(),
                    Utils.getDescriptionFromCommand(command),
                    Utils.amountToDouble(Utils.getAmountFromCommand(command))
            );

            expenses.add(expense);

            System.out.println("# Expense added succesfully (ID: " + expense.getId() + ")");
        }

    }

    public static void updateExpense(String command) {
        if (CommandValidator.updateCommandValidator(command)) {
            String id = Utils.getIdFromUpdateCommand(command);
            Expense expense = expenses
                    .stream()
                    .filter(e -> e.getId() == Integer.parseInt(id))
                    .findAny()
                    .orElse(null);

            if (expense == null) {
                System.out.println("Expense with ID: " + id + " was not found");
                return;
            }

            int expenseIndex = expenses.indexOf(expense);
            expense.setDescription(Utils.getDescriptionFromCommand(command));
            expense.setAmount(Utils.amountToDouble(Utils.getAmountFromCommand(command)));
            expenses.set(expenseIndex, expense);

            System.out.println("# Expense updated succesfully (ID: " + expense.getId() + ")");
        }
    }

    public static void deleteExpense(String command) {
        if (CommandValidator.deleteCommandValidator(command)) {
            String id = Utils.getIdFromCommand(command);
            Expense expense = expenses
                    .stream()
                    .filter(e -> e.getId() == Integer.parseInt(id))
                    .findAny().orElse(null);

            if (expense != null) {
                expenses.remove(expense);
                System.out.println("# Expense deleted succesfully (ID: " + expense.getId() + ")");
            } else {
                System.out.println("# Expense with ID " + id + " was not found");
            }

        }

    }

    public static void listExpenses() {
        System.out.println("# ID    Date            Description             Amount");
        for(Expense expense : expenses) {
            System.out.println(expense.toString());
        }
    }

    public static void summary(String command) {
        String[] commandArray = command.split(" ");

        if (commandArray.length == 1) {
            summary();
        } else {
            if (CommandValidator.summaryCommandValidator(command)) {
                summaryByMonth(Utils.getMonthFromCommand(command));
            }
        }
    }

    private static void summaryByMonth(String month) {
        double total = 0;
        int inputMonth = Integer.parseInt(month);

        for (Expense expense : expenses) {
            if (expense.getDate().getMonth().getValue() == inputMonth) {
                total += expense.getAmount();
            }
        }

        String monthLowerCase = Month.of(inputMonth).toString().toLowerCase();
        System.out.println("# Total expenses for " + monthLowerCase.substring(0,1).toUpperCase() + monthLowerCase.substring(1)+ ": $" + total);
    }

    private static void summary() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }

        System.out.println("# Total expenses: $" + total);
    }


    public static void saveExpensesToFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY.HH.mm.ss");
        String timestamp = LocalDateTime.now().format(formatter);


        try {
            Utils.writeFile(timestamp, expenses);
            System.out.println("JSON content written to file on desktop successfully.");

        } catch (IOException e) {
            System.out.println("An error occurred while saving the file " + e.getMessage());
        }
    }
}
