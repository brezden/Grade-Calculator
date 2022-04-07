//Group Work 1
//April 6th, 2022
//Created by: Wilson Agyapong, Alyssa Barski, Callum Brezden, Yuyang Liu and Daniel Qin

//These are imports for the following code.
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//Main Class
public class Main {
    //This is a scanner object that will take the userInput.
    private Scanner userInput;

    private GradingScheme[] schemes;

    //Main method
    public Main() {
        userInput = new Scanner(System.in);
        schemes = new GradingScheme[] {
                new GradingScheme(30, 40, 30),
                new GradingScheme(20, 20, 60)
        };
    }
    //Run method that will run the method to print out the title screen.
    //Also runs a loop that will ask for the filename of the txt file.
    public void run() {
        printTitle();
        while (true) {
            System.out.print("Please input the scores filename: ");
            String filename = userInput.nextLine();
            if (filename.equals("q")) {
                break;
            }
            //Try catch statement that will create an ArrayList that will hold the students scores
            try {
                ArrayList<StudentScore> studentScores = readStudentScores(filename);
                GradingScheme scheme = selectGradingScheme();
                //Runs the method that holds the print commands.
                printStudentGrades(studentScores, scheme);
                //Runs the method that will save the grades into a text file.
                saveFile(studentScores, scheme);

                //Catch statement that will run if the file does not exist.
            } catch (FileNotFoundException e) {
                System.out.println(filename + " is not exist!");
                continue;
            }
            System.out.println();
        }
        System.out.println("GoodBye!");
    }
    //Method that only prints out the main title screen for the project.
    private void printTitle() {
        System.out.println("=====================================");
        System.out.println("=  Welcome to Score Grading System  =");
        System.out.println("=====================================");
    }
    //Private method that uses the ArrayList. Method is called readStudentScores.
    private ArrayList<StudentScore> readStudentScores(String filename) throws FileNotFoundException {
        //Creates a new object called file.
        File file = new File(filename);
        //Creates a new scanner object.
        Scanner sc = new Scanner(file);
        //Creates a new ArrayList
        ArrayList<StudentScore> studentScores = new ArrayList<>();
        //While loop that will run through the student score
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            StudentScore studentScore = parseStudentScore(line);
            if (studentScore != null) {
                studentScores.add(studentScore);
            }
        }
        //Return statement that will return the StudentScores.
        return studentScores;
    }
    //Private method that analyzes the final scores.
    private StudentScore parseStudentScore(String line) {
        if (line == null || line.equals("")) {
            return null;
        }
        String[] parts = line.split(" ");
        String name = parts[0];
        //Adds the integers into there dedicated placeholders.
        int assignmentScore = Integer.parseInt(parts[1]);
        int projectScore = Integer.parseInt(parts[2]);
        int finalScore = Integer.parseInt(parts[3]);
        //Return statement that will return all the properties of the student.
        return new StudentScore(name, assignmentScore, projectScore, finalScore);
    }
    //Print method that will take the Arraylist of the students scores and run through them.
    private void printStudentGrades(ArrayList<StudentScore> studentScores, GradingScheme scheme) {
        if (studentScores == null || scheme == null) {
            return;
        }
        //This will print out the student scores. We used a for loop that will execute for every grade in the file.
        for (StudentScore studentScore : studentScores) {
            System.out.println(studentScore.getName() + ": " + studentScore.getGrade(scheme));
        }
    }
    //This is a method that will work the grading scheme.
    private GradingScheme selectGradingScheme() {
        //While loop that executes through the possible grading schemes while asking the user for the response.
        while (true) {
            System.out.println("Please select a grading scheme:");
            for (int i = 0; i < schemes.length; i++) {
                System.out.println((i + 1) + ". " + schemes[i]);
            }
            System.out.println((schemes.length + 1) + ". Input a New Grading Scheme");
            //Int that holds the userInput in the variable "select".
            int select = userInput.nextInt();
            userInput.nextLine();
            //This is a if statement that will check to see if the user has entered a proper value.
            if (select < 1 || select > schemes.length + 1) {
                //Print statement that will run if the input is not valid.
                System.out.println("Invalid Input!");
            } else {
                //This is a if statement that will run if the select is less or equal to the scheme length.
                if (select <= schemes.length) {
                    return schemes[select - 1];
                } else {
                    //This will run the newGradingScheme method that will get information from the user
                    //to make a new grading scheme and implement it into the grades.
                    GradingScheme scheme = inputNewGradingScheme();
                    if (scheme != null) {
                        return scheme;
                    }
                }
            }
        }
    }
    // Method that will calculate the new grading scheme if requested by user.
    private GradingScheme inputNewGradingScheme() {
        //Try Catch statement that will get the inputs from the user and assign them to values.
        try {
            //Asking for the percentage for the Assignment selection.
            System.out.print("Assignment(%): ");
            int assignmentPercent = userInput.nextInt();
            userInput.nextLine();
            //Asking for the percentage for the Project selection.
            System.out.print("Project(%): ");
            int projectPercent = userInput.nextInt();
            userInput.nextLine();
            //Asking for the Final for the Project selection.
            System.out.print("Final(%): ");
            int finalPercent = userInput.nextInt();
            userInput.nextLine();
            return new GradingScheme(assignmentPercent, projectPercent, finalPercent);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }
    //Method that will calculate the save file
    private void saveFile(ArrayList<StudentScore> studentScores, GradingScheme scheme) {
        //This will print once the user has finished entering the values and selecting the grading scheme.
        System.out.print("Save the grade scores? (y/n) ");
        //String that holds the input from the user.
        String input = userInput.nextLine().trim().toLowerCase();
        //Checks if the user enters "y"
        if (!input.equals("y")) {
            return;
        }
        //These following lines will ask the user for the output name.
        System.out.print("Please input the output filename: ");
        //Assigns the file name to a string called "fileName".
        String filename = userInput.nextLine();
        //Try statement that will save the file that the user has created.
        try {
            //Using the import FileWriter to create the new file.
            //This file will be named by using the string that we just saved from before.
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            //This will run through all the students in the text file and write them into the file.
            for (StudentScore studentScore : studentScores) {
                bw.write(studentScore.getName() + " " + studentScore.getGrade(scheme) + "\n");
            }
            bw.close();
            //Catch statement incase an error has happened during the saving of the file.
        } catch (IOException e) {
            System.out.println("Save Failed!");
            return;
        }
        //This will print once the file has successfully saved.
        System.out.println("Saved!");
    }
    //This is the main method for this file. It will run the above by running the run() method.
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

}
