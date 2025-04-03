public class StudentRecord {
    private String name;
    private String studentID;
    private String email;

    public StudentRecord(String name, String studentID, String email) {
        this.name = name;
        this.studentID = studentID;
        this.email = email;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getEmail() {
        return email;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "Name: " + name + ", ID: " + studentID + ", Email: " + email + "\n";
    }    
}