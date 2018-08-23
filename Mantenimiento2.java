import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.event.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.*;
import java.util.*;
import java.sql.*;

public class Mantenimiento2 implements ActionListener, ListSelectionListener {
	JFrame v;
	DefaultTableModel dtm;
	JTable jt_cliente;
	JScrollPane jsp_cliente;
	ListSelectionModel lsm;

	JLabel lbl_cedula, lbl_nombre, lbl_apellido, lbl_telefono, lbl_direccion, lbl_provincia, lbl_compraAnual;
	JTextField tf_cedula, tf_nombre, tf_apellido, tf_direccion, tf_telefono, tf_compraAnual;

	JButton btn_agregar, btn_modificar, btn_eliminar, btn_limpiar;
	JButton btn_escoger, btn_buscar, btn_listar;
	JComboBox<String> jcb_provincia;

	ArrayList<String[]> provinciasArray = new ArrayList<String[]>();

	Cliente cliente;
	Provincia provincia;
	Inicio inicio;

	Mantenimiento2(JFrame x)

	{
		v = x;
		v.revalidate();
		v.getContentPane().removeAll();
		cliente = new Cliente();
		provincia = new Provincia();
		inicio = new Inicio();
		
		dtm = new DefaultTableModel();
		cliente.cargarTabla(dtm);
		jt_cliente = new JTable(dtm);
		jsp_cliente = new JScrollPane(jt_cliente);
		lsm = jt_cliente.getSelectionModel();
		lsm.addListSelectionListener(this);
		jsp_cliente.setBounds(50, 50, 450, 150);
		v.add(jsp_cliente);

		

		lbl_cedula = new JLabel("Cedula");
		lbl_cedula.setBounds(520, 50, 80, 20);
		v.add(lbl_cedula);

		tf_cedula = new JTextField();
		tf_cedula.setBounds(600, 50, 80, 20);
		v.add(tf_cedula);

		lbl_nombre = new JLabel("Nombre");
		lbl_nombre.setBounds(520, 75, 80, 20);
		v.add(lbl_nombre);

		tf_nombre = new JTextField();
		tf_nombre.setBounds(600, 75, 80, 20);
		v.add(tf_nombre);
		tf_nombre.setEditable(false);

		lbl_apellido = new JLabel("Apellido");
		lbl_apellido.setBounds(520, 100, 80, 20);
		v.add(lbl_apellido);

		tf_apellido = new JTextField();
		tf_apellido.setBounds(600, 100, 80, 20);
		v.add(tf_apellido);
		tf_apellido.setEditable(false);

		lbl_provincia = new JLabel("Provincia");
		lbl_provincia.setBounds(520, 125, 80, 20);
		v.add(lbl_provincia);

		jcb_provincia = new JComboBox<String>(provincia.vleer());
		jcb_provincia.setBounds(600, 125, 120, 20);
		v.add(jcb_provincia);
		
		int index=0;
		while(jcb_provincia.getItemCount()>index) {
			System.out.println(jcb_provincia.getItemAt(index));
			System.out.println(jcb_provincia.getItemCount());
			index++;
		}

		lbl_direccion = new JLabel("Direccion");
		lbl_direccion.setBounds(520, 150, 80, 20);
		v.add(lbl_direccion);

		tf_direccion = new JTextField();
		tf_direccion.setBounds(600, 150, 80, 20);
		v.add(tf_direccion);
		tf_direccion.setEditable(false);

		lbl_telefono = new JLabel("Telefono");
		lbl_telefono.setBounds(520, 175, 80, 20);
		v.add(lbl_telefono);

		tf_telefono = new JTextField();
		tf_telefono.setBounds(600, 175, 80, 20);
		v.add(tf_telefono);
		tf_telefono.setEditable(false);

		lbl_compraAnual = new JLabel("CompraAnual");
		lbl_compraAnual.setBounds(520, 200, 80, 20);
		v.add(lbl_compraAnual);

		tf_compraAnual = new JTextField();
		tf_compraAnual.setBounds(600, 200, 80, 20);
		v.add(tf_compraAnual);
		tf_compraAnual.setEditable(false);

		btn_agregar = new JButton("Agregar");
		btn_agregar.setBounds(520, 225, 80, 20);
		btn_agregar.addActionListener(this);
		v.add(btn_agregar);
		btn_agregar.setEnabled(false);

		btn_buscar = new JButton("Buscar");
		btn_buscar.setBounds(520, 250, 80, 20);
		btn_buscar.addActionListener(this);
		v.add(btn_buscar);

		btn_modificar = new JButton("Modificar");
		btn_modificar.setBounds(520, 275, 80, 20);
		btn_modificar.addActionListener(this);
		v.add(btn_modificar);
		btn_modificar.setEnabled(false);

		btn_eliminar = new JButton("Eliminar");
		btn_eliminar.setBounds(520, 340, 80, 20);
		btn_eliminar.addActionListener(this);
		v.add(btn_eliminar);

		btn_limpiar = new JButton("Limpiar");
		btn_limpiar.setBounds(520, 300, 80, 20);
		btn_limpiar.addActionListener(this);
		v.add(btn_limpiar);
		
		limpiar();

		v.repaint();
		v.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_escoger) {
			int fila;
			fila = jt_cliente.getSelectedRow();
			tf_cedula.setText(jt_cliente.getValueAt(fila, 0).toString());
			tf_nombre.setText(jt_cliente.getValueAt(fila, 1).toString());
			tf_apellido.setText(jt_cliente.getValueAt(fila, 2).toString());
			jcb_provincia.setSelectedItem(jt_cliente.getValueAt(fila, 3).toString());
			tf_direccion.setText(jt_cliente.getValueAt(fila, 4).toString());
			tf_telefono.setText(jt_cliente.getValueAt(fila, 5).toString());
			tf_compraAnual.setText(jt_cliente.getValueAt(fila, 6).toString());
		}
		if (e.getSource() == btn_buscar) {
			cliente.buscar(tf_cedula.getText());
			tf_nombre.setText(cliente.getNombre());
			tf_apellido.setText(cliente.getApellido());
			tf_direccion.setText(cliente.getDireccion());
			tf_telefono.setText(cliente.getTelefono());
			tf_compraAnual.setText(cliente.getCompraAnual());
			jcb_provincia.setSelectedItem(cliente.getProvincia());

			tf_cedula.setEditable(false);
			tf_nombre.setEditable(true);
			tf_apellido.setEditable(true);
			tf_direccion.setEditable(true);
			tf_telefono.setEditable(true);
			tf_compraAnual.setEditable(true);

			cliente.Existe(tf_cedula.getText());

			if (cliente.getExiste()) {
				btn_modificar.setEnabled(true);
				btn_eliminar.setEnabled(true);

			} else {
				btn_agregar.setEnabled(true);
			}

		}

		if (e.getSource() == btn_limpiar) {

			limpiar();
			

		}
		if (e.getSource() == btn_agregar) {

			cliente.agregar(tf_cedula.getText(), tf_nombre.getText(), tf_apellido.getText(), tf_direccion.getText(),
					tf_telefono.getText(), tf_compraAnual.getText(), String.valueOf(jcb_provincia.getSelectedItem()));
			recarga();

		}
		if (e.getSource() == btn_modificar) {
			cliente.modificar(tf_cedula.getText(), tf_nombre.getText(), tf_apellido.getText(), tf_direccion.getText(),
					tf_telefono.getText(), tf_compraAnual.getText(), String.valueOf(jcb_provincia.getSelectedItem()));
			recarga();
		}
		if (e.getSource() == btn_eliminar) {
			cliente.eliminar(tf_cedula.getText());
			recarga();
			limpiar();
		}

	}

	public int returnPosition(String code) {
		int e = -1, i = 0;
		for (String[] item : provinciasArray) {
			if (item[0].equals(code)) {
				e = i;
				break;
			}
			i++;
		}
		return e;
	}

	public void limpiar() {
		tf_cedula.setText("");
		tf_cedula.setEditable(true);
		tf_nombre.setText("");
		tf_nombre.setEditable(false);
		tf_apellido.setText("");
		tf_apellido.setEditable(false);
		tf_direccion.setText("");
		tf_direccion.setEditable(false);
		tf_telefono.setText("");
		tf_telefono.setEditable(false);
		tf_compraAnual.setText("");
		tf_compraAnual.setEditable(false);
		jcb_provincia.setSelectedIndex(-1);
		
		btn_modificar.setEnabled(false);
		btn_agregar.setEnabled(false);
		btn_eliminar.setEnabled(false);
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

	}
	public void recarga() {
		jt_cliente.removeAll();
		dtm.setNumRows(0);
		dtm.setColumnCount(0);
		cliente.cargarTabla(dtm);
		jt_cliente.repaint();
	}
}