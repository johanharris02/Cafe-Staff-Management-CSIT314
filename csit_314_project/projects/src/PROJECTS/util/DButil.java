package PROJECTS.util;
import java.sql.*;

/**
 * JDBC 工具类 utility class
 */
public class DButil {
    /**
     * 工具类中的构造方法 都是私有的
     * 因为工具类当中的方法都是静态的，不需要new对象 之间采用类名调用
     */
    private DButil(){}

    //静态代码块在类加载的时候执行， 并且只执行一次。防止getConnection 2次
    //Static blocks of code are executed when the class loads, and only once. Prevents getConnection() 2 times
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象 Gets the database connection object
     *
     * @return连接对象
     * @throws SQLException
     */
    public static Connection getConnection()throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/csit314db","root","123456");
        return connection;
    }

    /**
     * 关闭资源 Close resource
     * @param connection 连接对象 connection object
     * @param statement 数据库操作对象 Database operation object
     * @param resultSet 结果集 result set
     */
    public static void clos(Connection connection, Statement statement, ResultSet resultSet){
        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
