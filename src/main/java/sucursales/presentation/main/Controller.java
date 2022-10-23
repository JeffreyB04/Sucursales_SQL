package sucursales.presentation.main;

import sucursales.Application;
import sucursales.logic.Service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 *
 * @author Escinf
 */
public class Controller implements ActionListener {
    Model model;
    View view;

    public Controller(View view, Model model) {
        this.model = model;
        this.view = view;
        view.setModel(model);
        view.setController(this);
    } 
    

    // ActionListener Interface    
    public void actionPerformed(ActionEvent e) {
        model.commit();
    }

    public void show() {
        Application.window.setContentPane(view.getPanel());
        Application.window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                //Service.instance().store();
            }
        });
    }
}
