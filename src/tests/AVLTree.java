package tests;

public class AVLTree<E extends Comparable<E>> extends BinarySearchTreeWithRotate<E> {

    /**
     * Flag to indicate that height of tree has increased.
     */
    private boolean increase;

    // Data Fields
    private boolean decrease;

    /**
     * Class to represent an AVL Node. It extends the
     * BinaryTree.Node by adding the balance field.
     */
    private static class AVLNode<E> extends BinaryTree.Node<E> {

        public static final int LEFT_HEAVY = -1;
        public static final int BALANCED = 0;
        public static final int RIGHT_HEAVY = 1;

        private int balance;

        public AVLNode(E item) {
            super(item);
            balance = BALANCED;
        }

        @Override
        public String toString() {
            return balance + ": " + super.toString();
        }
    }

    /*pseudocode add(E item)
    if the root is null
        create new tree with item + return true
    else if item == root.data
        item is already in the tree, return false
    else if item < root.data
        insert item recursively in left subtree
        if increase (i.e. the height of left subtree has increased)
            decrement balance (= hr - hl)
        if balance == 0 reset increase to false
        if balance < -1
            reset increase to false
            perform a rebalanceLeft
    else if item > root.data
    symmetric,
    balance is incremented if increase=true*/


    /**
     * add starter method.
     *
     * @param item The item being inserted.
     * @return true if the object is inserted; false
     * if the object already exists in the tree
     * @throws ClassCastException if item is not Comparable
     * @pre the item to insert implements the Comparable interface.
     */
    @Override
    public boolean add(E item) {
        increase = false;
        root = add((AVLNode<E>) root, item);
        return addReturn;
    }
    /**
     * Recursive add method. Inserts the given object into the tree.
     *
     * @param localRoot The local root of the subtree
     * @param item      The object to be inserted
     * @return The new local root of the subtree with the item inserted
     * @post addReturn is set true if the item is inserted,
     * false if the item is already in the tree.
     */
    private AVLNode<E> add(AVLNode<E> localRoot, E item) {
        if (localRoot == null) {
            addReturn = true;
            increase = true;
            return new AVLNode<E>(item);
        }
        int compResult = item.compareTo(localRoot.data);

        if (compResult == 0) {
            increase = false;
            addReturn = false;
            return localRoot;
        } else if (compResult < 0) {
            localRoot.left = add((AVLNode<E>) localRoot.left, item);
            //Upon return from recursion, we examine the global data field increase.
            if (increase) {
                decrementBalance(localRoot);
                if (localRoot.balance < AVLNode.LEFT_HEAVY) {
                    increase = false;
                    return rebalanceLeft(localRoot);
                }
            }
            return localRoot;
            //Rebalance not needed.
        } else {
            localRoot.right = add((AVLNode<E>) localRoot.right, item);
            if (increase) {
                incrementBalance(localRoot);
                if (localRoot.balance > AVLNode.RIGHT_HEAVY) {
                    increase = false;
                    return rebalanceRight(localRoot);
                }
            }
            return localRoot;
        }
    }

    /**
     * Recursive delete
     *
     * @param localRoot The local root of the subtree
     * @param item      The object to be inserted
     * @return The new local root of the subtree with the item inserted
     * @post addReturn is set true if the item is inserted,
     * false if the item is already in the tree.
     */


    /**
     * wrapper method delete@param target The object to be deleted@return The object deleted from the treeor null if the object was not in the tree@throws ClassCastException if target does not implement Comparable
     */
    public E delete(E target) {
        if (target == null) {
        }
        root = delete((AVLNode)root, target);
        return deleteReturn;
    }

    private AVLNode<E> delete(AVLNode<E> localRoot, E item) {
        if (localRoot == null) {
            // item is not in the tree.
            deleteReturn = null;
            return localRoot;
        }
        // Search for item to delete.
        int compResult = item.compareTo(localRoot.data);
        if (compResult < 0) {
            // item is smaller than localRoot.data.
            localRoot.left = delete((AVLNode)localRoot.left, item);
            if (decrease) {
                incrementBalance(localRoot);
                if (localRoot.balance > AVLNode.RIGHT_HEAVY) {
                    decrease = false;
                    return rebalanceRight(localRoot);
                }
            }
            return localRoot;
        } else if (compResult > 0) {
            // item is larger than localRoot.data.
            localRoot.right = delete((AVLNode)localRoot.right, item);
            if (decrease ) {
                decrementBalance(localRoot);
                if (localRoot.balance < AVLNode.LEFT_HEAVY) {
                    decrease = false;
                    return rebalanceLeft(localRoot);
                }
            }
            return localRoot;
        } else {
            // item is at local root.
            deleteReturn = localRoot.data;
            decrease = true;
            if (localRoot.left == null) {
                // If there is no left child, return right child
                // which can also be null.
                return (AVLNode<E>) localRoot.right;
            } else if (localRoot.right == null) {
                // If there is no right child, return left child.
                return (AVLNode<E>) localRoot.left;
            } else {
                // Node being deleted has 2 children, replace the data
                // with inorder predecessor.
                if (localRoot.left.right == null) {
                    // The left child has no right child.
                    // Replace the data with the data in the
                    // left child.
                    localRoot.data = localRoot.left.data;
                    // Replace the left child with its left child.
                    localRoot.left = localRoot.left.left;
                    return (AVLNode<E>)localRoot;
                } else {
                    // Search for the inorder predecessor (ip) and
                    // replace deleted node's data with ip.
                    //TODO Handle the case when a deleted node has two children.
                    localRoot.data = findLargestChild(localRoot.left);

                    decrementBalance((AVLNode<E>)localRoot.left);

                    return (AVLNode<E>)localRoot;
                }
            }
        }
    }

    private E findLargestChild(Node<E> parent) {
// If the right child has no right child, it is
// the inorder predecessor.
        if (parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        } else {
            return findLargestChild(parent.right);
        }
    }

    private void decrementBalance(AVLNode<E> node) {
        // Decrement the balance.
        node.balance--;
        if (node.balance == AVLNode.BALANCED) {
            /** If now balanced, overall height has not increased. */
            increase = false;
        }
    }

    private void incrementBalance(AVLNode<E> node) {
        // Increment the balance.
        node.balance++;
        if (node.balance == AVLNode.BALANCED) {
            /** If now balanced, overall height has not increased. */
            increase = false;
        }
    }

    /**+DEESQQWWSW
     * Method to rebalance left.
     *
     * @param localRoot Root of the AVL subtree that needs rebalancing
     * @return a new localRoot
     * @pre localRoot is the root of an AVL subtree that is critically left‐heavy.
     * @post Balance is restored.
     */
    private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot) {
        // Obtain reference to left child.
        AVLNode<E> leftChild = (AVLNode<E>) localRoot.left;
        // See whether left‐right heavy.
        if (leftChild.balance > AVLNode.BALANCED) {
            // Obtain reference to left‐right child.
            AVLNode<E> leftRightChild = (AVLNode<E>) leftChild.right;
            /** Adjust the balances to be their new values after
             the rotations are performed.
             */
            if (leftRightChild.balance < AVLNode.BALANCED) {
                leftChild.balance = AVLNode.BALANCED;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.RIGHT_HEAVY;
            } else {
                leftChild.balance = AVLNode.LEFT_HEAVY;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            // Perform left rotation.
            localRoot.left = rotateLeft(leftChild);
        } else { //Left-Left case
            /** In this case the leftChild (the new root) and the root
             (new right child) will both be balanced after the rotation.
             */
            leftChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
        // Now rotate the local root right.
        return (AVLNode<E>) rotateRight(localRoot);
    }

    /*if the left subtree has positive balance <<
        // left-right case <<
           if the left-right subtree has negative balance
                // left-right-left case
                // the new left subtree)
                set left subtree balance to 0
                // the new root
                set the left-right subtree balance to 0
                // the new right subtree
                set the local root balance to +1
            else // left-right-right case
                samma som ovan men till -1, 0, 0
            rotate left around left subtree <<
      else // left-left case
            set the left subtree balance to 0
            set the local root balance to 0
      rotate the local root right*/

    /**
     * Method to rebalance right.
     *
     * @param localRoot Root of the AVL subtree that needs rebalancing
     * @return a new localRoot
     * @pre localRoot is the root of an AVL subtree that is critically right‐heavy.
     * @post Balance is restored.
     */
    private AVLNode<E> rebalanceRight(AVLNode<E> localRoot) {
        // Obtain reference to right child.
        AVLNode<E> rightChild = (AVLNode<E>) localRoot.right;
        // See whether right-left heavy.
        if (rightChild.balance < AVLNode.BALANCED) {
            // Obtain reference to right-left child.
            AVLNode<E> rightLeftChild = (AVLNode<E>) rightChild.right;
            /** Adjust the balances to be their new values after
             the rotations are performed.
             */
            if (rightLeftChild.balance > AVLNode.BALANCED) {
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.LEFT_HEAVY;
            } else {
                rightChild.balance = AVLNode.RIGHT_HEAVY;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            // Perform right rotation.
            localRoot.left = rotateRight(rightChild);
        } else { //Right-Right case
            /** In this case the rightChild (the new root) and the root
             (new left child) will both be balanced after the rotation.
             */
            rightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
        // Now rotate the local root left.
        return (AVLNode<E>) rotateLeft(localRoot);
    }

}
