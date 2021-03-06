package org.insa.algo.shortestpath;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.insa.algo.utils.BinaryHeap;
import org.insa.algo.utils.Label;
import org.insa.graph.Arc;
import org.insa.graph.Node;
import org.insa.graphics.drawing.Drawing;
import org.insa.graphics.drawing.overlays.PointSetOverlay;

public class ShortestPathGraphicObserver implements ShortestPathObserver {

    // Drawing and Graph drawing
    protected Drawing drawing;
    protected PointSetOverlay psOverlay1, psOverlay2;

    public ShortestPathGraphicObserver(Drawing drawing) {
        this.drawing = drawing;
        psOverlay1 = drawing.createPointSetOverlay(1, Color.CYAN);
        psOverlay2 = drawing.createPointSetOverlay(1, Color.BLUE);
    }

    @Override
    public void notifyOriginProcessed(Node node) {
        drawing.drawMarker(node.getPoint(), Color.BLUE, Color.WHITE, Drawing.AlphaMode.OPAQUE);
    }

    @Override
    public void notifyNodeReached(Node node) {
        psOverlay1.addPoint(node.getPoint());
    }

    @Override
    public void notifyNodeMarked(Node node) {
        psOverlay2.addPoint(node.getPoint());
    }

    @Override
    public void notifyDestinationReached(Node node) {
        drawing.drawMarker(node.getPoint(), Color.BLUE, Color.WHITE, Drawing.AlphaMode.OPAQUE);
    }
    
    @Override
    public void notifyLabel(Label label) {
    	//do nothing
    }
    
    @Override
    public void notifySizeHeap(BinaryHeap<Label> heap) {
    	//do nothing
    }
    
    @Override
    public void notifyEnd(ArrayList<Arc> arcsAlgo, int nbIter) {
    	//do nothing
    }
    
    @Override
    public void notifySuccessors(List<Arc> successors) {
    	//do nothing
    }

}
