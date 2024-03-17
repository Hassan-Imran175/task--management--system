package com.taskmanager;

public class Task {
   private int id;
   private String description;
   private boolean completed;

   public Task(int id,String description, boolean completed){
       this.id=id;
       this.description=description;
       this.completed=completed;
   }

   public int getId(){
       return id;
   }
    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markAsCompleted(){
       completed= true;
    }

    @Override
    public String toString(){
       return getId()+ " Task: "+ getDescription()+ " (Completed: "+ isCompleted()+" )";
    }
}
