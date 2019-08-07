package tests;

public interface SearchTree<E extends Comparable<E>> {

	//@return true if the item is inserted, false if the item was already in the tree. */
	boolean add(E item);

	//@return A reference to the object in the tree that compares equal as determined by compareTo to the target. If not found null is returned.
	E find(E target);


	boolean contains(E target);


	//@return A reference to the object in the tree that compares equal as determined by compareTo to the target. if not found null is returned.
	E delete(E target);

	//@return true if the object was in the tree, false otherwise
	boolean remove(E target);

}	