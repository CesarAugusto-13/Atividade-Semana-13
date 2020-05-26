import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.cj.api.mysqla.result.Resultset;

public class TelaTemperatura extends JFrame
{
   private JTextField media;    
   
   Termometro ter = new Termometro();
   Temperatura temp = new Temperatura();
   Temperatura[] temperaturas = new Temperatura[(Double.parseDouble(temp.getValor()))];
   
   private JTable tabela;
   private DefaultTableModel modelo = new DefaultTableModel();
   private JScrollPane barraRolagem;

   public TelaTemperatura ()
   {
      super("Temperatura");
   
      setLayout(null);
      modelo.addColumn("Media");
        
      JPanel panelTitulo = new JPanel();
      panelTitulo.setLayout(new FlowLayout());
      
      JLabel titulo = new JLabel ("Temperatura");
      titulo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
      
      panelTitulo.add(titulo);
      
      add(panelTitulo, BorderLayout.NORTH);
        
      modelo = conection();
      
      tabela = new JTable(modelo);
      tabela.getColumnModel().getColumn(0).setPreferredWidth(9);
      
      barraRolagem = new JScrollPane(tabela);
      barraRolagem.setBounds(18, 90, 950, 150);
      add(barraRolagem);
      
      setTitle("Temperatura");
      setSize(1000, 500);
      setLocationRelativeTo(null);
      setVisible(true);
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
   }
   
   public DefaultTableModel conection() 
   {

      ter.media(temperaturas);
      ter.maior(temperaturas);
      ter.menor(temperaturas);
        
      return modelo;
  }
}