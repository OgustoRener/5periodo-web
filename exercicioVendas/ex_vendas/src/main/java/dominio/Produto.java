package dominio;

import java.util.List;
import javax.persistence.*;

@Entity
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private double preco;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "venda_produto",
                joinColumns = @JoinColumn(name = "produto_id"),
                inverseJoinColumns = @JoinColumn(name = "venda_id"))
    private List<Venda> vendas;

    public Produto(){    
        this("", 0);
    }

    public Produto(String nome, double preco) {
        setNome(nome);
        setPreco(preco);
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

    public double getPreco() {
        return this.preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public List<Venda> getVendas() {
        return this.vendas;
    }
    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    @Override
    public String toString() {
        return "Produto [id=" + id + ", nome=" + nome + ", preço=" + preco + "]";
    }
}
