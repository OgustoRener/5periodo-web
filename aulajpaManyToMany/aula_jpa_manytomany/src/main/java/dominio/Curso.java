package dominio;

import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(name = "nomes")
	private String nome;

	@ManyToMany(mappedBy = "cursos")
	private List<Estudante> estudante = new ArrayList<>();

	public Curso() {
		this("");
	}
	public Curso(String nome) {
		setNome(nome);
	}

	public Long getId() {
		return this.Id;
	}
	public void setId(Long id){
		this.Id = id;
	}

	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Estudante> getEstudante() {
		return this.estudante;
	}
	public void setEstudante(List<Estudante> estudante) {
		this.estudante = estudante;
	}

	@Override
	public String toString(){
		return "Curso [Id=" + Id + ", nome=" + nome + "]";
	}
}
