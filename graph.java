import java.util.Vector;

public class graph {
    private Vector<vertex> vertexes;
    private Vector<edge> edges;

  public graph(Vector<vertex> vert, Vector<edge> edgs) {
    vertexes = vert;
    edges = edgs;
  }

  public Vector<vertex> getVertexes() {
    return vertexes;
  }

  public Vector<edge> getEdges() {
    return edges;
  }  
} 

