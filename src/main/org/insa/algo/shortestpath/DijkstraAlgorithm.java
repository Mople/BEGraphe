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
        Arc[] predec = new Arc[nbNodes];
        Label startLabel = tabLabels[data.getOrigin().getId()];
        tas.insert(startLabel);
        startLabel.setcost(0);
        
        boolean fin = false;
        
        while (!fin ) {
        	Label currentLabel = tas.findMin();
        	currentLabel.marque = true;
        	
        	if (currentLabel.getNode().getId() == data.getDestination().getId()) {
        		fin=true;
        	}
        	else {
        		List<Arc> succ = currentLabel.getNode().getSuccessors();
        		
        		for (Arc arc : succ) {
        			Label succLabel = tabLabels[arc.getDestination().getId()];
        			if (!succLabel.marque) {
        				if (succLabel.getCost() > (currentLabel.getCost() + arc.getLength())) {
        					succLabel.setcost(currentLabel.getCost()+ arc.getLength());
        					tas.remove(succLabel);
        					tas.insert(succLabel);
        					succLabel.setFatherArc(arc);
        					succLabel.setFatherNode(currentLabel.getNode());
        				}
        			}
        		}
        		
        	}
        	
        	
        }
        
        
        
        
        
        
        /*
        tabLabels[data.getOrigin().getId()].cout=0;
        BinaryHeap<Node> tas = new BinaryHeap<Node>();
        tas.insert(data.getOrigin());
        notifyLabel(tabLabels[data.getOrigin().getId()]);
        
        
        //Iteration
        boolean allmarked=false;
        while (!allmarked) {
        	Node x = tas.findMin();
        	
        	tabLabels[x.getId()].marque=true;
        	
        	List<Arc> suivants = x.getSuccessors();
        	
        	for (int i=0;i<suivants.size();i++) {
        		Node y = suivants.get(i).getDestination();
        		if (!tabLabels[y.getId()].marque) {
        			
        			if (tabLabels[y.getId()].getCost() > (tabLabels[x.getId()].getCost() + suivants.get(i).getLength() )) {
        				tabLabels[y.getId()].cout = tabLabels[x.getId()].getCost() + suivants.get(i).getLength();
        				try {
        					tas.remove(y);
        				} catch (ElementNotFoundException e) {}
        				finally {
        					tas.insert(y);
        				}
        				tabLabels[y.getId()].fatherNode=x;
        				tabLabels[y.getId()].fatherArc=suivants.get(i);
        				
        				
        				
        			}
        		}
        	}
        	
        	//Check sommets non marqués
        	allmarked=true;
        	for (int i=0; allmarked&&i<nbNodes;i++) {
        		if (!tabLabels[i].marque) {
        			allmarked=false;
        		}
        	}
        }
        
        ArrayList<Arc> predecessorArcs = new ArrayList<>();
        //Vérification destination atteinte
        if (tabLabels[data.getDestination().getId()].marque) {
        	Arc arcPred = tabLabels[data.getDestination().getId()].fatherArc;
        	while (arcPred != null) {
        		predecessorArcs.add(arcPred);
        		arcPred =tabLabels[arcPred.getOrigin().getId()].fatherArc;
        	}
        	Collections.reverse(predecessorArcs);
        	solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, predecessorArcs));
        }
        else {
        	solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }
       
        
        return solution;*/
    }

}
