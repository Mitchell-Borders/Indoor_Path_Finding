
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AdjacencyMapGraph<V, E> implements Iterable<Edge<E>>, Graph<V,E> {

    @Override
    public Iterator<Edge<E>> iterator() {
        return (Iterator) new AdjacencyMapIterator();
    }

    // iterator for AdjacencyMapGraph, currently iterates through edges list
    private class AdjacencyMapIterator implements Iterator<E> {

        Iterator e = edges.iterator();

        @Override
        public boolean hasNext() {
            return e.hasNext();
        }

        @Override
        public E next() throws NoSuchElementException {
            return (E) e.next();
        }
    }
    
    private boolean isDirected;
    private PositionalList<Vertex<V>> vertices = new LinkedPositionalList<>();
    private PositionalList<Edge<E>> edges = new LinkedPositionalList<>();

    public AdjacencyMapGraph() {
        isDirected = false;
    }
    
    public AdjacencyMapGraph(boolean directed) {
        isDirected = directed;
    }

    @Override
    public int numVertices() {
        return vertices.size();
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
        return vertices;
    }

    @Override
    public int numEdges() {
        return edges.size();
    }

    @Override
    public Iterable<Edge<E>> edges() {
        return edges;
    }

    @Override
    public int outDegree(Vertex<V> v) {
        InnerVertex<V> vert = validate(v);
        return vert.getOutgoing().size();
    }

    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> v) {
        InnerVertex<V> vert = validate(v);
        return vert.getOutgoing().values(); // edges are the values in the adjacency map
    }

    @Override
    public int inDegree(Vertex<V> v) {
        InnerVertex<V> vert = validate(v);
        return vert.getIncoming().size();
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> v) {
        InnerVertex<V> vert = validate(v);
        return vert.getIncoming().values(); // edges are the values in the adjacency map
    }

    @Override
    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) {
        InnerVertex<V> origin = validate(u);
        return origin.getOutgoing().get(v); // will be null if no edge from u to v
    }

    @Override
    public Vertex<V>[] endVertices(Edge<E> e) {
        InnerEdge<E> edge = validate(e);
        return edge.getEndpoints();
    }

    private InnerVertex<V> validate(Vertex<V> v) throws IllegalArgumentException {
        if (!(v instanceof InnerVertex)) {
            throw new IllegalArgumentException("Invalid v");
        }
        InnerVertex<V> vertex = (InnerVertex<V>) v;
        if (vertex == null) {
            throw new IllegalArgumentException("vertex is null");
        }
        return vertex;
    }

    private InnerEdge<E> validate(Edge<E> e) throws IllegalArgumentException {
        if (!(e instanceof InnerEdge)) {
            throw new IllegalArgumentException("Invalid e");
        }
        InnerEdge<E> edge = (InnerEdge<E>) e;
        if (edge == null) {
            throw new IllegalArgumentException("edge is null");
        }
        return edge;
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e)
            throws IllegalArgumentException {
        InnerEdge<E> edge = validate(e);
        Vertex<V>[] endpoints = edge.getEndpoints();
        if (endpoints[0] == v) {
            return endpoints[1];
        } else if (endpoints[1] == v) {
            return endpoints[0];
        } else {
            throw new IllegalArgumentException("v is not incident to this edge");
        }
    }

    @Override
    public Vertex<V> insertVertex(V element) {
        InnerVertex<V> v = new InnerVertex<>(element, isDirected);
        v.setPosition(vertices.addLast(v));
        return v;
    }
  
    public Vertex<V> getVertex(V element) {
        for (Vertex<V> v : vertices) {
            if (v != null && v.getElement() != null && v.getElement().equals(element)) {
                return v;
            }
        }
        return null;
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) throws IllegalArgumentException {
        if (getEdge(u, v) == null) {
            InnerEdge<E> e = new InnerEdge<>(u, v, element);
            e.setPosition(edges.addLast(e));
            InnerVertex<V> origin = validate(u);
            InnerVertex<V> dest = validate(v);
            origin.getOutgoing().put(v, e);
            dest.getIncoming().put(u, e);
            return e;
        } else {
            throw new IllegalArgumentException("Edge from u: " + u + " to v: " + v + " exists");
        }
    }

    @Override
    public void removeVertex(Vertex<V> v) {
        InnerVertex<V> vert = validate(v);
        // remove all incident edges from the graph
        for (Edge<E> e : vert.getOutgoing().values()) {
            removeEdge(e);
        }
        for (Edge<E> e : vert.getIncoming().values()) {
            removeEdge(e);
        }
        // remove this vertex from the list of vertices
        vertices.remove(vert.getPosition());
    }

    //remove edge method, FIX THIS METHOD
    @Override
    public void removeEdge(Edge<E> e) {
        InnerEdge<E> edge = validate(e);
        edges.remove(edge.getPosition());
    }

    private class InnerVertex<V> implements Vertex<V> {

        private V element;
        private Position<Vertex<V>> pos;
        private Map<Vertex<V>, Edge<E>> outgoing, incoming;

        public InnerVertex(V elem, boolean graphIsDirected) {
            element = elem;
            outgoing = new ProbeHashMap<Vertex<V>, Edge<E>>();
            if (graphIsDirected) {
                incoming = new ProbeHashMap<>();
            } else {
                incoming = outgoing;
            }
        }

        @Override
        public V getElement() {
            return element;
        }

        @Override
        public void setPosition(Position<Vertex<V>> p) {
            pos = p;
        }

        @Override
        public Position<Vertex<V>> getPosition() {
            return pos;
        }

        public Map<Vertex<V>, Edge<E>> getOutgoing() {
            return outgoing;
        }

        public Map<Vertex<V>, Edge<E>> getIncoming() {
            return incoming;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    } 
    
    private class InnerEdge<E> implements Edge<E> {

        private E element;
        private Position<Edge<E>> pos;
        private Vertex<V>[] endpoints;

        public InnerEdge(Vertex<V> u, Vertex<V> v, E elem) {
            element = elem;
            endpoints = (Vertex<V>[]) new Vertex[]{u, v};  // array of length 2
        }

        @Override
        public E getElement() {
            return element;
        }

        @Override
        public Vertex<V>[] getEndpoints() {
            return endpoints;
        }

        @Override
        public void setPosition(Position<Edge<E>> p) {
            pos = p;
        }

        @Override
        public Position<Edge<E>> getPosition() {
            return pos;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    } 
}
