package com.indepfin.tallerdp.persistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import org.h2.tools.DeleteDbFiles;


public class DatabaseManager {
	
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/indepfin";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

//    public static void main(String[] args) throws Exception {
//        try {
//            // delete the H2 database named 'test' in the user home directory
//            DeleteDbFiles.execute("~", "test", true);
//            insertWithStatement();
//            DeleteDbFiles.execute("~", "test", true);
//            insertWithPreparedStatement();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void createTables() throws SQLException {
    	Connection connection = getDBConnection();
        PreparedStatement createPreparedStatement = null;

        String createTables = "CREATE TABLE USER(userId int primary key,"
        		+ " name varchar(255), username varchar(50), password varchar(50) );"
        		+ "CREATE TABLE EGRESO(egresoId int primary key,"
        		+ " userId int , monto varchar(50), fecha varchar(50), categoriaId int );"
        		+ "CREATE TABLE CATEGORIA(categoriaId int primary key,"
        		+ " nombre varchar(50) );"
        		+ "CREATE TABLE INGRESO(ingresoId int primary key,"
        		+ " userId int, monto varchar(50), fecha varchar(40) );"
        		+ "CREATE TABLE INVERSION(inversionId int primary key,"
        		+ " userId int, tipoInvId varchar(40) );";
        
        try {
            connection.setAutoCommit(false);
            createPreparedStatement = connection.prepareStatement(createTables);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

	// H2 SQL Prepared Statement Example
    public static void insertWithPreparedStatement(String query) throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement insertPreparedStatement = null;

        //String InsertQuery = "INSERT INTO PERSON" + "(id, name) values" + "(?,?)";
        try {
            connection.setAutoCommit(false);
                      
            insertPreparedStatement = connection.prepareStatement(query);
//            insertPreparedStatement.setInt(1, 1);
//            insertPreparedStatement.setString(2, "Jose");
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();
           
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
    
    public ResultSet selectWithPreparedQuery(String query) throws SQLException {
    	Connection connection = getDBConnection();
        PreparedStatement selectPreparedStatement = null;
        ResultSet result = null;
        try {
        	selectPreparedStatement = connection.prepareStatement(query);
            result = selectPreparedStatement.executeQuery();
            
            selectPreparedStatement.close();
            connection.commit();
            
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return result;
    }

    // H2 SQL Statement Example
    public static void insertWithStatement() throws SQLException {
        Connection connection = getDBConnection();
        Statement stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.execute("CREATE TABLE PERSON(id int primary key, name varchar(255))");
            stmt.execute("INSERT INTO PERSON(id, name) VALUES(1, 'Anju')");
            stmt.execute("INSERT INTO PERSON(id, name) VALUES(2, 'Sonia')");
            stmt.execute("INSERT INTO PERSON(id, name) VALUES(3, 'Asha')");

            ResultSet rs = stmt.executeQuery("select * from PERSON");
            System.out.println("H2 Database inserted through Statement");
            while (rs.next()) {
                System.out.println("Id "+rs.getInt("id")+" Name "+rs.getString("name"));
            }
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
                    DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}
