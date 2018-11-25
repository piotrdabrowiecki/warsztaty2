package pl.coderslab;

import pl.coderslab.utils.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args){



        try(Connection conn = DBConnection.getConnection()){




                String choice = "";
            while(!choice.equals("quit")) {

                Scanner scanner = new Scanner(System.in);


                User users[] = User.loadAllUsers(conn);
                for(int i = 0; i < users.length;i++){

                    System.out.println(users[i]);
                }
                System.out.println("                        ");
                System.out.println(" Wybierz jedną z opcji:\n" +
                        "\n" +
                        "    add – dodanie użytkownika,\n" +
                        "    edit – edycja użytkownika,\n" +
                        "    delete – usunięcie użytkownika,\n" +
                        "    quit – zakończenie programu.\"\n");
                choice = scanner.nextLine();
                if (choice.equals("add")) {
                    Scanner add = new Scanner(System.in);
                    System.out.println("Podaj username:");
                    String username = add.nextLine();
                    System.out.println("Podaj email:");
                    String email = add.nextLine();
                    System.out.println("Podaj password:");
                    String password = add.nextLine();
                    System.out.println("Podaj user_group");
                    String user_group = add.nextLine();

                    UserGroup group = new UserGroup(user_group);
                    int id_grupy = group.getId();

                    User adduser = new User(username, email, password, group);
                    adduser.saveToDB(conn);

                } else if (choice.equals("edit")) {
                    Scanner edit = new Scanner(System.in);
                    System.out.println("Podaj id:");
                    int Id = edit.nextInt();
                    System.out.println("Podaj username:");
                    String username = edit.nextLine();
                    System.out.println("Podaj email:");
                    String email = edit.nextLine();
                    System.out.println("Podaj password:");
                    String password = edit.nextLine();
                    System.out.println("Podaj user_group id");
                    int group_id = edit.nextInt();
                    UserGroup user_group = UserGroup.loadUserGroupById(conn, group_id);


                    User editedUser = User.loadUserById(conn, Id);
                    editedUser.setEmail(email);
                    editedUser.setPassword(password);
                    editedUser.setUsername(username);
                    editedUser.setUserGroup(user_group);
                    editedUser.saveToDB(conn);


                } else if (choice.equals("delete")) {
                    Scanner delete = new Scanner(System.in);
                    System.out.println("Podaj id:");
                    int Id = delete.nextInt();
                    User deleteUser = User.loadUserById(conn, Id);
                    deleteUser.delete(conn);



                } else if (choice.equals("quit")) {

                    System.out.println("........Koniec........");

                }


            }
















        }
        catch(SQLException e){


            e.printStackTrace();
        }






// create table solution(id int auto_increment, created datetime, updated datetime, description text, exercise_id int, users_id bigint, primary key(id), foreign key (exercise_id) references exercise(id), foreign key (users_id) references users(id));
// create table exercise(id int auto_increment, title varchar(255), description text, primary key(id));
























    }







}
