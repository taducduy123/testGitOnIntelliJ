package com.example.traningcentermanagement.Utils;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBConnect {

    private static DBConnect instance;
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection conn;

    // New THING
    private QueryRunner queryRunner;


    private DBConnect() {       // Singleton

        try {
            //
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            //
            queryRunner = new QueryRunner();





        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static DBConnect getInstance() {
        if (instance == null) {
            instance = new DBConnect();
        }
        return instance;
    }


    public <T> List<T> executeReturnQuery(String sql, Class<T> type, Object... parameters) {
        BasicRowProcessor rowProcessor = new BasicRowProcessor(); // Xu ly tung dong

        BeanListHandler<T> handler = new BeanListHandler<>(type, rowProcessor);

        try {
            return queryRunner.query(conn, sql, handler, parameters);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public int executeVoidQuery(String sql, Object... parameters) {
        // Mac dinh moi datatype deu ke thua tu object

        try {
            return queryRunner.update(conn, sql, parameters);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}
