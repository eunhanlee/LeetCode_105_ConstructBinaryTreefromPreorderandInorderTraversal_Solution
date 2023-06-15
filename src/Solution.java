import java.util.HashMap;

public class Solution {
    // Declare a HashMap to quickly find the index of elements in the inorder array
    private HashMap<Integer, Integer> inorderIndexMap;

    /**
     * Constructs a binary tree using the given preorder traversal array and inorder traversal array.
     *
     * @param preorder Preorder traversal array
     * @param inorder  Inorder traversal array
     * @return Root node of the constructed binary tree
     * @throws IllegalArgumentException If the given input is invalid (no root value found in the inorder array)
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Removing this check because of the problem guaranteed
//        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0)
//            return null;


        // Create a HashMap to map each value of the inorder array to its index
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        // Start building the binary tree recursively
        return buildTreeRecur(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    /**
     * A helper method to recursively build the binary tree.
     *
     * @param preorder  Preorder traversal array
     * @param inorder   Inorder traversal array
     * @param preStart  Starting index of the current subtree in the preorder array
     * @param preEnd    Ending index of the current subtree in the preorder array
     * @param inStart   Starting index of the current subtree in the inorder array
     * @param inEnd     Ending index of the current subtree in the inorder array
     * @return Root node of the constructed subtree
     * @throws IllegalArgumentException If the given input is invalid (no root value found in the inorder array)
     */
    private TreeNode buildTreeRecur(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        // Base case: Return an empty subtree if the indices go out of bounds
        if (inStart > inEnd || preStart > preEnd)
            return null;

        // Select the root value of the current subtree
        int currentRootValue = preorder[preStart];
        // Create a new node for the root value
        TreeNode resultRoot = new TreeNode(currentRootValue);

        // Find the index of the root value in the inorder array
        Integer rootIndexInorder = inorderIndexMap.get(currentRootValue);
        // Removing this check because of the problem guaranteed
//        if (rootIndexInorder == null)
//            throw new IllegalArgumentException("Invalid input: root value not found in inorder array.");

        // Build the left subtree
        resultRoot.left = buildTreeRecur(preorder, inorder, preStart + 1, preEnd, inStart, rootIndexInorder - 1);

        // Build the right subtree
        resultRoot.right = buildTreeRecur(preorder, inorder, preStart + (rootIndexInorder - inStart) + 1, preEnd, rootIndexInorder + 1, inEnd);

        // Return the root node of the constructed subtree
        return resultRoot;
    }
}
