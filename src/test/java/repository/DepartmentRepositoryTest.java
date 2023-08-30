package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Department;
import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentRepositoryTest {
    private IDepartmentRepository departmentRepository;
    private EntityManagerFactory emf;
    private EntityManager entityManager;

    private void saveTestingDepartments(int number) {
        for (int i = 0; i < number; i++) {
            Department department = new Department("test department" + i, new HashSet<>());
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(department);
            this.entityManager.getTransaction().commit();
        }
    }

    private Department saveDepartmentForTesting(String name) {
        Department department = new Department(name, new HashSet<>());
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(department);
        this.entityManager.getTransaction().commit();
        return department;
    }

    @BeforeEach
    public void setUp() {
        this.departmentRepository = new DepartmentRepository();
        this.emf = Persistence.createEntityManagerFactory("pos_pu");
        this.entityManager = emf.createEntityManager();
    }

    @AfterEach
    public void tearDown() {
        this.entityManager.close();
        this.emf.close();
    }

    @Test
    public void saveDepartment() {
        Department department = new Department("test_department4", new HashSet<>());
        departmentRepository.saveDepartment(department);
        assertNotNull(department.getId());
    }

    @Test
    public void getDepartments() {
        saveTestingDepartments(3);
        List<Department> departments = departmentRepository.getDepartments();

        assertEquals(3, departments.size());
    }

    @Test
    public void updateDepartment() {
        Department department = saveDepartmentForTesting("Department to be updated");
        Long departmentId = department.getId();
        entityManager.close();

        department.setName("department updated");
        departmentRepository.updateDepartment(department);

        this.entityManager = emf.createEntityManager();
        Department updatedDepartment = this.entityManager.find(Department.class, departmentId);
        assertEquals("department updated", updatedDepartment.getName());
    }

    @Test
    public void deleteDepartment() {
        saveTestingDepartments(2);
        List<Department> departments = this.entityManager.createQuery("SELECT d FROM Department d").getResultList();
        assertEquals(2, departments.size());

        Department department = this.entityManager.find(Department.class, 1);
        departmentRepository.deleteDepartment(department);

        departments = this.entityManager.createQuery("SELECT d FROM Department d").getResultList();
        assertEquals(1, departments.size());
    }
}