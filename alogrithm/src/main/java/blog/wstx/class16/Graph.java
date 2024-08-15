package blog.wstx.class16;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
	public HashMap<Integer, Node> nodes; // 点的集合
	public HashSet<Edge> edges; // 边的集合
	
	public Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}
}