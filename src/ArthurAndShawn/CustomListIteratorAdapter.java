package ArthurAndShawn;

import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class CustomListIteratorAdapter {
    private final ListIterator<String> iterator;

    public CustomListIteratorAdapter(List<String> list) {
        this.iterator = list.listIterator();
    }

    public boolean myHasNext() {
        return iterator.hasNext();
    }

    public boolean myHasPrevious() {
        return iterator.hasPrevious();
    }

    public String myNext() {
        if (!myHasNext()) {
            throw new NoSuchElementException();
        }
        return iterator.next();
    }

    public String myPrevious() {
        if (!myHasPrevious()) {
            throw new NoSuchElementException();
        }
        return iterator.previous();
    }

    public void myAdd(String e) {
        iterator.add(e);
    }
}
