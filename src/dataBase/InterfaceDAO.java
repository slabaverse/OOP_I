package dataBase;

import java.util.List;

/*
 * Uma interface funciona como um contrato entre as classes. Nela, adicionamos
 * apenas a assinatura dos métodos que as classes que forem seguir este contrato 
 * precisam implementar. Desta forma, podemos utilizar o polimorfismo dizendo que 
 * nossa referência é do tipo InterfaceDAO, sendo que o objeto em si poderá ser
 * de qualquer classe que implemente o contrato InterfaceDAO.
 */
public interface InterfaceDAO<T> {
	/*
	 * Ao declarar um <Tipo> genérico ao lado do nome da classe, estamos dizendo que
	 * esta classe poderá ser utilizada para qualquer tipo de objeto. É assim que as
	 * classes List<T> e ArrayList<T> funcionam também com classes que nós criamos.
	 */

	/*
	 * Uma DAO (Data Access Object) é um padrão de projeto (disciplina do quinto
	 * período). Este padrão de projeto centraliza em um único objeto a comunicação
	 * com o banco de dados em relação a uma única entidade ou a um conjunto de
	 * entidades.
	 */
	public void add(T reference);

	public void remove(T reference);
	
	public void update(T reference);

	public List<T> all();


	/*
	 * Como o método para recuperar um único objeto depende do identificador de cada
	 * classe, não vamos implementá-lo na Interface, que serve como um contrato
	 * genérico para todas as classes de DAO.
	 */
}