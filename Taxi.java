public class Taxi {
	private String name;
	private vertex currentPosition;
	//boolean availability;
	private int time;
	Taxi(String nm,vertex cp,int t)
	{
		name=nm;
		currentPosition=cp;
		time=t;
		//availability=av;
	}
	public String getName()
	{
		return name;
	}
	public vertex getPosition()
	{
		return currentPosition;
	}
	public void changeposition(vertex new1)
	{
		currentPosition=new1;
	}
	public void updatetime(int t)
	{
		time=t;
	}
	public int Time()
	{
		return time;
	}
}
