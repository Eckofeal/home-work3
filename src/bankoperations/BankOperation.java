package bankoperations;

import bankoperations.сlient.Client;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class BankOperation {

    private Client client;
    private Date issued;
    private Date expired;

    public BankOperation(Client client) {
        this.client = client;
        issued = new Date();
        expired = new Date();
        expired.setTime(issued.getTime());
    }

    public Client getClient() {
        return client;
    }

    public Date getIssued() {
        return issued;
    }

    public Date getExpired() {
        return expired;
    }

    public abstract void print();

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return "Class BankOperation [client = " + client + ", issued = "
                + dateFormat.format(issued) + ", expired = " + dateFormat.format(expired) + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        BankOperation bankOperation = (BankOperation) object;
        return client.equals(bankOperation.getClient())
                && (issued.getDate() == bankOperation.getIssued().getDate())
                && (issued.getMonth() == bankOperation.getIssued().getMonth())
                && (issued.getYear() == bankOperation.getIssued().getYear())
                && (issued.getTime() == bankOperation.getIssued().getTime())
                && (expired.getDate() == bankOperation.getExpired().getDate())
                && (expired.getMonth() == bankOperation.getExpired().getMonth())
                && (expired.getYear() == bankOperation.getExpired().getYear())
                && (expired.getTime() == bankOperation.getExpired().getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, issued, expired);
    }
}
