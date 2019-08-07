package tests;
/**
 * This class extends the BinarySearchTree by adding the rotate operations.
 * Rotation will change the balance of a search tree while preserving the
 * search tree property.
 * Used as a common base class for self‐balancing trees.
 */
public class BinarySearchTreeWithRotate<E extends Comparable<E>> extends BinarySearchTree<E> {
// Methods

    /**
     * Method to perform a right rotation.
     *
     * @param root The root of the binary tree to be rotated
     * @return The new root of the rotated tree
     * @pre root is the root of a binary search tree.
     * @post root.right is the root of a binary search tree,
     * root.right.right is raised one level,
     * root.right.left does not change levels,
     * root.left is lowered one level,
     * the new root is returned.
     */
    protected Node<E> rotateRight(Node<E> root) {
        Node<E> temp = root.left;
        root.left = temp.right;
        temp.right = root;
        return temp;
    }


     /* Rotera 1 steg i vanstervarv, dvs (Zag)
              x'                 y'
             / \                / \
            A   y'  -->        x'  C
               / \            / \
              B   C          A   B
    */


    /**
     * Method to perform a left rotation (rotateLeft).
     */
    protected Node<E> rotateLeft(Node<E> x) {
        Node<E> y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    /*
    private void rotateLeft(Entry x) {
        Entry y = x.right;
        E temp = x.element;
        x.element = y.element;
        y.element = temp;
        x.right = y.right;

        if (x.right != null)
            x.right.parent = x;

        y.right = y.left;
        y.left = x.left;

        if (y.left != null)
            y.left.parent = y;

        x.left = y;

    } //   rotateLeft
*/

}
