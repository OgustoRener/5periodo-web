package aplicativo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import dominio.Curso;
import dominio.Estudante;
import java.util.List;

public class Principal {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = null;
		EntityManager em = null;

		try{
			emf = Persistence.createEntityManagerFactory("aula-jpa");
			em = emf.createEntityManager();
			em.getTransaction().begin();

			Curso objC1 = new Curso("Python");
			Curso objC2 = new Curso("Java");
			Curso objC3 = new Curso("JavaScript");
			Curso objC4 = new Curso("C#");

			em.persist(objC1);
			em.persist(objC2);
			em.persist(objC3);
			em.persist(objC4);

			Estudante objE1 = new Estudante("Sambas");
			Estudante objE2 = new Estudante("Magican");
			Estudante objE3 = new Estudante("Maumas");
			Estudante objE4 = new Estudante("Dede");

			objE1.setCursos(Arrays.asList(objC1, objC2, objC4));
			objE2.setCursos(Arrays.asList(objC1, objC2, objC3));
			objE3.setCursos(Arrays.asList(objC3, objC4));
			objE4.setCursos(Arrays.asList(objC1, objC2, objC3, objC4));

			em.persist(objE1);
			em.persist(objE2);
			em.persist(objE3);
			em.persist(objE4);

			Query consultaEstudantes = em.createQuery("select estudante from Estudante estudante");
			ArrayList<Estudante> listaEstudante = (ArrayList<Estudante>) consultaEstudantes.getResultList();

			Query consultaCursos = em.createQuery("select curso from Curso curso");
			ArrayList<Curso> listaCurso = (ArrayList<Curso>) consultaCursos.getResultList();

			em.getTransaction().commit();

			for(Estudante objE : listaEstudante){
				System.out.println(objE);
			}
			for(Curso objC : listaCurso){
				System.out.println(objC);
			}
		}
		catch(Exception e){
			if(em.getTransaction() != null){
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		finally{
			if(em != null){
				em.close();
			}
			if(emf != null){
				emf.close();
			}
		}
	}
}