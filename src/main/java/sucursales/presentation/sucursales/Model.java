package sucursales.presentation.sucursales;

import sucursales.logic.Sucursal;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Model extends java.util.Observable {

    List<Sucursal> sucursales;

    Sucursal selected;

    public Model(){
       this.setSucursales(new ArrayList<Sucursal>());
    }

    public void setSucursales(List<Sucursal> sucursales){
        this.sucursales = sucursales;
    }

    public List<Sucursal> getSucursales(){return sucursales;}

    public Sucursal getSelected() {
        return selected;
    }

    public void setSelected(Sucursal selected) {
        this.selected = selected;
    }

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        commit();
    }

    public void commit() {
        setChanged();
        notifyObservers(null);
    }
}
