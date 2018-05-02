package sample.Employee;

public class Employee {

    //Variables
    private String firstName;
    private String lastName;
    private String birth;
    private String phone;
    private String address;
    private String email;


    public Employee(String firstName, String lastName, String birth, String phone, String address, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirth() {
        return birth;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}
