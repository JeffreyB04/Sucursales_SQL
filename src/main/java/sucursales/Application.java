package sucursales;

import sucursales.presentation.empleados.Model;
import sucursales.presentation.empleados.View;
import sucursales.presentation.sucursales.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;



public class Application {
    public static void main(String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
        catch (Exception ex) {};

        //EMPLEADOS
        Model empleadosModel= new Model();
        View empleadosView = new View();
        empleadosController = new sucursales.presentation.empleados.Controller(empleadosView,empleadosModel);

        //EMPLEADO
        sucursales.presentation.empleado.Model empleadoModel= new sucursales.presentation.empleado.Model();
        sucursales.presentation.empleado.View empleadoView = new sucursales.presentation.empleado.View();
        empleadoController = new sucursales.presentation.empleado.Controller(empleadoView,empleadoModel);

        //MAIN
        sucursales.presentation.main.Model mainModel= new sucursales.presentation.main.Model();
        sucursales.presentation.main.View mainView = new sucursales.presentation.main.View();
        mainController = new sucursales.presentation.main.Controller(mainView, mainModel);

        //SUCURSALES
        sucursales.presentation.sucursales.Model sucursalesModel = new sucursales.presentation.sucursales.Model();
        sucursales.presentation.sucursales.View sucursalesView = new sucursales.presentation.sucursales.View();
        sucursalesController = new Controller(sucursalesView,sucursalesModel);

        //SUCURSALE
        sucursales.presentation.sucursal.Model sucursalModel = new sucursales.presentation.sucursal.Model();
        sucursales.presentation.sucursal.View sucursalView = new sucursales.presentation.sucursal.View();
        sucursalController = new sucursales.presentation.sucursal.Controller(sucursalView,sucursalModel);

        //ACERCADE
        sucursales.presentation.acercade.Model acercaDeModel = new sucursales.presentation.acercade.Model();
        sucursales.presentation.acercade.View acercaDeView = new sucursales.presentation.acercade.View();
        acercadeController = new sucursales.presentation.acercade.Controller(acercaDeView,acercaDeModel);



        mainView.getPanel().add("Empleados",empleadosView.getPanel());
        mainView.getPanel().add("Sucursales",sucursalesView.getPanel());
        mainView.getPanel().add("Acerca de ...",acercaDeView.getPanel());


        window = new JFrame();
        window.setSize(400,300);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("SISE: Sistema de Sucursales y Empleados");
        window.setVisible(true);
        mainController.show();


    }

    public static sucursales.presentation.empleados.Controller empleadosController;
    public static sucursales.presentation.main.Controller mainController;

    public static sucursales.presentation.empleado.Controller empleadoController;

    public static Controller sucursalesController;

    public static sucursales.presentation.acercade.Controller acercadeController;

    public static sucursales.presentation.sucursal.Controller sucursalController;



    public static JFrame window;

    public static  final int  MODO_AGREGAR=0;
    public static final int MODO_EDITAR=1;

    public static Border BORDER_ERROR = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);

}
