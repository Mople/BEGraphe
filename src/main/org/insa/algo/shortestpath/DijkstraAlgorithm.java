package org.insa.algo.shortestpath;

import java.util.ArrayList;
import java.util.List;
import org.insa.graph.*;
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
        tabLabels[data.getOrigin().getId()].cout=0;
        BinaryHeap<Node> Tas = new BinaryHeap<Node>();
        Tas.insert(data.getOrigin());
        
        //Iteration
        boolean allmarked=false;
        while (!allmarked) {
        	Node x = Tas.findMin();
        	tabLabels[x.getId()].marque=true;
        	
        	List<Arc> Suivants = x.getSuccessors();
        	
        	for (int i=0;i<Suivants.size();i++) {
        		
        	}
        	
        	
        	
        	
        	//Check sommets non marqués
        	allmarked=true;
        	for (int i=0; allmarked&&i<nbNodes;i++) {
        		if (!tabLabels[i].marque) {
        			allmarked=false;
        		}
        	}
        }
        
        
        
        
        
        
        
        
        
        return solution;
    }

}
