package homework_48.project_exchange.model;

// Тема 5: Пункт обмена валюты:
//
//количество валют - не боле 10
//основная валюта - EUR
//маржинальность при обмене - 5% от биржевого курса
//добавление/удаление/редактирование и поиск транзакций по обмену валюты по датам в интервале ОТ и ДО
//сохранение и считывание списка транзакций по обмену валюты из файла
//виды транзакций:
//продажа
//покупка
//просмотр отчета по объему транзакций по обмену валюты по виду транзакций:
//продажа
//покупка

import java.time.LocalDate;
import java.util.Objects;

public class Transaction implements Comparable<Transaction> {

    private int number;
    private String name;
    private boolean type; // true -  продажа, false -покупка
    private LocalDate date;
    private double res;
    private double marge; // моржа

    public Transaction(int number,
                       String name,
                       boolean type,
                       LocalDate date,
                       double res,
                       double marge)
    {
        this.number = number;
        this.name = name;
        this.type = type;
        this.date = date;
        this.res = res;
        this.marge = marge;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public boolean isType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getRes() {
        return res;
    }

    public double getMarge() {
        return marge;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setRes(double res) {
        this.res = res;
    }

    public void setMarge(double marge) {
        this.marge = marge;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("exchange.currencyExchange.model.Transaction{");
        sb.append("number=").append(number);
        sb.append(", name='").append(name).append('\'');
        sb.append(", type=").append(type);
        sb.append(", date=").append(date);
        sb.append(", res=").append(res);
        sb.append(", marge=").append(marge);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return number == that.number && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, date);
    }

    @Override
    public int compareTo(Transaction o) {
        return this.getDate().compareTo(o.getDate());
    }


} // end of class

