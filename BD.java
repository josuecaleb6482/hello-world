import java.sql.*;

public class BD
{
   String URL, user, pass;
   Connection con;
   Statement stmt;
   ResultSet rs;

   BD()
   {
      URL = "jdbc:mysql://localhost/1ls221";
      user = "root";
      pass = "";
   }
   
   public void abrir()
   {
      try
      {
          Class.forName("com.mysql.cj.jdbc.Driver");
          con = DriverManager.getConnection(URL,user,pass);
          stmt = con.createStatement();
          
      }
      catch(Exception e)
      {
         System.out.println("Error BD"+e.toString());
      }
   }

   public void cerrar()
   {
      try
      {
         if (rs != null)
            rs.close();
         stmt.close();
         con.close();
      }
      catch(Exception e)
      {
         System.out.println("Error BD cerrar "+e.toString());
      }

   }
   
   public ResultSet executeQuery(String sql)
   {
      try
      {
         abrir();
         rs = stmt.executeQuery(sql);
      }
      catch(Exception e)
      {
         System.out.println("Error BD abrir"+e.toString());
      }
      return rs;
   }   
   
   public Connection con()
   {
      return con;
   }
   
   public void executeUpdate(String sql)
   {
	   try
	   {
		   abrir();
		   stmt.executeUpdate(sql);
		   
	   }catch(Exception e){
		   System.out.println("Error de update "+e.toString());
	   }
   }
   
   public void executeInsertUpdate(String sql)
   {
      try
      {
         abrir();
         stmt.executeUpdate(sql);
      }
      catch(Exception e)
      {
         System.out.println("Error BD abrir"+e.toString());
      }
   }   
}






