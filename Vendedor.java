import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.table.*;

public class Vendedor {
	String codigo, nombre, apellido, departamento, cargo, venta_anual, venta_mensual;
	boolean existe;

	public boolean getExiste() {
		return existe;
	}

	public void setCodigo(String s) {
		codigo = s;
	}

	public String getCodigo() {
		return codigo;
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

	public void setDepartamento(String s) {
		departamento = s;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setCargo(String s) {
		cargo = s;
	}

	public String getCargo() {
		return cargo;
	}

	// ds
	public void setVentaA(String s) {
		venta_anual = s;
	}

	public String getVentaA() {
		return venta_anual;
	}

	public void setVentaM(String s) {
		venta_mensual = s;
	}

	public String getVentaM() {
		return venta_mensual;
	}

	public void cargarTabla(DefaultTableModel dtm) {
		String sql;
		ResultSet rs;
		BD bd = new BD();
		dtm.addColumn("Codigo");
		dtm.addColumn("Nombre");
		dtm.addColumn("Apellido");
		dtm.addColumn("Departamento");
		dtm.addColumn("Cargo");
		dtm.addColumn("Venta_Mensual");
		dtm.addColumn("Venta_Anual");

		Object[] fila = new Object[7];

		try {
			sql = "select * from vendedor, departamento where vendedor.departamento = departamento.codigo";

			rs = bd.executeQuery(sql);
			while (rs.next()) {
				fila[0] = rs.getString("codigo");
				fila[1] = rs.getString("nombre");
				fila[2] = rs.getString("apellido");
				fila[3] = rs.getString("departamento.nombre");
				fila[4] = rs.getString("cargo");
				fila[5] = rs.getString("venta_mensual");
				fila[6] = rs.getString("venta_anual");
				dtm.addRow(fila);
			}
			bd.cerrar();
		} catch (Exception e) {
			System.out.println("Error vendedor cargarTabla" + e.toString());
		}
	}

	public void buscar(String cod) {

		codigo = "";
		nombre = "";
		apellido = "";
		departamento = "";
		cargo = "";
		venta_anual = "";
		venta_mensual = "";

		ResultSet rs;
		String sql;
		try {
			sql = "select v.codigo, v.nombre, v.apellido, v.cargo, v.venta_mensual, s.nombre, v.venta_anual from vendedor v, departamento s ";
			sql = sql + " where v.codigo = '" + cod + "' and v.departamento = s.codigo";
			BD bd = new BD();
			rs = bd.executeQuery(sql);
			if (rs.next()) {
				codigo = rs.getString("codigo");
				nombre = rs.getString("nombre");
				apellido = rs.getString("apellido");
				departamento = rs.getString("s.nombre");
				cargo = rs.getString("cargo");
				venta_anual = rs.getString("venta_anual");
				venta_mensual = rs.getString("venta_mensual");
			}
			bd.cerrar();
		} catch (Exception e) {
			System.out.println("Error vendedor buscar " + e.toString());
		}

	}

	public void Existe(String cod) {
		ResultSet rs;
		String sql;
		existe = false;
		try {
			sql = "select * from vendedor where codigo='" + cod + "'";
			BD bd = new BD();
			rs = bd.executeQuery(sql);

			if (rs.next()) {
				existe = true;

			} else {
				existe = false;
			}
			bd.cerrar();
		} catch (Exception e) {
			System.out.println("Error vendedor buscar " + e.toString());
		}

	}

	public void agregar(String cod, String nom, String ape, String cargo, String venm, String vena, String deptno) {

		String sql;

		try {
			BD bd = new BD();
			bd.abrir();

			sql = "insert into vendedor(codigo,nombre,apellido,cargo,venta_mensual,venta_anual, departamento) values('"
					+ cod + "','" + nom + "','" + ape + "','" + cargo + "','" + venm + "','" + vena
					+ "', (select codigo from departamento where nombre = '" + deptno + "'))";

			bd.executeInsertUpdate(sql);

			bd.cerrar();

			JOptionPane.showMessageDialog(null, "Vendedor " + cod + " Agregado Correctamente");

		} catch (Exception e) {
			System.out.println("error " + e.toString());
		}

	}

	public void modificar(String cod, String nom, String ape, String cargo, String venm, String vena, String deptno) {

		String sql1, sqlp;

		try {

			BD bd = new BD();
			ResultSet rs;
			// Class.forName("com.mysql.cj.jdbc.Driver");

			sqlp = "select * from departamento where nombre = '" + deptno + "'";
			rs = bd.executeQuery(sqlp);

			if (rs.next()) {
				departamento = rs.getString("codigo");
			}

			sql1 = "update vendedor set nombre = '" + nom + "',apellido = '" + ape + "',cargo = '" + cargo
					+ "', venta_mensual = '" + venm + "', venta_anual = '" + vena + "', departamento = '" + departamento
					+ "'" + " where codigo = '" + cod + "'";

			bd.executeUpdate(sql1);

			bd.cerrar();
			JOptionPane.showMessageDialog(null, "Vendedor " + cod + " Modificado Correctamente");
		} catch (Exception e) {
			System.out.println("error ggg " + e.toString());
		}

	}

	public void eliminar(String cod) {
		String sql;

		try {

			int respuesta = JOptionPane.showConfirmDialog(null, "Seguro Desea Eliminar el Vendedor " + cod, "ELIMINAR",
					JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			if (respuesta == JOptionPane.YES_OPTION) {
				BD bd = new BD();
				sql = "delete from vendedor where codigo = '" + cod + "'";
				bd.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Vendedor " + cod + " Modificado Correctamente");
				bd.cerrar();
			} else if (respuesta == JOptionPane.NO_OPTION) {
				JOptionPane.showMessageDialog(null, "Accion Cancelada", "WARNING", respuesta);

			}
		} catch (Exception e) {

		}
	}

}
