package blog.wstx.class16;

public class Edge {
	public int weight; // 有权重的边
	public Node from; // 有向图有指向
	public Node to;

	public Edge(int weight, Node from, Node to) {
		this.weight = weight;
		this.from = from;
		this.to = to;
	}

}