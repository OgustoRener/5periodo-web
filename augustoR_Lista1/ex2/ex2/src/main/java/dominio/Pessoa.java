package dominio;


import java.io.Serializable;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //identifica que apenas as classes concretas serão geradas.
public class Pessoa implements Serializable {

	//private static final long serialVersionUID = 1L; //Id padrão do Serializable
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long idPessoa;
	private String nome;
	private String idade;
		
	public Pessoa() {
		this("", "");
	}

	public Pessoa(String nome, String idade) {
		setNome(nome);
		setIdade(idade);
	}

	public long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}
	
	@Override
	public String toString() {
		return "Pessoa [idPessoa=" + idPessoa + ", nome=" + nome + ", IDADE=" + idade + "]";
	}
	
	//public abstract void imprimeDados();
}
