package sucursales.data;
import sucursales.logic.Empleado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDao {
    Database db;


    public EmpleadoDao() {
        db = Database.instance();
    }
    public void create(Empleado e) throws Exception {
        String sql = "insert into " +
                "Empleado " +
                "(cedula, nombre, telefono, salarioBase, sucursal) " +
                "values(?,?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCedula());
        stm.setString(2, e.getNombre());
        stm.setString(3, e.getTelefono());
        stm.setFloat(4, e.getSalarioBase());
        stm.setString(5, e.getSucursal().getCodigo());
        db.executeUpdate(stm);
    }
    public Empleado read(String codigo) throws Exception {
        /*String sql = "select " +
                "* " +
                "from  Empleado s " +
                "where s.cedula=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, codigo);
        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            return fromEmpleado(rs, "s");
        } else {
            throw new Exception("EMPLEADO NO EXISTE");
        }*/
        String sql = "select " +
                "* " +
                "from  Empleado e " +
                " inner join Sucursal s on e.sucursal=s.codigo "+
                "where s.cedula=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, codigo);
        ResultSet rs = db.executeQuery(stm);
        Empleado e;
        SucursalDao sucursalDao = new SucursalDao();
        if (rs.next()) {
            e=fromEmpleado(rs, "e");
            e.setSucursal(sucursalDao.from(rs,"s"));
            return e;
        } else {
            throw new Exception("EMPLEADO NO EXISTE");
        }
    }
    public void update(Empleado e) throws Exception {
        String sql = "update " +
                "Empleado " +
                "set nombre=?, telefono=?, salarioBase=?, sucursal=? " +
                "where cedula=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getNombre());
        stm.setString(2, e.getTelefono());
        stm.setFloat(3, e.getSalarioBase());
        stm.setString(4, e.getSucursal().getCodigo());
        stm.setString(5, e.getCedula());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("EMPLEADO NO EXISTE");
        }
    }
    public void delete(Empleado e) throws Exception {
        String sql = "delete " +
                "from Empleado " +
                "where cedula=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCedula());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("EMPLEADO NO EXISTE");
        }
    }
    public List<Empleado> findByNombre(String nombre) throws Exception {
        SucursalDao sucursalDao = new SucursalDao();
        List<Empleado> resultado = new ArrayList<Empleado>();
        String sql = "select * " +
                "from " +
                "empleado e " +
                " inner join Sucursal s on e.sucursal=s.codigo "+
                "where e.nombre like ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, "%" + nombre + "%");
        ResultSet rs = db.executeQuery(stm);
        Empleado empleado;
        while (rs.next()) {
            empleado=fromEmpleado(rs, "e");
            empleado.setSucursal(sucursalDao.from(rs, "s"));
            resultado.add(empleado);
        }
        return resultado;
    }
    public Empleado fromEmpleado(ResultSet rs, String alias) throws Exception {
        //SucursalDao sucursalDao = new SucursalDao();
        Empleado e = new Empleado();
        e.setCedula(rs.getString(alias + ".cedula"));
        e.setNombre(rs.getString(alias + ".nombre"));
        e.setTelefono(rs.getString(alias + ".telefono"));
        e.setSalarioBase(rs.getFloat(alias + ".salario"));
        //e.setSucursal(sucursalDao.read(rs.getString(alias + ".sucursal")));
        //TODO: e.setSucursal(rs.getString(alias + ".sucursal"));
        return e;
    }
}