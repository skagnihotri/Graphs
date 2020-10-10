package graph;

import java.util.HashMap;

public class GraphCLient {

	public static void main(String[] args) {
		Graph g = new Graph(7);
		g.addedge(1, 2, 4);
		g.addedge(1, 4, 5);
		g.addedge(2, 3, 6);
		g.addedge(3, 4, 3);
		g.addedge(4, 5, 10);
		g.addedge(5, 6, 11);
		g.addedge(6, 7, 13);
		g.addedge(5, 7, 12);
		System.out.println(g);
//		System.out.println(g.removeedge(2, 3));
//		System.out.println(g.removeedge(6, 7));
//		System.out.println(g.removeedge(4, 5));
//		System.out.println(g.haspath(1, 6, new HashMap<Integer, Boolean>()));
		g.printallpaths(1, 6, new HashMap<Integer, Boolean>(), "");
//		System.out.println(g.BFS(1, 6));
//		System.out.println(g.DFS(1, 6));
//		g.BFT();
//		System.out.println();
//		g.DFT();
//		System.out.println(g.isCyclic());
//		System.out.println(g.isConnected());
//		System.out.println(g.isTree());
//		System.out.println(g.getCC());
//		System.out.println(g.isBipartite());
	}

}
