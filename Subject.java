package ArthurAndShawn;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private final List<Observer> observers = new ArrayList<>();

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
