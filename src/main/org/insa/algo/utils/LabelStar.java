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
	
	
	public int compareTo(LabelStar autre) {
		int resultat;
		double cost = this.getCost() + this.getEstimation();
		double autreCost = autre.getCost() + autre.getEstimation();
		if (cost<autreCost) {
			resultat = -1;
		}
		else if (cost == autreCost) {
			if( this.getEstimation()<autre.getEstimation()) {
				resultat = -1;
			}
			else if (this.getEstimation() == autre.getEstimation()) {
				resultat = 0;
			}
			else {
				resultat = 1;
			}
		}
		else {
			resultat = 1;
		}
		return resultat;
		
		
	}
}