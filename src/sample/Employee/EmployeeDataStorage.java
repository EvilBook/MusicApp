package sample.Employee;

public class EmployeeDataStorage {

    private static EmployeeDataStorage ourInstance = new EmployeeDataStorage();
    public String message1;
    int message;

    public void setMessage1(String message1) {
        this.message1 = message1;
    }

    public String getMessage1() {
        return message1;
    }

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
