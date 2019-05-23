package org.insa.algo.utils ;

import org.insa.graph.*;

public class Label implements Comparable<Label>{
	
	public Node currentNode;
	public boolean marque;
	public double cout;
	public Node fatherNode;
	public Arc fatherArc;
	public boolean inTas;
	public boolean atteint;
	
	public Label(Node sommet){
		this.currentNode = sommet;
		this.marque = false;
		this.cout = Double.POSITIVE_INFINITY;
		this.fatherNode = null;
		this.fatherArc = null;
		this.inTas = false;
		this.atteint = false;
	}
	
	public double getCost() {
		return this.cout;
	}
	
	public double getTotalCost(){
		return this.cout;
	}
	
	public void setcost(double c) {
		this.cout=c;
	}
	
	public Node getNode() {
		return this.currentNode;
	}
	
	public Arc getFatherArc() {
		return this.fatherArc;
	}
	
	public void setFatherNode(Node n) {
		this.fatherNode=n;
	}
	
	public void setFatherArc(Arc a) {
		this.fatherArc = a;
	}
	
		
	public String toString() {
		return "Id : " + this.currentNode.getId() + " , Marquage : " + this.marque + " , Cout : " + this.cout;
	}
	
	public int compareTo(Label autre) {
		int resultat;
		if (this.getTotalCost() < autre.getTotalCost()) {
			resultat = -1;
		}
		else if (this.getTotalCost() == autre.getTotalCost()) {
			resultat = 0;
		}
		else {
			resultat = 1;
		}
		return resultat;
	}
	
	
	
	
	
}