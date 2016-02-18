package message;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 2016/2/18.
 */
public class DBUtils {
    public static String URL;
    public static String USERNAME;
    public static String PASSWORD;
    public static String DRIVER;

    //连接配置文件(db-config)，配置文件里面包含连接数据库所必须的信息
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("db-config");

    private DBUtils(){}

    static {
        URL = resourceBundle.getString("jdbc.url");
        USERNAME = resourceBundle.getString("jdbc.username");
        PASSWORD = resourceBundle.getString("jdbc.password");
        DRIVER = resourceBundle.getString("jdbc.driver");
        try{
            Class.forName(DRIVER);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    //连接数据库
    public static Connection getconnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败！");
        }
        return connection;
    }

    //关闭数据库
    public static void close(ResultSet resultSet, Statement statement,Connection connection){
        try{
            if(resultSet!=null)
                resultSet.close();
            if(statement!=null)
                statement.close();
            if(connection!=null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
