package Day1.ReadingWritingConsol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String [] lines = new String[100];

        System.out.println("Enter lines of text upto 100");
        System.out.println("Enter 'stop' to quit.");

        for (int i = 0 ; i < 100 ; i++) {
            try {
                lines[i] = bufferedReader.readLine();
                System.out.println(lines[i]);
                if (lines[i].equals("stop"))
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
