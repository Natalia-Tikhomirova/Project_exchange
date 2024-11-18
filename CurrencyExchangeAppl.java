package homework_48.project_exchange;

import homework_48.project_exchange.view.CurrencyExchange;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CurrencyExchangeAppl {

    public static void main(String[] args)
    {
        List<CurrencyExchange> currencyExchangeList = new ArrayList<>(); // список валют с курсами из ENUM
        Scanner scanner = new Scanner(System.in);

        for (CurrencyExchange currency : CurrencyExchange.values())
        {
            currencyExchangeList.add(currency); // добавление валют в список
        }

        // вывод на экран списка валют и курсов
        System.out.println("Initial list of currencies and exchange rates:");
        printList(currencyExchangeList);

        // вызов метода поиска/удаления дубликатов
        checkRemoveDuplicate(currencyExchangeList);
        System.out.println("List of available currencies and their exchange rates to euro:");
        printList(currencyExchangeList);

        System.out.println("Enter the number of the currency to be exchanged with ");
        int choice = -1;
        while (choice < 0 || choice >= currencyExchangeList.size())
        {
            choice = scanner.nextInt();  // выбор пользователем валюты по номеру

            if (choice < 0 || choice >= currencyExchangeList.size())
            {
                System.out.println("Invalid input! Try again please...");
            }
        }

        System.out.println("Input the amount of money in: " + currencyExchangeList.get(choice).getCurrency_codes());
        double amountOfMoney = scanner.nextDouble(); // ввод пользователем суммы для обмена

        // вывод на экран всей информации по обмену валют
        System.out.println(amountOfMoney + " " + currencyExchangeList.get(choice).getCurrency_codes() + " <=> " + currencyExchanger(amountOfMoney, currencyExchangeList.get(choice).getCurrent_exchange()) + "EUR");
    }


    private static double currencyExchanger(double amountOfMoney, double currencyExchange)
    {
        return amountOfMoney * currencyExchange;
    }

    public static List<CurrencyExchange> checkRemoveDuplicate(List<CurrencyExchange> currencyExchangeList)
    {
        List<CurrencyExchange> unDuplicateList = new ArrayList<>();

        for (int i = 0; i < currencyExchangeList.size() / 2; i++)
        {
            for (int j = currencyExchangeList.size() / 2; j < currencyExchangeList.size(); j++)
            {
                if(currencyExchangeList.get(i).getCurrency_codes().equals(currencyExchangeList.get(j).getCurrency_codes()))
                {
                    currencyExchangeList.remove(i);
                }
            }
        }
        return currencyExchangeList;
    }

    private static void printList(List<CurrencyExchange> currencyExchangeList)
    {
        for (CurrencyExchange rate : currencyExchangeList)
        {
            System.out.println(rate.ordinal() + " => " + rate + " ");  // с порядковым номером
        }
        System.out.println("\n------------------------------------------------------------------------");
    }



} // end of class

