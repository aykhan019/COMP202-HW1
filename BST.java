public class BST {
    private BSTNode root;
    private String key; // "name" or "studentID" based BST

    public BST(String key) {
        this.key = key;
        root = null;
    }

    // Helper method to get the key value from a StudentRecord as a String.
    private String getRecordKeyValue(StudentRecord record) {
        if (key.equals("name")) {
            return record.getName();
        } else if (key.equals("studentID")) {
            return record.getStudentID();
        }
        // Invalid key
        return "";
    }
    
    // Insert a new student record into the BST
    public void insert(StudentRecord record) {
        root = insertRec(root, record);
    }
   
    // Recursive insertion logic. Use key to compare (either name or studentID)
    private BSTNode insertRec(BSTNode root, StudentRecord record) {
        if (root == null) {
            return new BSTNode(record);
        }
        // TODO: Compare based on the chosen key ("name" or "studentID")
        // Example:
        // if (key.equals("name")) { compare record.getName() with root.record.getName() }
        // else if (key.equals("studentID")) { compare record.getStudentID() with root.record.getStudentID() }
        // Use appropriate comparison to decide whether to go left or right.

        // Compare record to insert with the current node
        int result = getRecordKeyValue(record).compareTo(getRecordKeyValue(root.record));
        
        if (result < 0){ // Insert StudentRecord to the left
        	root.left = insertRec(root.left, record);
        } else if (result > 0){ // Insert StudentRecord to the right
        	root.right = insertRec(root.right, record);
        } else { // Do not insert StudentRecord, element with the given key already exists in BST
        	// System.out.printf("No insertion happened! Student Record with the given key already exists!");
            // return root;
        }
        return root;
    }
    
    public StudentRecord searchByEmail(String email) {
        return searchByEmailRec(root, email);
    }

    // inorder search for email, from left to right.
    private StudentRecord searchByEmailRec(BSTNode current, String email) {
    	if (current == null) { return null; }
    
    	// Search Left Subtree
    	StudentRecord fromLeft = searchByEmailRec(current.left, email);
        // check to not to return null since we have a chance to find in right subtree   
    	if (fromLeft != null) {  	
    		return fromLeft;
    	}
    	
    	// Search current node
    	if (current.record.getEmail().equals(email)) {
    		return current.record;
    	}
    	
    	// Search Right Subtree
    	StudentRecord fromRight = searchByEmailRec(current.right, email); // last hope, return whatever you find in the right subtree
    	return fromRight;
    }
    
    // Search for a student record based on the key
    public StudentRecord search(String value) {
        return searchRec(root, value);
    }

    // Recursive search logic based on the key
    private StudentRecord searchRec(BSTNode root, String value) {
        if (root == null) return null; // the root we are looking for is null, value is not found
        // TODO: Implement search logic based on the key
        // Compare value with the current node's record based on key and traverse accordingly.
        
        // Compare the value to search for with the current node
        int result = value.compareTo(getRecordKeyValue(root.record));

        if (result < 0) { // Search in the left subtree
        	return searchRec(root.left, value);
        } else if (result > 0) { // Search in the right subtree
        	return searchRec(root.right, value);
        } else { // Found the value, return the record
        	return root.record;
        }
    }

    // This helper function returns the minimum key in the subtree
    private BSTNode findMin(BSTNode root) {
    	  while (root.left != null) {
    		  	root = root.left;
    	  }
    	  return root;
    }
    
    // Deletes student record from BST given student name and returns deleted record
    public StudentRecord deleteByStudentName(String name) {
	    StudentRecord record = search(name);
	    if (record == null) {
	        return null;
	    }
	    root = deleteRec(root, name);
	    return record;
    }
    
    // Deletes student record from BST given student ID and returns deleted record
    public StudentRecord deleteByStudentId(String id) {
	    StudentRecord record = search(id);
	    if (record == null) {
	        return null;
	    }
	    root = deleteRec(root, id);
	    return record;
    }
    
    // Delete a student record from the BST based on the key
    public void delete(String value) {
        root = deleteRec(root, value);
    }
    
    // Recursive deletion logic based on the key
    private BSTNode deleteRec(BSTNode root, String value) {
        if (root == null) return null;
        // TODO: Implement deletion logic here
        // Handle the cases for deletion (no child, one child, two children) and maintain BST properties.
        
        // Compare the value of the key to delete with the current node
        int result = value.compareTo(getRecordKeyValue(root.record));
       
        if (result < 0) { // Search in the left subtree and delete
        	root.left = deleteRec(root.left, value);
        }
        else if (result > 0) { // Search in the right subtree and delete
        	root.right = deleteRec(root.right, value);
        } 
        else { // Found the Node, delete the record
        	// 3 Cases for the Node to delete
        	// No Child, Simply delete the node
        	if (root.right == null && root.left == null) { 
        		return null;
        	} 
        	// One child
            else if (root.left == null) { 
                return root.right; // Replace with right subtree
            } else if (root.right == null) {
                return root.left; // Replace with left subtree
            }
        	// Two children
        	else  { 
        		// Find node to replace with
            	BSTNode minNode = findMin(root.right); // Minimum Node in the right subtree
            	root.record = minNode.record; // Replace records
            	root.right = deleteRec(root.right, getRecordKeyValue(minNode.record)); // Delete the minimum node in the right subtree
        	}
        }
        return root;
    }
    
    // This function updates oldStudentRecord properties except key
    private void updateStudentRecord(StudentRecord oldStudentRecord, StudentRecord newStudentRecord) {
    	oldStudentRecord.setEmail(newStudentRecord.getEmail());
    	if (key.equals("name")) {
        	oldStudentRecord.setStudentID(newStudentRecord.getStudentID());
    	} else if (key.equals("studentID")) {
        	oldStudentRecord.setName(newStudentRecord.getName());
    	}
    }
    
    public void update(String value, StudentRecord newRecord) {
        root = updateRec(root, value, newRecord);
    }

    private BSTNode updateRec(BSTNode root, String value, StudentRecord newRecord) {
        if (root == null) return null;
        // TODO: Implement update logic
        // Find the node based on "name" or "studentID".
        // If found, update the record while maintaining BST properties.
        
        // Compare the value of the key to delete with the current node
        int result = value.compareTo(getRecordKeyValue(root.record));
       
        if (result < 0) { // Search in the left subtree and delete
        	root.left = updateRec(root.left, value, newRecord);
        }
        else if (result > 0) { // Search in the right subtree and delete
        	root.right = updateRec(root.right, value, newRecord);
        } 
        else { // Found the Node, update the record
        	// If key is not changing
        	if (getRecordKeyValue(root.record).equals(getRecordKeyValue(newRecord))) {
        		updateStudentRecord(root.record, newRecord); // update Student Record except the key
        	}
        	else { // if key is changing
        		delete(value); // delete old record
        		insert(newRecord); // insert new record
        	}
        }
        	
        return root;
    }
    // Perform in-order traversal and display student records
    public void inOrderTraversal() {
        inOrderRec(root);
    }

    // Recursive in-order traversal
    private void inOrderRec(BSTNode root) {
        if (root != null) {
            inOrderRec(root.left);
            // Display record details in the specified format
            System.out.println("Name: " + root.record.getName() +
                               ", ID: " + root.record.getStudentID() +
                               ", Email: " + root.record.getEmail());
            inOrderRec(root.right);
        }
    }
}
 