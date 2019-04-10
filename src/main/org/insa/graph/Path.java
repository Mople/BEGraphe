package org.insa.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;

/**
 * <p>
 * Class representing a path between nodes in a graph.
 * </p>
 * 
 * <p>
 * A path is represented as a list of {@link Arc} with an origin and not a list
 * of {@link Node} due to the multi-graph nature (multiple arcs between two
 * nodes) of the considered graphs.
 * </p>
 *
 */
public class Path {

    /**
     * Create a new path that goes through the given list of nodes (in order),
     * choosing the fastest route if multiple are available.
     * 
     * @param graph Graph containing the nodes in the list.
     * @param nodes List of nodes to build the path.
     * 
     * @return A path that goes through the given list of nodes.
     * 
     * @throws IllegalArgumentException If the list of nodes is not valid, i.e. two
     *         consecutive nodes in the list are not connected in the graph.
     * 
     * 
     */
    public static Path createFastestPathFromNodes(Graph graph, List<Node> nodes)
            throws IllegalArgumentException {
    	
    	//Cas liste nodes vide
    	if(nodes.size()==0) {
    		return new Path(graph);
    	}
    	
    	//Cas liste nodes contient un seul element
    	else if (nodes.size()==1) {
    		return new Path(graph,nodes.get(0));
    	}
    	
    	//Cas liste nodes contient plusieurs elements
    	else {
    		
    		//Iterator pour parcourir tous les elements de nodes
    		Iterator<Node> iterator = nodes.iterator();
    		Node origine = iterator.next();
    		
    		//Initialisation de la liste des arc les plus rapides
    		List<Arc> arc_path= new ArrayList<Arc>();
    		Arc fastest_arc=null;
    		
    		//Parcours des elements de nodes
    		while (iterator.hasNext()) {    			
    			List<Arc> arcS = origine.getSuccessors();
    			Node destination = iterator.next();
    			
    			//Iterator pour parcourir les successeurs d'une node
    			Iterator<Arc> Ite_arc = arcS.iterator();
    			
    			double time_min=0.0;
    			
    			//Parcours des successeur
    			while (Ite_arc.hasNext()) {
    				Arc element = Ite_arc.next();
    				
    				//Verification de la bonne destination
    				if(element.getDestination()==destination) {
    					
    					//Cas ou on a deja trouve un arc 
    					if (fastest_arc!=null) {
    						double time_arc = element.getMinimumTravelTime();
    						
    						if(time_arc<time_min) {
    							time_min=time_arc;
    							fastest_arc=element;
    						}
    						
    					}
    					//Cas premier arc trouve
    					else {
    						time_min=element.getMinimumTravelTime();
    						fastest_arc=element;
    					}
    					
    					
    				}
    				
    			}
    			//Si aucun arc n'a la bonne destination
    			if (fastest_arc==null) {
    				throw new IllegalArgumentException("Cannot create path from invalid list of nodes");
    			}
    			//Sinon l'ajouter dans la liste pour le path
        		arc_path.add(fastest_arc);
    			
    		}
    		return new Path(graph,arc_path);
    		
    		
    	}
    
        	
        	
        
        
    }

    /**
     * Create a new path that goes through the given list of nodes (in order),
     * choosing the shortest route if multiple are available.
     * 
     * @param graph Graph containing the nodes in the list.
     * @param nodes List of nodes to build the path.
     * 
     * @return A path that goes through the given list of nodes.
     * 
     * @throws IllegalArgumentException If the list of nodes is not valid, i.e. two
     *         consecutive nodes in the list are not connected in the graph.
     * 
     * 
     */
    public static Path createShortestPathFromNodes(Graph graph, List<Node> nodes)
            throws IllegalArgumentException {
    	if(nodes.size()==0) {
    		return new Path(graph);
    	}
    	
    	//Cas liste nodes contient un seul element
    	else if (nodes.size()==1) {
    		return new Path(graph,nodes.get(0));
    	}
    	
    	//Cas liste nodes contient plusieurs elements
    	else {
    		
    		//Iterator pour parcourir tous les elements de nodes
    		Iterator<Node> iterator = nodes.iterator();
    		Node origine = iterator.next();
    		
    		//Initialisation de la liste des arc les plus rapides
    		List<Arc> arc_path= new ArrayList<Arc>();
    		Arc shortest_arc=null;
    		
    		//Parcours des elements de nodes
    		while (iterator.hasNext()) {    			
    			List<Arc> arcS = origine.getSuccessors();
    			Node destination = iterator.next();
    			
    			//Iterator pour parcourir les successeurs d'une node
    			Iterator<Arc> Ite_arc = arcS.iterator();
    			
    			float dist_min=0.0f;
    			
    			//Parcours des successeur
    			while (Ite_arc.hasNext()) {
    				Arc element = Ite_arc.next();
    				
    				//Verification de la bonne destination
    				if(element.getDestination()==destination) {
    					
    					//Cas ou on a deja trouve un arc 
    					if (shortest_arc!=null) {
    						float dist_arc = element.getLength();
    						
    						if(dist_arc<dist_min) {
    							dist_min=dist_arc;
    							shortest_arc=element;
    						}
    						
    					}
    					//Cas premier arc trouve
    					else {
    						dist_min=element.getLength();
    						shortest_arc=element;
    					}
    					
    					
    				}
    				
    			}
    			//Si aucun arc n'a la bonne destination
    			if (shortest_arc==null) {
    				throw new IllegalArgumentException("Cannot create path from invalid list of nodes");
    			}
    			//Sinon l'ajouter dans la liste pour le path
        		arc_path.add(shortest_arc);
    			
    		}
    		return new Path(graph,arc_path);
    		
    		
    	}
    }

    /**
     * Concatenate the given paths.
     * 
     * @param paths Array of paths to concatenate.
     * 
     * @return Concatenated path.
     * 
     * @throws IllegalArgumentException if the paths cannot be concatenated (IDs of
     *         map do not match, or the end of a path is not the beginning of the
     *         next).
     */
    public static Path concatenate(Path... paths) throws IllegalArgumentException {
        if (paths.length == 0) {
            throw new IllegalArgumentException("Cannot concatenate an empty list of paths.");
        }
        final String mapId = paths[0].getGraph().getMapId();
        for (int i = 1; i < paths.length; ++i) {
            if (!paths[i].getGraph().getMapId().equals(mapId)) {
                throw new IllegalArgumentException(
                        "Cannot concatenate paths from different graphs.");
            }
        }
        ArrayList<Arc> arcs = new ArrayList<>();
        for (Path path: paths) {
            arcs.addAll(path.getArcs());
        }
        Path path = new Path(paths[0].getGraph(), arcs);
        if (!path.isValid()) {
            throw new IllegalArgumentException(
                    "Cannot concatenate paths that do not form a single path.");
        }
        return path;
    }

    // Graph containing this path.
    private final Graph graph;

    // Origin of the path
    private final Node origin;

    // List of arcs in this path.
    private final List<Arc> arcs;

    /**
     * Create an empty path corresponding to the given graph.
     * 
     * @param graph Graph containing the path.
     */
    public Path(Graph graph) {
        this.graph = graph;
        this.origin = null;
        this.arcs = new ArrayList<>();
    }

    /**
     * Create a new path containing a single node.
     * 
     * @param graph Graph containing the path.
     * @param node Single node of the path.
     */
    public Path(Graph graph, Node node) {
        this.graph = graph;
        this.origin = node;
        this.arcs = new ArrayList<>();
    }

    /**
     * Create a new path with the given list of arcs.
     * 
     * @param graph Graph containing the path.
     * @param arcs Arcs to construct the path.
     */
    public Path(Graph graph, List<Arc> arcs) {
        this.graph = graph;
        this.arcs = arcs;
        this.origin = arcs.size() > 0 ? arcs.get(0).getOrigin() : null;
    }

    /**
     * @return Graph containing the path.
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * @return First node of the path.
     */
    public Node getOrigin() {
        return origin;
    }

    /**
     * @return Last node of the path.
     */
    public Node getDestination() {
        return arcs.get(arcs.size() - 1).getDestination();
    }

    /**
     * @return List of arcs in the path.
     */
    public List<Arc> getArcs() {
        return Collections.unmodifiableList(arcs);
    }

    /**
     * Check if this path is empty (it does not contain any node).
     * 
     * @return true if this path is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.origin == null;
    }

    /**
     * Get the number of <b>nodes</b> in this path.
     * 
     * @return Number of nodes in this path.
     */
    public int size() {
        return isEmpty() ? 0 : 1 + this.arcs.size();
    }

    /**
     * Check if this path is valid.
     * 
     * A path is valid if any of the following is true:
     * <ul>
     * <li>it is empty;</li>
     * <li>it contains a single node (without arcs);</li>
     * <li>the first arc has for origin the origin of the path and, for two
     * consecutive arcs, the destination of the first one is the origin of the
     * second one.</li>
     * </ul>
     * 
     * @return true if the path is valid, false otherwise.
     */
    public boolean isValid() {
        boolean valide = false;
        if (isEmpty() || size() == 1) {
        	valide = true;
        } 
        else if (this.arcs.get(0).getOrigin() == getOrigin()) {
        	valide = true;
        	for (int i = 0; i < this.arcs.size() - 1; i++) {
        		valide = this.arcs.get(i).getDestination() == this.arcs.get(i+1).getOrigin() && valide ;
        	}
        }
        return valide;
    }

    /**
     * Compute the length of this path (in meters).
     * 
     * @return Total length of the path (in meters).
     */
    public float getLength() {
        float longeur = 0.0f; 
        if (!isEmpty()) {
        	for (int i = 0; i < this.arcs.size(); i++ ) {
        		longeur += this.arcs.get(i).getLength();
        	}
        }
        return longeur;
    }

    /**
     * Compute the time required to travel this path if moving at the given speed.
     * 
     * @param speed Speed to compute the travel time.
     * 
     * @return Time (in seconds) required to travel this path at the given speed (in
     *         kilometers-per-hour).
     * 
     * 
     */
    public double getTravelTime(double speed) {
    	return getLength() * 3600.0 / (speed * 1000.0);
    }

    /**
     * Compute the time to travel this path if moving at the maximum allowed speed
     * on every arc.
     * 
     * @return Minimum travel time to travel this path (in seconds).
     * 
     * 
     */
    public double getMinimumTravelTime() {
        double time=0.0;
        if(!isEmpty()) {
        	for(int i=0;i<this.arcs.size();i++) {
        		time += this.arcs.get(i).getMinimumTravelTime();
        	}
        }
        return time;
    }

}
