/**
 * Class to implement a binary search tree data structure using linked nodes
 */
public class TreeSet<E extends Comparable<E>>{
    // data members
    private TreeNode root;
    private int size;
    // Inner class for the tree nodes
    private class TreeNode{
        E value;
        TreeNode left, right;
        TreeNode(E val){
            value = val;
            left = right = null;
        }
    }
    /**
     * Default constructor
     * Create an empty binary search tree
     * Time complexity: O(1)
     */
    public TreeSet(){
        root = null;
        size = 0;
    }
    /**
     * Getter of the size of the tree
     * @return the number of nodes in the tree
     * Time complexity: O(1)
     */
    public int size(){
        return size;
    }
    /**
     * @return true if the tree is empty, false otherwise
     * Time complexity: O(1)
     */
    public boolean isEmpty(){
        return (size == 0);
    }
    /**
     * clears the tree
     * Time complexity: O(1)
     */
    public void clear(){
        root = null;
        size = 0;
    }
    /**
     * Search method
     * @param element the value being looked up
     * @return true if element is found in the tree, false otherwise
     * Time complexity: O(logn) --> O(n)
     */
    public boolean contains(E element){
        if (root == null){
            return false;
        }
        TreeNode current = root;
        while(current != null){
            if(element.compareTo(current.value) == 0){
                return true;
            }
            else if(element.compareTo(current.value) < 0){
                current = current.left;
            }
            else{
                current = current.right;
            }
        }
        return false;
    }
    /**
     * Ading a new element to the tree
     * @param element the value to add in the tree
     * @return true if element was added succesfully, false if element already exists in the tree
     * Time complexity: O(logn) --> O(n)
     */
    public boolean add(E element){
        TreeNode newNode = new TreeNode(element);
        if (root == null){
            root = newNode;
        }
        else{
            TreeNode current = root, parent = null;
            while(current != null){
                parent = current;
                if(element.compareTo(current.value) == 0){
                    return false;
                }
                else if(element.compareTo(current.value) < 0){
                    current = current.left;
                }
                else{
                    current = current.right;
                }
            }
            if(element.compareTo(parent.value) < 0){
                parent.left = newNode;
            }
            else{
                parent.right = newNode;
            }
        }
        size++;
        return true;
    }
    /**
     * Removing an element from the tree
     * @param element the value to be removed from the tree
     * @return true if element was found and removed, false if element was not found
     * Time complexity: O(logn) --> O(n)
     */
    public boolean remove(E element){
        if (root == null){
            return false;
        }
        TreeNode nodeToDelete = root, parent = null;
        while(nodeToDelete != null){
            if(element.compareTo(nodeToDelete.value) == 0){
                break;
            }
            parent = nodeToDelete;
            if(element.compareTo(nodeToDelete.value) < 0){
                nodeToDelete = nodeToDelete.left;
            }
            else{
                nodeToDelete = nodeToDelete.right;
            }
        }
        if (nodeToDelete == null){
            return false;
        }
        if(nodeToDelete.left == null && nodeToDelete.right == null){
            if(parent == null){
                root = null; size = 0;
            }
            else{
                if(parent.left == nodeToDelete)
                    parent.left = null;
                else
                    parent.right = null;
            }
        }
        else if(nodeToDelete.left == null){
            if(parent == null)
                root = nodeToDelete.right;
            else if (parent.left == nodeToDelete)
                parent.left = nodeToDelete.right;
            else
                parent.right = nodeToDelete.right;
        }
        else if(nodeToDelete.right == null){
            if(parent == null)
                root = nodeToDelete.left;
            else if(parent.left == nodeToDelete)
                parent.left = nodeToDelete.left;
            else
                parent.right = nodeToDelete.left;
        }
        else{
            TreeNode rightMostCurrent = nodeToDelete.left;
            TreeNode rightMostParent = nodeToDelete;
            while(rightMostCurrent.right != null){
                rightMostParent = rightMostCurrent;
                rightMostCurrent = rightMostCurrent.right;
            }
            nodeToDelete.value = rightMostCurrent.value;
            if(rightMostParent.left == rightMostCurrent)
                rightMostParent.left = rightMostCurrent.left;
            else
                rightMostParent.right = rightMostCurrent.left;
        }
        size--;
        return true;
    }
    /**
     * Preorder Traversal method
     * Time complexity: O(n)
     */
    public void preorder(){
        System.out.print("[");
        preorder(root);
        System.out.println("]");
    }
    /**
     * Preorder Traversal recursive helper method
     * @param node the node where the traversal starts
     * Time complexity: O(n)
     */
    private void preorder(TreeNode node){
        if(node != null){
            System.out.print(node.value + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }
    /**
     * Inorder Traversal method
     * Time complexity: O(n)
     */
    public void inorder(){
        System.out.print("[");
        inorder(root);
        System.out.println("]");
    }
    /**
     * Inorder Traversal recursive helper method
     * @param node the node where the traversal starts
     * Time complexity: O(n)
     */
    private void inorder(TreeNode node){
        if(node != null){
            inorder(node.left);
            System.out.print(node.value + " ");
            inorder(node.right);
        }
    }
    /**
     * Postorder Traversal method
     * Time complexity: O(n)
     */
    public void postorder(){
        System.out.print("[");
        postorder(root);
        System.out.println("]");
    }
    /**
     * Postorder Traversal recursive helper method
     * @param node the node where the traversal starts
     * Time complexity: O(n)
     */
    private void postorder(TreeNode node){
        if(node != null){
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.value + " ");
        }
    }
}
