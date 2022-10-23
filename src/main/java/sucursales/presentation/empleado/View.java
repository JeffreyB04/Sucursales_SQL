package sucursales.presentation.empleado;

import sucursales.Application;
import sucursales.logic.Empleado;
import sucursales.logic.Sucursal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    Controller controller;
    Model model;
    private JPanel panel;
    private JLabel cedulaLbl;
    private JLabel nombreLbl;
    private JTextField cedulaFld;
    private JTextField nombreFld;
    private JLabel telefonoLbl;
    private JTextField telefonoFld;
    private JLabel salarioLbl;
    private JButton guardarButton;
    private JButton cancelarButton;
    private JTextField salarioFld;
    private JLabel imagenFld;
    private JTextField sucursalFld;
    private JLabel sucursalLbl;

    Image mapa;
    Image iconoAzul;
    Image iconoRojo;

    JLabel icon;


    public View() {
        cargaMapa();

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Empleado n = take();
                    model.getCurrent().setCedula(n.getCedula());
                    model.getCurrent().setNombre(n.getNombre());
                    model.getCurrent().setTelefono(n.getTelefono());
                    model.getCurrent().setSalarioBase(n.getSalarioBase());
                    try {
                        controller.guardar(model.getCurrent());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.hide();
            }
        });
    }

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

    public JLabel getImagenFld() {
        return imagenFld;
    }


    @Override
    public void update(Observable updatedModel, Object parametros) {
        Empleado current = model.getCurrent();
        this.cedulaFld.setEnabled(model.getModo() == Application.MODO_AGREGAR);
        this.cedulaFld.setText(current.getCedula());
        nombreFld.setText(current.getNombre());
        telefonoFld.setText(current.getTelefono());
        salarioFld.setText(String.valueOf(current.getSalarioBase()));
        sucursalFld.disable();
        sucursalFld.setText(current.getSucursal().getReferencia());
        this.panel.validate();
        cargaIconos();
    }

    public Empleado take() {
        Empleado e = new Empleado();
        e.setCedula(cedulaFld.getText());
        e.setNombre(nombreFld.getText());
        e.setTelefono(telefonoFld.getText());
        e.setSalarioBase(Float.parseFloat(salarioFld.getText()));
        return e;
    }

    private boolean validate() {
        boolean valid = true;
        if (cedulaFld.getText().isEmpty()) {
            valid = false;
            cedulaLbl.setBorder(Application.BORDER_ERROR);
            cedulaLbl.setToolTipText("Id requerido");
        } else {
            cedulaLbl.setBorder(null);
            cedulaLbl.setToolTipText(null);
        }

        if (sucursalFld.getText().isEmpty()) {
            valid = false;
            sucursalLbl.setBorder(Application.BORDER_ERROR);
            sucursalLbl.setToolTipText("Sucursal requerido");
        } else {
            sucursalLbl.setBorder(null);
            sucursalLbl.setToolTipText(null);
        }

        if (nombreFld.getText().length() == 0) {
            valid = false;
            nombreLbl.setBorder(Application.BORDER_ERROR);
            nombreLbl.setToolTipText("Nombre requerido");
        } else {
            nombreLbl.setBorder(null);
            nombreLbl.setToolTipText(null);
        }
        if (telefonoFld.getText().length() == 0) {
            valid = false;
            telefonoLbl.setBorder(Application.BORDER_ERROR);
            telefonoLbl.setToolTipText("Telefono requerido");
        } else {
            telefonoLbl.setBorder(null);
            telefonoLbl.setToolTipText(null);
        }
        if (salarioFld.getText().length() == 0) {
            valid = false;
            salarioLbl.setBorder(Application.BORDER_ERROR);
            salarioLbl.setToolTipText("Salario requerido");
        } else {
            salarioLbl.setBorder(null);
            salarioLbl.setToolTipText(null);
        }

        return valid;
    }

    private void cargaIconos() {

        try {
            iconoAzul = ImageIO.read(new File("src/main/java/sucursales/image/iconoAzul.png"));
            iconoRojo = ImageIO.read(new File("src/main/java/sucursales/image/iconoRojo.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        imagenFld.removeAll();
        int cont = 0;
        for (Sucursal sucursal : model.getSucursales()){
            icon = new JLabel();
            icon.putClientProperty("index", cont);

            icon.setIcon(new ImageIcon(iconoAzul));
            icon.setBounds(sucursal.getUbicacionX(),sucursal.getUbicacionY(),30,30);

            icon.createToolTip().setBackground(Color.YELLOW);
            icon.setToolTipText(String.valueOf(sucursal.getReferencia()));

            if(icon.getToolTipText().equals(model.getCurrent().getSucursal().getReferencia())){
                icon.setIcon(new ImageIcon(iconoRojo));
                icon.setBounds(sucursal.getUbicacionX(),sucursal.getUbicacionY(),30,30);
            }

            icon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (Sucursal sucursal : model.getSucursales()){
                        icon.setIcon(new ImageIcon(iconoAzul));
                    }
                    icon = (JLabel) e.getSource();
                    int row = (int) icon.getClientProperty("index");
                    if (e.getClickCount() == 1){
                        icon.setIcon(new ImageIcon(iconoRojo));
                        for (int i = 0; i <= model.getSucursales().size(); i++){
                            if (model.getSucursales().get(row).getReferencia().equals(model.getSucursales().get(i).getReferencia())){
                                model.getCurrent().setSucursal(model.getSucursales().get(row));
                                sucursalFld.setText(model.getSucursales().get(row).getReferencia());
                                sucursalFld.setForeground(Color.RED);
                                break;
                            }
                        }
                    }
                   if (e.getClickCount() == 2){
                        icon.setIcon(new ImageIcon(iconoAzul));
                        for (int i = 0; i <= model.getSucursales().size(); i++){
                            sucursalFld.setText("");
                            sucursalFld.setForeground(Color.RED);
                        }
                    }
                }
            });

            cont++;
            imagenFld.add(icon);
            imagenFld.repaint();
        }
        imagenFld.setText("");
    }

    public void cargaMapa(){

        try {
            mapa = ImageIO.read(new File("src/main/java/sucursales/image/mapa.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mapa = mapa.getScaledInstance(500,500,0);
        BufferedImage result = new BufferedImage(780,600,BufferedImage.TYPE_INT_RGB);
        Graphics g = result.getGraphics();
        g.drawImage(mapa,0,0,780,600,null);
        imagenFld.setIcon(new ImageIcon(mapa));
    }

}
