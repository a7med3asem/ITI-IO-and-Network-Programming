package Day4.Lab2.Calculator.ClientSide;

import Day4.Lab2.Calculator.ServerSide.CalculatorInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
//        if (args[0] != null && args[1] != null && args[2] != null) {
        if (args.length == 3) {
            try {
                // Getting instance of local registry table
                Registry registry = LocateRegistry.getRegistry(1099);
                // Locating name service and get object reference
                CalculatorInterface calculatorInterfaceRef = (CalculatorInterface) registry.lookup("CalculatorService");
                double number1 = Double.parseDouble(args[0])
                        , number2 = Double.parseDouble(args[2]);
                double result;
                // Call Server methods
                switch (args[1].trim()) {
                    case "+" :
                        result = calculatorInterfaceRef.add(number1, number2);
                        break;
                    case "-" :
                        result = calculatorInterfaceRef.subtract(number1, number2);
                        break;
                    case "*" :
                        result = calculatorInterfaceRef.multiply(number1, number2);
                        break;
                    case "/" :
                        result = calculatorInterfaceRef.divide(number1, number2);
                        break;
                    default:
                        System.out.println("Invalid statement!");
                        return;
                }
                // Print result
                System.out.println(result);
            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
            }
        }
    }
}
