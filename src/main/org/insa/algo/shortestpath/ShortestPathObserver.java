package org.insa.algo.shortestpath;

import org.insa.algo.utils.Label;
import org.insa.graph.Arc;
import org.insa.graph.Node;

import java.util.ArrayList;
import java.util.List;

import org.insa.algo.utils.BinaryHeap;
//import org.insa.algo.utils.*;

public interface ShortestPathObserver {
	
	/**
	 * Notify the observer that the origin has been processed.
	 * 
	 * @param node Origin.
	 */
	public void notifyOriginProcessed(Node node);
	
	/**
	 * Notify the observer that a node has been reached for the first
	 * time.
	 * 
	 * @param node Node that has been reached.
	 */
	public void notifyNodeReached(Node node);
	
	/**
	 * Notify the observer that a node has been marked, i.e. its final
	 * value has been set.
	 * 
	 * @param node Node that has been marked.
	 */
	public void notifyNodeMarked(Node node);

	/**
	 * Notify the observer that the destination has been reached.
	 * 
	 * @param node Destination.
	 */
	public void notifyDestinationReached(Node node);
	
	/** Notify the observer about actual node's label
	 * 
	 * @param label to print
	 */
	public void notifyLabel(Label label);
	
	/** Notify the observer about the BinaryHeap size
	 * 
	 * @param BinaryHeap the heap of labels to check the size
	 */
	public void notifySizeHeap(BinaryHeap<Label> heap);
	
	/** Notify the observer about the final number of iterations
	 * and the number of arcs of the path
	 * 
	 * @param int number of iterations
     * @param ArrayList<Arc> arcs of the algorithm
	 */
	public void notifyEnd(ArrayList<Arc> arcsAlgo, int nbIter);
	
	/** Notify the observer about the number of successors
	 * tested on each iteration 
	 *
	 * @param List<Arc> list of tested successors
	 */
	public void notifySuccessors(List<Arc> successors);
	
}
