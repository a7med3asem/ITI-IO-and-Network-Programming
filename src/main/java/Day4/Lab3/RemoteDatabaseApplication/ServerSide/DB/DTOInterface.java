package Day4.Lab3.RemoteDatabaseApplication.ServerSide.DB;

import Day4.Lab3.RemoteDatabaseApplication.Models.Employee;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DTOInterface extends Remote{
    boolean createEmployee(Employee employee) throws RemoteException;
    void updateEmployee(Employee employee) throws RemoteException;
    void deleteEmployee(Employee employee) throws RemoteException;
    Employee retrieveEmployee(int id) throws RemoteException;
    List<Employee> retrieveAllEmployees() throws RemoteException;
}
