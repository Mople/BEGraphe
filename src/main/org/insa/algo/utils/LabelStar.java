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
	
	
	
}