package bank;

import address.Address;
import bank.employee.Employee;
import bank.organization.Organization;
import bankoperations.сlient.Client;
import bankoperations.*;
import human.Human;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

public class BankSystem extends Organization {

    private final static double USDBUY = 2.495;
    private final static double USDSELL = 2.515;
    private final static double EUROBUY = 2.9;
    private final static double EUROSELL = 2.912;
    private final static double RUBBUY = 3.42;
    private final static double RUBSELL = 3.46;

    private Bank[] banks;

    public BankSystem(String name, Address address, LocalDateTime foundedAt) {
        super(name, address, foundedAt);
        banks = new Bank[0];
    }

    public void setBanks(Bank[] banks) {
        this.banks = banks;
    }

    public Bank[] getBanks() {
        return banks;
    }

    @Override
    public void payTax() {
        System.out.printf("%-60s%s%s", "\n", "BANK SYSTEM TAX:", "\n");
        System.out.println(super.getName() + " bank system: ");
        double usdTax = 0;
        double eurTax = 0;
        double rubTax = 0;
        double bynTax = 0;
        for (Bank element : banks) {
            if (element.getUsd().getAmount() > 0) {
                usdTax += element.getUsd().getAmount() * 13 / 100;
                element.getUsd().setAmount(element.getUsd().getAmount() - usdTax);
            }
            if (element.getEur().getAmount() > 0) {
                eurTax += element.getEur().getAmount() * 13 / 100;
                element.getEur().setAmount(element.getEur().getAmount() - eurTax);
            }
            if (element.getRub().getAmount() > 0) {
                rubTax += element.getRub().getAmount() * 13 / 100;
                element.getRub().setAmount(element.getRub().getAmount() - rubTax);
            }
            if (element.getByn().getAmount() > 0) {
                bynTax += element.getByn().getAmount() * 13 / 100;
                element.getByn().setAmount(element.getByn().getAmount() - bynTax);
            }
        }
        System.out.println("USD tax paid: " + usdTax);
        System.out.println("EURO tax paid: " + eurTax);
        System.out.println("RUB tax paid: " + rubTax);
        System.out.println("BYN tax paid: " + bynTax);
    }

    public void addBank(Bank bank) {
        if (bank == null) {
            return;
        }
        if (indexOfBank(bank) == -1) {
            banks = add(bank);
        } else {
            System.out.println("Bank already exist.");
        }
    }

    private int indexOfBank(Bank bank) {
        int result = -1;
        if (banks.length != 0) {
            int flag = 0;
            for (int i = 0; i < banks.length; i++) {
                flag++;
                if (banks[i].equals(bank)) {
                    result = i;
                    return result;
                }
            }
            if (flag == banks.length) {
                result = -1;
            }
        } else {
            result = -1;
        }
        return result;
    }

    public void removeBank(Bank bank) {
        if (bank == null || bank == null || banks.length == 0) {
            return;
        }
        banks = remove(bank);
    }

    private Bank[] add(Bank bank) {
        if (bank == null) {
            return banks;
        }
        Bank[] returnList;
        if (banks == null) {
            returnList = new Bank[1];
            returnList[0] = bank;
        } else {
            returnList = new Bank[banks.length + 1];
            returnList = copyThenInsert(banks, returnList, bank);
        }
        return returnList;
    }

    private Bank[] remove(Bank bank) {
        Bank[] result = new Bank[banks.length - 1];
        int index = indexOfBank(bank);
        for (int i = 0, j = 0; i < banks.length; i++, j++) {
            if (i == index) {
                j--;
            } else {
                result[j] = banks[i];
            }
        }
        return result;
    }

    private Bank[] copyThenInsert(Bank[] copyThis, Bank[] intoThis, Bank thenInsertThis) {
        for (int i = 0; i < intoThis.length; i++) {
            if (i != intoThis.length - 1) {
                intoThis[i] = copyThis[i];
            } else {
                intoThis[i] = thenInsertThis;
            }
        }
        return intoThis;
    }

    public static void exchangeRates() {
        System.out.printf("%-60s%s%s", "\n", "EXCHANGE RATES:", "\n");
        System.out.printf("%15s %8s%n", "BUY", "SELL");
        System.out.printf("%-12s%-8s%-6s%s%n", "1 USD", USDBUY, USDSELL, "BYN");
        System.out.printf("%-12s%-8s%-6s%s%n", "1 EURO", EUROBUY, EUROSELL, "BYN");
        System.out.printf("%-12s%-8s%-6s%s%n", "1 RUB", RUBBUY, RUBSELL, "BYN");
    }

    @Override
    public void printOrganization() {
        System.out.printf("%-60s%s%s", "\n", "BANK SYSTEM INFORMATION:", "\n");
        super.printOrganization();
        System.out.println("Number of banks in system: " + Bank.count);
        System.out.println("Number of employees: " + Employee.count);
        System.out.println("Credits issued: " + Credit.count);
        System.out.println("Mortgage issued: " + Mortgage.count);
        System.out.println("Number types of credits in banks: " + CreditType.count);
        System.out.println("Number of bank clients: " + Client.сount);
    }

    public void searchForCreditType(String moneyType) {
        System.out.printf("%-60s%s%s", "\n", "CREDIT SEARCH RESULT:", "\n");
        int flag = 0;
        int otherBanks = 0;
        for (Bank element : banks) {
            if (element instanceof CreditBank) {
                CreditBank creditBank = (CreditBank) element;
                CreditType[] creditTypes = creditBank.findCreditType(moneyType);
                if (creditTypes != null && creditTypes.length != 0) {
                    for (CreditType creditTypeElement : creditTypes) {
                        System.out.print("Bank \"" + element.getName() + "\" : ");
                        creditTypeElement.printCreditType();
                    }
                } else {
                    flag++;
                }
            } else {
                otherBanks++;
            }
        }
        if (flag == banks.length - otherBanks) {
            System.out.println("No credits found for your request.");
        }
    }

    public void searchForCreditType(String moneyType, double moneyAmount) {
        System.out.printf("%-60s%s%s", "\n", "CREDIT SEARCH RESULT:", "\n");
        int flag = 0;
        int otherBanks = 0;
        for (Bank element : banks) {
            if (element instanceof CreditBank) {
                CreditBank creditBank = (CreditBank) element;
                CreditType[] creditTypes = creditBank.findCreditType(moneyType, moneyAmount);
                if (creditTypes != null && creditTypes.length != 0) {
                    for (CreditType creditTypeElement : creditTypes) {
                        System.out.print("Bank \"" + element.getName() + "\" : ");
                        creditTypeElement.printCreditType();
                    }
                } else {
                    flag++;
                }
            } else {
                otherBanks++;
            }
        }
        if (flag == banks.length - otherBanks) {
            System.out.println("No credits found for your request.");
        }
    }

    public BankOperation[] searchOperation(Human human) {
        BankOperation[] result = null;
        if (human == null) {
            return null;
        }
        if (banks != null && banks.length > 0) {
            result = new BankOperation[0];
            for (Bank element : banks) {
                result = addOperation(result, element.findOperation(human));
            }
        }
        return result;
    }

    private BankOperation[] addOperation(BankOperation[] base, BankOperation[] copied) {
        if (copied == null || copied.length == 0) {
            return base;
        }
        BankOperation[] result;
        if (base == null) {
            result = copied;
        } else {
            result = new BankOperation[base.length + copied.length];
            result = copyThenInsert(base, copied, result);
        }
        return result;
    }

    private BankOperation[] copyThenInsert(BankOperation[] unionThis, BankOperation[] withThis, BankOperation[] andPutHere) {
        for (int i = 0; i < andPutHere.length; i++) {
            if (i < unionThis.length) {
                andPutHere[i] = unionThis[i];
            } else {
                andPutHere[i] = withThis[i - unionThis.length];
            }
        }
        return andPutHere;
    }

    @Override
    public String toString() {
        return "Class BankSystem [name = " + getName() + ", address = " + getAddress() + ", foundedAt = "
                + getFoundedAt().getDayOfMonth() + "." + getFoundedAt().getMonth() + "."
                + getFoundedAt().getYear() + ", USDBUY = " + USDBUY + ", USDSELL = " + USDSELL + ", EUROBUY = " +
                EUROBUY + ", EUROSELL = " + EUROSELL + ", RUBBUY = " + RUBBUY +
                ", RUBSELL = " + RUBSELL + ", banks = " + banks + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        BankSystem bankSystem = (BankSystem) object;
        return Arrays.equals(banks, bankSystem.getBanks())
                && getAddress().equals(bankSystem.getAddress())
                && getFoundedAt().equals(bankSystem.getFoundedAt())
                && (getName() == bankSystem.getName() || (getName() != null && getName().equals(bankSystem.getName())));
    }

    @Override
    public int hashCode() {
        return Objects.hash(USDBUY, USDSELL, EUROSELL, EUROBUY, RUBBUY, RUBSELL, banks, getName(),
                getFoundedAt(), getAddress());
    }
}
