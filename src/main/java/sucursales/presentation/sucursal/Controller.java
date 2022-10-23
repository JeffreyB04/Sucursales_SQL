package sucursales.presentation.sucursal;

import sucursales.Application;
import sucursales.logic.Service;
import sucursales.logic.Sucursal;


import javax.swing.*;
import java.awt.*;

public class Controller {

    View view;
    Model model;

    public Controller(View view, Model model) {
        model.setCurrent(new Sucursal());

        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void preAgregar(){
        model.setModo(Application.MODO_AGREGAR);
        model.setCurrent(new Sucursal());
        view.agregaSucursal();
        model.commit();
        this.show();
    }

    JDialog dialog;
    public void show(){
        dialog = new JDialog(Application.window,"Sucursal", true);
        dialog.setSize(800,600);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setContentPane(view.getPanel());
        Point location = Application.window.getLocation();
        dialog.setLocation( location.x+400,location.y+100);
        dialog.setVisible(true);
    }

    public void hide(){
        dialog.dispose();
    }

    public void guardar(Sucursal s) throws Exception {
        switch (model.getModo()) {
            case Application.MODO_AGREGAR:
                Service.instance().sucursalAdd(s);
                model.setCurrent(new Sucursal());
                break;
            case Application.MODO_EDITAR:
                Service.instance().sucursalUpdate(s);
                model.setCurrent(s);
                break;
        }
        Application.sucursalesController.buscar("");
        model.commit();
    }

    public void editar(Sucursal s){
        model.setModo(Application.MODO_EDITAR);
        view.editarSucursal(s);
        model.setCurrent(s);
        model.commit();
        this.show();
    }
}
