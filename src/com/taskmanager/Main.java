package com.taskmanager;

import java.util.Scanner;

public class Main {
    public static void main(String[]args){
        int choice,index;
        String description;

        TaskManager taskmanager= new TaskManager();
        Scanner scanner= new Scanner(System.in);

        try {
            while (true) {
                System.out.println("\n 1. Add the task\n");
                System.out.println("2. View the task\n");
                System.out.println("3. Mark the task as completed\n");
                System.out.println("4. Delete the task\n");
                System.out.println("5. Exit\n");
                System.out.println("Enter your choice:");
                String choiceString =scanner.nextLine();

                try{
                    choice= Integer.parseInt(choiceString);
                }
                catch(NumberFormatException e){
                    System.out.println("Enter the valid input!!!");
                    continue;
                }

                System.out.println("\n");

                switch (choice) {
                    case 1:
                        System.out.println("Enter the description:");
                        description = scanner.nextLine();
                        System.out.println("\n");
                        taskmanager.addTask(description);
                        break;
                    case 2:
                        taskmanager.viewTask();
                        break;
                    case 3:
                        System.out.println("Enter the index number (Starts from 0):");
                        String indexString = scanner.nextLine();
                        try{
                            index= Integer.parseInt(indexString);
                        }
                        catch(NumberFormatException e){
                            System.out.println("Enter the valid input!!!");
                            continue;
                        }
                        System.out.println("\n");
                        taskmanager.markTaskAsCompleted(index);
                        break;
                    case 4:
                        System.out.println("Enter the index number (Starts from 0):");
                        String indexStrings = scanner.nextLine();
                        try{
                            index= Integer.parseInt(indexStrings);
                        }
                        catch(NumberFormatException e){
                            System.out.println("Enter the valid input!!!");
                            continue;
                        }
                        taskmanager.deleteTask(index);
                        break;
                    case 5:
                        try{
                            Thread.sleep(2000);
                        }
                        catch(InterruptedException e){
                            e.printStackTrace();
                        }
                        System.out.println("-------Exiting the program. Goodbye!!--------------");
                        taskmanager.closeConnection();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("---------------Invalid choice! kindly enter a valid choice-----------------\n");
                }
            }
        }
        finally{
            try{
                Thread.sleep(2000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            taskmanager.closeConnection();
            System.out.println("-------Exiting the program. Goodbye!!--------------");
        }
    }
}
