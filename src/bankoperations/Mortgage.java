package bankoperations;

import bankoperations.сlient.Client;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class Mortgage extends BankOperation {

    public static int count = 0;

    private MortgagedApartment apartment;
    private final int termInYears = 5;
    private final int yearPercent = 19;
    private double givenMoneyAmount;
    private double backMoneyAmount;
    private double monthlyPayment;
    private double moneyPaid;
    private String moneyType;

    public Mortgage(Client client, MortgagedApartment apartment) {
        super(client);
        this.apartment = apartment;
        super.getExpired().setYear(super.getExpired().getYear() + termInYears);
        givenMoneyAmount = apartment.getPrice().getAmount();
        backMoneyAmount = givenMoneyAmount + (givenMoneyAmount * termInYears * yearPercent);
        monthlyPayment = backMoneyAmount / (termInYears * 12);
        moneyType = apartment.getPrice().getType();
        count++;
    }

    public MortgagedApartment getApartment() {
        return apartment;
    }

    public double getGivenMoneyAmount() {
        return givenMoneyAmount;
    }

    public double getBackMoneyAmount() {
        return backMoneyAmount;
    }

    public int getTermInYears() {
        return termInYears;
    }

    public int getYearPercent() {
        return yearPercent;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public String getMoneyType() {
        return moneyType;
    }

    public void setMoneyPaid(double moneyPaid) {
        if (moneyPaid > 0) {
            this.moneyPaid = moneyPaid;
        }
    }

    public double getMoneyPaid() {
        return moneyPaid;
    }

    @Override
    public void print() {
        System.out.printf("%-60s%s%s", "\n", "MORTGAGE:", "\n");
        System.out.println("1. CLIENT INFORMATION.");
        super.getClient().printClient();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        System.out.println("\n2. MORTGAGE.");
        System.out.print("Bank paid to the client: " + givenMoneyAmount
                + " " + moneyType + " at " + yearPercent + "% per annum for a priod of " + termInYears + " years (");
        System.out.print(dateFormat.format(super.getIssued()));
        System.out.print(" - ");
        System.out.print(dateFormat.format(super.getExpired()));
        System.out.print(").");
        System.out.println("\nTo be paid to the bank: " + backMoneyAmount + " " + moneyType);
        System.out.println("Monthly payment: " + monthlyPayment);
        System.out.println("Money paid: " + moneyPaid);
        System.out.println("\n3. MORTGAGED PROPERTY.");
        System.out.println("Apartment address: " + apartment.getCity() +
                ", " + apartment.getStreet() + " " +
                apartment.getHouseNumber() + ", ap." + apartment.getRoomNumber());
        System.out.println("Collateral value of an apartment: " +
                (apartment.getPrice().getAmount() * 30 / 100) + " " + apartment.getPrice().getType() + ".");
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return "Class Mortgage [count = " + count + " ,client = " + getClient() + ", issued = "
                + dateFormat.format(getIssued()) + ", expired = " + dateFormat.format(getExpired())
                + ", apartment = " + apartment + ", givenMoneyAmount = " + givenMoneyAmount
                + ", termInYears = " + termInYears + ", yearPercent = " + yearPercent
                + ", backMoneyAmount = " + backMoneyAmount + ", monthlyPayment = " + monthlyPayment
                + ", moneyPaid = " + moneyPaid + ", moneyType = " + moneyType + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Mortgage mortgage = (Mortgage) object;
        return apartment.equals(mortgage.getApartment())
                && (getIssued().getDate() == mortgage.getIssued().getDate())
                && (getIssued().getMonth() == mortgage.getIssued().getMonth())
                && (getIssued().getYear() == mortgage.getIssued().getYear())
                && (getIssued().getTime() == mortgage.getIssued().getTime())
                && (getExpired().getDate() == mortgage.getExpired().getDate())
                && (getExpired().getMonth() == mortgage.getExpired().getMonth())
                && (getExpired().getYear() == mortgage.getExpired().getYear())
                && (getExpired().getTime() == mortgage.getExpired().getTime())
                && givenMoneyAmount == mortgage.getGivenMoneyAmount()
                && backMoneyAmount == mortgage.getBackMoneyAmount()
                && monthlyPayment == mortgage.getMonthlyPayment()
                && moneyPaid == mortgage.getMoneyPaid()
                && (moneyType == mortgage.getMoneyType() || (moneyType != null && moneyType.equals(mortgage.getMoneyType())))
                && termInYears == mortgage.getTermInYears() && yearPercent == mortgage.getYearPercent();
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, apartment, termInYears, yearPercent, givenMoneyAmount, backMoneyAmount, monthlyPayment, moneyPaid, moneyType,
                getClient(), getIssued(), getExpired());
    }
}
