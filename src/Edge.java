public class Edge{
    public final double cost;
    public final Node target;

    public Edge(Node targetNode, double costVal){
        cost = costVal;
        target = targetNode;
    }
	public String toString() {
		return target.toString() + " " + cost;
	}
	public double getCost() {
		return cost;
	}
	public Node getTarget() {
		return target;
	}
	
}