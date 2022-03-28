
import java.io.File;
import java.io.IOException;

public class TestClass {

    // just a class for testing stuff
    
    public static void main(String[] args) throws IOException {
        AdjacencyMapGraph<String, Integer> amg = PopulateGraph.fromCSV(new File("C:\\Users\\Audrey\\Desktop\\GraphCSV.csv"), false);
        System.out.println(((PositionalList)amg.vertices()).size());
        for(Edge e : amg){
            Vertex<String> v = amg.endVertices(e)[0];
            Vertex<String> v2 = amg.endVertices(e)[1];
            System.out.println("Distance outside between " + v + " and " + v2 +": " + amg.getEdge(v,v2)+"\n");            
        }
       
        System.out.println(amg.getEdge(amg.getVertex("MU"),amg.getVertex("Old Main")));
        System.out.println(((PositionalList)amg.vertices()).size());
    }
    
}
