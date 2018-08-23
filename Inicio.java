import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

public class Inicio implements ActionListener {
	JFrame ventana;
	JMenuBar menuBar;
	JMenu mModulo,mmMantenimiento, mmReportes;
	JMenuItem mmInicio, maSalir;
	JMenuItem mmPresentacion;
	JMenuItem mimCliente, mimVendedor;
	JMenuItem mirCliente, mirVendedor;
	JLabel lbltitulo, lblnombre, lblcedula, lblfecha, lbluniver, lblgrupo;

	DefaultListModel<String> listModel;
	JList<String> lst_lista;
	JScrollPane listScroll;

	public static void main(String[] args) {
		new Inicio();
	}

	Inicio() {
		ventana = new JFrame("Inicio");
		ventana.setBounds(100, 100, 750, 500);
		ventana.setLayout(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font font = new Font("Serif", Font.BOLD, 12);

		lbltitulo = new JLabel("Proyecto #3", JLabel.CENTER);
		lbltitulo.setFont(font);
		lbltitulo.setBounds(35, 40, 400, 120);
		ventana.add(lbltitulo);

		menuBar = new JMenuBar();

		// menuBar.add(mArchivo);

		mmInicio = new JMenu("Inicio");

		mmPresentacion = new JMenuItem("Presentacion");
		mmPresentacion.addActionListener(this);
		mmInicio.add(mmPresentacion);

		// Menu Mantenenimiento y Reportes al Modulo
		mmMantenimiento = new JMenu("Mantenimientos");
		mmMantenimiento.addActionListener(this);

		mmReportes = new JMenu("Reportes");
		mmReportes.addActionListener(this);
		

		// Agregamos al Menu Mantenimiento
		mimCliente = new JMenuItem("Clientes");
		mimCliente.addActionListener(this);
		mmMantenimiento.add(mimCliente);

		mimVendedor = new JMenuItem("Vendedores");
		mimVendedor.addActionListener(this);
		mmMantenimiento.add(mimVendedor);

		// Agregamos al Menu Reportes
		mirCliente = new JMenuItem("Reporte Clientes");
		mirCliente.addActionListener(this);
		mmReportes.add(mirCliente);

		mirVendedor = new JMenuItem("Reporte Vendedores");
		mirVendedor.addActionListener(this);
		mmReportes.add(mirVendedor);

		//menuBar.add(mModulo);
		menuBar.add(mmInicio);
		menuBar.add(mmMantenimiento);
		menuBar.add(mmReportes);
		ventana.setJMenuBar(menuBar);
		/*
		 * listModel = new DefaultListModel<String>(); lst_lista = new
		 * JList<String>(listModel); listScroll = new JScrollPane(lst_lista);
		 * listScroll.setBounds(50,50,200,200); ventana.add(listScroll);
		 */

		ventana.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mmPresentacion)
			new Presentacion(ventana);
		if (e.getSource() == maSalir)
			System.exit(0);
		// Menu mantenimiento
		if (e.getSource() == mimCliente)
			new Mantenimiento2(ventana);
		if (e.getSource() == mimVendedor)
			new Mantenimiento(ventana);
		// Menu reporte
		if (e.getSource() == mirCliente)
			new Reporte(ventana);
		if (e.getSource() == mirVendedor)
			new Reporte2(ventana);

	}

}
