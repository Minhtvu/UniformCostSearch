import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;

public class UniformCostSearch{
    public static void UCS(Node source, Node goal){
        source.pathCost = 0;
        PriorityQueue<Node> queue = new PriorityQueue<Node>(20,
        		new Comparator<Node>(){

            public int compare(Node i, Node j){
                if(i.pathCost > j.pathCost){
                    return 1;
                }
                else if (i.pathCost < j.pathCost){
                    return -1;
                }
                else{
                    return 0;
                }
            }
         }
        );
        queue.add(source);
        Set<Node> explored = new HashSet<Node>();
        do{
            Node current = queue.poll();
            explored.add(current);
            for(Edge e: current.adjacencies){
                Node child = e.target;
                double cost = e.cost;
                if(!queue.contains(child)){
                	child.pathCost = current.pathCost + cost;
                }
                if(!explored.contains(child) && !queue.contains(child)){
                    child.parent = current;
                    queue.add(child);
                }
                else if((queue.contains(child))&&(child.pathCost > current.pathCost + cost)){
                	child.parent=current;
                    queue.remove(child);
                    queue.add(child);
                }
            }
        }while(!queue.isEmpty());

    }

    public static List<Node> printPath(Node target){
        List<Node> path = new ArrayList<Node>();
        for(Node node = target; node!=null; node = node.parent){
            path.add(node);
        }
        Collections.reverse(path);
        return path;
    }
    private static int findIndex(Node target, ArrayList<Node> graph){
    	for(int i = 0; i < graph.size();i++)
    	{
    		Node check = graph.get(i);
    		if (check.toString().equals(target.toString())){
    			return i;
    		}
    	}
    	return -1;
    }
    private static Node getNode(String name, ArrayList<Node> graph)
    {
    	for(Node a: graph){
    		if(a.toString().equals(name))
    			return a;
    	}
    	return new Node("null");
    }
    public static ArrayList<Node> readFile(String file_name)
    {
    	ArrayList<Node> graph = new ArrayList<Node>();
    	File file = new File(file_name); 
        try {
            Scanner scanner = new Scanner(file); 

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("END OF INPUT")){
                	break;
                }
                String[] fields = line.split(" ");
                Node source = new Node(String.valueOf(fields[0]));
                Node target = new Node(String.valueOf(fields[1]));
                int cost = Integer.parseInt(fields[2]);
                if(graph.contains(source)){
                	source = graph.get(findIndex(source,graph));
                }
            	if(graph.contains(target))
            	{
            		target = graph.get(findIndex(target,graph));
            		target.addEdge(source, cost);
            		graph.set(findIndex(target,graph), target);            	
            	}
            	else
            	{
            		target.addEdge(source, cost);
            		graph.add(target);
            	}
            	source.addEdge(target, cost);
            	if(graph.contains(source)){
            		graph.set(findIndex(source,graph), source);
            	}
            	else{
            		graph.add(source);
            	}                        
            }
            scanner.close(); 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (NumberFormatException e){
            System.out.println("Reached End of Line"); 
    }
        return graph;
    }
    public static void main(String[] args){
    	if (args.length < 3)
    	{
		System.out.println("Wrong arguments");
    		System.exit(0);
    	}
        ArrayList<Node> graph = readFile(args[0]);
        Node source = getNode(args[1],graph);
        Node target = getNode(args[2],graph);
    	UCS(source,target);
        List<Node> path = printPath(target);
        double distance = 0;
        for(Node a: path){
        	if (a.parent != null)
        	{
        		distance += a.costToParent();
        	}
        }
        if (distance > 0){
        	System.out.println("Distance: " + distance + " km");
        	System.out.println("Route: ");
        for(Node a: path)
        {
        	if (a.parent != null)
        		System.out.println(a.parent + " to " + a + ", " + a.costToParent() + " km");
        }}
        else
        {
        	System.out.println("Distance: infinity");
        	System.out.println("Route: ");
        	System.out.print("none");
        }
    }
}
