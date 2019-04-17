package org.insa.algo.utils ;

import org.insa.graph.Node;

public class Label {
	
	public Node currentNode;
	public boolean marque;
	public double cout;
	public Node fatherNode;
	
	public Label(Node sommet){
		this.currentNode = sommet;
		this.marque = false;
		this.cout = Double.POSITIVE_INFINITY;
		this.fatherNode = null;
	}
	
	public double getCost() {
		return this.cout;
	}
	
	
	
}