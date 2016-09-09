import java.io.IOException;
import java.util.*;

public class map {
	   Vector<vertex> vertxs=new Vector<vertex>();
	   Vector<edge> edges=new Vector<edge>();
	  graph finalgraph= new graph(vertxs, edges);
	  Shortestpath shpath=new Shortestpath(finalgraph) ;
	  public MySet<Taxi> taxiset=new MySet<Taxi>();
	  
	  //add edge to graph
	  void addEdge(String source,String dest,int dur)
	  {		
		  	vertex SV=new vertex(source);
	  		vertex DV=new vertex(dest);	  		
			boolean flag=false;
			boolean flag2=false;
	  		Iterator<vertex> it=vertxs.iterator();
			while (it.hasNext())
			{
				vertex h=it.next();
				if (h.getName().equals(source))
				{ flag=true;
				  SV=h;
				} 
				if (h.getName().equals(dest)) 
				{ 
					flag2=true; 
					DV=h; 
				}
			}
			if (!flag)
			{
				vertxs.add(SV);
			}
			if (!flag2)
			{ 
				vertxs.add(DV);
			}
		
			edge e=new edge(SV,DV,dur);
			edges.addElement(e);
	  }
	
	//booking nearest taxi and tracking of taxi 
	void customer(String source,String Destination,int t) throws IOException
	{
		Node<Taxi> u=taxiset.getTop();
		
		while(u!=null)
		{
			if(u.getElement().Time()<=t)
				u.getElement().updatetime(t);
			u=u.getNext();
		}
		
		vertex src=null;
		vertex des=null;
		
		Iterator<vertex> it=finalgraph.getVertexes().iterator();
		while (it.hasNext())
		{
			vertex vit=it.next();
			if(vit.getName().equals(source)) 
			{ 	src=vit;
				break;
			} 
		}
		
		Iterator<vertex> itt=finalgraph.getVertexes().iterator();
		while (itt.hasNext())
		{
			vertex vitt=itt.next();
			if(vitt.getName().equals(Destination)) 
			{ 	des=vitt;
				break;
			}
		}
		
		if(src!=null&&des!=null)
		{	Taxi nearestTaxi=null;
			int shorttime=20000;
			Node<Taxi> f=taxiset.getTop();
			//print available taxi and its location
			System.out.println("Available Taxis:");
			while(f!=null)
			{	//to check that given taxi is not busy
				if(f.getElement().Time()<=t)
				{
					vertex taxiposition=null;
					Iterator<vertex> it2=vertxs.iterator();
					while (it2.hasNext())
					{
						vertex ver=it2.next();
						if (ver.getName().equals(f.getElement().getPosition().getName()))
						{	
							taxiposition=ver;
						}
					}
					System.out.print("Path of Taxi("+f.getElement().getName()+"):");
					Shortestpath shpath = new Shortestpath(finalgraph);
					shpath.execute(taxiposition);
					Vector<vertex> path = shpath.getShortestPath(src);
					int timetakn=shpath.distance.get(src);
					if(path!=null)
					{	//print path from taxi current position to source of customer
						for (int i=0;i<path.size();i++)
				      	{	System.out.print(path.get(i).getName());
				      		if(i!=path.size()-1)
				      			System.out.print(",");
				      	
				      	}
						//choose nearest taxi
						if(shorttime>timetakn)
						{	shorttime=timetakn;
							nearestTaxi=f.getElement();
						}
						System.out.print(". time taken is "+timetakn+" units");
					}
					else //taxi is in customer source place
					{	System.out.print(source+". time taken is 0 units");
						shorttime=0;
						nearestTaxi=f.getElement();
					}	
					System.out.println("");
				}
				f=f.getNext();	
			}
			
			if(nearestTaxi!=null)
			{Shortestpath shpath1 = new Shortestpath(finalgraph);
			shpath1.execute(src);
			Vector<vertex> finalpath = shpath1.getShortestPath(des);
			int finaltimetakn=shpath1.distance.get(des);
			
			System.out.println("** Chose "+nearestTaxi.getName()+" to service the customer request **");
			System.out.print("Path of customer:");
			
			//print path from source to destination of customer
			for (int i=0;i<finalpath.size();i++)
			{	System.out.print(finalpath.get(i).getName());
	      		if(i!=finalpath.size()-1)
	      			System.out.print(",");   	
			}
			System.out.println(". time taken is "+finaltimetakn+" units");
			
			//update details of taxi
			int newtime=nearestTaxi.Time()+finaltimetakn+shorttime;
			nearestTaxi.updatetime(newtime); //set taxi busy
			nearestTaxi.changeposition(des); //update position of taxi
			}
			else
				throw new IOException("No taxi available.....All taxis are busy!!!");
		}
		else
			System.out.println("Place does not exist!!!");
	}	  
}
