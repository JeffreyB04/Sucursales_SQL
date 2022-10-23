package sucursales.presentation.empleado;

import sucursales.logic.Empleado;
import sucursales.logic.Sucursal;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Model extends java.util.Observable{
    Empleado current;
    int modo;
    List<Sucursal> sucursales;


    public Model() {
        sucursales = new ArrayList<>();
    }
    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }

    public Empleado getCurrent() {
        return current;
    }

    public void setCurrent(Empleado current) {
        this.current = current;
    }


    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        this.commit();
    }

    public void commit(){
        setChanged();
        notifyObservers(null);
    }


}
