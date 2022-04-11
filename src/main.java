
public class main {
    public static void main(String[] args) {
        AdjacencyMapGraph Graph = new AdjacencyMapGraph();
        Graph.insertVertex("QBB");
        Graph.insertVertex("Sugihara");
        Graph.insertVertex("Memorial Union");
        Graph.insertEdge("QBB", "Sugihara", 10);

        Dijkstra.ShortestPath(Graph, start, end);

    }
}
