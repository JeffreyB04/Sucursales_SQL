package sucursales.logic;

import sucursales.data.EmpleadoDao;
import sucursales.data.SucursalDao;

import java.util.List;

public class Service {
    private static Service theInstance;

    public static Service instance() {
        if (theInstance == null) {
            theInstance = new Service();
        }
        return theInstance;
    }

    //private Data data;

    private Service() {
        //data = new Data();
        sucursalDao= new SucursalDao();
        empleadoDao= new EmpleadoDao();
/*        try {
            data = XmlPersister.instance().load();
        } catch (Exception e) {
            data = new Data();
        }
 */
    }

    public List<Empleado> empleadosSearch(String nombre) throws Exception {
        return  empleadoDao.findByNombre(nombre);
    }

    public List<Sucursal> sucursalesSearch(String filtro) throws Exception {
        return  sucursalDao.findByReferencia(filtro);
    }


   public Sucursal sucursalGet(String codigo) throws Exception{
       return sucursalDao.read(codigo);
   }
    public Empleado empleadoGet(String cedula) throws Exception{
        return empleadoDao.read(cedula);
    }


   public void sucursalAdd(Sucursal sucursal) throws Exception{
       sucursalDao.create(sucursal);
   }
    public void empleadoAdd(Empleado empleado) throws Exception{
        empleadoDao.create(empleado);
    }


    public void sucursalUpdate(Sucursal sucursal) throws Exception{
        sucursalDao.update(sucursal);
    }
    public void empleadoUpdate(Empleado empleado) throws Exception{
        empleadoDao.update(empleado);
    }

    public void sucursalDelete(Sucursal sucursal) throws Exception {
        sucursalDao.delete(sucursal);
    }
    public void empleadoDelete(Empleado empleado) throws Exception {
        empleadoDao.delete(empleado);
    }

    private SucursalDao sucursalDao;
    private EmpleadoDao empleadoDao;



   /* public List<Empleado> empleadosSearch(String filtro) {
        return data.getEmpleados().stream()
                .filter(e -> e.getNombre().contains(filtro))
                .sorted(Comparator.comparing(e -> e.getCedula()))
                .collect(Collectors.toList());
    }

      public List<Sucursal> sucursalesSearch(String filtro) {
        return data.getSucursales().stream().filter(s -> s.getReferencia().contains(filtro)).collect(Collectors.toList());
    }
        public Empleado empleadoGet(String cedula) throws Exception {
        Empleado result = data.getEmpleados().stream().filter(e -> e.getCedula().equals(cedula)).findFirst().orElse(null);
        if (result != null) return result;
        else throw new Exception("Empleado no existe");
    }

    public Sucursal sucursalGet(String codigo) throws Exception {
        Sucursal result = data.getSucursales().stream().filter(e -> e.getCodigo().equals(codigo)).findFirst().orElse(null);
        if (result != null) return result;
        else throw new Exception("Sucursal no existe");
    }
    public void empleadoAdd(Empleado empleado) throws Exception {
        Empleado result = data.getEmpleados().stream().filter(e -> e.getCedula().equals(empleado.getCedula())).findFirst().orElse(null);
        if (result == null) data.getEmpleados().add(empleado);
        else throw new Exception("Empleado ya existe");
    }

    public void sucursalAdd(Sucursal suc) {
        List<Sucursal> result = data.getSucursales().stream().filter(s -> s.getCodigo().equals(suc.getCodigo())).collect(Collectors.toList());
        if (result.size() == 0)
            data.getSucursales().add(suc);

    }
    public void empleadoUpdate(Empleado empleado) throws Exception {
        Empleado result;
        try {
            result = this.empleadoGet(empleado.cedula);
            data.getEmpleados().remove(result);
            data.getEmpleados().add(empleado);
        } catch (Exception e) {
            throw new Exception("Empleado no existe");
        }
    }

    public void sucursalUpdate(Sucursal sucursal) throws Exception {
        Sucursal result;
        try {
            result = this.sucursalGet(sucursal.codigo);
            data.getSucursales().remove(result);
            data.getSucursales().add(sucursal);
        } catch (Exception e) {
            throw new Exception("Sucursal no existe");
        }
    }

    public void empleadoDelete(Empleado empleado) throws Exception {
        Empleado result = data.getEmpleados().stream().filter(e -> e.getCedula().equals(empleado.getCedula())).findFirst().orElse(null);
        if (result != null) data.getEmpleados().remove(empleado);
        else throw new Exception("Empleado ya existe");
    }

     public void sucursalDelete(Sucursal sucursal) throws Exception {
        Sucursal result = data.getSucursales().stream().filter(e -> e.getCodigo().equals(sucursal.getCodigo())).findFirst().orElse(null);
        if (result != null) data.getSucursales().remove(sucursal);
        else throw new Exception("Sucursal ya existe");
    }

    public void store() {
        try {
            XmlPersister.instance().store(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    */

}