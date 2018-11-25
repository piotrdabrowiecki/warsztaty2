package pl.coderslab;


import pl.coderslab.utils.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private UserGroup userGroup;


    public User(){


    }
    public User(String username, String email, String password, UserGroup userGroup){
        this.username = username;
        this.email = email;
        setPassword(password);
        this.userGroup = userGroup;



    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {

        this.password = BCrypt.hashpw(password, BCrypt.gensalt());

    }
    public void setUsername(String username){


        this.username = username;

    }
    public void setUserGroup(UserGroup userGroup){

        this.userGroup = userGroup;

    }

    public void saveToDB(Connection conn) throws SQLException {
        if (this.id == 0) {
            String sql = "INSERT INTO users(username, email, password, user_group_id) VALUES (?, ?, ?, ?)";
            String[] generatedColumns = { "ID" };
            PreparedStatement preparedStatement = conn.prepareStatement(sql, generatedColumns);
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.email);
            preparedStatement.setString(3, this.password);
            preparedStatement.setInt(4, this.userGroup.getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
        } else {
            String sql = "UPDATE users SET username=?, email=?, password=?, id=? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.email);
            preparedStatement.setString(3, this.password);
            preparedStatement.setInt(4, this.userGroup.getId());
            preparedStatement.setInt(5, this.id);
            preparedStatement.executeUpdate();
        }
    }
    static public User loadUserById(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM users where id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            User loadedUser = new User();
            loadedUser.id = resultSet.getInt("id");
            loadedUser.username = resultSet.getString("username");
            loadedUser.password = resultSet.getString("password");
            loadedUser.email = resultSet.getString("email");
            loadedUser.userGroup = UserGroup.loadUserGroupById(conn,resultSet.getInt("user_group_id"));
            return loadedUser;}
        return null;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    static public User[] loadAllUsers(Connection conn) throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM users";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User loadedUser = new User();
            loadedUser.id = resultSet.getInt("id");
            loadedUser.username = resultSet.getString("username");
            loadedUser.password = resultSet.getString("password");
            loadedUser.email = resultSet.getString("email");
            loadedUser.userGroup = UserGroup.loadUserGroupById(conn, resultSet.getInt("user_group_id"));
            users.add(loadedUser);}
        User[] uArray = new User[users.size()]; uArray = users.toArray(uArray);
        return uArray;
    }
    public void delete(Connection conn) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM users WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, this.id);
            preparedStatement.executeUpdate();
            this.id = 0;
        }
    }
    static public User[] loadAllByGroupId(Connection conn, int id) throws SQLException{
        ArrayList<User> users_by_group = new ArrayList<User>();
        String sql = "SELECT * FROM users where user_group_id = ? ";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User loadedUser = new User();
            loadedUser.id = resultSet.getInt("id");
            loadedUser.username = resultSet.getString("username");
            loadedUser.password = resultSet.getString("password");
            loadedUser.email = resultSet.getString("email");
            loadedUser.userGroup = UserGroup.loadUserGroupById(conn, resultSet.getInt("user_group_id"));
            users_by_group.add(loadedUser);}
        User[] uArray = new User[users_by_group.size()]; uArray = users_by_group.toArray(uArray);
        return uArray;











    }














}
































