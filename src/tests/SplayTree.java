package tests;

public class SplayTree<E extends Comparable<E>> extends BinarySearchTree<E> {

    /** Method to perform a right rotation.
     @pre root is the root of a binary search tree.
     @post root.right is the root of a binary search tree,
     root.right.right is raised one level,
     root.right.left does not change levels,
     root.left is lowered one level,
     the new root is returned.
     @param localRoot The root of the binary tree to be rotated
     @return The new root of the rotated tree
     */
    protected Node<E> zig(Node<E> localRoot) {
        Node<E> temp = localRoot.left;
        localRoot.left = temp.right;
        temp.right = localRoot;
        return temp;
    }

    /** Method to perform a left rotation (rotateLeft).*/
    protected Node<E> zag(Node<E> localRoot) {
        Node<E> temp = localRoot.right;
        localRoot.right = temp.left;
        temp.left = localRoot;
        return temp;
    }

    /**
     * left of right child. zig around the right child then zag about the local root
     * @param localRoot
     * @return
     */
    protected Node<E> zigzag(Node<E> localRoot) {
        zig(localRoot.right);
        return zag(localRoot);
    }

    /**
     * right of left child
     * @param localRoot
     * @return
     */
    protected Node<E> zagzig(Node<E> localRoot) {
        zag(localRoot.left);
        return zig(localRoot);
    }

    protected Node<E> zigzig(Node<E> localRoot) {
        Node<E> zigged = zig(localRoot.right);
        return zig(localRoot);
    }

    protected Node<E> zagzag(Node<E> localRoot) {
        Node<E> zagged = zag(localRoot.left);
        return zag(localRoot);
    }

    @Override
    public E find(E target) {
        if (target == null) {
            return null;
        } else {
            return find(root, target);
        }
    }

    private E find(Node<E> localRoot, E target) {
        if (localRoot == null) {
            return null;
        }
        int compResult = target.compareTo(localRoot.data);

        if (compResult == 0) {
            return localRoot.data;
        } else if(compResult < 0) {
            E returned = find(localRoot.left, target);
            if (returned != null) {
                zig(localRoot);
            }
            return returned;
        } else {E returned = find(localRoot.right, target);
            if (returned != null) {
                zag(localRoot);
            }
            return returned;
        }
    }


}
