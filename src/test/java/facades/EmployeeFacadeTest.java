package facades;

import dtos.EmployeeDTO;
import entities.Employee;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeFacadeTest
{

    private static EntityManagerFactory emf;
    private static EmployeeFacade facade;

    public EmployeeFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = EmployeeFacade.getInstance(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Employee e");
            facade.create(new EmployeeDTO(new Employee("Long", "en adresse", 25000)));
            facade.create(new EmployeeDTO(new Employee("Morten", "en anden adresse", 1500)));
            facade.create(new EmployeeDTO(new Employee("Betul", "en helt tredje adresse", 50000)));
            facade.create(new EmployeeDTO(new Employee("Denis", "en fjerde adresse", 100)));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method
    /*@Test
    public void testAFacadeMethod() throws Exception {
        assertEquals(2, facade.getRenameMeCount(), "Expects two rows in the database");
    }*/

    @Test
    public void createTest()
    {
        EmployeeDTO result = facade.create(new EmployeeDTO(new Employee("Casper", "En helt femte adresse", 500)));
        assertEquals(21, result.getId());
    }

    @Test
    public void getByIdTest()
    {
        EmployeeDTO result = facade.getById(3);
        assertEquals("Betul", result.getName());
    }

    @Test
    public void getByNameTest()
    {
        List<EmployeeDTO> result = facade.getByName("Morten");
        assertEquals("Morten", result.get(0).getName());
    }

    @Test
    public void getAllTest()
    {
        List<EmployeeDTO> result = facade.getAll();
        assertEquals(4, result.get(3).getId());
    }

    @Test
    public void getEmployeeWithHighestSalary()
    {
        EmployeeDTO result = facade.getEployeeWithHighestSalary();
        assertEquals("Betul", result.getName());
    }

}
