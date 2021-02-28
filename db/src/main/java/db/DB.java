package db;

import java.sql.*;

public class DB {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement psInsert;

    public static int status(String str) throws SQLException {
        int status;
        ResultSet rs = stmt.executeQuery("SELECT status FROM users WHERE login = '" + str + "';");
        System.out.println(rs.getString("status"));
        status = rs.getInt("status");
        rs.close();
        return status;
    }

    public static String getNickname(String login, String password) throws SQLException {
        String nick = null;
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT nickname FROM users " +
                    "WHERE login = '" + login + "' AND password = '" + password + "';");
            System.out.println(rs.getString("nickname"));
            nick = rs.getString("nickname");
            rs.close();
        } catch (SQLException e){
            e.getErrorCode();
            nick = null;
            rs.close();
        } finally {
            return nick;
        }
    }

    public static String checkUserExisting(String login) throws SQLException {
        String nick = null;
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT nickname FROM users " +
                    "WHERE login = '" + login + "';");
            System.out.println(rs.getString("nickname"));
            nick = rs.getString("nickname");
            rs.close();
        } catch (SQLException e){
            e.getErrorCode();
            nick = null;
            rs.close();
        } finally {
            return nick;
        }
    }
    //передачу параметров лучше через массив, наверное (настройки добавятся помимо смены ника)
    public static void updSettings(String login, String nickname){
        try {
            updNickname(login, nickname);
        } catch (SQLException e) {
            e.getErrorCode();
        }
    }

    public static void updNickname (String login, String nickname) throws SQLException{
        stmt.executeUpdate("UPDATE users SET nickname = '" + nickname + "' WHERE login = '" + login + "';");
        System.out.println("updNick");
    }

    public static void addUser(String login, String password, String nickname) throws SQLException {
        stmt.executeUpdate(
                "INSERT INTO users (login, password, nickname) " +
                        "VALUES ('" + login + "', '" + password + "', '" + nickname + "');");
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        stmt = connection.createStatement();
    }

    private static void disconnect() {
        try {
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
