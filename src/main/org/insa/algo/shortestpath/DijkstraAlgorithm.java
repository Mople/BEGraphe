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
        
        
        for (int i=0; i<nbNodes ;i++) {
        	tabLabels[i] = new Label(list_node.get(i));
        }
        
       
        BinaryHeap<Label> tas = new BinaryHeap<Label>();
        
        Label startLabel = tabLabels[data.getOrigin().getId()];
        tas.insert(startLabel);
        startLabel.setcost(0);
        
        boolean fin = false;
        
        while (!fin && !tas.isEmpty()) {
        	Label currentLabel = tas.findMin();
        	if (currentLabel == startLabel) {
        		notifyOriginProcessed(currentLabel.getNode());
        	}
        	
        	
        	
        	if (currentLabel.getNode().getId() == data.getDestination().getId()) {
        		fin=true;
        		notifyDestinationReached(data.getDestination());
        	}
        	else {
        		currentLabel.marque = true;
        		tas.remove(currentLabel);
        		notifyNodeMarked(currentLabel.getNode());
        		List<Arc> succ = currentLabel.getNode().getSuccessors();
        		
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
	            				}
	        					succLabel.setFatherArc(arc);
	        					succLabel.setFatherNode(currentLabel.getNode());
	        				}
	        			}
        			}
        		}
        		tas.printSorted();
        		
        	}
        	
        	
        }
        if (tas.isEmpty()) {
        	solution = new ShortestPathSolution(data, Status.INFEASIBLE);        	
        }
        
        if (fin) {
        	ArrayList<Arc>predec = new ArrayList<Arc>();
        	Arc arcPred = tabLabels[data.getDestination().getId()].getFatherArc();
        	while (arcPred != null) {
        		predec.add(arcPred);
        		arcPred = tabLabels[arcPred.getOrigin().getId()].getFatherArc();
        	}
        	Collections.reverse(predec);
        	solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph,predec));
        }
        return solution;
        
        
        
    }

}
