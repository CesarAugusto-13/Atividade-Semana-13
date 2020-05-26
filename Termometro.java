import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class Termometro 
{
	public Temperatura[] ultimosDias(Connection conn, int numero) throws Exception
   {
		Temperatura[] temperaturas = new Temperatura[numero];
		String sqlSelect = "SELECT id, valor FROM TEMPERATURA ORDER BY id DESC";
		Temperatura temperatura;
 
		PreparedStatement stm = null;
		ResultSet rs = null;
		try 
      {
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery();
			int i = 0;
			while (rs.next() && i < numero) 
         {
				temperatura = new Temperatura();
				temperatura.setId(rs.getInt(1));
				temperatura.setValor(rs.getDouble(2));

				temperaturas[i] = temperatura;
				i++;
 			}
			if(i < temperaturas.length)
         {
				throw new Exception("Existem apenas "+i+" temperaturas na tabela.");
			}
		} 
      catch (SQLException e) 
      {
			e.printStackTrace();
		} 
      finally 
      {
			if (rs != null) 
         {
				try
            {
					rs.close();
				} 
            catch (SQLException e1) 
            {
					System.out.print(e1.getStackTrace());
				}
			}
			if (stm != null) 
         {
				try 
            {
					stm.close();
				} 
            catch (SQLException e1) 
            {
					System.out.print(e1.getStackTrace());
				}
			}
		}
		return temperaturas;
	}

	public double media(Temperatura[] temps)
   {
		double ac = 0;
      for (Temperatura temp : temps)
      {
         ac = ac + temp.getValor ();
      }
      return ac / temps.length;
	}
	public double maior(Temperatura[] temps)
   {
		double aux = temps[0].getValor();
      for (int i = 1; i < temps.length; i++)
      {
         if (temps[i].getValor() > aux)
            aux = temps[i].getValor();
      }
      return aux;
	}
	public double menor(Temperatura[] temps)
   {
		return Double.NaN;
	}
}