package sucursales.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;

@XmlAccessorType(XmlAccessType.FIELD)
public class Sucursal {
    @XmlID
    String codigo;
    String referencia;
    String direccion;
    float zonaje;
    int ubicacionX;
    int ubicacionY;

    public Sucursal(String codigo, String referencia, String direccion, float zonaje, int ubicacionX, int ubicacionY) {
        this.codigo = codigo;
        this.referencia = referencia;
        this.direccion = direccion;
        this.zonaje = zonaje;
        this.ubicacionX = ubicacionX;
        this.ubicacionY = ubicacionY;
    }

    public Sucursal(){this("","","", 0.0f,0,0);}

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public float getZonaje() {
        return zonaje;
    }

    public void setZonaje(float zonaje) {
        this.zonaje = zonaje;
    }

    public int getUbicacionX() {
        return ubicacionX;
    }

    public int setUbicacionX(int ubicacionX) {
        this.ubicacionX = ubicacionX;
        return ubicacionX;
    }

    public int getUbicacionY() {
        return ubicacionY;
    }

    public void setUbicacionY(int ubicacionY) {
        this.ubicacionY = ubicacionY;
    }
}
