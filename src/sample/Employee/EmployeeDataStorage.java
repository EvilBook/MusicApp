package sample.Employee;

//Singleton class
public class EmployeeDataStorage {

    private static EmployeeDataStorage ourInstance = new EmployeeDataStorage();
    int message;

    public static EmployeeDataStorage getInstance(){

        return ourInstance;
    }

    private EmployeeDataStorage(){

    }

    public Integer getMessage (){

        return message;
    }

    public void setMessage(int message){

        this.message = message;
    }
}
