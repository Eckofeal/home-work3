package bankoperations;

import address.Address;
import bank.currency.Currency;
import java.util.Objects;

public class MortgagedApartment extends Address {

    private int roomNumber;
    private Currency price;

    public MortgagedApartment(Address address, int roomNumber, Currency price) {
        super(address.getCity(), address.getStreet(), address.getHouseNumber());
        this.roomNumber = roomNumber;
        this.price = price;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setPrice(Currency price) {
        this.price = price;
    }

    public Currency getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Class MortgagedApartment [city = " + getCity() + ", street = "
                + getStreet() + ", houseNumber = " + getHouseNumber()
                + ", roomNumber = " + roomNumber + ", price = " + price + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        MortgagedApartment mortgagedApartment = (MortgagedApartment) object;
        return getHouseNumber() == mortgagedApartment.getHouseNumber()
                && (getCity() == mortgagedApartment.getCity() || (getCity() != null && getCity().equals(mortgagedApartment.getCity())))
                && (getStreet() == mortgagedApartment.getStreet() || (getStreet() != null && getStreet().equals(mortgagedApartment.getStreet())))
                && roomNumber == mortgagedApartment.getRoomNumber() && price.equals(mortgagedApartment.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getHouseNumber(), roomNumber, price);
    }
}
