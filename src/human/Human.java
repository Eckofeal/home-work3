package human;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Human {

    private String firstName;
    private String lastName;
    private int age;
    private LocalDateTime birthday;

    public Human(String firstName, String lastName, LocalDateTime birthDay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthDay;
        int age = (int) ChronoUnit.YEARS.between(this.birthday, LocalDateTime.now());
        if (age > 18 && age < 150) {
            this.age = age;
        }
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAge(int age) {
        if (age > 18 && age < 150) {
            this.age = age;
        }
    }

    public int getAge() {
        return age;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "Class Human [firstName = " + firstName + ", lastName = "
                + lastName + ", age = " + age + ", birthday = "
                + birthday.getDayOfMonth() + "." + birthday.getMonth() + "."
                + birthday.getYear() + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Human human = (Human) object;
        return age == human.getAge()
                && (firstName == human.getFirstName() || (firstName != null && firstName.equals(human.getFirstName())))
                && (lastName == human.getLastName() || (lastName != null && lastName.equals(human.getLastName())))
                && (birthday.equals(human.getBirthday()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, birthday);
    }
}
