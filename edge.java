public class edge  { 
  private vertex source;
  private vertex destination;
  private int distance; 
  
  public edge(vertex src, vertex dest, int dist) {
    source = src;
    destination = dest;
    distance = dist;
  }
  
  
  public vertex getDestination() {
    return destination;
  }

  public vertex getSource() {
    return source;
  }
  public int getDistance() {
    return distance;
  }
  
  
} 