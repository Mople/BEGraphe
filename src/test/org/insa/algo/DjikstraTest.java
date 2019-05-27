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

public class DjikstraTest {

	// Small graph use for tests
    private static Graph graph;

    // List of nodes
    private static Node[] nodes;

    // List of arcs in the graph, a2b is the arc from node A (0) to B (1).
    @SuppressWarnings("unused")
    private static Arc a2b, a2d, b2d, d2b, b2c, d2c, d2e, c2e, e2c, e2a;
    
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
        a2d = Node.linkNodes(nodes[0], nodes[3], 5, RoadInfo, null);
        b2d = Node.linkNodes(nodes[1], nodes[3], 2, RoadInfo, null);
        b2c = Node.linkNodes(nodes[1], nodes[2], 1, RoadInfo, null);
        c2e = Node.linkNodes(nodes[2], nodes[4], 4, RoadInfo, null);
        d2b = Node.linkNodes(nodes[3], nodes[1], 3, RoadInfo, null);
        d2c = Node.linkNodes(nodes[3], nodes[2], 9, RoadInfo, null);
        d2e = Node.linkNodes(nodes[3], nodes[4], 2, RoadInfo, null);
        e2a = Node.linkNodes(nodes[4], nodes[0], 3, RoadInfo, null);
        e2c = Node.linkNodes(nodes[4], nodes[2], 5, RoadInfo, null);
        
        graph = new Graph("ID", "", Arrays.asList(nodes), null);

        
    }
}
