import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.event.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.*;
import java.util.*;
import java.sql.*;

public class Mantenimiento implements ActionListener, ListSelectionListener {
	JFrame v;
	DefaultTableModel dtm;
	JTable jt_vendedor;
	JScrollPane jsp_vendedor;
	ListSelectionModel lsm;

	JLabel lbl_codigo, lbl_nombre, lbl_apellido, lbl_departamento, lbl_cargo, lbl_ventaMensual, lbl_ventaAnual;
	JTextField tf_codigo, tf_nombre, tf_apellido, tf_cargo, tf_ventaMensual, tf_ventaAnual;

	JButton btn_agregar, btn_modificar, btn_eliminar, btn_limpiar;
	JButton btn_escoger, btn_buscar, btn_listar;
	JComboBox<String> jcb_departamento;

	ArrayList<String[]> departamentoArray = new ArrayList<String[]>();

	Vendedor vendedor;
	Departamento departamento;
	Inicio inicio;

	Mantenimiento(JFrame x)

	{
		v = x;

		v.getContentPane().removeAll();
		v.revalidate();
		departamento = new Departamento();
		vendedor = new Vendedor();
		inicio = new Inicio();
		inicio.lbltitulo.setVisible(false);

		dtm = new DefaultTableModel();
		vendedor.cargarTabla(dtm);
		jt_vendedor = new JTable(dtm);
		jsp_vendedor = new JScrollPane(jt_vendedor);
		lsm = jt_vendedor.getSelectionModel();
		lsm.addListSelectionListener(this);
		jsp_vendedor.setBounds(50, 50, 450, 150);
		v.add(jsp_vendedor);

		lbl_codigo = new JLabel("Codigo");
		lbl_codigo.setBounds(520, 50, 80, 20);
		v.add(lbl_codigo);

		tf_codigo = new JTextField();
		tf_codigo.setBounds(600, 50, 80, 20);
		v.add(tf_codigo);

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

		lbl_departamento = new JLabel("Departamento");
		lbl_departamento.setBounds(520, 125, 80, 20);
		v.add(lbl_departamento);

		jcb_departamento = new JComboBox<String>(departamento.vleer());
		jcb_departamento.setBounds(600, 125, 120, 20);
		v.add(jcb_departamento);
		
		int index=0;
		while(jcb_departamento.getItemCount()>index) {
			System.out.println(jcb_departamento.getItemAt(index));
			System.out.println(jcb_departamento.getItemCount());
			index++;
		}

		lbl_ventaMensual = new JLabel("VentaMensual");
		lbl_ventaMensual.setBounds(520, 150, 80, 20);
		v.add(lbl_ventaMensual);

		tf_ventaMensual = new JTextField();
		tf_ventaMensual.setBounds(600, 150, 80, 20);
		v.add(tf_ventaMensual);
		tf_ventaMensual.setEditable(false);

		lbl_ventaAnual = new JLabel("ventaAnual");
		lbl_ventaAnual.setBounds(520, 175, 80, 20);
		v.add(lbl_ventaAnual);

		tf_ventaAnual = new JTextField();
		tf_ventaAnual.setBounds(600, 175, 80, 20);
		v.add(tf_ventaAnual);
		tf_ventaAnual.setEditable(false);

		lbl_cargo = new JLabel("CompraAnual");
		lbl_cargo.setBounds(520, 200, 80, 20);
		v.add(lbl_cargo);

		tf_cargo = new JTextField();
		tf_cargo .setBounds(600, 200, 80, 20);
		v.add(tf_cargo);
		tf_cargo.setEditable(false);

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
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_escoger) {
			int fila;
			fila = jt_vendedor.getSelectedRow();
			tf_codigo.setText(jt_vendedor.getValueAt(fila, 0).toString());
			tf_nombre.setText(jt_vendedor.getValueAt(fila, 1).toString());
			tf_apellido.setText(jt_vendedor.getValueAt(fila, 2).toString());
			jcb_departamento.setSelectedItem(jt_vendedor.getValueAt(fila, 3).toString());
			tf_cargo.setText(jt_vendedor.getValueAt(fila, 4).toString());
			tf_ventaMensual.setText(jt_vendedor.getValueAt(fila, 5).toString());
			tf_ventaAnual.setText(jt_vendedor.getValueAt(fila, 6).toString());
		}
		if (e.getSource() == btn_buscar) {
			vendedor.buscar(tf_codigo.getText());
			tf_nombre.setText(vendedor.getNombre());
			tf_apellido.setText(vendedor.getApellido());
			tf_cargo.setText(vendedor.getCargo());
			tf_ventaMensual.setText(vendedor.getVentaM());
			tf_ventaAnual.setText(vendedor.getVentaA());
			jcb_departamento.setSelectedItem(vendedor.getDepartamento());

			tf_codigo.setEditable(false);
			tf_nombre.setEditable(true);
			tf_apellido.setEditable(true);
			tf_cargo.setEditable(true);
			tf_ventaMensual.setEditable(true);
			tf_ventaAnual.setEditable(true);

			vendedor.Existe(tf_codigo.getText());

			if (vendedor.getExiste()) {
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

			vendedor.agregar(tf_codigo.getText(), tf_nombre.getText(), tf_apellido.getText(), tf_cargo.getText(),
					tf_ventaMensual.getText(), tf_ventaAnual.getText(), String.valueOf(jcb_departamento.getSelectedItem()));
			recarga();

		}
		if (e.getSource() == btn_modificar) {
			vendedor.modificar(tf_codigo.getText(), tf_nombre.getText(), tf_apellido.getText(), tf_cargo.getText(),
					tf_ventaMensual.getText(), tf_ventaAnual.getText(), String.valueOf(jcb_departamento.getSelectedItem()));
			recarga();
		}
		if (e.getSource() == btn_eliminar) {
			vendedor.eliminar(tf_codigo.getText());
			recarga();
			limpiar();
		}

	}

	public int returnPosition(String code) {
		int e = -1, i = 0;
		for (String[] item : departamentoArray) {
			if (item[0].equals(code)) {
				e = i;
				break;
			}
			i++;
		}
		return e;
	}

	public void limpiar() {
		tf_codigo.setText("");
		tf_codigo.setEditable(true);
		tf_nombre.setText("");
		tf_nombre.setEditable(false);
		tf_apellido.setText("");
		tf_apellido.setEditable(false);
		tf_cargo.setText("");
		tf_cargo.setEditable(false);
		tf_ventaMensual.setText("");
		tf_ventaMensual.setEditable(false);
		tf_ventaAnual.setText("");
		tf_ventaAnual.setEditable(false);
		jcb_departamento.setSelectedIndex(-1);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

	}
	public void recarga() {
		jt_vendedor.removeAll();
		dtm.setNumRows(0);
		dtm.setColumnCount(0);
		vendedor.cargarTabla(dtm);
		jt_vendedor.repaint();
	}
}