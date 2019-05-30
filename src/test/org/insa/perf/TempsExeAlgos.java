package org.insa.perf;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.algo.ArcInspector;
import org.insa.algo.ArcInspectorFactory;
import org.insa.algo.shortestpath.AStarAlgorithm;
import org.insa.algo.shortestpath.DijkstraAlgorithm;
import org.insa.algo.shortestpath.ShortestPathData;
import org.insa.graph.Graph;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;

public class TempsExeAlgos {
	private int origine;
	private int destination;
	private float timeDijkstra;
	private float timeAStar;
	private int nbSommetsVisitesD;
	private int nbSommetsVisitesA;
	private int nbSommetsMarquesD;
	private int nbSommetsMarquesA;
	

	public TempsExeAlgos(String mapName, int typeEvaluation, int origine, int destination) throws Exception {

		this.origine = origine;
		this.destination = destination;
		GraphReader reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream("D:\\INSA\\3A\\"+mapName+".mapgr"))));
		Graph graph = reader.read();
		ArcInspector arcInspector;
		if (typeEvaluation == 0) {
			arcInspector = ArcInspectorFactory.getAllFilters().get(0);
		}
		else {
			arcInspector = ArcInspectorFactory.getAllFilters().get(2);
		}
		ShortestPathData data = new ShortestPathData(graph, graph.get(origine),graph.get(destination), arcInspector);

		
		long startTime;
		long endTime;

		DijkstraAlgorithm DijkstraAlgo = new DijkstraAlgorithm(data);
		startTime = System.nanoTime();
		DijkstraAlgo.run();
		endTime = System.nanoTime();
		this.timeDijkstra = (endTime-startTime)/1000000.0f;
		this.nbSommetsMarquesD = DijkstraAlgo.getNbMarque();
		this.nbSommetsVisitesD = DijkstraAlgo.getNbVisite();

		
		
		startTime = 0;
		endTime = 0;
	
		AStarAlgorithm AStarAlgo = new AStarAlgorithm(data);
		startTime = System.nanoTime();
		AStarAlgo.run();
		endTime = System.nanoTime();
		this.timeAStar = (endTime-startTime)/1000000.0f;
		this.nbSommetsMarquesA = AStarAlgo.getNbMarque();
		this.nbSommetsVisitesA = AStarAlgo.getNbVisite();
		
	}
	
	public int getOrigine() {
		return this.origine;
	}
	
	public int getDestination() {
		return this.destination;
	}
	
	public float getTempsExecutionDijkstra() {
		return this.timeDijkstra;
	}
	
	public float getTempsExecutionAStar() {
		return this.timeAStar;
	}
	
	public int getMarqueD() {
		return this.nbSommetsMarquesD;
	}
	
	public int getMarqueA() {
		return this.nbSommetsMarquesA;
	}
	
	public int getVisiteD() {
		return this.nbSommetsVisitesD;
	}
	
	public int getVisiteA() {
		return this.nbSommetsVisitesA;
	}


}