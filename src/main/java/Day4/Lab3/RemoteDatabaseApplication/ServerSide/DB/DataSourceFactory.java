package Day4.Lab3.RemoteDatabaseApplication.ServerSide.DB;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataSourceFactory {
    public static DataSource getMySQLDataSource () {
        MysqlDataSource mysqlDataSource = null;
        try {
            Properties props = new Properties();
            FileInputStream file = new FileInputStream("db.properties");
            props.load(file);
            mysqlDataSource = new MysqlDataSource();

            mysqlDataSource.setURL(props.getProperty("MYSQL_DB_URL"));
            mysqlDataSource.setUser(props.getProperty("MYSQL_DB_USERNAME"));
            mysqlDataSource.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mysqlDataSource;
    }
}
