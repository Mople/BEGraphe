package org.insa.algo.utils ;

import org.insa.graph.*;

public class Label implements Comparable<Label>{
	
	public Node currentNode;
	public boolean marque;
	public double cout;
	public Node fatherNode;
	public Arc fatherArc;
	public boolean inTas;
	
	public Label(Node sommet){
		this.currentNode = sommet;
		this.marque = false;
		this.cout = Double.POSITIVE_INFINITY;
		this.fatherNode = null;
		this.fatherArc = null;
		this.inTas = false;
	}
	
	public double getCost() {
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
		return "id : " + this.currentNode.getId() + " , marquage : " + this.marque + " , cout : " + this.cout;
	}
	
	public int compareTo(Label autre) {
		int resultat;
		if (this.getCost() < autre.getCost()) {
			resultat = -1;
		}
		else if (this.getCost() == autre.getCost()) {
			resultat = 0;
		}
		else {
			resultat = 1;
		}
		return resultat;
	}
	
	
	
	
	
}