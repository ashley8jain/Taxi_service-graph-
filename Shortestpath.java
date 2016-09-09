import java.io.IOException;
import java.util.*;

	public class Shortestpath {
		Vector<vertex> vertexs;
		Vector<edge> edges;
		Vector<vertex> cloud;
		Vector<vertex> priorityqueue;
		Map<vertex,vertex> predecessors;
		Map<vertex,Integer> distance;

	//constructor	
	public Shortestpath(graph gr)
	{
		vertexs=gr.getVertexes();
		edges=gr.getEdges();
	}
	
	//set minimum distance to all vertexes(other than source) from source vertex
	public void execute(vertex source) throws IOException
	{
		cloud =new Vector<vertex>();
		priorityqueue=new Vector<vertex>();
		distance= new HashMap<vertex,Integer>();
		predecessors= new HashMap<vertex, vertex>();
		
		
		distance.put(source,0);
		//set infinity value distance to all vertexes except source vertex
		Vector<vertex> nonsourcevertex=vertexs;
		Iterator<vertex> i=nonsourcevertex.iterator();
		while(i.hasNext())
		{	vertex nonsourcevert=i.next();
			if(!nonsourcevert.equals(source))
				distance.put(nonsourcevert, Integer.MAX_VALUE);
		}
		
		//update distance of all vertexes except source to final shortest distance from source
		priorityqueue.add(source);
		while (priorityqueue.size()>0)
		{
			vertex node= getMinimumkeyinpriorityqueue(priorityqueue);
			cloud.add(node);
			priorityqueue.remove(node);
			//update distance of all adjacent vertices of "node"
			Vector<vertex> adjacentvertexs= getAdjacent(node);
			Iterator<vertex> it=adjacentvertexs.iterator();
			while (it.hasNext())
			{   
				vertex tgt=it.next();
				//edge relaxation
				if (distance.get(tgt)>distance.get(node)+getDistanceofedge(node,tgt))
				{
					distance.put(tgt, distance.get(node)+getDistanceofedge(node,tgt));
					predecessors.put(tgt,node);
					priorityqueue.add(tgt);
				}
			}
		}
	}
	
	public boolean isIncloud(vertex vertex)
	{
		return cloud.contains(vertex);
	}
	
	public Vector<vertex> getAdjacent(vertex node)
	{
		Vector<vertex> neighbours=new Vector<vertex>();
		Iterator<edge> it=edges.iterator();
		while (it.hasNext())
		{
			edge e=it.next();
			if (e.getSource()==node&&!isIncloud(e.getDestination()))
			{
				neighbours.add(e.getDestination());
			}
			else if (e.getDestination()==node && !isIncloud(e.getSource()))
			{
				neighbours.add(e.getSource());
			}
		}
		return neighbours;
	}
	
	public int getDistanceofedge(vertex s,vertex t) throws IOException
	{
		Iterator<edge> it=edges.iterator();
		while (it.hasNext())
		{
		  edge e=it.next();
		  if(e.getSource()==s && e.getDestination()==t)
			  return e.getDistance();
		  else if(e.getDestination()==s && e.getSource()==t)
			  return e.getDistance();
		}
		throw new IOException("Edge does not exist");
	}
	
	public vertex getMinimumkeyinpriorityqueue(Vector<vertex> vertexes)
	{
		vertex minimum=null;
		Iterator<vertex> it=vertexes.iterator();
		while (it.hasNext())
		{
			vertex v=it.next();
			if (minimum==null)
			{
				minimum=v;
			}
			else
			{ 
				if(distance.get(v)<distance.get(minimum))
				{ 
					minimum=v; 
				}
			}
		}
		return minimum;
	}
	
	public Vector<vertex> getShortestPath(vertex target)
	{
		Vector<vertex> path=new Vector<vertex>();
		vertex step=target;
		if (predecessors.get(step) == null) {
		      return null;
		    }
		    path.add(step);
		    while (predecessors.get(step) != null) {
		      step = predecessors.get(step);
		      path.add(step);
		    }   
		    Collections.reverse(path);
		    return path;
	}
	
}
