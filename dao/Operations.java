package homework_48.project_exchange.dao;

import homework_48.project_exchange.model.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface Operations {

    void mainMenu();

    Transaction addTrans(int num);

    Boolean removeTrans(int num);

    Transaction findTrans(int num);

    List<Transaction> findTransByDate(LocalDate dateFrom, LocalDate dateTo); // Natalia

    List<Transaction> findTransByType(boolean type); // Natalia

    int quantity(); // Natalia

    double calcRes(String name);

    double calcMarge(double sum);

    void saveToFile(String fileName); // Natalia

    void loadToFile(String fileName); // Natalia

    void printTrans();

} // end of class

