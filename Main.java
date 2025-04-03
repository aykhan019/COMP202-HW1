import java.util.Scanner;

public class Main {
    // Two BSTs: one for names and one for student IDs.
    private static BST bstByName = new BST("name");
    private static BST bstByID = new BST("studentID");

    // This helper method returns true if inputs are valid, otherwise false
    private static boolean recordInputsAreValid(String studentID, String name, String email) {
    	// Check if inputs are not blank
    	if (studentID.isBlank() || name.isBlank() || email.isBlank())	{
    	    return false;
    	}
    	
    	// Check if student ID is a 5 digit string
    	if (!studentID.matches("\\d{5}")) {
            System.out.println("Invalid student ID. ID should contain 5 digits only.");
            return false;
        }
    	 
    	// Check if email contains '@' and '.'
    	if (!email.contains("@") || !email.contains(".")) {
            System.out.println("Invalid email. It must contain '@' and '.' characters.");
            return false;
        }

    	return true;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            // Display menu options
            System.out.println("Welcome to the Student Records System");
            System.out.println("1. Insert new record");
            System.out.println("2. Search record");
            System.out.println("3. Delete record");
            System.out.println("4. Update record");
            System.out.println("5. Display records");
            System.out.println("6. Exit");
            System.out.print("Enter your option: \n");
            option = scanner.nextInt();
            scanner.nextLine(); 
            boolean valid = false;

            switch (option) {
                case 1:
                    // TODO: Implement insertion logic:
                    //  - Read student name, student ID, and email from user.
                    //  - Validate student ID and email format.
                    //  - Create a new StudentRecord and insert it into both bstByName and bstByID.
                	
                    // System.out.println("\n===== Insert New Record =====");
                    String studentID, name, email;
                    do {
                	  System.out.println("Enter student name: ");
                      name = scanner.nextLine().trim();

                      System.out.println("Enter student ID: ");
                      studentID = scanner.nextLine().trim();
                      
                      System.out.println("Enter student email: ");
                      email = scanner.nextLine().trim();
                      
                      valid = recordInputsAreValid(studentID, name, email);
                      if (!valid) {
	                          System.out.println("Invalid input. Please try again.\n");
                      }
                    }
                    while (!valid);
                	
                    // If inputs are valid, insert a new record
                    StudentRecord record = new StudentRecord(name, studentID, email);

                    // Insert record to both BSTs
                    if (bstByName.search(record.getName()) == null && bstByID.search(record.getStudentID()) == null){
                    	bstByName.insert(record);
                    	bstByID.insert(record);
                    	System.out.printf("Record for %s added successfully.\n\n", name);
                    } else {
                	     System.out.println("Student record insertion failed since record ID or name already exists in one or both trees");
                    }
                    
                    // // Print outputs
                    // System.out.println("\nBinary Search Tree (BST) by Name");
                    // bstByName.inOrderTraversal();
                    // System.out.println("\nBinary Search Tree (BST) by ID");
                    // bstByID.inOrderTraversal();

                    // // Pause to see output before menu appears again
                    //System.out.println("\nPress Enter to continue to the menu...");
                    //scanner.nextLine();

                    break;
                case 2:
                    // TODO: Implement search logic:
                    //  - Ask the user which field to search by (name, studentID, or email).
                    //  - Search the corresponding BST or use linear search (for email).
                    //  - Display the result.
                	
                    // System.out.println("\n===== Search Record =====");
                    String input;
                    do {
                    	// Receive Search option
                  	  	System.out.println("Search by (name/studentID/email): ");
                        input = scanner.nextLine().trim();
                        valid = true;
                        
                        // Search By Name
                        if (input.equals("name")) {
                        	System.out.println("Enter search value: ");
                            name = scanner.nextLine().trim();
                            StudentRecord r1 = bstByName.search(name);
                            if (r1 != null) {
                            	System.out.println("Record found:");
                            	System.out.println(r1);
                            }  else {
                            	System.out.println("Record Not found!");
                            }
                        }
                        // Search By Student ID
                        else if (input.equals("studentID")) {
                        	System.out.println("Enter search value: ");
                            studentID = scanner.nextLine().trim();
                            StudentRecord r2 = bstByID.search(studentID);
                            if (r2 != null) {
                            	System.out.println("Record found:");
                            	System.out.println(r2);
                            } else {
                            	System.out.println("Record Not found!");
                            }
                        }
                        // Search By Email
                        else if (input.equals("email")) {                     
                        	System.out.println("Enter search value: ");
                            email = scanner.nextLine().trim();
                            
                            StudentRecord r3 = bstByName.searchByEmail(email);

                            if (r3 != null) {
                                System.out.println("Record found:");
                                System.out.println(r3);
                            } else {
                                System.out.println("Record Not found!");
                            }
                        } 
                        // Invalid Search Option
                        else {
                            valid = false;
                        }
                        
                        if (!valid) {
                            System.out.println("Invalid input. Please try again.\n");
                        }
                      }
                      while (!valid);
                	
                    break;
                case 3:
                    // TODO: Implement deletion logic:
                    //  - Read the identifier (e.g., name) to delete.
                    //  - Delete the record from both bstByName and bstByID.
                	
                 	 System.out.println("Enter student name to delete: ");
                 	 String studentToDeleteName = scanner.nextLine().trim();

                 	 StudentRecord deletedStudentRecord1 = bstByName.deleteByStudentName(studentToDeleteName);
                 	 StudentRecord deletedStudentRecord2 = bstByID.deleteByStudentId(deletedStudentRecord1.getStudentID());

                 	 if (deletedStudentRecord1 != null && deletedStudentRecord2 != null) {
                 	     System.out.println("Student record deleted successfully.\n");
                 	 } else {
                 	     System.out.println("Student record deletion failed in one or both trees\n");
                 	 }                    	
                	
                    break;
                case 4:
                    // TODO: Implement update logic:
                    //  - Read the student's identifier and the new student ID.
                    //  - Validate the new student ID.
                    //  - Update the student record in both BSTs.
                	System.out.println("Enter student name to update ID: ");
                    String nameInput = scanner.nextLine().trim();
                    valid = true;
                    
                    String newId;
                    do {
                        System.out.println("Enter new student ID: ");
                        newId = scanner.nextLine().trim();
                        
                        valid = newId.matches("\\d{5}");
                        if (!valid) {
                            System.out.println("Invalid ID. Try again.\n");
                        }
                    } while (!valid);
                    
                    // Valid ID is retrieved
                    String oldId;
                    StudentRecord updatedRecord = bstByName.search(nameInput);
                    if (updatedRecord == null) {
                        System.out.println("Record Not found!\n");
                        break;
                    }
                    oldId = updatedRecord.getStudentID();
                    updatedRecord.setStudentID(newId);
                    
                    bstByName.update(nameInput, updatedRecord);
                    bstByID.update(oldId, updatedRecord);
                    System.out.println("Student ID updated successfully.\n");
                    
                    break;
                case 5:
                    // TODO: Implement display logic:
                    //  - Perform an in-order traversal on bstByName to display all records.
                	System.out.println("Student Records:");
                	bstByName.inOrderTraversal();
                    System.out.println("");
                	// System.out.println("==== Student Records: ");
                	// bstByID.inOrderTraversal();
                    break;
                case 6:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 6);

        scanner.close();
    }
}
