package Day4.Lab3.RemoteDatabaseApplication.ServerSide.DB;

import Day4.Lab3.RemoteDatabaseApplication.Models.Employee;

import javax.sql.DataSource;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DTOImplementation extends UnicastRemoteObject implements DTOInterface {
    public DTOImplementation() throws RemoteException {
    }

    public boolean createEmployee(Employee employee) throws RemoteException {
        try {
            DataSource dataSource = DataSourceFactory.getMySQLDataSource();
            Connection connection = dataSource.getConnection();
            PreparedStatement pst = connection.prepareStatement("insert into hr.employees values(?, ?, ?, ?, ?, ?);");

            pst.setInt(1, employee.getId());
            pst.setString(2, employee.getFirstName());
            pst.setString(3, employee.getMiddleName());
            pst.setString(4, employee.getLastName());
            pst.setString(5, employee.getEmailAddress());
            pst.setString(6, employee.getPhoneNumber());

            // verification
            int rowsUpdated = pst.executeUpdate();
            System.out.println("Added rows: " + rowsUpdated);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateEmployee(Employee employee) throws RemoteException{
        try {
            DataSource dataSource = DataSourceFactory.getMySQLDataSource();
            Connection connection = dataSource.getConnection();
            Statement statement =
                    connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("select * from hr.employees;");
            resultSet.next();

            resultSet.updateInt("id", employee.getId());
            resultSet.updateString("fname", employee.getFirstName());
            resultSet.updateString("mname", employee.getMiddleName());
            resultSet.updateString("lname", employee.getLastName());
            resultSet.updateString("email", employee.getEmailAddress());
            resultSet.updateString("phone", employee.getPhoneNumber());

            resultSet.updateRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(Employee employee) throws RemoteException{
        try {
            DataSource dataSource = DataSourceFactory.getMySQLDataSource();
            Connection connection = dataSource.getConnection();
            Statement statement =
                    connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sqlStmt = "select * from hr.employees where id = " + employee.getId() + ";";
            ResultSet resultSet = statement.executeQuery(sqlStmt);
            resultSet.deleteRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Employee retrieveEmployee(int id) throws RemoteException{
        Employee employee = new Employee();
        try {
        DataSource dataSource = DataSourceFactory.getMySQLDataSource();
        Connection connection = dataSource.getConnection();

        PreparedStatement pst = connection.prepareStatement("select * from hr.employees where id = ? ;");
        pst.setInt(1, id);
        ResultSet resultSet = pst.executeQuery();
        resultSet.next();

        if (resultSet != null) {
            employee.setId(resultSet.getInt(1));
            employee.setFirstName(resultSet.getString("fname"));
            employee.setMiddleName(resultSet.getString("mname"));
            employee.setLastName(resultSet.getString("lname"));
            employee.setEmailAddress(resultSet.getString("email"));
            employee.setPhoneNumber(resultSet.getString("phone"));
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public List<Employee> retrieveAllEmployees() throws RemoteException {
        List<Employee> employees = new ArrayList<>();
        try {
            DataSource dataSource = DataSourceFactory.getMySQLDataSource();
            Connection connection = dataSource.getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from hr.employees;");

            while (resultSet.next()) {
                Employee employee = new Employee();

                employee.setId(resultSet.getInt(1));
                employee.setFirstName(resultSet.getString("fname"));
                employee.setMiddleName(resultSet.getString("mname"));
                employee.setLastName(resultSet.getString("lname"));
                employee.setEmailAddress(resultSet.getString("email"));
                employee.setPhoneNumber(resultSet.getString("phone"));

                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;

    }
}