package com.taskmanager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> task;
    private Connection connection;

    public TaskManager(){
        this.task= new ArrayList<>();
        this.connection= establishConnection();
        loadTaskFromDatabase();
    }

    private Connection establishConnection(){
        Connection connection= null;

        try{
            connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/taskmanager","root","User1234@");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

    public void addTask(String description){
        Task newtask = new Task(task.size()+1, description, false);
        task.add(newtask);

        try(PreparedStatement preparedStatement= connection.prepareStatement(
                "INSERT INTO taskmanager.task (id,description,completed) VALUES (?,?,?)")){
            preparedStatement.setInt(1,newtask.getId());
            preparedStatement.setString(2,newtask.getDescription());
            preparedStatement.setBoolean(3, newtask.isCompleted());
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        System.out.println("Task added =======> "+ newtask);
    }

    public void deleteTask(int index){
        if(index>=0 && index<task.size()){
            Task taskToDelete= task.get(index);
            task.remove(index);
            try(PreparedStatement preparedStatement= connection.prepareStatement(
                    "DELETE FROM taskmanager.task WHERE id=?"
            )){
                preparedStatement.setInt(1, taskToDelete.getId() );
                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("----------Invalid Index!!-----------------");
        }
    }

    private void loadTaskFromDatabase(){
        try(Statement statement= connection.createStatement()){
            ResultSet resultSet= statement.executeQuery("SELECT * FROM taskmanager.task");
            while(resultSet.next()){
               int id= resultSet.getInt("id");
               String description= resultSet.getString("description");
               boolean completed= resultSet.getBoolean("completed");
                task.add(new Task(id,description,completed));
            }
        }
            catch(SQLException e){
                e.printStackTrace();
            }
        }

    public void viewTask(){
        System.out.print("Tasks added:\n");
        for(Task tasks: task){
            System.out.println(tasks);
        }
    }

    public void markTaskAsCompleted(int index){
        if(index>=0 && index<task.size()){
            Task marktask= task.get(index);
            marktask.markAsCompleted();

            try(PreparedStatement preparedStatement= connection.prepareStatement(
                    "UPDATE taskmanager.task SET completed=? WHERE id=?"
            )){
                preparedStatement.setBoolean(1,true);
                preparedStatement.setInt(2,marktask.getId());
                preparedStatement.executeUpdate();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            System.out.println("Task marked as completed: "+marktask);
        }
        else{
            System.out.println("----------Invalid Index!!-----------------");
        }
    }

    public void closeConnection(){
        try {
            if (connection != null) {
                connection.close();
                System.out.println("\nDatabase connection is closed!");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

}
