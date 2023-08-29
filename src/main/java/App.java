import model.Department;
import repository.DepartmentRepository;
import repository.IDepartmentRepository;

import java.util.List;

//Used only for testing
public class App {

    public static void main(String[] args) {
        IDepartmentRepository departmentRepository = new DepartmentRepository();

        //Creating
        System.out.println("Saving");
        Department department = new Department();
        department.setName("Test");
        departmentRepository.saveDepartment(department);
        System.out.println("Saved1");

        Department department2 = new Department();
        department2.setName("Test2");
        departmentRepository.saveDepartment(department2);
        System.out.println("Saved 2");

        //Retrieving
        System.out.println("\nRetrieving");
        List<Department> departments = departmentRepository.getDepartments();
        System.out.println(departments);

        //Updating
        department.setName("Updated department");
        departmentRepository.updateDepartment(department);
        List<Department> departments2 = departmentRepository.getDepartments();
        System.out.println(departments2);

        //Deleting
        departmentRepository.deleteDepartment(department);
        List<Department> departments3 = departmentRepository.getDepartments();
        System.out.println(departments3);
        System.out.println("Deleted");
    }
}
