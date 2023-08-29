package repository;

import model.Department;
import model.Item;

import java.util.List;

public interface IDepartmentRepository {

    /**
     * Retrieved all saved departments on the database.
     * @return List of all departments.
     */
    List<Department> getDepartments();

    /**
     * Saves a department to a database
     * @param department The department to save to the database
     * @return True if successfully saved.
     */
    boolean saveDepartment(Department department);

    /**
     * Removes a department record on the database.
     * @param department The department to remove from the database.
     * @return True if successfully removed.
     */
    boolean deleteDepartment(Department department);

    /**
     * Update a department record information.
     * @param department The department with new information.
     * @return True if successfully updated.
     */
    public boolean updateDepartment(Department department);

    /**
     * Adds an item to a department.
     * @param department The department to add an item to.
     * @param item The item to be added to a department
     * @return true if added successfully.
     */
    public boolean addItem(Department department, Item item);

    /**
     * Removes an item to a department.
     * @param department The department to remove an item from.
     * @param item The item to be removed from a department
     * @return true if removed successfully.
     */
    public boolean removeItem(Department department, Item item);

    /**
     * Closes the connection to the database.
     */
    public void close();
}
