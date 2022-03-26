import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra{
    // The NodeDistance class will help with how nodes(vertices) and their distance from the main node is handled.
    private class NodeDistance implements Comparator<NodeDistance> {
        public int distance; //Distance to adjacent nodes
        public int node; //Adjacent node

        public NodeDistance(int distance, int node) {
            this.distance = distance;
            this.node = node;
        }

        @Override
        public int compare(NodeDistance node1, NodeDistance node2){
            if (node1.distance < node2.distance)
                return 1;
            if (node1.distance > node2.distance)
                return -1;
            return 0;
        }

        public boolean equals(Object obj){
            if(!(obj instanceof NodeDistance)){
                return false;
            }
            NodeDistance nd = (NodeDistance) obj;
            return this.node == nd.node;
        }
    }

    public static Vertex[] ShortestPath(Graph G, Vertex start, Vertex end){
        Map<Vertex, Integer> distance = new Map<Vertex, Integer>();
        distance.put(start, 0); // Starting node gets weight of zero
        for(Vertex v : G.vertices()){
            distance.put(v, Integer.MAX_VALUE); // All other nodes get max weight
        }

        PriorityQueue pq = new PriorityQueue();
        // Initialize PriorityQueue with nodes and their distance from center
        for(Entry<Vertex, Integer> entry : distance.entrySet()){
            pq.add(new NodeDistance(entry.value, entry.key));
        }

        while(!pq.isEmpty()){
            newVertex = pq.poll();

            for(Edge edge : G.outDegree(newVertex)){
                Vertex oppositeVertex;
                // Need to get vertex opposite of the current edge
                if(edge.getEndpoints()[0] == vertex){
                    oppositeVertex = edge.getEndpoints()[1];
                }
                else{
                    oppositeVertex = edge.getEndpoints()[0];
                }
                // Edge relaxation.
                if (distance.get(newVertex.node).intValue() + edge.getElement() < distance.get(oppositeVertex).intValue()){
                    distance.replace(oppositeVertex, (distance.get(newVertex.node).intValue() + edge.getElement()));
                    int removedValue = pq.remove(oppositeVertex);
                }
            }

        }
        Vertex[] finalResults = new Vertex[distance.size()];
        int counter = 0;
        // Return vertex in order they are away from starting vertex.
        for(Vertex vertex : distance.keySet()){
            finalResults[counter++] = vertex;
        }
        return finalResults;
    }



}