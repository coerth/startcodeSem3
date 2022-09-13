package dtos;

import entities.Employee;
import entities.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmployeeDTO
{
    private Long id;
    private String name;
    private String address;
    private int salary;

    public EmployeeDTO(Employee e)
    {
        if(e.getId() != null)
        {
            this.id = e.getId();
        }
        this.name = e.getName();
        this.address = e.getAddress();
        this.salary = e.getSalary();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getSalary() {
        return salary;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeDTO)) return false;
        EmployeeDTO that = (EmployeeDTO) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "employeeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                '}';
    }

    public static List<EmployeeDTO> getDtos(List<Employee> employees){
        List<EmployeeDTO> employeeDTOS = new ArrayList();
        employees.forEach(employee->employeeDTOS.add(new EmployeeDTO(employee)));
        return employeeDTOS;
    }
}
