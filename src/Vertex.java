
public interface Vertex<V> {
        public abstract V getElement();
        public abstract void setPosition(Position<Vertex<V>> p);
        public abstract Position<Vertex<V>> getPosition();
}
