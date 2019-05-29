package org.insa.algo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
//import java.util.ArrayList;
import java.util.Arrays;

import org.insa.graph.Arc;
import org.insa.graph.Graph;
import org.insa.graph.Node;
import org.insa.graph.RoadInformation;
import org.insa.graph.RoadInformation.RoadType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.insa.algo.shortestpath.*; 
import java.io.DataInputStream;

public class DjikstraTest {

	// Small graph use for tests
    private static Graph graph;

    // List of nodes
    private static Node[] nodes;

    // List of arcs in the graph, a2b is the arc from node A (0) to B (1).
    @SuppressWarnings("unused")
    private static Arc a2b, a2c, a2e, b2c, c2d_1, c2d_2, c2d_3, c2a, d2a, d2e, e2d;

    
    @BeforeClass
    public static void initAll() throws IOException {

    	// Define roads
    			RoadInformation RoadInfo = new RoadInformation(RoadType.UNCLASSIFIED, null, true, 1, null);

        // Create nodes
        nodes = new Node[5];
        for (int i = 0; i < nodes.length; ++i) {
            nodes[i] = new Node(i, null);
        }

        // Add arcs (CM exemple)
        a2b = Node.linkNodes(nodes[0], nodes[1], 10, RoadInfo, null);
        a2c = Node.linkNodes(nodes[0], nodes[2], 15, RoadInfo, null);
        a2e = Node.linkNodes(nodes[0], nodes[4], 15, RoadInfo, null);
        b2c = Node.linkNodes(nodes[1], nodes[2], 10, RoadInfo, null);
        c2d_1 = Node.linkNodes(nodes[2], nodes[3], 20, RoadInfo, null);
        c2d_2 = Node.linkNodes(nodes[2], nodes[3], 10, RoadInfo, null);
        c2d_3 = Node.linkNodes(nodes[2], nodes[3], 15, RoadInfo, null);
        d2a = Node.linkNodes(nodes[3], nodes[0], 15, RoadInfo, null);
        d2e = Node.linkNodes(nodes[3], nodes[4], 22, RoadInfo, null);
        e2d = Node.linkNodes(nodes[4], nodes[0], 10, RoadInfo, null);
        
        graph = new Graph("ID", "", Arrays.asList(nodes), null);

    }
    
    @Test
    public void testAlgorithm() {
    	System.out.println("---Test fonctionnement Dijkstra en fonction de Bellman Ford---");
    	System.out.println("---------------Affichage : (Sommet pere, Coût)----------------");
    	// on va tester un parcours de Djikstra et Bellman Ford
    	// sur un exemple du poly simple pour tous les chemins possibles
    	
    	for (int i = 0; i < nodes.length; i++) {
    		System.out.print("s"+nodes[i].getId()+": ");
    		for (int j = 0; j < nodes.length; j++) {
    			if (nodes[i] != nodes[j]) {
    				ArcInspector arcInspector = new ArcInspectorFactory().getAllFilters().get(0);
    				ShortestPathData data = new ShortestPathData(graph, nodes[i], nodes[j], arcInspector) ;
    				
    				// get algorithms to test
    				BellmanFordAlgorithm BellmanFordAlgo = new BellmanFordAlgorithm(data);
    				DijkstraAlgorithm DijkstraAlgo = new DijkstraAlgorithm(data);
    				
    				// on va comparer les solutions obtenues avec Bellman Ford a Dijkstra
    				ShortestPathSolution DijkstraSolution = DijkstraAlgo.run();
    				ShortestPathSolution BellmanFordSolution = BellmanFordAlgo.run();
    				
    				// Si aucun chemin trouvé
    				if (DijkstraSolution.getPath() == null) {
    					assertEquals( BellmanFordSolution.getPath(), DijkstraSolution.getPath());
    					System.out.print("n");
    				}
    				else {
    					assertEquals(DijkstraSolution.getPath().getLength(),BellmanFordSolution.getPath().getLength(), 0);
    					// On doit afficher le cout et le sommet precedent 
    					Node pere = DijkstraSolution.getPath().getArcs().get(DijkstraSolution.getPath().getArcs().size()-1).getOrigin();
    					System.out.print("(s"+pere.getId()+","+DijkstraSolution.getPath().getLength()+") ");
    				}
    			}
    			else {
    				System.out.print("(--,----) ");
    			}
    		}
    		System.out.println();
    	}
    }
    @Test
    public void testDijkstraDistance() {
    	ArcInspector arcInspector = new ArcInspectorFactory().getAllFilters().get(0);
    	GraphReader reader = new BinaryGraphReader (new DataInputStream(new BufferedInputStream(new FileInputStream("ici mettrecheminmap"))));
    }
}
