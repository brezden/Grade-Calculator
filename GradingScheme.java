//This is a public class that is used for Grading Schemes.
public class GradingScheme {
    //The following couple lines are saving integers assigned to value for the scheme.
    private int assignmentPercent;

    private int projectPercent;

    private int finalPercent;

    //This is method that will calculate if the users values that were entered for the grading scheme equal 100%
    public GradingScheme(int assignmentPercent, int projectPercent, int finalPercent) throws IllegalArgumentException {
        if (assignmentPercent < 0
                || projectPercent < 0
                || finalPercent < 0
                || assignmentPercent + projectPercent + finalPercent != 100) {
            //This will run if the users values are not equal to 100%.
            throw new IllegalArgumentException("Percent parameters are invalid!");
        }
        //Using this keyword to assign the values.
        this.assignmentPercent = assignmentPercent;
        this.projectPercent = projectPercent;
        this.finalPercent = finalPercent;
    }
    //This is a toString method that will take the values from above and merge them into one string.
    public String toString() {
        //Returns the string that will contain all the values from above.
        return "Assignment: " + assignmentPercent + "%, Project: " + projectPercent + "%, Final: " + finalPercent + "%.";
    }

    //The following lines of code are all getters and setters that will return and set the values that we used from above.
    public int getAssignmentPercent() {
        return assignmentPercent;
    }

    public void setAssignmentPercent(int assignmentPercent) {
        this.assignmentPercent = assignmentPercent;
    }

    public int getProjectPercent() {
        return projectPercent;
    }

    public void setProjectPercent(int projectPercent) {
        this.projectPercent = projectPercent;
    }

    public int getFinalPercent() {
        return finalPercent;
    }

    public void setFinalPercent(int finalPercent) {
        this.finalPercent = finalPercent;
    }
}
