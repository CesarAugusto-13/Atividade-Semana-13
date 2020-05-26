import java.sql.Connection;
import java.sql.SQLException;

public class Teste 
{
   public static void main(String[] args) 
   {
      Connection conn = null;
      Temperatura temperatura;
   
      try 
      {
         ConexaoBD bd = new ConexaoBD();
         conn = bd.conectar();
         conn.setAutoCommit(false);
      
         for(int i = 0; i < 100; i++)
         {
           	 temperatura = new Temperatura();
           	 temperatura.setValor(40*Math.random());
           	 temperatura.incluir(conn);
         }
         conn.commit();
         Termometro termo = new Termometro();

         Temperatura[] temps = termo.ultimosDias(conn, 200);

         for(int i = 0; i < temps.length; i++)
         {
        	   System.out.println(temps[i]);
         }
      } 
      catch (Exception e) 
      {
         e.printStackTrace();
         if (conn != null) 
         {
            try 
            {
               conn.rollback();
            } 
            catch (SQLException e1) 
            {
               System.out.print(e1.getStackTrace());
            }
         }
      } 
      finally 
      {
         if (conn != null) 
         {
            try 
            {
               conn.close();
            } 
            catch (SQLException e1) 
            {
               System.out.print(e1.getStackTrace());
            }
         }
      }
   }
}