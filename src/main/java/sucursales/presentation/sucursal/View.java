package sucursales.presentation.sucursal;

import sucursales.Application;
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
    private JTextField codigoFld;
    private JLabel referenciaLbl;
    private JLabel codigoLbl;
    private JTextField referenciaFld;
    private JTextField direccionFld;
    private JLabel zonajeLbl;
    private JLabel direccionLbl;
    private JTextField zonajeFld;
    private JButton guardarButton;
    private JButton cancelarButton;
    private JLabel imagenlbl;
    Image mapa;
    Image iconAzul;
    JLabel icono;


    public View() {

        cargaMapa();
        imagenlbl.setLayout(null);

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Sucursal n = take();
                    model.getCurrent().setCodigo(n.getCodigo());
                    model.getCurrent().setReferencia(n.getReferencia());
                    model.getCurrent().setDireccion(n.getDireccion());
                    model.getCurrent().setZonaje(n.getZonaje());
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
    public void update(Observable updatedModel, Object parametros) {
        Sucursal current = model.getCurrent();
        this.codigoFld.setEnabled(model.getModo() == Application.MODO_AGREGAR);
        this.codigoFld.setText(current.getCodigo());
        codigoFld.setText(current.getCodigo());
        referenciaFld.setText(current.getReferencia());
        direccionFld.setText(current.getDireccion());
        zonajeFld.setText(String.valueOf(current.getZonaje()));
        this.panel.validate();
    }

    public Sucursal take() {
        Sucursal s = new Sucursal();
        s.setCodigo(codigoFld.getText());
        s.setReferencia(referenciaFld.getText());
        s.setDireccion(direccionFld.getText());
        s.setZonaje(Float.parseFloat(zonajeFld.getText()));

        return s;
    }

    private boolean validate() {
        boolean valid = true;
        if (codigoFld.getText().isEmpty()) {
            valid = false;
            codigoLbl.setBorder(Application.BORDER_ERROR);
            codigoLbl.setToolTipText("Codigo requerido");
        } else {
            codigoLbl.setBorder(null);
            codigoLbl.setToolTipText(null);
        }

        if (referenciaFld.getText().length() == 0) {
            valid = false;
            referenciaLbl.setBorder(Application.BORDER_ERROR);
            referenciaLbl.setToolTipText("Referencia requerido");
        } else {
            referenciaLbl.setBorder(null);
            referenciaLbl.setToolTipText(null);
        }
        if (direccionFld.getText().length() == 0) {
            valid = false;
            direccionLbl.setBorder(Application.BORDER_ERROR);
            direccionLbl.setToolTipText("Direccion requerida");
        } else {
            direccionLbl.setBorder(null);
            direccionLbl.setToolTipText(null);
        }
        if (zonajeFld.getText().length() == 0) {
            valid = false;
            zonajeLbl.setBorder(Application.BORDER_ERROR);
            zonajeLbl.setToolTipText("Zonaje requerido");
        } else {
            zonajeLbl.setBorder(null);
            zonajeLbl.setToolTipText(null);
        }

        return valid;
    }

    public void agregaSucursal(){

        try {
            iconAzul = ImageIO.read(new File("src/main/java/sucursales/image/iconoAzul.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        icono = new JLabel();
        imagenlbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 1){

                    icono.setIcon(new ImageIcon(iconAzul));
                    icono.setBounds(model.getCurrent().setUbicacionX(e.getX()), model.getCurrent().setUbicacionX(e.getY()),30,30);
                    model.getCurrent().setUbicacionX(e.getX());
                    model.getCurrent().setUbicacionY(e.getY());
                    icono.setLocation(e.getX(), e.getY());

                }

            }
        });
        imagenlbl.removeAll();
        imagenlbl.add(icono);
        imagenlbl.repaint();
    }

    public void editarSucursal(Sucursal s){

        try {
            iconAzul = ImageIO.read(new File("src/main/java/sucursales/image/iconoAzul.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        icono = new JLabel();
        icono.setIcon(new ImageIcon(iconAzul));
        icono.setBounds(s.getUbicacionX(),s.getUbicacionY(),30,30);

        imagenlbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1){

                    icono.setBounds(model.getCurrent().setUbicacionX(e.getX()), model.getCurrent().setUbicacionX(e.getY()),30,30);
                    model.getCurrent().setUbicacionX(e.getX());
                    model.getCurrent().setUbicacionY(e.getY());
                    icono.setLocation(e.getX(), e.getY());

                }

            }
        });
        imagenlbl.removeAll();
        imagenlbl.add(icono);
        imagenlbl.repaint();
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
        imagenlbl.setIcon(new ImageIcon(mapa));

        imagenlbl.setText("");
    }


}
