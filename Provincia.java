import java.sql.*;
import java.util.*;
public class Provincia
{
   String codigo, nombre;
   List<Provincia> aprovincia = new ArrayList<Provincia>();
   
   public void setCodigo(String s)
   {
      codigo = s;
   }
   public String getCodigo()
   {
      return codigo;
   }
   public void setNombre(String s)
   {
      nombre = s;
   }
   public String getNombre()
   {
      return nombre;
   }
   Provincia(int a)
   {
   }
   Provincia()
   {
      cargar();
   }
   public void cargar()
   {
      String sql;
      ResultSet rs;
      Provincia provincia;
      try
      {
         BD bd = new BD();
         sql = "select * from provincia order by codigo";
         rs = bd.executeQuery(sql);
         while (rs.next())
         {
            provincia = new Provincia(0);
            provincia.setNombre(rs.getString("nombre"));
            provincia.setCodigo(rs.getString("codigo"));
            aprovincia.add(provincia);
         }
         bd.cerrar();
      }
      catch(Exception e)
      {
         System.out.println("error provincia cargar "+e.toString());
      }
   }
 
   public String[] vleer()
   {
       int largo = aprovincia.size();
       String[] prov = new String[largo];
       int i;
       for (i=0;i<largo;i++)
          prov[i] = aprovincia.get(i).getNombre();
       return prov;
   }
   
   
   

}




