package org.insa.algo.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.insa.graph.*;
import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.utils.*;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        Graph graph = data.getGraph();
        
        
        //Initialisation
        final int nbNodes = graph.size();
        List<Node> list_node=graph.getNodes();
        Label[] tabLabels = new Label[nbNodes];
        int nbIter = 0;
       
        for (int i=0; i<nbNodes ;i++) {
        	tabLabels[i] = newLabel(list_node.get(i),data);
        }
        
        BinaryHeap<Label> tas = new BinaryHeap<Label>();
        
        Label startLabel = tabLabels[data.getOrigin().getId()];
        tas.insert(startLabel);
        startLabel.setcost(0);
        startLabel.atteint=true;
        
        boolean fin = false;
        boolean pathNul = false;
        
        while (!fin && !tas.isEmpty()) {
        	nbIter ++; 
        	Label currentLabel = tas.findMin();
        	if (currentLabel == startLabel) {
        		notifyOriginProcessed(currentLabel.getNode());
        	}
        	
        	if (currentLabel.getNode().getId() == data.getDestination().getId()) {
        		fin=true;
        		notifyDestinationReached(data.getDestination());
        		if (startLabel==currentLabel) {
        			pathNul=true;
        		}
        	}
        	else {
        		currentLabel.marque = true;
        		tas.remove(currentLabel);
        		notifyNodeMarked(currentLabel.getNode());
        		notifyLabel(currentLabel); 
        		notifySizeHeap(tas);
        		List<Arc> succ = currentLabel.getNode().getSuccessors();
        		notifySuccessors(succ); 
        		
        		for (Arc arc : succ) {
        			if (data.isAllowed(arc)) {
        				Label succLabel = tabLabels[arc.getDestination().getId()];
        				if (!succLabel.atteint) {
        					notifyNodeReached(succLabel.getNode());
        					succLabel.atteint = true;
        				}
        			
	        			if (!succLabel.marque) {
	        				if (succLabel.getCost() > (currentLabel.getCost() + data.getCost(arc))) {
	        					succLabel.setcost(currentLabel.getCost()+ data.getCost(arc));
	        					try {
	            					tas.remove(succLabel);
	            				} catch (ElementNotFoundException e) {}
	            				finally {
	            					tas.insert(succLabel);
	            					notifySizeHeap(tas); 
	            				}
	        					succLabel.setFatherArc(arc);
	        					succLabel.setFatherNode(currentLabel.getNode());
	        				}
	        			}
        			}
        		}
        		//tas.printSorted();
        		
        	}
        	
        	
        }
        if (tas.isEmpty()) {
        	solution = new ShortestPathSolution(data, Status.INFEASIBLE);        	
        }
        
        if (fin) {
        	if (pathNul) {
        		solution =new ShortestPathSolution(data, Status.INFEASIBLE);
        	}
        	else {
	        	
	        	ArrayList<Arc>predec = new ArrayList<Arc>();
	        	Arc arcPred = tabLabels[data.getDestination().getId()].getFatherArc();
	        	while (arcPred != null) {
	        		predec.add(arcPred);
	        		arcPred = tabLabels[arcPred.getOrigin().getId()].getFatherArc();
	        	}
	        	Collections.reverse(predec);
	    		notifyEnd(predec, nbIter);
	        	solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph,predec));
        	}
        	
        }
        return solution;
        
        
        
    }
    
    protected Label newLabel(Node node, ShortestPathData data) {
    	return new Label(node);    	
    }

}
