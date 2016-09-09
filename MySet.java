public class MySet<X> {
	Node<X> top;
	MySet(){
		top=null;
	}
	public void addElement(X element)
	{  	Node<X> tmp=top;
		boolean flag=true;
		while(tmp!=null){
			if(tmp.getElement().equals(element))
			{	flag=false;
				break;
			}
			tmp=tmp.getNext();
		}
		if(flag)
		top=new Node<X>(element,top);
		
	}
	public MySet<X> union(MySet<X> otherSet)
	{  MySet<X> setunion= new MySet<X>();
		Node<X> tmp1=top;
		while(tmp1!=null)
		{
			setunion.addElement(tmp1.getElement());
			tmp1=tmp1.getNext();
		}		
		Node<X> topofother=otherSet.getTop();
		Node<X> tmp=topofother;
		while(tmp!=null)
		{	boolean flag=true;
			tmp1=top;
			while(tmp1!=null)
			{
				if(tmp1.getElement().equals(tmp.getElement()))
				{	flag=false;
					break;
				}
				tmp1=tmp1.getNext();
			}
			if(flag)
			{
				setunion.addElement(tmp.getElement());
			}
			tmp=tmp.getNext();
		}
		return setunion;		
	}
	public MySet<X> intersection(MySet<X> otherSet)
	{   
		MySet<X> intersectionset=new MySet<X>();
		Node<X> p=top;
		while(p!=null)
		{
			Node<X> tmp=otherSet.getTop();
			while(tmp!=null)
			{
				if(p.getElement().equals(tmp.getElement()))
					intersectionset.addElement(p.getElement());
				tmp=tmp.getNext();
			}
			p=p.getNext();
		}
		return intersectionset;		
	}
	public Node<X> getTop()
	{
		return top;
	}

}
