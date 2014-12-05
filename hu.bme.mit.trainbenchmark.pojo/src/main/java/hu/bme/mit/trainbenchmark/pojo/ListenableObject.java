package hu.bme.mit.trainbenchmark.pojo;

public abstract class ListenableObject {

    private transient Graph.ChangeNotifier changeNotifier;

    Graph.ChangeNotifier getChangeNotifier() {
        return changeNotifier;
    }

    void setChangeNotifier(Graph.ChangeNotifier changeNotifier) {
        this.changeNotifier = changeNotifier;
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        firePropertyChange(this, propertyName, oldValue, newValue);
    }

    protected void firePropertyChange(Object source, String propertyName, Object oldValue, Object newValue) {
        if (changeNotifier != null) {
            changeNotifier.firePropertyChange(source, propertyName, oldValue, newValue);
        }
    }
}
