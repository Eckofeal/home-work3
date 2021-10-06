import address.Address;
import bank.BankSystem;
import bank.CreditBank;
import bank.MortgageBank;
import bank.currency.Currency;
import bankoperations.*;
import bankoperations.сlient.Client;
import bankoperations.сlient.work.Work;
import human.Human;
import java.time.LocalDateTime;
import java.time.Month;

public class MainClass {

    public static void printOperations(BankOperation[] bankOperations) {
        for (BankOperation bankOperation : bankOperations) {
            bankOperation.print();
        }
    }

    public static void main(String[] args) {
        Currency byn1 = new Currency(1000000, Currency.BYN);
        Currency rub1 = new Currency(1250000, Currency.RUB);
        Currency eur1 = new Currency(150000, Currency.EURO);
        Currency usd1 = new Currency(175000, Currency.USD);
        Currency byn2 = new Currency(2000000, Currency.BYN);
        Currency eur2 = new Currency(250000, Currency.EURO);
        Currency usd2 = new Currency(275000, Currency.USD);
        Currency rub2 = new Currency(2250000, Currency.RUB);
        Currency byn3 = new Currency(3000000, Currency.BYN);
        Currency rub3 = new Currency(3250000, Currency.RUB);
        Currency eur3 = new Currency(350000, Currency.EURO);
        Currency usd3 = new Currency(375000, Currency.USD);
        Currency byn4 = new Currency(4000000, Currency.BYN);
        Currency rub4 = new Currency(4250000, Currency.RUB);
        Currency eur4 = new Currency(450000, Currency.EURO);
        Currency usd4 = new Currency(475000, Currency.USD);
        Address adr1 = new Address("Minsk", "Lenina", 3);
        Address adr2 = new Address("Moscow", "Old Arbat", 12);
        Address adr3 = new Address("London", "Piccadilly", 7);
        Address adr4 = new Address("Berlin", "Unter den Linden", 9);
        Address adr5 = new Address("Barcelona", "La Rambla", 4);
        Address adr6 = new Address("Paris", "Richelieu", 16);
        Address adr7 = new Address("Kiev", "Kreshyatnik", 1);
        CreditBank bank1 = new CreditBank("Commercial Banking", adr1,
                LocalDateTime.of(2017, Month.JULY, 9, 12, 0), usd1, eur1, rub1, byn1);
        CreditBank bank2 = new CreditBank("Finance", adr2,
                LocalDateTime.of(2000, Month.AUGUST, 26, 0, 0), usd2, eur2, rub2, byn2);
        MortgageBank bank3 = new MortgageBank("Future's", adr3,
                LocalDateTime.of(1993, Month.MAY, 3, 18, 6), usd2, eur2, rub2, byn2);
        MortgageBank bank4 = new MortgageBank("Absolute", adr4,
                LocalDateTime.of(1886, Month.DECEMBER, 31, 23, 59), usd3, eur3, rub3, byn3);
        Work work1 = new Work("Google", "Sales Manager", 6500, Currency.USD);
        Work work2 = new Work("KFC", "Restaurant Crew", 850, Currency.BYN);
        Work work3 = new Work("Adidas", "Production Line Engineer", 2800, Currency.EURO);
        Client client1 = new Client("Tom", "Fox", LocalDateTime.of(1990, Month.APRIL, 5, 10, 15), work1);
        Client client2 = new Client("Alexa", "Dilan", LocalDateTime.of(1998, Month.SEPTEMBER, 18, 18, 27), work2);
        Client client3 = new Client("Tom", "Fox", LocalDateTime.of(1984, Month.NOVEMBER, 7, 3, 41), work3);
        CreditType creditType1 = new CreditType(
                "Elementary USD", Currency.USD, 1, 12, 1000, 2000);
        CreditType creditType2 = new CreditType(
                "Light USD", Currency.USD, 1, 9, 2000, 5000);
        CreditType creditType3 = new CreditType(
                "Medium USD", Currency.USD, 2, 8, 5000, 10000);
        CreditType creditType4 = new CreditType(
                "High USD", Currency.USD, 3, 7, 10000, 20000);
        CreditType creditType5 = new CreditType(
                "Inescapable USD", Currency.USD, 5, 7, 20000, 50000);
        CreditType creditType6 = new CreditType(
                "Light EURO", Currency.EURO, 2, 17, 1000, 6000);
        CreditType creditType7 = new CreditType(
                "Medium EURO", Currency.EURO, 2, 14, 6000, 15000);
        CreditType creditType8 = new CreditType(
                "High EURO", Currency.EURO, 2, 11, 15000, 30000);
        CreditType creditType9 = new CreditType(
                "Light RUB", Currency.RUB, 1, 25, 70000, 210000);
        CreditType creditType10 = new CreditType(
                "Medium RUB", Currency.RUB, 3, 23, 210000, 400000);
        CreditType creditType11 = new CreditType(
                "High RUB", Currency.RUB, 5, 20, 400000, 1000000);
        CreditType creditType13 = new CreditType(
                "Medium BYN", Currency.BYN, 1, 8, 20000, 50000);
        CreditType creditType14 = new CreditType(
                "High BYN", Currency.BYN, 2, 11, 50000, 150000);
        bank1.addCreditType(creditType1);
        bank1.addCreditType(creditType2);
        bank1.addCreditType(creditType6);
        bank1.addCreditType(creditType9);
        bank1.addCreditType(creditType5);
        bank1.addCreditType(creditType11);
        bank1.addCreditType(creditType14);
        bank2.addCreditType(creditType3);
        bank2.addCreditType(creditType7);
        bank2.addCreditType(creditType10);
        bank2.addCreditType(creditType13);
        bank2.addCreditType(creditType4);
        bank2.addCreditType(creditType8);
        Credit credit1 = new Credit(client1, creditType3, 6000);
        Credit credit2 = new Credit(client2, creditType7, 11000);
        Credit credit3 = new Credit(client3, creditType14, 80000);
        Credit credit4 = new Credit(client3, creditType1, 1500);
        credit1.setMoneyPaid(580);
        System.out.println("\n\n/////////////////////////////////FIRST////////////////////////////\n\n");
        credit1.print();
        //POLYMORPH CALL
        bank1.addOperation(credit1);
        //POLYMORPH CALL
        bank2.addOperation(credit2);
        //POLYMORPH CALL
        bank2.addOperation(credit3);
        System.out.println("\n\n/////////////////////////////////SECOND////////////////////////////\n\n");
        bank1.printBankInformation();
        Address address1 = new Address("New York", "Park Avenue", 1);
        BankSystem bankSystem = new BankSystem("Unions", address1, LocalDateTime.of(1934, Month.MARCH, 8, 18, 0));
        bankSystem.addBank(bank1);
        bankSystem.addBank(bank2);
        bankSystem.addBank(bank3);
        bankSystem.addBank(bank4);
        //Home task call(Business question)
        System.out.println("\n\n/////////////////////////////////THIRD////////////////////////////\n\n");
        bankSystem.searchForCreditType("USD");
        bankSystem.searchForCreditType(Currency.USD, 1500);
        bankSystem.searchForCreditType(Currency.EURO, 300);
        bankSystem.searchForCreditType("qwe");
        bankSystem.searchForCreditType("123", 50000);
        bankSystem.searchForCreditType(Currency.USD, 1500000);
        //static call
        System.out.println("\n\n/////////////////////////////////FOURTH////////////////////////////\n\n");
        BankSystem.exchangeRates();
        //search before CreditType remove
        System.out.println("\n\n/////////////////////////////////FIFTH////////////////////////////\n\n");
        bankSystem.searchForCreditType(Currency.BYN);
        bank1.removeCreditType(creditType14);
        //search after CreditType remove
        bankSystem.searchForCreditType(Currency.BYN);
        System.out.println("\n\n/////////////////////////////////SIXTH////////////////////////////\n\n");
        Work work4 = new Work("Microsoft", "Programming Engineer", 12500, Currency.USD);
        Work work5 = new Work("McDonald's", "Restaurant Crew", 1050, Currency.BYN);
        Work work6 = new Work("Turkish Airlines", "Pilot", 2100, Currency.EURO);
        Client client4 = new Client("Bill", "Milligan", LocalDateTime.of(1991, Month.DECEMBER, 1, 12, 44), work4);
        Client client5 = new Client("Alisha", "Willis", LocalDateTime.of(1989, Month.FEBRUARY, 23, 16, 11), work5);
        Client client6 = new Client("Richard", "Forester", LocalDateTime.of(1977, Month.AUGUST, 29, 4, 3), work6);
        MortgagedApartment apartment1 = new MortgagedApartment(adr5, 39, new Currency(185000, Currency.EURO));
        MortgagedApartment apartment2 = new MortgagedApartment(adr6, 13, new Currency(240000, Currency.EURO));
        MortgagedApartment apartment3 = new MortgagedApartment(adr7, 47, new Currency(110000, Currency.RUB));
        Mortgage mortgage1 = new Mortgage(client4, apartment1);
        Mortgage mortgage2 = new Mortgage(client5, apartment2);
        Mortgage mortgage3 = new Mortgage(client6, apartment3);
        //POLYMORPH CALL
        bank3.addOperation(mortgage1);
        //POLYMORPH CALL
        bank3.addOperation(mortgage2);
        //POLYMORPH CALL
        bank4.addOperation(mortgage3);
        //POLYMORPH IN ACTION
        Human human1 = new Human(client5.getFirstName(), client5.getLastName(), client5.getBirthday());
        BankOperation[] operations1 = bank3.findMortgage(human1);
        printOperations(operations1);
        System.out.println("\n\n/////////////////////////////////SEVENTH////////////////////////////\n\n");
        //POLYMORPH CALL
        bank1.addOperation(credit4);
        //POLYMORPH CALL
        bank1.addOperation(credit2);
        //POLYMORPH CALL
        bank1.addOperation(credit3);
        //POLYMORPH IN ACTION
        BankOperation[] operations2 = bank1.getCredits();
        printOperations(operations2);
        System.out.println("\n\n/////////////////////////////////EIGHTH////////////////////////////\n\n");
        //OVERRIDED abstract method from Organization class.
        bank1.payTax();
        bankSystem.payTax();
        //Class CreditBank don't have a realization of "printOrganization()" method.
        //Class Bank don't have it too. It was called from Organization class.
        bank1.printOrganization();
        //MY OVERRIDED METHOD (Organization class and bank.BankSystem class)
        bankSystem.printOrganization();
        System.out.println("\n\n/////////////////////////////////NINTH////////////////////////////\n\n");
        Work work7 = new Work("Oxford", "Professor", 3400, Currency.USD);
        Client client7 = new Client("Mick", "Flick", LocalDateTime.of(2001, Month.JANUARY, 14, 1, 15), work7);
        Credit credit5 = new Credit(client7, creditType1, 1500);
        Credit credit6 = new Credit(client7, creditType2, 4000);
        Address adr8 = new Address("Warshaw", "Nowy Swiat", 11);
        Address adr9 = new Address("Los Angeles", "Rodeo Drive", 5);
        MortgagedApartment apartment4 = new MortgagedApartment(adr8, 24, new Currency(95000, Currency.EURO));
        MortgagedApartment apartment5 = new MortgagedApartment(adr8, 13, new Currency(400000, Currency.USD));
        Mortgage mortgage4 = new Mortgage(client7, apartment4);
        Mortgage mortgage5 = new Mortgage(client7, apartment5);
        bank1.addOperation(credit5); //or .addCredit(credit5) it works the same
        bank2.addOperation(credit6); //or .addCredit(credit6) it works the same
        bank3.addOperation(mortgage4); //or .addMortgage(mortgage4) it works the same
        bank4.addOperation(mortgage5); //or .addMortgage(mortgage5) it works the same
        Human human2 = new Human(client7.getFirstName(), client7.getLastName(), client7.getBirthday());
        BankOperation[] operations3 = bankSystem.searchOperation(client7); //or .searchOperation(human2) it works the same
        printOperations(operations3);
    }
}
