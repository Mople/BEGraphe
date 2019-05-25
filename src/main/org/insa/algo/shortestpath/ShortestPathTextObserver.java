package org.insa.algo.shortestpath;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.insa.algo.utils.BinaryHeap;
import org.insa.algo.utils.Label;
import org.insa.graph.Arc;
import org.insa.graph.Node;

public class ShortestPathTextObserver implements ShortestPathObserver {

    private final PrintStream stream;

    public ShortestPathTextObserver(PrintStream stream) {
        this.stream = stream;
    }

    @Override
    public void notifyOriginProcessed(Node node) {
    	stream.println("Origin node " + node.getId() + " processed.");
    }

    @Override
    public void notifyNodeReached(Node node) {
        stream.println("Node " + node.getId() + " reached.");
    }

    @Override
    public void notifyNodeMarked(Node node) {
        stream.println("Node " + node.getId() + " marked.");
    }

    @Override
    public void notifyDestinationReached(Node node) {
    	stream.println("Destination node " + node.getId() + " reached.");
    }
    
    @Override
    public void notifyLabel(Label label) {
    	stream.println("Label"+label.toString());
    }
    
    @Override
    public void notifySizeHeap(BinaryHeap<Label> heap) {
    	stream.println("Heap size: "+heap.size());
    }
    
    @Override
    public void notifyEnd(ArrayList<Arc> arcsAlgo, int nbIter) {
    	stream.println("Number of arc: "+arcsAlgo.size()+", number of iterations: "+nbIter);
    }
    
    @Override
    public void notifySuccessors(List<Arc> successors) {
    	stream.println("Number of successors: "+successors.size());
    }

}
