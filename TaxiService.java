
import java.io.IOException;
import java.util.Iterator;

public class TaxiService{
	map mp;
	
	public TaxiService() {
		mp=new map();
	}

	public void performAction(String actionMessage) throws IOException
	{
		try
		{	
			System.out.println("action to be performed: " + actionMessage);
			String[] action=actionMessage.split(" ");
			if(action[0].equals("edge"))
			{
				mp.addEdge(action[1], action[2],Integer.parseInt(action[3]));
			}
			else if(action[0].equals("taxi"))
			{	vertex currentpos=null;
				Iterator<vertex> it=mp.vertxs.iterator();
				while (it.hasNext())
				{
					vertex h=it.next();
					if (h.getName().equals(action[2]))
					{currentpos=h;
					}
				}
				Taxi element=new Taxi(action[1],currentpos,0);
				mp.taxiset.addElement(element);
			}
			else if(action[0].equals("customer"))
			{	
				mp.customer(action[1],action[2],Integer.parseInt(action[3]));
				System.out.println("");	
			}
			else if(action[0].equals("printTaxiPosition"))
			{	int tm=Integer.parseInt(action[1]);
				Node<Taxi> tp=mp.taxiset.getTop();
				boolean flag=false;
				while(tp!=null)
				{	if(tp.getElement().Time()<=tm)
					{System.out.println(tp.getElement().getName()+":"+tp.getElement().getPosition().getName());
					tp.getElement().updatetime(tm);;
					flag=true;
					}
					tp=tp.getNext();
				}
				if(!flag)
					throw new IOException("All taxis are busy!!!");
				System.out.println("");	
			}
			else
			{
				throw new IOException("Incorrect query");
			}
			
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			System.out.println("");
		}
	
	}
}
