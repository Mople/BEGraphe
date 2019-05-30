package org.insa.algo;

import org.insa.algo.shortestpath.AStarAlgorithm;
import org.insa.algo.shortestpath.DijkstraAlgorithm;
import org.insa.algo.shortestpath.ShortestPathData;
import org.insa.algo.shortestpath.ShortestPathSolution;

public class AStarTest extends DjikstraTest{
	
	public ShortestPathSolution getAlgoSolution(ShortestPathData data) {
    	DijkstraAlgorithm AStarAlgo = new AStarAlgorithm(data);
    	ShortestPathSolution AStarSolution = AStarAlgo.run();
    	return AStarSolution;
    }
}