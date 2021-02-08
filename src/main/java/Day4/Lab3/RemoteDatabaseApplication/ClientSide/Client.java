package Day4.Lab3.RemoteDatabaseApplication.ClientSide;

import Day4.Lab3.RemoteDatabaseApplication.Models.Employee;
import Day4.Lab3.RemoteDatabaseApplication.ServerSide.DB.DTOInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        try {
            // Getting instance of local registry table
            Registry registry = LocateRegistry.getRegistry(1099);
            // Locating name service and get object reference
            DTOInterface dtoInterface = (DTOInterface) registry.lookup("DatabaseConnection");
            // Call Server methods
            Employee employee = new Employee();
            employee.setId(111);
            employee.setFirstName("Ahmed");
            employee.setMiddleName("Asim");
            employee.setLastName("Sery");
            dtoInterface.createEmployee(employee);

            Employee newEmployee = dtoInterface.retrieveEmployee(110);
            // Print result
            System.out.println(newEmployee.toString());

            List<Employee> employees = dtoInterface.retrieveAllEmployees();
            System.out.println("Number of employees = " + employees.size());
            for (Employee employee1 : employees) {
                System.out.println(employee1.toString());
            }
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
