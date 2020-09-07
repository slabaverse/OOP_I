package dataBase;

import java.util.List;

import entities.Marketplace;

public class MarketplaceDAO implements InterfaceDAO<Marketplace> {

    @Override
    public void add(Marketplace reference) {
	// TODO Auto-generated method stub
	
    }
    
/*	public void adicionar(Desenvolvedora desenvolvedora) {
		try {
			String sql = "INSERT INTO Desenvolvedora VALUES ('" + desenvolvedora.getNome() + "','"
					+ desenvolvedora.getEmail() + "','" + desenvolvedora.getSenha() + "')";
			UtilBD.alterarBD(sql);
		} catch (SQLException e) {
			AlertaFX.erro("Não foi possível inserir a desenvolvedora no banco!");
		}
	}*/

    

    @Override
    public void remove(Marketplace reference) {
	// TODO Auto-generated method stub
	
    }
    
    /*	public void remover(Desenvolvedora desenvolvedora) {
		try {
			String sql = "DELETE FROM Desenvolvedora WHERE nome = '" + desenvolvedora.getNome() + "'";
			UtilBD.alterarBD(sql);
		} catch (SQLException e) {
			AlertaFX.erro("Não foi possível remover a desenvolvedora do banco!");
		}

	}*/


    @Override
    public void update(Marketplace reference) {
	// TODO Auto-generated method stub
	
    }
    
    /*	public void update(Desenvolvedora desenvolvedora) {
		try {
			String sql = " FROM Desenvolvedora WHERE nome = '" + desenvolvedora.getNome() + "'";
			UtilBD.alterarBD(sql);
		} catch (SQLException e) {
			AlertaFX.erro("Não foi possível remover a desenvolvedora do banco!");
		}

	}*/

    @Override
    public List<Marketplace> all() {
	// TODO Auto-generated method stub
	return null;
    }
    /*	public List<Desenvolvedora> todos() {
		List<Desenvolvedora> retorno = new ArrayList<Desenvolvedora>();
		try {
			String sql = "SELECT Nome, Email, Senha FROM Desenvolvedora";
			ResultSet resultSet = UtilBD.consultarBD(sql);
			while (resultSet.next()) {
				String nome = resultSet.getString("Nome");
				String email = resultSet.getString("Email");
				String senha = resultSet.getString("Senha");
				retorno.add(new Desenvolvedora(nome, email, senha));
			}
			resultSet.getStatement().close();
		} catch (SQLException e) {
			AlertaFX.erro("Não foi possível consultar todas as desenvolvedoras do banco!");
		}
		return retorno;
	}*/
}
