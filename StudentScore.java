//Class for calculating the Student Scores
public class StudentScore {
    //The following couple lines are saving integers assigned to value for the students name and scores.
    private String name;

    private int assignmentScore;

    private int projectScore;

    private int finalScore;

    //Method used for assigning the values for the student.
    //The parameters passed in are the values from above.
    public StudentScore(String name, int assignmentScore, int projectScore, int finalScore) {
        this.name = name;
        this.assignmentScore = assignmentScore;
        this.projectScore = projectScore;
        this.finalScore = finalScore;
    }
    //This is a method that will calculate the values from the grading scheme.
    public String getGrade(GradingScheme scheme) {
        if (scheme == null) {
            return "F";
        }
        //This line of code will calculate the values and assign them out of a 100.
        //It takes the percentage value from the area of the scheme and calculates it with that.
        double totalScore = (double)assignmentScore * scheme.getAssignmentPercent() / 100
                + (double)projectScore * scheme.getProjectPercent() / 100
                + (double)finalScore * scheme.getFinalPercent() / 100;
        //The following is a series of if statements that will return the value that corresponds to the letter grade.
        if (totalScore >= 90) {
            return "A+";
        } else if (totalScore >= 85) {
            return "A";
        } else if (totalScore >= 80) {
            return "A-";
        } else if (totalScore >= 77) {
            return "B+";
        } else if (totalScore >= 73) {
            return "B";
        } else if (totalScore >= 70) {
            return "B-";
        } else if (totalScore >= 67) {
            return "C+";
        } else if (totalScore >= 63) {
            return "C";
        } else if (totalScore >= 60) {
            return "C-";
        } else if (totalScore >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    //The following lines of code are all getters and setters that will return and set the values that we used from above.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAssignmentScore() {
        return assignmentScore;
    }

    public void setAssignmentScore(int assignmentScore) {
        this.assignmentScore = assignmentScore;
    }

    public int getProjectScore() {
        return projectScore;
    }

    public void setProjectScore(int projectScore) {
        this.projectScore = projectScore;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }
}
