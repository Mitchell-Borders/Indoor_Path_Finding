
public interface Edge<E> {
        public E getElement();
        public Vertex[] getEndpoints();
        public void setPosition(Position<Edge<E>> p);
        public Position<Edge<E>> getPosition();
}
