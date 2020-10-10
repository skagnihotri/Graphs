package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import heap.HeapGeneric;

public class Graph {

	private int numv;
	private int[][] matrix;

	public Graph(int v) {

		this.numv = v;
		matrix = new int[numv + 1][numv + 1];
	}

	public void addedge(int u, int v, int cost) {
		matrix[u][v] = cost;
		matrix[v][u] = cost;
	}

	public int removeedge(int u, int v) {
		int temp = matrix[u][v];
		matrix[u][v] = 0;
		matrix[v][u] = 0;
		return temp;
	}

	public int numEdge() {
		int count = 0;
		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if (matrix[i][j] != 0)
					count++;
			}
		}
		return count / 2;
	}

	// in numEdge loop of j can be started from i then there is no need to divide
	// count by 2;
	public boolean containEdge(int u, int v) {
		return matrix[u][v] != 0;
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 1; i < matrix.length; i++) {
			str += i + "->";
			for (int j = 1; j < matrix[0].length; j++) {
				if (matrix[i][j] != 0) {
					str += " " + j + "[" + matrix[i][j] + "] ,";
				}
			}
			str += "\n";
		}
		return str;
	}

	public boolean haspath(int src, int dist, HashMap<Integer, Boolean> visited) {
		visited.put(src, true);
		if (containEdge(src, dist)) // direct edge
			return true;
		/*
		 * if(Src==dst) return true;
		 */
		// source neighbours
		for (int nbr = 1; nbr < matrix[0].length; nbr++) {
			if (matrix[src][nbr] != 0 && !visited.containsKey(nbr)) {
				boolean ans = haspath(nbr, dist, visited);
				if (ans)
					return true;
			}
		}
		return false;
	}

	public void printallpaths(int src, int dist, HashMap<Integer, Boolean> visited, String ans) {

		visited.put(src, true);
		if (src == dist) // direct edge
		{
			System.out.println(ans + dist);
			visited.remove(dist);
			return;
		}
		// visited.put(src, true);
		for (int nbr = 1; nbr < matrix[0].length; nbr++) {
			if (matrix[src][nbr] != 0 && !visited.containsKey(nbr)) {
				printallpaths(nbr, dist, visited, ans + src);
				// visited.remove(nbr);
			}
		}
		visited.remove(src);
	}

	private class pair {
		int vname;
		String psf;
		String color;

		public pair(int vname, String psf) {

			this.vname = vname;
			this.psf = psf;
		}

		public pair(int vname, String psf, String color) {

			this.vname = vname;
			this.psf = psf;
			this.color = color;
		}

	}

	public boolean BFS(int src, int dst) {
		HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		LinkedList<pair> queue = new LinkedList<>();
		// put the src pair in queue
		pair sp = new pair(src, src + "");
		queue.addLast(sp);
		// work till the queue is empty
		while (!queue.isEmpty()) {
			// remove the pair
			pair rp = queue.removeFirst();
			// ignore the second value
			if (visited.containsKey(rp.vname)) {
				continue;
			}
			// put in visited
			visited.put(rp.vname, true);
			// equal
			if (rp.vname == dst) {
				System.out.println(rp.psf);
				return true;
			}
			// nbrs
			for (int nbr = 1; nbr < matrix[0].length; nbr++) {
				if (matrix[rp.vname][nbr] != 0 && !visited.containsKey(nbr)) {
					pair np = new pair(nbr, rp.psf + nbr);
					queue.addLast(np);
				}
			}
		}
		return false;
	}

	public boolean DFS(int src, int dst) {
		HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		LinkedList<pair> stack = new LinkedList<>();
		// put the src pair in queue
		pair sp = new pair(src, src + "");
		stack.addFirst(sp);
		// work till the queue is empty
		while (!stack.isEmpty()) {
			// remove the pair
			pair rp = stack.removeFirst();
			// ignore the second value
			if (visited.containsKey(rp.vname)) {
				continue;
			}
			// put in visited
			visited.put(rp.vname, true);
			// equal
			if (rp.vname == dst) {
				System.out.println(rp.psf);
				return true;
			}
			// nbrs
			for (int nbr = 1; nbr < matrix[0].length; nbr++) {
				if (matrix[rp.vname][nbr] != 0 && !visited.containsKey(nbr)) {
					pair np = new pair(nbr, rp.psf + nbr);
					stack.addFirst(np);
				}
			}
		}
		return false;
	}

	public void BFT() {
		HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		LinkedList<pair> queue = new LinkedList<>();
		// put the src pair in queue
		for (int src = 1; src < matrix[0].length; src++) {
			if (visited.containsKey(src)) {
				continue;
			}
			pair sp = new pair(src, src + "");
			queue.addLast(sp);
			// work till the queue is empty
			while (!queue.isEmpty()) {
				// remove the pair
				pair rp = queue.removeFirst();
				// ignore the second value
				if (visited.containsKey(rp.vname)) {
					continue;
				}
				// put in visited
				visited.put(rp.vname, true);
				// equal
//			if (rp.vname == dst) {
//				System.out.println(rp.psf);
//				return true;
//			}
				// print
				System.out.println(rp.vname + " via " + rp.psf);
				// nbrs
				for (int nbr = 1; nbr < matrix[0].length; nbr++) {
					if (matrix[rp.vname][nbr] != 0 && !visited.containsKey(nbr)) {
						pair np = new pair(nbr, rp.psf + nbr);
						queue.addLast(np);
					}
				}
			}
		}

	}

	public void DFT() {
		HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		LinkedList<pair> stack = new LinkedList<>();
		// put the src pair in queue
		for (int src = 1; src < matrix[0].length; src++) {
			if (visited.containsKey(src)) {
				continue;
			}
			pair sp = new pair(src, src + "");
			stack.addFirst(sp);
			// work till the queue is empty
			while (!stack.isEmpty()) {
				// remove the pair
				pair rp = stack.removeFirst();
				// ignore the second value
				if (visited.containsKey(rp.vname)) {
					continue;
				}
				// put in visited
				visited.put(rp.vname, true);
				// equal
//			if (rp.vname == dst) {
//				System.out.println(rp.psf);
//				return true;
//			}
				// print
				System.out.println(rp.vname + " via " + rp.psf);
				// nbrs
				for (int nbr = 1; nbr < matrix[0].length; nbr++) {
					if (matrix[rp.vname][nbr] != 0 && !visited.containsKey(nbr)) {
						pair np = new pair(nbr, rp.psf + nbr);
						stack.addFirst(np);
					}
				}
			}
		}

	}

	public boolean isCyclic() {
		HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		LinkedList<pair> queue = new LinkedList<>();
		// put the src pair in queue
		for (int src = 1; src < matrix[0].length; src++) {
			if (visited.containsKey(src)) {
				continue;
			}
			pair sp = new pair(src, src + "");
			queue.addLast(sp);
			// work till the queue is empty
			while (!queue.isEmpty()) {
				// remove the pair
				pair rp = queue.removeFirst();
				// ignore the second value
				if (visited.containsKey(rp.vname)) {

					return true;
				}
				// put in visited
				visited.put(rp.vname, true);
				// equal
//			if (rp.vname == dst) {
//				System.out.println(rp.psf);
//				return true;
//			}
				// print
//				System.out.println(rp.vname + " via " + rp.psf);
				// nbrs
				for (int nbr = 1; nbr < matrix[0].length; nbr++) {
					if (matrix[rp.vname][nbr] != 0 && !visited.containsKey(nbr)) {
						pair np = new pair(nbr, rp.psf + nbr);
						queue.addLast(np);
					}
				}
			}
		}

		return false;
	}

	public boolean isConnected() {
		HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		LinkedList<pair> queue = new LinkedList<>();
		int flag = 0;
		// put the src pair in queue
		for (int src = 1; src < matrix[0].length; src++) {
			if (visited.containsKey(src)) {
				continue;
			}
			flag++;
			pair sp = new pair(src, src + "");
			queue.addLast(sp);
			// work till the queue is empty
			while (!queue.isEmpty()) {
				// remove the pair
				pair rp = queue.removeFirst();
				// ignore the second value
				if (visited.containsKey(rp.vname)) {
					continue;
				}
				// put in visited
				visited.put(rp.vname, true);
				// print
//				System.out.println(rp.vname + " via " + rp.psf);
				// nbrs
				for (int nbr = 1; nbr < matrix[0].length; nbr++) {
					if (matrix[rp.vname][nbr] != 0 && !visited.containsKey(nbr)) {
						pair np = new pair(nbr, rp.psf + nbr);
						queue.addLast(np);
					}
				}
			}
		}
		if (flag == 1)
			return true;
		else
			return false;
	}

	public boolean isTree() {
		return !isCyclic() && isConnected();

	}

	public ArrayList<ArrayList<Integer>> getCC() {
		ArrayList<ArrayList<Integer>> tres = new ArrayList<ArrayList<Integer>>();
		HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		LinkedList<pair> queue = new LinkedList<>();
		// put the src pair in queue
		for (int src = 1; src < matrix[0].length; src++) {
			if (visited.containsKey(src)) {
				continue;
			}
			ArrayList<Integer> comp = new ArrayList<Integer>();
			pair sp = new pair(src, src + "");
			queue.addLast(sp);
			// work till the queue is empty
			while (!queue.isEmpty()) {
				// remove the pair
				pair rp = queue.removeFirst();
				// ignore the second value
				if (visited.containsKey(rp.vname)) {
					continue;
				}
				// put in visited
				visited.put(rp.vname, true);

				// add in component array list
				comp.add(rp.vname);
				// nbrs
				for (int nbr = 1; nbr < matrix[0].length; nbr++) {
					if (matrix[rp.vname][nbr] != 0 && !visited.containsKey(nbr)) {
						pair np = new pair(nbr, rp.psf + nbr);
						queue.addLast(np);
					}
				}
			}
			tres.add(comp);
		} // for src
		return tres;
	}

	public boolean isBipartite() {
//		if (isTree())
//			return true;
		HashMap<Integer, String> visited = new HashMap<Integer, String>();
		LinkedList<pair> queue = new LinkedList<>();
		// put the src pair in queue
		for (int src = 1; src < matrix[0].length; src++) {
			if (visited.containsKey(src)) {
				continue;
			}
			pair sp = new pair(src, src + "", "R");
			queue.addLast(sp);
			// work till the queue is empty
			while (!queue.isEmpty()) {
				// remove the pair
				pair rp = queue.removeFirst();
				// ignore the second value

				if (visited.containsKey(rp.vname)) {
					String cc = visited.get(rp.vname);
					String nc = rp.color;
					if (!cc.equals(nc)) {
						return false;
					}

					continue;
				}
				// put in visited
				visited.put(rp.vname, rp.color);

				// nbrs
				for (int nbr = 1; nbr < matrix[0].length; nbr++) {
					if (matrix[rp.vname][nbr] != 0 && !visited.containsKey(nbr)) {
						String nc = rp.color.equals("R") ? "G" : "R";
						pair np = new pair(nbr, rp.psf + nbr, nc);
						queue.addLast(np);
					}
				}
			}

		}
		return true;

	}

	private class primspair implements Comparable<primspair> {
		int vname;
		int acqname;
		int cost;

		public primspair(int vname, int acqname, int cost) {
			this.vname = vname;
			this.acqname = acqname;
			this.cost = cost;
		}

		@Override
		public int compareTo(primspair o) {
			return o.cost - this.cost;
		}

	}

	public Graph Prims() {
		Graph mst = new Graph(matrix.length - 1);
		HeapGeneric<primspair> heap = new HeapGeneric<>();
		HashMap<Integer, primspair> map = new HashMap<>();
		for (int i = 0; i < matrix.length; i++) {
			primspair np = new primspair(i, 0, Integer.MAX_VALUE);
			heap.add(np);
			map.put(i, np);
		}
		return mst;
	}
}
