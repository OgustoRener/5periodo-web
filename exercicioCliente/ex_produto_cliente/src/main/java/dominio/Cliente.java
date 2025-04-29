package dominio;

import java.util.List;
import javax.persistence.*;
import javax.persistence.CascadeType;

@Entity
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Venda> vendas;

    public Cliente(){
        this("");
    }

    public Cliente(String nome) {
        setNome(nome);
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Venda> getVendas() {
        return this.vendas;
    }
    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nome=" + nome + "]";
    }
}
