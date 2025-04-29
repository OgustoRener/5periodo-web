package aplicativo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import dominio.Produto;
import dominio.Venda;
import javax.persistence.*;


public class Principal {
    public static void main(String[] args) {
        
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
             emf = Persistence.createEntityManagerFactory("aula-jpa");
             em = emf.createEntityManager();
             em.getTransaction().begin();

            Produto objP1 = new Produto("Cubo Magico", 10.0);
            Produto objP2 = new Produto("Teclado", 20.0);
            Produto objP3 = new Produto("Rachador", 30.0);
            Produto objP4 = new Produto("Mochila", 40.0);
            Produto objP5 = new Produto("Mouse", 50.0);
            Produto objP6 = new Produto("Monitor", 60.0);

            em.persist(objP1);
            em.persist(objP2);
            em.persist(objP3);
            em.persist(objP4);
            em.persist(objP5);
            em.persist(objP6);

            Venda objV1 = new Venda(100.0);
            Venda objV2 = new Venda(200.0);
            Venda objV3 = new Venda(300.0);

            objV1.setProdutos(Arrays.asList(objP1, objP2, objP3));
            objV2.setProdutos(Arrays.asList(objP4, objP5));
            objV3.setProdutos(Arrays.asList(objP6));

            em.persist(objV1);
            em.persist(objV2);
            em.persist(objV3);


            Query consultaProdutos = em.createQuery("select produto from Produto produto");
            ArrayList<Produto> listaProduto = (ArrayList<Produto>) consultaProdutos.getResultList();
            Query consultaVendas = em.createQuery("select venda from Venda venda");
            ArrayList<Venda> listaVenda = (ArrayList<Venda>) consultaVendas.getResultList();  

            em.getTransaction().commit();
            emf.close();
            em.close();


            for(Produto objP : listaProduto){
                System.out.println(objP);
            }
            for(Venda objV : listaVenda){
                System.out.println(objV);
            }
        }
        catch (Exception e){
            if(em.getTransaction() != null) {
                em.getTransaction().rollback();
                }
                e.printStackTrace();
        }
        finally {
            if(em != null) {
                em.close();
            }
            if(emf != null) {
                emf.close();
            }
        }
    }
}
