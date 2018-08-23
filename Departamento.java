import java.sql.*;
import java.util.*;

public class Departamento {

	String codigo, nombre;
	List<Departamento> adepartamento = new ArrayList<Departamento>();

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

	Departamento(int a)
	   {
	   }

	Departamento()
	   {
	      cargar();
	   }

	public void cargar() {
		String sql;
		ResultSet rs;
		Departamento departamento;
		try {
			BD bd = new BD();
			sql = "select * from departamento order by codigo";
			rs = bd.executeQuery(sql);
			while (rs.next()) {
				departamento = new Departamento(0);
				departamento.setNombre(rs.getString("nombre"));
				departamento.setCodigo(rs.getString("codigo"));
				adepartamento.add(departamento);
			}
			bd.cerrar();
		} catch (Exception e) {
			System.out.println("error departamento cargar " + e.toString());
		}
	}

	public String[] vleer() {
		int largo = adepartamento.size();
		String[] dept = new String[largo];
		int i;
		for (i = 0; i < largo; i++)
			dept[i] = adepartamento.get(i).getNombre();
		return dept;
	}

}
