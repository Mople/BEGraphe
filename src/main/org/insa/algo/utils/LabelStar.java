package org.insa.algo.utils ;

import org.insa.graph.*;
import org.insa.algo.shortestpath.*;
import org.insa.algo.AbstractInputData;

public class LabelStar extends Label implements Comparable<Label> {
	
	public double estimation;
	
	public LabelStar(Node sommet, ShortestPathData data){
		super(sommet);
		if (data.getMode() == AbstractInputData.Mode.LENGTH) {
			this.estimation = Point.distance(sommet.getPoint(),data.getDestination().getPoint());
		}
		else {
			int vitesse = Math.max(data.getMaximumSpeed(), data.getGraph().getGraphInformation().getMaximumSpeed());
			this.estimation = Point.distance(sommet.getPoint(),data.getDestination().getPoint())/(vitesse*1000.0f/3600.0f);
		}
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