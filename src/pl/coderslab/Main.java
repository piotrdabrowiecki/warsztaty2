package pl.coderslab;

import pl.coderslab.utils.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {


    public static void main(String[] args){
        User user = new User("Artur", "email@example.com", "password", new UserGroup("grupa"));




        try(Connection conn = DBConnection.getConnection()){

            //user.saveToDB(conn);
//            User user1 = User.loadUserById(conn, 1);

//            System.out.println(user1.toString());
//            user1.setEmail("artur.hacia@coderslab.pl");
//            user1.saveToDB(conn);
          // User[] users = User.loadAllUsers(conn);
          // for(User userElement : users){
          //     System.out.println(userElement);
          // }
        //    User user4 = User.loadUserById(conn, 4);
        //    user4.delete(conn);
//         w jaki sposob do metody delete(conn) jest przekazywany parametr id
//         (skad ona zna paramter id)

            UserGroup group1 = new UserGroup("nowa_grupa_testowa");
            group1.saveToDB(conn);
























        }
        catch(SQLException e){


            e.printStackTrace();
        }






// create table solution(id int auto_increment, created datetime, updated datetime, description text, exercise_id int, users_id bigint, primary key(id), foreign key (exercise_id) references exercise(id), foreign key (users_id) references users(id));
// create table exercise(id int auto_increment, title varchar(255), description text, primary key(id));
























    }







}
