package sucursales.presentation.acercade;

import java.util.Observer;

public class Model extends java.util.Observable{

    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        commit();
    }

    public Model() {

    }

    public void commit(){
        setChanged();
        notifyObservers(null);
    }
}
