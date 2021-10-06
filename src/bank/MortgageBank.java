package bank;

import address.Address;
import bank.currency.Currency;
import bankoperations.BankOperation;
import human.Human;
import bankoperations.сlient.Client;
import bankoperations.Mortgage;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

public class MortgageBank extends Bank {

    private Mortgage[] mortgages;

    public MortgageBank(String name, Address address, LocalDateTime foundedAt) {
        super(name, address, foundedAt);
    }

    public MortgageBank(String nameOfBank, Address address, LocalDateTime foundedAt, Currency usd, Currency eur, Currency rub, Currency byn) {
        super(nameOfBank, address, foundedAt, usd, eur, rub, byn);
    }

    public void setMortgages(Mortgage[] mortgages) {
        this.mortgages = mortgages;
    }

    public Mortgage[] getMortgages() {
        return mortgages;
    }

    public void addMortgage(Mortgage mortgage) {
        if (mortgages != null) {
            if (indexOfMortgage(mortgages, mortgage) == -1) {
                mortgages = add(mortgages, mortgage);
            } else {
                System.out.println("Credit already exist.");
            }
        } else {
            mortgages = add(mortgages, mortgage);
        }
    }

    public void removeMortgage(Mortgage mortgage) {
        if (mortgage == null || mortgage == null || mortgages.length == 0) {
            return;
        }
        mortgages = remove(mortgages, mortgage);
    }

    public Mortgage[] findMortgage(Client client) {
        Mortgage[] result = null;
        if (client == null) {
            return null;
        }
        if (mortgages != null && mortgages.length > 0) {
            result = new Mortgage[0];
            for (Mortgage element : mortgages) {
                if (element.getClient().getFirstName() == client.getFirstName() &&
                        element.getClient().getLastName() == client.getLastName()) {
                    result = add(result, element);
                }
            }
        }
        return result;
    }

    private int indexOfMortgage(Mortgage[] mortgages, Mortgage mortgage) {
        int result = -1;
        if (mortgages.length != 0) {
            int flag = 0;
            for (int i = 0; i < mortgages.length; i++) {
                flag++;
                if (mortgages[i].equals(mortgage)) {
                    result = i;
                    return result;
                }
            }
            if (flag == mortgages.length) {
                result = -1;
            }
        } else {
            result = -1;
        }
        return result;
    }

    private Mortgage[] add(Mortgage[] mortgages, Mortgage mortgage) {
        if (mortgage == null) {
            return mortgages;
        }
        Mortgage[] result;
        if (mortgages == null) {
            result = new Mortgage[1];
            result[0] = mortgage;
        } else {
            result = new Mortgage[mortgages.length + 1];
            result = copyThenInsert(mortgages, result, mortgage);
        }
        return result;
    }

    private Mortgage[] remove(Mortgage[] mortgages, Mortgage mortgage) {
        Mortgage[] result = new Mortgage[mortgages.length - 1];
        int index = indexOfMortgage(mortgages, mortgage);
        for (int i = 0, j = 0; i < mortgages.length; i++, j++) {
            if (i == index) {
                j--;
            } else {
                result[j] = mortgages[i];
            }
        }
        return result;
    }

    private Mortgage[] copyThenInsert(Mortgage[] copyThis, Mortgage[] intoThis, Mortgage thenInsertThis) {
        for (int i = 0; i < intoThis.length; i++) {
            if (i != intoThis.length - 1) {
                intoThis[i] = copyThis[i];
            } else {
                intoThis[i] = thenInsertThis;
            }
        }
        return intoThis;
    }

    @Override
    public void payTax() {
        System.out.printf("%-60s%s%s", "\n", "MORTGAGE BANK TAX:", "\n");
        System.out.println(super.getName() + " bank: ");
        if (super.getUsd().getAmount() > 0) {
            double tax = super.getUsd().getAmount() * 13 / 100;
            super.getUsd().setAmount(super.getUsd().getAmount() - tax);
            System.out.println("USD tax paid: " + tax);
        }
        if (super.getUsd().getAmount() > 0) {
            double tax = super.getEur().getAmount() * 13 / 100;
            super.getEur().setAmount(super.getEur().getAmount() - tax);
            System.out.println("EURO tax paid: " + tax);
        }
        if (super.getUsd().getAmount() > 0) {
            double tax = super.getUsd().getAmount() * 13 / 100;
            super.getUsd().setAmount(super.getUsd().getAmount() - tax);
            System.out.println("RUB tax paid: " + tax);
        }
        if (super.getUsd().getAmount() > 0) {
            double tax = super.getUsd().getAmount() * 13 / 100;
            super.getUsd().setAmount(super.getUsd().getAmount() - tax);
            System.out.println("BYN tax paid: " + tax);
        }
    }

    @Override
    public void printBankInformation() {
        System.out.printf("%-60s%s%s", "\n", "CREDIT BANK ININFORMATION:", "\n");
        System.out.println(super.getName() + ", located on " + super.getAddress().getCity() +
                ", " + super.getAddress().getStreet() + " " + super.getAddress().getHouseNumber() + " street, founded in " +
                super.getFoundedAt().getDayOfMonth() + "." + super.getFoundedAt().getMonth() + "." + super.getFoundedAt().getYear());
        System.out.println("Number of mortgage in bank: " + mortgages.length);
        System.out.println("Bank capital:");
        System.out.println(super.getUsd().getAmount() + " " + Currency.USD);
        System.out.println(super.getEur().getAmount() + " " + Currency.EURO);
        System.out.println(super.getRub().getAmount() + " " + Currency.RUB);
        System.out.println(super.getByn().getAmount() + " " + Currency.BYN);
    }

    @Override //POLYMORPH
    public void addOperation(BankOperation operation) {
        if (mortgages != null) {
            if (indexOfOperation(mortgages, operation) == -1) {
                mortgages = transform(add(mortgages, operation));
            } else {
                System.out.println("Credit already exist.");
            }
        } else {
            mortgages = transform(add(mortgages, operation));
        }
    }

    @Override //POLYMORPH
    public void removeOperation(BankOperation operation) {
        if (operation == null || mortgages == null || mortgages.length == 0) {
            return;
        }
        mortgages = transform(add(mortgages, operation));
    }

    /*@Override //POLYMORPH
    public BankOperation[] findOperation(Client client) {
        BankOperation[] result = null;
        if (client == null) {
            return null;
        }
        if (mortgages != null && mortgages.length > 0) {
            result = new BankOperation[0];
            for (BankOperation element : mortgages) {
                if (element.getClient().getFirstName() == client.getFirstName() &&
                        element.getClient().getLastName() == client.getLastName()) {
                    result = add(result, element);
                }
            }
        }
        return result;
    }*/
    @Override //POLYMORPH
    public BankOperation[] findOperation(Human human) {
        BankOperation[] result = null;
        if (human == null) {
            return null;
        }
        if (mortgages != null && mortgages.length > 0) {
            result = new BankOperation[0];
            for (BankOperation element : mortgages) {
                if (element.getClient().getFirstName() == human.getFirstName() &&
                        element.getClient().getLastName() == human.getLastName()) {
                    result = add(result, element);
                }
            }
        }
        return result;
    }

    public Mortgage[] transform(BankOperation[] bankOperations) {
        Mortgage[] mortgages = new Mortgage[bankOperations.length];
        for (int i = 0; i < bankOperations.length; i++) {
            mortgages[i] = (Mortgage) bankOperations[i];
        }
        return mortgages;
    }

    private int indexOfOperation(BankOperation[] operations, BankOperation operation) {
        int result = -1;
        if (operations.length != 0) {
            int flag = 0;
            for (int i = 0; i < operations.length; i++) {
                flag++;
                if (operations[i].equals(operation)) {
                    result = i;
                    return result;
                }
            }
            if (flag == operations.length) {
                result = -1;
            }
        } else {
            result = -1;
        }
        return result;
    }

    private BankOperation[] add(BankOperation[] operations, BankOperation operation) {
        if (operation == null) {
            return operations;
        }
        BankOperation[] result;
        if (operations == null) {
            result = new BankOperation[1];
            result[0] = operation;
        } else {
            result = new BankOperation[operations.length + 1];
            result = copyThenInsert(operations, result, operation);
        }
        return result;
    }

    private BankOperation[] copyThenInsert(BankOperation[] copyThis, BankOperation[] intoThis, BankOperation thenInsertThis) {
        for (int i = 0; i < intoThis.length; i++) {
            if (i != intoThis.length - 1) {
                intoThis[i] = copyThis[i];
            } else {
                intoThis[i] = thenInsertThis;
            }
        }
        return intoThis;
    }

    private BankOperation[] remove(BankOperation[] bankOperations, BankOperation operation) {
        BankOperation[] result = new BankOperation[bankOperations.length - 1];
        int index = indexOfOperation(bankOperations, operation);
        for (int i = 0, j = 0; i < bankOperations.length; i++, j++) {
            if (i == index) {
                j--;
            } else {
                result[j] = bankOperations[i];
            }
        }
        return result;
    }

    public Mortgage[] findMortgage(Human human) {
        Mortgage[] result = null;
        if (human == null) {
            return null;
        }
        if (mortgages != null && mortgages.length > 0) {
            result = new Mortgage[0];
            for (Mortgage element : mortgages) {
                if (element.getClient().getFirstName() == human.getFirstName() &&
                        element.getClient().getLastName() == human.getLastName()) {
                    result = add(result, element);
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Class MortgageBank [name = " + getName() + ", address = " + getAddress() + ", foundedAt = "
                + getFoundedAt().getDayOfMonth() + "." + getFoundedAt().getMonth() + "."
                + getFoundedAt().getYear() + ", count = " + count + ", usd = " + getUsd() + ", eur = "
                + getEur() + ", rub = " + getRub() + ", byn = " + getByn() + ", employees = " + getEmployees()
                + ", mortgages = " + mortgages + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        MortgageBank mortgageBank = (MortgageBank) object;
        return getUsd().equals(mortgageBank.getUsd()) && getEur().equals(mortgageBank.getEur()) && getRub().equals(mortgageBank.getRub())
                && getByn().equals(mortgageBank.getByn()) && Arrays.equals(getEmployees(), mortgageBank.getEmployees())
                && (getName() == mortgageBank.getName() || (getName() != null && getName().equals(mortgageBank.getName())))
                && getAddress().equals(mortgageBank.getAddress()) && getFoundedAt().equals(mortgageBank.getFoundedAt())
                && Arrays.equals(mortgages, mortgageBank.getMortgages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, getUsd(), getEur(), getRub(), getByn(), getEmployees(), getName(), getAddress(), getFoundedAt(), mortgages);
    }
}
