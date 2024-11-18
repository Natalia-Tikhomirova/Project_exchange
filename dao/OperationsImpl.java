package homework_48.project_exchange.dao;

import homework_48.project_exchange.model.Transaction;
import homework_48.project_exchange.view.CurrencyExchange;

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
        return null;
    }

    @Override
    public Transaction findTrans(int num) {
        return null;
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
    public double calcRes(String name) {
        Scanner scanner = new Scanner(System.in);

        // спрашиваем у пользователя сумму валюты для обмена
        System.out.println("Enter the number of the currency to be exchanged with: ");
        double amount = scanner.nextDouble();  // сумма, кот. пользователь хочет обменять

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

        double rateWithMargin = 0;

        // уточняем тип операции (покупка или продажа) ?
        System.out.println("Input the type of transaction (1 - buy, 2 - sell): ");
        int operationType = scanner.nextInt();

        //  1 - покупка, 2 - продажа
        // считаем курс с маржей
        double margin = rate * 0.05; // 5% от курса
        if (operationType == 2) {  // продажа
            //  маржа вычитается из курса
            rateWithMargin = rate - margin;
        } else {  // покупка
            // маржа добавляется к курсу
            rateWithMargin = rate + margin;
        }

        // Рассчитываем результат обмена с учетом маржи
        double result = 0;
        if (operationType == 2) {  // продажа
            result = amount * rateWithMargin;  // продаем валюту и получаем евро
        } else {  // покупка
            result = amount / rateWithMargin;  // покупаем валюту за евро
        }

        // результат обмена
        System.out.println("Exchange result: " + result + " EUR");  // результат в EUR
        return result;
    }

    @Override
    public double calcMarge(double sum) {
        return 0;
    }

    @Override
    public void printTrans() {

    }


} // end of class

