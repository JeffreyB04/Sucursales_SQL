package sucursales.presentation.sucursales;

import sucursales.Application;
import sucursales.logic.Sucursal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;


public class View implements Observer {

    Model model;
    Controller controller;
    private JTextField codigoFld;
    private JPanel panel;
    private JLabel referenciaLbl;
    private JTextField referenciaFld;
    private JButton buscarButton;
    private JTextField direccionFld;
    private JButton agregarButton;
    private JTable sucursalesTable;
    private JButton borrarButton;
    private JButton reporteButton;
    private JLabel imageFld;
    private JPanel panelImage;
    Image mapa;
    Image iconoAzul;
    Image iconoRojo;
    JLabel icon;

    public View() {

        cargaMapa();
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.buscar(referenciaFld.getText());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.preAgregar();
            }
        });

        sucursalesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = sucursalesTable.getSelectedRow();
                    controller.editar(row);
                }
                if (e.getClickCount() == 1){
                    int row = sucursalesTable.getSelectedRow();
                    controller.obtieneSelected(row);
                }

            }
        });
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = sucursalesTable.getSelectedRow();
                controller.borrar(row);
            }
        });
        reporteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.imprimir();
                    if (Desktop.isDesktopSupported()) {
                        File myFile = new File("sucursales.pdf");
                        Desktop.getDesktop().open(myFile);
                    }
                } catch (Exception ex) { }
            }
        });
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    public JPanel getPanel() {
        return panel;
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
        imageFld.setText("");
        int[] cols = {TableModel.CODIGO, TableModel.REFERENCIA,TableModel.DIRECCION, TableModel.ZONAJE};
        sucursalesTable.setModel(new TableModel(cols, model.getSucursales()));
        sucursalesTable.setRowHeight(30);
        Components();
        this.panel.revalidate();
    }

    public void Components() {

        try {
            iconoAzul = ImageIO.read(new File("src/main/java/sucursales/image/iconoAzul.png"));
            iconoRojo = ImageIO.read(new File("src/main/java/sucursales/image/iconoRojo.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imageFld.removeAll();
        int cont = 0;
        for (Sucursal sucursal : model.getSucursales()){
            icon = new JLabel();
            icon.putClientProperty("index", cont);
            icon.createToolTip().setBackground(Color.YELLOW);
            icon.setToolTipText(String.valueOf(sucursal.getReferencia())+ ".  \n" +String.valueOf(sucursal.getDireccion()));
            icon.setIcon(new ImageIcon(iconoAzul));
            icon.setBounds(sucursal.getUbicacionX(),sucursal.getUbicacionY(),30,30);

           if (controller.isSelected()){
                int row1 = (int) icon.getClientProperty("index");
                if (model.getSelected().getReferencia().equals(model.getSucursales().get(row1).getReferencia())){
                    icon.setBounds(model.getSucursales().get(row1).getUbicacionX(),model.getSucursales().get(row1).getUbicacionY(),30,30);
                    icon.setIcon(new ImageIcon(iconoRojo));
                }

            }else{
                  icon.setIcon(new ImageIcon(iconoAzul));
                  icon.setBounds(sucursal.getUbicacionX(),sucursal.getUbicacionY(),30,30);


            }
            cont++;
            imageFld.add(icon);
            imageFld.repaint();
        }

        imageFld.setText("");
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
        imageFld.setIcon(new ImageIcon(mapa));
    }

}
