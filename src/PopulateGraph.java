
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PopulateGraph {
    
    public static AdjacencyMapGraph<String, Integer> fromCSV(File file, boolean isDirected) {
        AdjacencyMapGraph<String, Integer> amg = new AdjacencyMapGraph<String, Integer>(isDirected);
        try{
            Scanner scan = new Scanner(file); // scan file
            Scanner s = new Scanner(scan.nextLine()); // scan each line
            s.useDelimiter(",");
            amg.insertEdge(amg.insertVertex(s.next()), amg.insertVertex(s.next()), s.nextInt()); // first entry
            while(scan.hasNext()){
                //each line from file in format: buildingOneName(as string),buildingTwoName(as string),distance(as integer) e.g. CIE,CME,0
                s = new Scanner(scan.nextLine());
                s.useDelimiter(",");
                String v1 = s.next(), v2 = s.next();
                Integer edge = s.nextInt();
                
                // getVertex() returns null if vertex is not already in list
                // if vertex not in AdjacencyMapGraph vertices list, V1 is a new vertex. Otherwise it refers to the same vertex that is already in vertices list
                Vertex V1 = (amg.getVertex(v1)==null)?amg.insertVertex(v1):amg.getVertex(v1); 
                Vertex V2 = (amg.getVertex(v2)==null)?amg.insertVertex(v2):amg.getVertex(v2);
                amg.insertEdge(V1, V2, edge);
            }
        }
        catch(FileNotFoundException e){System.out.println("File not found");}
        return amg;
    }
}
