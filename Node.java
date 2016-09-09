public class Node<T>
		{
			private T ele;
			private Node<T> next;
			Node(){
				ele=null;
				next=null;
			}
			public Node(T o,Node<T> n)
			{
				ele=o;
				next=n;
			}
			public void setNext(Node<T> n)
			{
				next=n;
			}	
			public T getElement()
			{
				return ele;
			}
			public Node<T> getNext()
			{
				return next;
			}
			
		}