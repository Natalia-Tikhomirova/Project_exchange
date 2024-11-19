package homework_48.project_exchange.dao;

import homework_48.project_exchange.model.Transaction;
import homework_48.project_exchange.view.CurrencyExchange;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OperationsImpl implements Operations {

    List<Transaction> transactions;

    public OperationsImpl(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public void mainMenu() {

    }

    @Override
    public Transaction addTrans(int num) {



        return null;
    }

    @Override
    public Boolean removeTrans(int num) {
        // Ищем транзакцию по номеру и удаляем
        return transactions.removeIf(transaction -> transaction.getNumber() == num);
    }

    @Override
    public Transaction findTrans(int num) {
        // Возвращаем транзакцию по номеру
        for (Transaction transaction : transactions) {
            if (transaction.getNumber() == num) {
                return transaction;
            }
        }
        return null; // Если не найдена
    }

    @Override
    public List<Transaction> findTransByDate(LocalDate dateFrom, LocalDate dateTo) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction transaction : transactions) {
            LocalDate date = transaction.getDate();
            if ((date.isEqual(dateFrom) || date.isAfter(dateFrom)) &&
                    (date.isEqual(dateTo) || date.isBefore(dateTo))) {
                result.add(transaction);
            }
        }
        return result;
    }

    @Override
    public List<Transaction> findTransByType(boolean type) {

        List<Transaction> result = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.isType() == type) {
                result.add(transaction);
            }
        }
        return result;
    }

    @Override
    public int quantity() {
        return transactions.size();
    }

    @Override
    public double calcRes(String name, double amount) // название валюты и сумма для обмена
    // если сумма 'amount' положительная — это покупка, если отрицательная — это продажа.
    {
        CurrencyExchange currency = null; // ищем курс в enum
        for (CurrencyExchange ce : CurrencyExchange.values())
        {
            if (ce.getCurrency_codes().equals(name)) // название валюты "name" передано в этот метод
            {
                currency = ce;
                break;
            }
        }
        // если валюта не найдена, сообщаем об этом и возвращаем 0
        if (currency == null) {
            System.out.println("Currency with code " + name + " not found");
            return 0;
        }

        // получили курс из enum для валюты
        double rate = currency.getCurrent_exchange();

        // маржа для курса валюты, через метод calcMarge
        double margin = calcMarge(name);

        double rateWithMargin = 0; // курс с маржей

        if (amount < 0) { //  если < 0 = продажа, иначе покупка
            rateWithMargin = rate - margin;  // если продажа, то маржа вычитается
        } else {
            rateWithMargin = rate + margin;  // если покупка, то маржа добавляется
        }

        //  результат обмена с учетом маржи
        double result = 0;
        if (amount < 0) {  // опять, если сумма отрицательная — это продажа
            result = Math.abs(amount) * rateWithMargin;  // продаем валюту и получаем евро
        } else {  // Покупка
            result = Math.abs(amount) / rateWithMargin;  // покупаем валюту за евро
        }

        System.out.println("Result of the exchange " + result + " EUR");
        return result;
    }

    @Override
    public double calcMarge(String currencyName)
    {
        //  курс по названию валюты
        CurrencyExchange currency = null;
        for (CurrencyExchange ce : CurrencyExchange.values()) {
            if (ce.getCurrency_codes().equals(currencyName)) {
                currency = ce;
                break;
            }
        }

        //  валюта не найдена, тогда -> 0
        if (currency == null) {
            System.out.println("Currency with code " + currencyName + " not found.");
            return 0;
        }

        // вытаскиваем курс для данной валюты
        double rate = currency.getCurrent_exchange();

        //  маржа (5% от курса)
        double margin = rate * 0.05;
        return margin;
    }

    @Override
    public void saveToFile(String fileName) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(transactions);
            System.out.println("Transactions successfully saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    @Override
    public void loadToFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            transactions = (List<Transaction>) ois.readObject();
            System.out.println("Transactions successfully loaded from file: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading: " + e.getMessage());
        }
    }

    @Override
    public void printTrans() {
        if (transactions.isEmpty()) {
            System.out.println("The transaction list is empty.");
        } else {
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }

} // end of class

