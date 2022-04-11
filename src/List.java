
import java.lang.Iterable;

public interface List<E> extends Iterable<E>{
    int size();
    boolean isEmpty();
    E get(int i) throws IndexOutOfBoundsException;
    E set(int i, E e)throws IndexOutOfBoundsException;
    void add(int i, E e)throws IndexOutOfBoundsException;
    E remove(int i)throws IndexOutOfBoundsException;
}
