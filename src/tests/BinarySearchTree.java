package tests;
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> implements SearchTree<E> {

    // Data Fields
    /**
     * Return value from the public add method.
     */
    protected boolean addReturn;

    /**
     * Return value from the public delete method.
     */
    protected E deleteReturn;

    /**
     * pseudo code E fnd(E target)
     * if the root is null
     * return null, we failed to fnd the target
     * compare the value of target with root.data
     * if they are equal
     * target has been found,
     * do action
     * or return data
     * or return reference to node
     * or whatever you want to do
     * else if target is less than root.data
     * return result of searching the left subtree
     * else
     * return result of searching the rightsubtree
     */


    @Override
    public boolean contains(E target) {
        return false;
    }


    /*
    @pre: The target object must implement the Comparable interface.
    @param target The object being sought
    @return The object, if found, otherwise null*/
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
        } else if (compResult < 0) {
            return find(localRoot.left, target);
        } else {
            return find(localRoot.right, target);
        }
    }

	/*pseudocode add (E item) is
	if the root is null
	insert a new node  (i.e. root = new ...)
	compare the value of item with root.data
	if they are equal
	item has been found, 
	do action (update)
	or exception
	or nothing 
	or whatever...
	else if item is less than root.data
	add to the left subtree
	else
	add to the right subtree*/

    /**
     * Recursive add method.@post: The data feld addReturn is set true ifthe item is added to the tree, false if the item is already in the tree.@param localRoot The local root of the subtree@param item The object to be inserted@return The new local root that now containsthe inserted item
     */
    public boolean add(E item) {
        if (item == null) {
            //To do return false;
        }
        root = add(root, item);
        return addReturn;
    }

    private Node<E> add(Node<E> localRoot, E item) {
        if (localRoot == null) {
            addReturn = true;
            return new Node<>(item);
        }
        int compResult = item.compareTo(localRoot.data);

        if (compResult == 0) {
            addReturn = false;
            return localRoot;
        } else if (compResult < 0) {
            localRoot.left = add(localRoot.left, item);
            return localRoot;
        } else {
            localRoot.right = add(localRoot.right, item);
            return localRoot;
        }
    }


	/*pseudokod delete(BST t, E x)
		if t.empty() then return
		if x < t.element() then
			delete(t.left(), x);
		elsif x > t.element() then
			delete(t.right(), x);
	// we have found the node to take away no children
		elsif empty(t.left()) && empty(t.right()) then
			t = null
		elsif empty(t.left()) then // no left child
			länka förbi åt höger
			t = t.right
		elsif empty(t.right()) then // no right child
			länka förbi åt vänster
			t = t.left
		else // both children exist
			ersätt innehållet i noden med
			största objektet i vänster delträd
			dvs innehållet i t = deletemax(t.left())
			//eller med minsta i höger delträd
		end if
	end delete*/

    /**
     * wrapper method delete@param target The object to be deleted@return The object deleted from the treeor null if the object was not in the tree@throws ClassCastException if target does not implement Comparable
     */
    public E delete(E target) {
        if (target == null) {
        }
        root = delete(root, target);
        return deleteReturn;
    }

    /**
     * Recursive delete method.
     * post: The item is not in the tree;
     * deleteReturn is equal to the deleted item
     * as it was stored in the tree or null
     * if the item was not found.
     *
     * @param localRoot The root of the current subtree
     * @param item      The item to be deleted
     * @return The modified local root that does not contain
     * the item
     */
    private Node<E> delete(Node<E> localRoot, E item) {
        if (localRoot == null) {
            // item is not in the tree.
            deleteReturn = null;
            return localRoot;
        }
        // Search for item to delete.
        int compResult = item.compareTo(localRoot.data);
        if (compResult < 0) {
            // item is smaller than localRoot.data.
            localRoot.left = delete(localRoot.left, item);
            return localRoot;
        } else if (compResult > 0) {
            // item is larger than localRoot.data.
            localRoot.right = delete(localRoot.right, item);
            return localRoot;
        } else {
            // item is at local root.
            deleteReturn = localRoot.data;
            if (localRoot.left == null) {
                // If there is no left child, return right child
                // which can also be null.
                return localRoot.right;
            } else if (localRoot.right == null) {
                // If there is no right child, return left child.
                return localRoot.left;
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
                    return localRoot;
                } else {
                    // Search for the inorder predecessor (ip) and
                    // replace deleted node's data with ip.
                    localRoot.data = findLargestChild(localRoot.left);
                    return localRoot;
                }
            }
        }
    }

    /**
     * Find the node that is the
     * inorder predecessor and replace it
     * with its left child (if any).
     * post: The inorder predecessor is removed from the tree.
     *
     * @param parent The parent of possible inorder
     *               predecessor (ip)
     * @return The data in the ip
     */
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

    @Override
    public boolean remove(E target) {
        return false;
    }

}