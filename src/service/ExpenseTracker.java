package service;

import model.Expense;
import validate.CommandValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseTracker {

    static List<Expense> expenses = new ArrayList<>();

    public static String[] splitCommand(String command) {
       String[] commands = command.split(" ");
       return commands;
    }

    public static void addExpense(String[] commands, String command) {
        if(CommandValidator.addCommandValidator(commands, command)) {
            Expense expense = new Expense(
                    expenses.size() +1,
                    LocalDate.now(),
                    commands[2],
                    Integer.parseInt(commands[4])
            );

            expenses.add(expense);

            System.out.println("# Expense added succesfully (ID: " + expense.getId() + ")");
        }

    }

    public static void updateExpense(String[] commands, String command) {
        if (CommandValidator.updateCommandValidator(commands, command)) {
            Expense expense = expenses
                    .stream()
                    .filter(e -> e.getId() == Integer.parseInt(commands[2]))
                    .findAny()
                    .orElse(null);

            if (expense == null) {
                System.out.println("Expense with ID: " + commands[2] + " was not found");
                return;
            }

            int expenseIndex = expenses.indexOf(expense);
            expense.setDescription(commands[4]);
            expense.setAmount(Double.parseDouble(commands[6]));
            expenses.set(expenseIndex, expense);

            System.out.println("# Expense updated succesfully (ID: " + expense.getId() + ")");
        }
    }

    public static void deleteExpense(String[] commands, String command) {
        if (CommandValidator.deleteCommandValidator(commands, command)) {
            Expense expense = expenses
                    .stream()
                    .filter(e -> e.getId() == Integer.parseInt(commands[2]))
                    .findAny()
                    .get();

            expenses.remove(expense);
            System.out.println("# Expense deleted succesfully (ID: " + expense.getId() + ")");
        }

    }

    public static void listExpenses() {
        System.out.println("# ID    Date            Description             Amount");
        for(Expense expense : expenses) {
            System.out.println(expense.toString());
        }
    }

    public static void summary() {
    }
}
