import com.mysql.cj.result.IntegerValueFactory;

public class Person {


    private String name;
    private String address;
    private Integer age;
    private Integer salary;
    private Integer ident;


    public Person(String name, Integer age, String address, Integer salary){

        this.name = name;
        this.age = age;
        this.address = address;
        this.salary = salary;

    }

    public Person(String first_name, String address, int age, int salary) {
        this.name = first_name;
        this.address = address;
        this.age = age;
        this.salary= salary;



    }



    public Person(int idNum, String first_name, String address, int age, int salary) {
        this.ident = idNum;
        this.name = first_name;
        this.address = address;
        this.age = age;
        this.salary = salary;
    }



    public Integer getIdent() {
        return ident;
    }

    public void setIdent(Integer ident) {
        this.ident = ident;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return  name+" "+age+" "+address +" "+ salary;
    }




}