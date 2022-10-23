package sucursales.presentation.acercade;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

    Controller controller;
    Model model;
    private JPanel panel;
    private JLabel telInfoFld;
    private JLabel imageLogo;
    private JLabel siselbl;

    public JPanel getPanel() {
        return panel;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        siselbl.setFont(new Font("Serif", Font.PLAIN, 50));
        imageLogo.setText("");
        imageLogo.setIcon(new ImageIcon("src/main/java/sucursales/image/SISE.jpeg"));
    }
}
