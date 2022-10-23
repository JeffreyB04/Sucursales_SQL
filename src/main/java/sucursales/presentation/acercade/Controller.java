package sucursales.presentation.acercade;

import sucursales.Application;

import java.awt.event.ActionEvent;

public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setModel(model);
        view.setController(this);
    }

    public void show(){
        Application.window.setContentPane(view.getPanel());
    }

    // ActionListener Interface
    public void actionPerformed(ActionEvent e) {
        model.commit();
    }


}
