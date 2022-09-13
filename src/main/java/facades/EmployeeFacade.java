package facades;

import dtos.EmployeeDTO;
import entities.Employee;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private EmployeeFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EmployeeDTO create(EmployeeDTO employeeDTO){
        Employee Employee = new Employee(employeeDTO.getName(), employeeDTO.getAddress(), employeeDTO.getSalary());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(Employee);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new EmployeeDTO(Employee);
    }

    public EmployeeDTO update(EmployeeDTO employeeDTO)
    {
        EntityManager em = getEntityManager();
        Employee employeeFromDB = em.find(Employee.class, employeeDTO.getId());
        if(employeeFromDB == null)
        {
            throw new EntityNotFoundException("No such Employee with id:" + employeeDTO.getId());
        }

        Employee Employee = new Employee( employeeDTO.getId(),employeeDTO.getName(), employeeDTO.getAddress(), employeeDTO.getSalary());

        try {
            em.getTransaction().begin();
            em.merge(Employee);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new EmployeeDTO(Employee);
    }
    public EmployeeDTO getById(long id) { //throws EmployeeNotFoundException {
        EntityManager em = emf.createEntityManager();
        Employee Employee = em.find(Employee.class, id);
//        if (Employee == null)
//            throw new EmployeeNotFoundException("The Employee entity with ID: "+id+" Was not found");
        return new EmployeeDTO(Employee);
    }

    public List<EmployeeDTO> getByName(String name)
    {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.name=:name",Employee.class);
            query.setParameter("name", name);

            return EmployeeDTO.getDtos(query.getResultList());
    }

    public EmployeeDTO getEmployeeWithHighestSalary()
    {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e  ORDER BY e.salary DESC ", Employee.class);

        return new EmployeeDTO(query.setMaxResults(1).getSingleResult());
    }
    
    //TODO Remove/Change this before use

    
    public List<EmployeeDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        List<Employee> Employees = query.getResultList();
        return EmployeeDTO.getDtos(Employees);
    }


    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        EmployeeFacade pe = getInstance(emf);
        /*pe.getAll().forEach(dto->System.out.println(dto));*/

       EmployeeDTO employee  = pe.getEmployeeWithHighestSalary();

        System.out.println(employee);
        pe.update(new EmployeeDTO(new Employee(Long.valueOf(1), "Henriette", "Balkonen", 300)));
    }

}
