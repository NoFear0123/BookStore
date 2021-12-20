package utils;/*
@create 2021-10-13 21:14
*/

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns=new ThreadLocal<Connection>();
    static {
        try {
            Properties properties=new Properties();
            //读取配置文件
            InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(inputStream);
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            //System.out.println(dataSource.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //获取链接
    public static Connection getConnection(){
        Connection conn=conns.get();
        if (conn==null){
            try {
                conn=dataSource.getConnection();//从数据库连接池获取连接
                conns.set(conn);//保存到ThreadLocal对象中，供后面的jdbc使用
                conn.setAutoCommit(false);//设置为手动管理事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        /*Connection conn=null;
        try {
            conn=dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
        return conn;
    }
    //关闭连接
    /*public static void close(Connection conn){
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }*/

    //提交事务和关闭连接
    public static void commitAndClose(){
        Connection connection=conns.get();
        if (connection!=null){
            try {
                connection.commit();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        //一定要执行，否则出错。
        conns.remove();
    }
    //回滚事务
    public static void rollbackAndClose(){
        Connection connection=conns.get();
        if (connection!=null){
            try {
                connection.rollback();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        //一定要执行，否则出错。
        conns.remove();
    }
}
