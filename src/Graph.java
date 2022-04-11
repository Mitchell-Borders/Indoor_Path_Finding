
public interface Graph<V,E> {
    int numVertices();
    Iterable vertices();
    int numEdges();
    Iterable<Edge<E>> edges();
    Edge<E> getEdge(Vertex<V> u, Vertex<V> v);
    Vertex<V>[] endVertices(Edge<E> e);
    Vertex<V> opposite(Vertex<V> v, Edge<E> e);
    int outDegree(Vertex<V> v);
    int inDegree(Vertex<V> v);
    Iterable outgoingEdges(Vertex<V> v);
    Iterable incomingEdges(Vertex<V> v);
    Vertex<V> insertVertex(V v);
    Edge<E> insertEdge(Vertex<V> u,Vertex<V> v,E e);
    void removeVertex(Vertex<V> v);
    void removeEdge(Edge<E> e);
}
