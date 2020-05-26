import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
 
public class Temperatura 
{
	private int id;
	private double valor;
 
	public Temperatura()
   {
		
	}
	public Temperatura(int id)
   {
		this.id = id;
	}
	public Temperatura(int id, double valor) 
   {
		this.id = id;
		this.valor = valor;
	}
	public int getId() 
   {
		return id;
	}
	public double getValor() 
   {
		return valor;
	}
	public void setId(int id) 
   {
		this.id = id;
	}
	public void setValor(double valor) 
   {
		this.valor = valor;
	}
	public String toString() 
   {
		return "Temperatura [id=" + id + ", valor=" + valor + "]";
	}
	public void incluir(Connection conn) 
   {
		String sqlInsert = "INSERT INTO TEMPERATURA(valor) VALUES (?)";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try 
      {
			stm = conn.prepareStatement(sqlInsert);
			stm.setDouble(1, getValor());
			stm.execute();
			//pega o id gerado pelo MySQL
			String selecao = "select LAST_INSERT_ID()";
			//fecha o prepared statement para usar de novo
			stm.close();
			stm = conn.prepareStatement(selecao);
			rs = stm.executeQuery();
			if(rs.next())
         {
				this.setId(rs.getInt(1));
			}
		} 
      catch (Exception e) 
      {
			e.printStackTrace();
			try 
         {
				conn.rollback();
			} 
         catch (SQLException e1) 
         {
				System.out.print(e1.getStackTrace());
			}
		} 
      finally 
      {
			if(rs != null)
         {
				try 
            {
					rs.close();
				} 
            catch (SQLException e) 
            {
					e.printStackTrace();
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
	}
 
	public void excluir(Connection conn) 
   {
		String sqlDelete = "DELETE FROM TEMPERATURA WHERE id = ?";
		PreparedStatement stm = null;
		try 
      {
			stm = conn.prepareStatement(sqlDelete);
			stm.setInt(1, getId());
 
			stm.execute();
		} 
      catch (Exception e) 
      {
			e.printStackTrace();
			try 
         {
				conn.rollback();
			} 
         catch (SQLException e1) 
         {
				System.out.print(e1.getStackTrace());
			}
		} 
      finally 
      {
			if (stm != null)
         {
				try {
					stm.close();
				}
            catch (SQLException e1) 
            {
					System.out.print(e1.getStackTrace());
				}
			}
		}
	}
 
	public void atualizar(Connection conn) 
   {
		String sqlUpdate = "UPDATE TEMPERATURA SET Valor = ? WHERE id = ?";
		PreparedStatement stm = null;
		try 
      {
			stm = conn.prepareStatement(sqlUpdate);
			stm.setDouble(1, getValor());
			stm.setInt(2, getId());
 
			stm.execute();
		} 
      catch (Exception e) 
      {
			e.printStackTrace();
			try 
         {
				conn.rollback();
			} 
         catch (SQLException e1) 
         {
				System.out.print(e1.getStackTrace());
			}
		} 
      finally 
      {
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
	}
 
	public void carregar(Connection conn) 
   {
		String sqlSelect = "SELECT valor FROM TEMPERATURA WHERE id = ?";
 
		PreparedStatement stm = null;
		ResultSet rs = null;
		try 
      {
			stm = conn.prepareStatement(sqlSelect);
			stm.setInt(1, getId());
			rs = stm.executeQuery();
 
			if (rs.next()) 
         {
				this.setValor(rs.getDouble(1));
			}
		}
      catch (Exception e) 
      {
			e.printStackTrace();
			try 
         {
				conn.rollback();
			} catch (SQLException e1) 
         {
				System.out.print(e1.getStackTrace());
			}
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
	}
}