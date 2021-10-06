package bank;

import address.Address;
import bank.employee.Employee;
import bank.organization.Organization;
import bankoperations.BankOperation;
import bank.currency.*;
import human.Human;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

public abstract class Bank extends Organization {

    public static int count = 0;

    private Currency usd;
    private Currency eur;
    private Currency rub;
    private Currency byn;
    private Employee[] employees;

    public Bank(String name, Address address, LocalDateTime foundedAt) {
        super(name, address, foundedAt);
        count++;
    }

    public Bank(String nameOfBank, Address address, LocalDateTime foundedAt, Currency usd, Currency eur, Currency rub, Currency byn) {
        this(nameOfBank, address, foundedAt);
        this.usd = usd;
        this.eur = eur;
        this.rub = rub;
        this.byn = byn;
    }

    public void setUsd(Currency usd) {
        this.usd = usd;
    }

    public Currency getUsd() {
        return usd;
    }

    public void setEur(Currency eur) {
        this.eur = eur;
    }

    public Currency getEur() {
        return eur;
    }

    public void setRub(Currency rub) {
        this.rub = rub;
    }

    public Currency getRub() {
        return rub;
    }

    public void setByn(Currency byn) {
        this.byn = byn;
    }

    public Currency getByn() {
        return byn;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    //ABSTRACT
    public abstract void printBankInformation();

    //ABSTRACT
    public abstract void addOperation(BankOperation operation);

    //ABSTRACT
    public abstract void removeOperation(BankOperation operation);

    //ABSTRACT
    /*public abstract BankOperation[] findOperation(Client client);*/
    //ABSTRACT
    public abstract BankOperation[] findOperation(Human human);

    @Override
    public String toString() {
        return "Class Bank [name = " + getName() + ", address = " + getAddress() + ", foundedAt = "
                + getFoundedAt().getDayOfMonth() + "." + getFoundedAt().getMonth() + "."
                + getFoundedAt().getYear() + ", count = " + count + ", usd = " + usd + ", eur = " +
                eur + ", rub = " + rub + ", byn = " + byn + ", employees = " + employees + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Bank bank = (Bank) object;
        return usd.equals(bank.getUsd()) && eur.equals(bank.getEur()) && rub.equals(bank.getRub())
                && byn.equals(bank.getByn()) && Arrays.equals(employees, bank.getEmployees())
                && (getName() == bank.getName() || (getName() != null && getName().equals(bank.getName())))
                && getAddress().equals(bank.getAddress()) && getFoundedAt().equals(bank.getFoundedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, usd, eur, rub, byn, employees, getName(), getAddress(), getFoundedAt());
    }
}