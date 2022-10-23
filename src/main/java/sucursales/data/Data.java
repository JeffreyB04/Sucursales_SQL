package sucursales.data;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
//crear en C un directorio prop y agregar el archivo database

public class Data {
   /* private List<Empleado> empleados;
    private List<Sucursal> sucursales;

    public Data() {
      empleados = new ArrayList<>();
        sucursales= new ArrayList<>();

        sucursales.add(new Sucursal("001","Liberia","Guanacaste, Liberia",1.0f,60,131));
        sucursales.add(new Sucursal("002","Golfito","Puntarenas",2.0f,374,370));
        sucursales.add(new Sucursal("003","Tortuguero","Limon",4.0f,358,132));

        empleados.add(new Empleado("111", "Franklin Chang","78972356",7500.0,sucursales.get(0)));
        empleados.add(new Empleado("222", "Sandra Cauffman","54986721",8200.0,sucursales.get(1)));
        empleados.add(new Empleado("333", "Ivan Vargas","4533246",7800.0,sucursales.get(2)));
    }
    public List<Empleado> getEmpleados() {
        return empleados;
    }
   public List<Sucursal> getSucursales() {
        return sucursales;
    }
    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
    /*public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    } */
}


