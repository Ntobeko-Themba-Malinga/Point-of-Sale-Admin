package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import model.Department;
import model.Item;

import java.util.List;

public class DepartmentRepository implements IDepartmentRepository {
    private final EntityManager entityManager;

    public DepartmentRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pos_pu");
        this.entityManager = emf.createEntityManager();
    }

    /**
     * Retrieves a department from the database.
     * @param department The department to retrieve
     * @return Retrieved department.
     */
    private Department find(Department department) {
        return this.entityManager.find(Department.class, department.getId());
    }

    /**
     * Retrieved all saved departments on the database.
     * @return List of all departments.
     */
    @Override
    public List<Department> getDepartments() {
        Query query = this.entityManager.createQuery("SELECT d FROM Department d");
        return query.getResultList();
    }

    /**
     * Saves a department to a database
     * @param department The department to save to the database
     * @return True if successfully saved.
     */
    @Override
    public boolean saveDepartment(Department department) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(department);
        this.entityManager.getTransaction().commit();
        return true;
    }

    /**
     * Removes a department record on the database.
     * @param department The department to remove from the database.
     * @return True if successfully removed.
     */
    @Override
    public boolean deleteDepartment(Department department) {
        this.entityManager.getTransaction().begin();
        Department departmentToRemove = this.entityManager.find(Department.class, department.getId());
        this.entityManager.remove(departmentToRemove);
        this.entityManager.getTransaction().commit();
        return true;
    }

    /**
     * Update a department record information.
     * @param department The department with new information.
     * @return True if successfully updated.
     */
    @Override
    public boolean updateDepartment(Department department) {
        this.entityManager.getTransaction().begin();
        Department departmentToUpdate = find(department);
        departmentToUpdate.setName(department.getName());
        departmentToUpdate.setItems(department.getItems());
        this.entityManager.getTransaction().commit();
        return true;
    }

    /**
     * Adds an item to a department.
     * @param department The department to add an item to.
     * @param item The item to be added to a department
     * @return true if added successfully.
     */
    @Override
    public boolean addItem(Department department, Item item) {
        this.entityManager.getTransaction().begin();
        Department departmentToAddItem = find(department);
        departmentToAddItem.getItems().add(item);
        this.entityManager.getTransaction().commit();
        return true;
    }

    /**
     * Removes an item to a department.
     * @param department The department to remove an item from.
     * @param item The item to be removed from a department
     * @return true if removed successfully.
     */
    @Override
    public boolean removeItem(Department department, Item item) {
        this.entityManager.getTransaction().begin();
        Department departmentToRemoveItem = find(department);
        departmentToRemoveItem.getItems().remove(item);
        this.entityManager.getTransaction().commit();
        return true;
    }

    /**
     * Closes the connection to the database.
     */
    @Override
    public void close() {
        entityManager.close();
    }
}
