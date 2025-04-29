package dominio;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

public class Venda {
    

    private long id;
    private double valorTotal;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToMany(mappedBy = "vendas")
    @JoinColumn(name = "venda_id")
    private List<Produto> produtos;

    public Venda(){
        this(0);
    }
    public Venda(double valorTotal) {
        setValorTotal(valorTotal);
    }
    
    public long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public double getValorTotal() {
        return this.valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Produto> getProdutos() {
        return this.produtos;
    }
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente getCliente() {
        return this.cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Venda [id=" + id + ", valorTotal=" + valorTotal + ", produtos=" + produtos + ", cliente=" + cliente + "]";
    }


}
