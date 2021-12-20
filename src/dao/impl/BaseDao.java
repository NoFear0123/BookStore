package dao.impl;/*
@create 2021-10-13 22:16
*/

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JDBCUtils;

import javax.sql.rowset.JdbcRowSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    //使用DBUtils操作数据库
    QueryRunner queryRunner=new QueryRunner();
    public int update(String sql,Object ...args){
        Connection conn= JDBCUtils.getConnection();
        try {
            return queryRunner.update(conn,sql,args);
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }

    }
        //返回一个查询信息
    public <T> T queryForOne(Class<T> type,String sql,Object ...args){
        Connection conn=JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }
        //返回多个查询信息列表
    public <T> List<T> queryForList(Class<T> type, String sql, Object ...args){
        Connection conn=JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }
    //返回单个值
    public Object queryForSingleValue(String sql, Object ...args){
        Connection conn=JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new ScalarHandler(),args);
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }
}
