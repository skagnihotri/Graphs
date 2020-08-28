#include <bits/stdc++.h>
using namespace std;

class Graph {
	unordered_map<string, list<string>> adjlist;
public:
	Graph() {
		this->adjlist.clear();
	}

	void add(string x, string y) {
		adjlist[x].push_back(y);
	}

	void display() {
		cout << "Graph :-\n";

		for (auto node : adjlist) {
			cout << node.first << " --> ";
			for (auto nbr : node.second) {
				cout << nbr << ", ";
			}
			cout << '\n';
		}
	}

	void TopologicalSort_dfs_helper(string node, unordered_map<string, bool> &visited, list<string> &ordering) {

		visited[node] = true;

		for (auto nbr : adjlist[node]) {
			if (!visited.count(nbr)) {
				TopologicalSort_dfs_helper(nbr, visited, ordering);
			}
		}

		ordering.push_front(node);
		return;
	}

	// Topological sort using dfs
	list<string> TopologicalSort_dfs() {
		list<string> ordering;
		unordered_map<string, bool> visited;

		for (auto node : adjlist) {
			if (!visited.count(node.first)) {
				TopologicalSort_dfs_helper(node.first, visited, ordering);
			}
		}

		return ordering;
	}
};


int main() {

#ifndef ONLINE_JUDGE
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
#endif

	Graph g;
	int edgeList;
	cin >> edgeList;

	while (edgeList--) {
		string x, y;
		cin >> x >> y;
		g.add(x, y);
	}

	g.display();

	list<string> Tsort = g.TopologicalSort_dfs();
	cout << "Topological Sort :-\n";
	for (auto node : Tsort) {
		cout << node << ", ";
	}
	cout << '\n';


	return 0;
}