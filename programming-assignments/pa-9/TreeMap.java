import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Stack;

/**
 * Class to implement a binary search tree data structure using linked nodes
 */
public class TreeMap<K extends Comparable<K>, V> {
    // data members
    private TreeNode root;
    private int size;
    private Comparator<K> compare;

    // Inner class for the tree nodes
    private class TreeNode {
        MapEntry<K, V> value;
        TreeNode left, right;

        TreeNode(K key, V value) {
            this.value = new MapEntry<>(key, value);
            left = right = null;
        }
    }

    /**
     * Default constructor
     * Create an empty binary search tree
     * Time complexity: O(1)
     */
    public TreeMap() {
        this.root = null;
        size = 0;
        this.compare = null;
    }

    /**
     * constructor to create a tree map with a custom comparator
     * time complexity: o(1)
     */
    public TreeMap(Comparator<K> compare) {     
        root = null;
        size = 0;
        this.compare = compare;
    }

    /**
     * Getter of the size of the tree
     * 
     * @return the number of nodes in the tree
     *         Time complexity: O(1)
     */
    public int size() {
        return size;
    }

    /**
     * @return true if the tree is empty, false otherwise
     *         Time complexity: O(1)
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * clears the tree
     * Time complexity: O(1)
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Search method
     * @param element the value being looked up
     * @return true if element is found in the tree, false otherwise
     * Time complexity: O(logn) --> O(n)
     */
    public boolean contains(K k) {
        TreeNode current = root;
        while (current != null) {
            int comp;
            if (compare != null) {
                comp = compare.compare(k, current.value.getKey());
            } else {
                comp = k.compareTo(current.value.getKey());
            }
            if (comp == 0) {
                return true;
            } else if (comp < 0) {
                current = current.left;
            } else {
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
    public boolean add(K k, V v) {                                                  ///fix this!!!!!
        TreeNode newNode = new TreeNode(k, v);
        if (root == null) {
            root = newNode;
        } else {
            TreeNode current = root, parent = null;
            while (current != null) {
                parent = current;
                int comp;
                if (compare != null) {
                    comp = compare.compare(k, current.value.getKey());
                } else {
                    comp = k.compareTo(current.value.getKey());
                }
                if (comp == 0) { // duplicate
                    return false;
                } else if (comp < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
            int comp;
            if (compare != null) {
                comp = compare.compare(k, parent.value.getKey());
            } else {
                comp = k.compareTo(parent.value.getKey());
            }
            if (comp < 0) {
                parent.left = newNode;
            } else {
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
    public boolean remove(K k) {
        if (root == null) {
            return false; 
        }
        TreeNode current = root;
        TreeNode parent = null;
        while (current != null) {
            int comparison;
            if (compare != null) {
                comparison = compare.compare(k, current.value.getKey());
            } else {
                comparison = k.compareTo(current.value.getKey());
            }
            if (comparison == 0) {
                break; // found node to remove
            }
            parent = current;
            if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        if (current == null) {
            return false; 
        }

        // if node has no children
        if (current.left == null && current.right == null) {
            if (parent == null) {
                root = null; 
            } else if (parent.left == current) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        // if node has one child at right
        else if (current.left == null) {
            if (parent == null) {
                root = current.right;
            } else if (parent.left == current) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        }
        // if node has one child at left
        else if (current.right == null) {
            if (parent == null) {
                root = current.left;
            } else if (parent.left == current) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        }
        // if node has 2 children
        else {
            TreeNode predecessorParent = current;
            TreeNode predecessor = current.left;
            while (predecessor.right != null) {
                predecessorParent = predecessor;
                predecessor = predecessor.right;
            }
            current.value = predecessor.value;
            if (predecessorParent.left == predecessor) {
                predecessorParent.left = predecessor.left;
            } else {
                predecessorParent.right = predecessor.left;
            }
        }
        size--;
        return true;
    }

    /**
     * Preorder Traversal method
     * Time complexity: O(n)
     */
    public void preorder() {
        System.out.print("[");
        preorder(root);
        System.out.println("]");
    }

    /**
     * Preorder Traversal recursive helper method
     * @param node the node where the traversal starts
     * Time complexity: O(n)
     */
    private void preorder(TreeNode node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    /**
     * Inorder Traversal method
     * Time complexity: O(n)
     */
    public void inorder() {
        System.out.print("[");
        inorder(root);
        System.out.println("]");
    }

    /**
     * Inorder Traversal recursive helper method
     * @param node the node where the traversal starts
     * Time complexity: O(n)
     */
    private void inorder(TreeNode node) {
        if (node != null) {
            inorder(node.left); 
            System.out.print(node.value + " "); 
            inorder(node.right); 
        }
    }

    /**
     * Postorder Traversal method
     * Time complexity: O(n)
     */
    public void postorder() {
        System.out.print("[");
        postorder(root);
        System.out.println("]");
    }

    /**
     * Postorder Traversal recursive helper method
     * @param node the node where the traversal starts
     * Time complexity: O(n)
     */
    private void postorder(TreeNode node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.value + " ");
        }
    }

    /**
     * returns the smallest key-value pair greater than or equal to the given key or null if no such key exists
     * time complexity: o(n) where h is the height of the tree
     */
    public MapEntry<K, V> ceiling(K k) {
        if (root == null) {
            return null;
        }
        TreeNode current = root;
        TreeNode ceilNode = null;
        while (current != null) {
            int comp;
            if (compare != null) {
                comp = compare.compare(k, current.value.getKey());
            } else {
                comp = k.compareTo(current.value.getKey());
            }
            if (comp == 0) {
                return current.value;
            } else if (comp < 0) { 
                ceilNode = current;
                current = current.left;
            } else { 
                current = current.right;
            }
        }
        if (ceilNode == null) {
            return null;
        } else {
            return ceilNode.value;
        }
    }

    /**
     * returns the largest key-value pair less than or equal to the given key or null if no such key exists
     * time complexity: o(n) where h is the height of the tree
     */
    public MapEntry<K, V> floor(K k) {
        if (root == null) {
            return null;
        }
        TreeNode current = root;
        TreeNode fNode = null;
        while (current != null) {
            int comp;
            if (compare != null) {
                comp = compare.compare(k, current.value.getKey());
            } else {
                comp = k.compareTo(current.value.getKey());
            }
            if (comp == 0) {
                return current.value;
            } else if (comp > 0) { 
                fNode = current;
                current = current.right;
            } else { 
                current = current.left;
            }
        }
        if (fNode == null) {
            return null;
        } else {
            return fNode.value;
        }
    }

    /**
     * returns the smallest key-value pair in the tree or null if the tree is empty
     * time complexity: o(n) where n is the height of the tree
     */
    public MapEntry<K, V> first() {
        if (root == null) {
            return null;
        }
        TreeNode current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    /**
     * returns the largest key-value pair in the tree or null if the tree is empty
     * time complexity: o(n) where n is the height of the tree
     */
    public MapEntry<K, V> last() {
        if (root == null) {
            return null;
        }
        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

    /**
     * returns a collection of values in the tree 
     * time complexity: o(n) 
     */
    public Collection<V> values() {
        ArrayList<V> values = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {   
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            values.add(current.value.getValue());
            current = current.right;
        }

        return values;
    }

    /**
     * returns a collection of keys in the tree
     * time complexity: o(n)
    */
    public Collection<K> keys() {
        ArrayList<K> keys = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            keys.add(current.value.getKey());
            current = current.right;
        }
        return keys;
    }

}
