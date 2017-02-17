import java.util.ArrayList;

public class Node{

    public final String value;
    public double pathCost;
    public ArrayList<Edge> adjacencies;
    public Node parent;

    public Node(String val){

        value = val;
        adjacencies = new ArrayList<Edge>();
    }
    public void addEdge(Node target, int cost)
    {
    	adjacencies.add(new Edge(target,cost));
    }
    public String toString(){
        return value;
    }
    @Override
    public boolean equals(Object o){
    	 if(o instanceof Node){
             String toCompare = o.toString();
             return value.equals(toCompare);
         }
         return false;
    }
    public double costToParent()
    {
    	for(Edge a: adjacencies)
    	{
    		if (a.getTarget().toString().equals(parent.toString())){
    			return a.cost;
    		}
    	}
    	return 0;
    }
}