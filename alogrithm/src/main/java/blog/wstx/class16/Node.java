package blog.wstx.class16;

import java.util.ArrayList;

// 点结构的描述
public class Node {
	public int value; // 当前节点的值
	public int in; // 以此节点为终的数量
	public int out; // 以此节点为始的数量
	public ArrayList<Node> nexts; // 此节点指向的节点集合
	public ArrayList<Edge> edges; // 以此节点为头的边的集合

	public Node(int value) {
		this.value = value;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
	}
}
