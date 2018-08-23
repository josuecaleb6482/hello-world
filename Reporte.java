import javax.swing.*;
import javax.swing.table.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.*;
import java.util.*;

public class Reporte implements ListSelectionListener, ActionListener {
	JFrame v;
	DefaultTableModel dtm;
	JTable jt_cliente;
	JScrollPane jsp_cliente;
	ListSelectionModel lsm;

	JButton btn_reporte;
	int tiporeporte;
	ButtonGroup btnGroup;
	JRadioButton jrb_cedula;
	JRadioButton jrb_nombre;
	JRadioButton jrb_apellido;
	JLabel lbl_cedula, lbl_nombre, lbl_apellido;

	Reporte(JFrame x) {
		v = x;
		v.revalidate();
		v.getContentPane().removeAll();

		Cliente cliente = new Cliente();

		DefaultTableModel dtm;
		JTable jt_cliente;
		JScrollPane jsp_cliente;
		ListSelectionModel lsm;

		dtm = new DefaultTableModel();
		cliente.cargarTabla(dtm);
		jt_cliente = new JTable(dtm);
		jsp_cliente = new JScrollPane(jt_cliente);
		lsm = jt_cliente.getSelectionModel();
		lsm.addListSelectionListener(this);
		jsp_cliente.setBounds(50, 50, 450, 260);
		v.add(jsp_cliente);

		jrb_cedula = new JRadioButton();
		jrb_cedula.setBounds(280, 350, 20, 20);
		v.add(jrb_cedula);

		jrb_nombre = new JRadioButton();
		jrb_nombre.setBounds(280, 375, 20, 20);
		v.add(jrb_nombre);

		jrb_apellido = new JRadioButton();
		jrb_apellido.setBounds(280, 400, 20, 20);
		v.add(jrb_apellido);

		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(jrb_cedula);
		btnGroup.add(jrb_apellido);
		btnGroup.add(jrb_nombre);

		lbl_cedula = new JLabel("Cedula");
		lbl_cedula.setBounds(300, 350, 80, 20);
		v.add(lbl_cedula);

		lbl_nombre = new JLabel("Nombre");
		lbl_nombre.setBounds(300, 375, 80, 20);
		v.add(lbl_nombre);

		lbl_apellido = new JLabel("Apellido");
		lbl_apellido.setBounds(300, 400, 80, 20);
		v.add(lbl_apellido);

		btn_reporte = new JButton("Reporte");
		btn_reporte.setBounds(50, 350, 100, 20);
		btn_reporte.addActionListener(this);
		v.add(btn_reporte);

		v.repaint();
		v.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_reporte) {
			try {
				BD bd = new BD();
				bd.abrir();
				Map<String, Object> parametro = new HashMap<String, Object>();

				if (jrb_cedula.isSelected()) {
					parametro.put("orden", "cedula");
					parametro.put("titulo", "Reporte de Cliente por Cedula");
				} else if (jrb_nombre.isSelected()) {
					parametro.put("orden", "nombre");
					parametro.put("titulo", "Reporte de Cliente por Nombre");
				} else {
					parametro.put("orden", "apellido");
					parametro.put("titulo", "Reporte de Cliente por Apellido");
				}
				JasperPrint jasperPrint = JasperFillManager.fillReport("R221-1.jasper", parametro, bd.con());
				JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
				jasperViewer.setVisible(true);
			} catch (Exception e1) {
				System.out.println(e1.toString());
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
