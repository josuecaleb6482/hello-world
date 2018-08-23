import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.table.*;

public class Cliente {
	String codigo, cedula, nombre, apellido, direccion, telefono, provincia, compra_anual;
	boolean existe;

	public boolean getExiste() {
		return existe;
	}

	public void setCedula(String s) {
		cedula = s;
	}

	public String getCedula() {
		return cedula;
	}

	public void setNombre(String s) {
		nombre = s;
	}

	public String getNombre() {
		return nombre;
	}

	public void setApellido(String s) {
		apellido = s;
	}

	public String getApellido() {
		return apellido;
	}

	public void setDireccion(String s) {
		direccion = s;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setTelefono(String s) {
		telefono = s;
	}

	public String getTelefono() {
		return telefono;
	}

	// ds
	public void setProvincia(String s) {
		provincia = s;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setCompraAnual(String s) {
		compra_anual = s;
	}

	public String getCompraAnual() {
		return compra_anual;
	}

	public void cargarTabla(DefaultTableModel dtm) {
		String sql;
		ResultSet rs;
		BD bd = new BD();
		dtm.addColumn("Cedula");
		dtm.addColumn("Nombre");
		dtm.addColumn("Apellido");
		dtm.addColumn("Direccion");
		dtm.addColumn("Provincia");
		dtm.addColumn("Telefono");
		dtm.addColumn("Compra anual");

		Object[] fila = new Object[7];

		try {
			sql = "select * from cliente, provincia ";
			sql = sql + "where cliente.provincia = provincia.codigo";

			rs = bd.executeQuery(sql);
			while (rs.next()) {
				fila[0] = rs.getString("cedula");
				fila[1] = rs.getString("nombre");
				fila[2] = rs.getString("apellido");
				fila[3] = rs.getString("direccion");
				fila[4] = rs.getString("provincia.nombre");
				fila[5] = rs.getString("telefono");
				fila[6] = rs.getString("compra_anual");
				dtm.addRow(fila);
			}
			bd.cerrar();
		} catch (Exception e) {
			System.out.println("Error cliente cargarTabla" + e.toString());
		}
	}

	public void buscar(String ced) {
		ResultSet rs;
		String sql;
		cedula = "";
		nombre = "";
		apellido = "";
		direccion = "";
		telefono = "";
		provincia = "";
		compra_anual = "";

		try {
			sql = "select cliente.cedula, cliente.nombre, cliente.apellido, cliente.direccion, cliente.telefono, provincia.nombre, compra_anual from cliente, provincia ";
			sql = sql + "where cliente.cedula = '" + ced + "' and cliente.provincia = provincia.codigo";
			BD bd = new BD();
			rs = bd.executeQuery(sql);

			if (rs.next()) {
				cedula = rs.getString("cedula");
				nombre = rs.getString("nombre");
				apellido = rs.getString("apellido");
				direccion = rs.getString("direccion");
				telefono = rs.getString("telefono");
				provincia = rs.getString("provincia.nombre");
				compra_anual = rs.getString("compra_anual");
			}
			bd.cerrar();
		} catch (Exception e) {
			System.out.println("Error cliente buscar " + e.toString());
		}

	}

	public void Existe(String ced) {
		ResultSet rs;
		String sql;
		existe = false;
		try {
			sql = "select * from cliente where cedula='" + ced + "'";
			BD bd = new BD();
			rs = bd.executeQuery(sql);

			if (rs.next()) {
				existe = true;

			} else {
				existe = false;
			}
			bd.cerrar();
		} catch (Exception e) {
			System.out.println("Error cliente buscar " + e.toString());
		}

	}

	public void agregar(String ced, String nom, String ape, String dir, String tel, String comp, String prov) {

		String sql;

		try {
			BD bd = new BD();
			bd.abrir();

			sql = "insert into cliente(cedula,nombre,apellido,direccion,telefono,compra_anual, provincia) values('"
					+ ced + "','" + nom + "','" + ape + "','" + dir + "','" + tel + "','" + comp
					+ "', (select codigo from provincia where nombre = '" + prov + "'))";

			bd.executeInsertUpdate(sql);

			bd.cerrar();

			JOptionPane.showMessageDialog(null, "Cliente " + ced + " Agregado Correctamente");

		} catch (Exception e) {
			System.out.println("error " + e.toString());
		}

	}

	public void modificar(String ced, String nom, String ape, String dir, String tel, String comp, String prov) {

		String sql1, sqlp;

		try {

			BD bd = new BD();
			ResultSet rs;
			// Class.forName("com.mysql.cj.jdbc.Driver");

			sqlp = "select * from provincia where nombre = '" + prov + "'";
			rs = bd.executeQuery(sqlp);

			if (rs.next()) {
				provincia = rs.getString("codigo");
			}

			sql1 = "update cliente set nombre = '" + nom + "',apellido = '" + ape + "',direccion = '" + dir
					+ "', telefono = '" + tel + "', compra_anual = '" + comp + "', provincia = '" + provincia + "'"
					+ " where cedula= '" + ced + "'";

			bd.executeUpdate(sql1);

			bd.cerrar();
			JOptionPane.showMessageDialog(null, "Cliente " + ced + " Modificado Correctamente");
		} catch (Exception e) {
			System.out.println("error ggg " + e.toString());
		}
	}

	public void eliminar(String ced) {
		String sql;

		try {
			
			int respuesta = JOptionPane.showConfirmDialog(null, "Seguro Desea Eliminar el Cliente "+ced, "ELIMINAR", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			if(respuesta == JOptionPane.YES_OPTION) {
				BD bd = new BD();
				sql = "delete from cliente where cedula = '" + ced + "'";
				bd.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Cliente " + ced + " Modificado Correctamente");
				bd.cerrar();
			}else if(respuesta == JOptionPane.NO_OPTION){
				JOptionPane.showMessageDialog(null, "Accion Cancelada", "WARNING", respuesta);

			}
			
			

		} catch (Exception e) {

		}
	}
}
