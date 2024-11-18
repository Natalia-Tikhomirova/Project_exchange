package homework_48.project_exchange.test;

import homework_48.project_exchange.dao.OperationsImpl;
import homework_48.project_exchange.model.Transaction;
import homework_48.project_exchange.view.CurrencyExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OperationsImplTest {

    private OperationsImpl operations;

    @BeforeEach
    void setUp() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, "USD", true, LocalDate.now(), 100, 1.5)); //продажа
        transactions.add(new Transaction(2, "EUR", false, LocalDate.now().minusDays(1), 200, 2.0)); //покупка
        transactions.add(new Transaction(3, "USD", true, LocalDate.now().minusDays(2), 300, 1.0)); //продажа
        transactions.add(new Transaction(4, "GPB", false, LocalDate.now().minusDays(3), 400, 1.2)); //покупка

        operations = new OperationsImpl(transactions);

    }

    @Test
    void addTrans() {
        int newTransNum = 5;
        Transaction add = operations.addTrans(newTransNum);
        assertEquals(newTransNum, add.getNumber());
    }


    @Test
    void removeTrans() {
        boolean res = operations.removeTrans(2);
        assertTrue(res);
        assertNull(operations.findTrans(2));
        boolean resNotFound = operations.removeTrans(111);
    }

    @Test
    void findTrans() {
        Transaction found = operations.findTrans(3);
        assertNotNull(found);
        assertEquals(3,found.getNumber());
        Transaction notFound = operations.findTrans(111);

    }

    @Test
    void findTransByDate() {
        LocalDate dateFrom = LocalDate.now().minusDays(2);
        LocalDate dateTo = LocalDate.now();
        List<Transaction> foundTrans = operations.findTransByDate(dateFrom, dateTo);
        assertNotNull(foundTrans);
        assertFalse(foundTrans.isEmpty());
        assertEquals(3, foundTrans.size());
    }

    @Test
    void findTransByType() {

        // так как true- продажа, false -покупка, находим транзакции данного типа
        // сначала проверяем, что метод возвращает только продажи
        List<Transaction> saleTransactions = operations.findTransByType(true);
        // исходя из тестового набора данных, ожидается 2 продажи
        assertEquals(2, saleTransactions.size());
        // проверяем валюты продаж
        assertEquals("USD", saleTransactions.get(0).getName());
        assertEquals("USD", saleTransactions.get(1).getName());

        // аналогичныу тесты для покупки
        List<Transaction> buyingTransactions = operations.findTransByType(false);
        assertEquals(2, buyingTransactions.size());
        assertEquals("EUR", buyingTransactions.get(0).getName());
        assertEquals("GPB", buyingTransactions.get(1).getName());
        
    }

    @Test
    void quantity() {
        int count = operations.quantity();
        // узнаем количество транзакций
        assertEquals(4, count);
    }

    @Test
    void calcRes() {
        // тестируем покупку USD
        double actualUsdBuying = operations.calcRes("USD", 100);

        double expectedUsdBuying = 100 / (CurrencyExchange.USD.getCurrent_exchange() + CurrencyExchange.USD.getCurrent_exchange() * 0.05);//узнаем сколько получим с покупки 100 долларов
        assertEquals(expectedUsdBuying, actualUsdBuying, 0.001);

        // продажa USD
        double actualUsdSell = operations.calcRes("USD", -100);
        double expectedUsdSell = 100 * (CurrencyExchange.USD.getCurrent_exchange() - CurrencyExchange.USD.getCurrent_exchange() * 0.05);// узнаем сколько получим с продажи 100 долларов
        assertEquals(expectedUsdSell, actualUsdSell, 0.001);

        // делаем тест на покупку не существующей валюты
        double invalidRes = operations.calcRes("INVALID", 100);
        assertEquals(0, invalidRes);// так как валюты не существует, то и результат получим 0
    }

    @Test
    void calcMarge() {
        // рассчитываем маржу для доллара
        double usdMargin = operations.calcMarge("USD");
        assertEquals(0.05 * CurrencyExchange.USD.getCurrent_exchange(), usdMargin, 0.001);

        // несуществующая валюта
        double invalidMargin = operations.calcMarge("INVALID");
        assertEquals(0, invalidMargin);// так как такой валюты нет, то и маржа будет 0
    }

} // end of class
