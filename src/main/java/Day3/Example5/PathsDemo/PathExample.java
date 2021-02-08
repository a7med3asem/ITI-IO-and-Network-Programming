package Day3.Example5.PathsDemo;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {
    public static void main(String[] args) {
        String filePath = "G:\\JETS\\IO and Network Programming\\Day3\\src\\main\\resources\\Test.txt";
        Path path1 = Paths.get(filePath);
        System.out.println("Path of : " + filePath);
        System.out.println("is equal " + path1);

        String originalPath = "G:\\JETS\\IO and Network Programming\\Day3\\src\\main\\java\\Day3\\Example5\\..\\Example6";
        Path path2 = Paths.get(originalPath);
        System.out.println("path2 = " + path2);

        Path path3 = path2.normalize();
        System.out.println("path2 = " + path3);

        // Using Files to create directories
        Path path4 = Paths.get("G:\\JETS\\subdir");
        try {
            Path newDir = Files.createDirectory(path4);
        } catch (FileAlreadyExistsException e) {
            // the directory already exists.
        } catch (IOException e) {
            // something else went wrong
            e.printStackTrace();
        }

        // Copying a file from one path to another
        Path sourcePath = Paths.get("G:\\JETS\\IO and Network Programming\\Day3\\src\\main\\java\\Day3\\Example1\\BuffersExample");
        Path destinationPath = Paths.get("G:\\JETS\\IO and Network Programming\\Day3\\src\\test\\java");

        try {
            Files.copy(sourcePath, destinationPath.resolve(sourcePath.getFileName()));
//            Path bytes = Files.copy(sourcePath, destinationPath);

        } catch (FileAlreadyExistsException e) {
            // destination file already exists
        } catch (IOException e) {
            // something else went wrong
            e.printStackTrace();
        }
    }
}
