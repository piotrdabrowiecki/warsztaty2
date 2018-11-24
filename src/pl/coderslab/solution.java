package pl.coderslab;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class solution {
    private int id;
    private String created;
    private String updated;
    private String description;
    private int exercise_id;
    private int users_id;

    public solution(){




    }
    public solution(String description){


        this.description = description;


    }
    public int getId(){

        return id;
    }
    static public solution loadSolutionById(Connection conn, int id) throws SQLException {

        String sql = "SELECT * FROM solution where id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            solution loadedSolution = new solution();
            loadedSolution.id = resultSet.getInt("id");
            loadedSolution.created = resultSet.getString("created");
            loadedSolution.updated = resultSet.getString("updated");
            loadedSolution.description = resultSet.getString("description");
            loadedSolution.exercise_id = resultSet.getInt("exercise_id");
            loadedSolution.users_id = resultSet.getInt("users_id");
            return loadedSolution;}
        return null;
    }
    static public solution[] loadAllSolutions(Connection conn) throws SQLException {
        ArrayList<solution> solutions = new ArrayList<solution>();
        String sql = "SELECT * FROM solution";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            solution loadedSolution = new solution();
            loadedSolution.id = resultSet.getInt("id");
            loadedSolution.created = resultSet.getString("created");
            loadedSolution.updated = resultSet.getString("updated");
            loadedSolution.description = resultSet.getString("description");
            loadedSolution.exercise_id = resultSet.getInt("exercise_id");
            loadedSolution.users_id = resultSet.getInt("users_id");
            solutions.add(loadedSolution);
        }
        solution[] sArray = new solution[solutions.size()];
        sArray = solutions.toArray(sArray);
        return sArray;
    }
    public void delete(Connection conn) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM solution WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, this.id);
            preparedStatement.executeUpdate();
            this.id = 0;
        }
    }
    public void saveToDB(Connection conn) throws SQLException {
        if (this.id == 0) {
            String sql = "INSERT INTO solution(created,updated, description, exercise_id, users_id) VALUES (?, ?, ?, ?, ?)";
            String[] generatedColumns = { "ID" };
            PreparedStatement preparedStatement = conn.prepareStatement(sql, generatedColumns);
            preparedStatement.setString(1, this.created);
            preparedStatement.setString(2, this.updated);
            preparedStatement.setString(3, this.description);
            preparedStatement.setInt(4, this.exercise_id);
            preparedStatement.setInt(5, this.users_id);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
        } else {
            String sql = "UPDATE solution SET created=?,updated=?,description=?,exercise_id=?, users_id=?, id=? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, this.created);
            preparedStatement.setString(2, this.updated);
            preparedStatement.setString(3, this.description);
            preparedStatement.setInt(4, this.exercise_id);
            preparedStatement.setInt(5, this.users_id);
            preparedStatement.setInt(6, this.id);
            preparedStatement.executeUpdate();
        }
    }
    static public solution[] loadAllByUserId(Connection conn, int id) throws SQLException{
        ArrayList<solution> solutions_user = new ArrayList<solution>();
        String sql = "SELECT * FROM solution where users_id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            solution loadedSolution = new solution();
            loadedSolution.id = resultSet.getInt("id");
            loadedSolution.created = resultSet.getString("created");
            loadedSolution.updated = resultSet.getString("updated");
            loadedSolution.description = resultSet.getString("description");
            loadedSolution.exercise_id = resultSet.getInt("exercise_id");
            loadedSolution.users_id = resultSet.getInt("users_id");
            solutions_user.add(loadedSolution);
            }
        solution[] suArray = new solution[solutions_user.size()];
        suArray = solutions_user.toArray(suArray);
        return suArray;



    }
    static public solution[] loadAllByExerciseId(Connection conn, int id) throws SQLException {
        ArrayList<solution> solutions_exercise = new ArrayList<solution>();
        String sql = "SELECT * FROM solution where exercise_id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            solution loadedSolution = new solution();
            loadedSolution.id = resultSet.getInt("id");
            loadedSolution.created = resultSet.getString("created");
            loadedSolution.updated = resultSet.getString("updated");
            loadedSolution.description = resultSet.getString("description");
            loadedSolution.exercise_id = resultSet.getInt("exercise_id");
            loadedSolution.users_id = resultSet.getInt("users_id");
            solutions_exercise.add(loadedSolution);
        }
        solution[] seArray = new solution[solutions_exercise.size()];
        seArray = solutions_exercise.toArray(seArray);
        return seArray;



    }














}
