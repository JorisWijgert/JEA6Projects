import javax.persistence.*;

/**
 * Created by Joris on 23-2-2017.
 */
@Entity
@NamedQueries({@NamedQuery(name="employee.all",query="SELECT e FROM Employee e")})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="emp_salary")
    private int salary;

    @Column(name="emp_name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
