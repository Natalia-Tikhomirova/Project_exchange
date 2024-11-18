package homework_48.project_exchange.dao;

import homework_48.project_exchange.model.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        return 0;
    }

    @Override
    public double calcMarge(double sum) {
        return 0;
    }

    @Override
    public void printTrans() {

    }


} // end of class

