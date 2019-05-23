package org.insa.algo.utils ;

import org.insa.graph.*;
import org.insa.algo.shortestpath.*;

public class LabelStar extends Label implements Comparable<Label> {
	
	public double estimation;
	
	public LabelStar(Node sommet, ShortestPathData data){
		super(sommet);
		this.estimation = Point.distance(sommet.getPoint(), data.getDestination().getPoint()) ;
	}
	
	public double getEstimation() {
		return this.estimation;
	}
	
	public double getTotalCost() {
		return this.cout+this.estimation;
	}
	
	
	public int compareTo(LabelStar autre) {
		int resultat;
		if (this.getTotalCost() < autre.getTotalCost()) {
			resultat = -1;
		}
		else if (this.getTotalCost() == autre.getTotalCost()) {
			if(this.estimation < autre.estimation) {
				resultat = -1;
			}
			else if (this.estimation == autre.estimation) {
				resultat =0;
			}
			else {
				resultat=1;
			}
		}
		else {
			resultat = 1;
		}
		return resultat;
	}
}