package sucursales.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlIDREF;

@XmlAccessorType (XmlAccessType.FIELD)
public class Empleado {
    String cedula;
    String nombre;
    String telefono;
    float salarioBase;
    @XmlIDREF
    Sucursal sucursal;


    public Empleado(String cedula, String nombre, String telefono, float salario, Sucursal sucursal) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.salarioBase = salario;
        this.sucursal = sucursal;

    }

    public Empleado() {
        this.cedula = "";
        this.nombre = "";
        this.telefono = "";
        this.salarioBase = 0.0f;
        this.sucursal = new Sucursal();
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public float getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(float salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

}
