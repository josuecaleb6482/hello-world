import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class Presentacion 
{
   JFrame v;
   JLabel lbltitulo, lblnombre, lblcedula, lblfecha, lbluniver, lblgrupo;
   
   
   public static void main(String[] args)
   {
      new Inicio();
   }
   
   Presentacion(JFrame x)
   {
	   	  v=x;
	      v.setBounds(100,100,500,500);
	      //v.setLayout(null);
	      v.getContentPane().removeAll();
	      v.revalidate();
	      v.repaint();		  
	      
	      Font font = new Font("Serif", Font.BOLD, 12);
	      
		  lbltitulo= new JLabel("Proyecto #3", JLabel.CENTER);
		  lbltitulo.setFont(font);
		  lbltitulo.setBounds(35,40,400,120);
		  v.add(lbltitulo);
	      
		  lblnombre= new JLabel("JOSUE FLOREZ", JLabel.CENTER);
		  lblnombre.setFont(font);
		  lblnombre.setBounds(35,60,400,120);
		  v.add(lblnombre);
		  
		  lblcedula= new JLabel("8-805-394", JLabel.CENTER);
		  lblcedula.setFont(font);
		  lblcedula.setBounds(35,80,400,120);
		  v.add(lblcedula);
		  
		  lblgrupo= new JLabel("Grupo 1LS-221", JLabel.CENTER);
		  lblgrupo.setFont(font);
		  lblgrupo.setBounds(35,100,400,120);
		  v.add(lblgrupo);
		  
		  lblfecha= new JLabel("2018", JLabel.CENTER);
		  lblfecha.setFont(font);
		  lblfecha.setBounds(35,120,400,120);
		  v.add(lblfecha);
		  
   }
      
}
