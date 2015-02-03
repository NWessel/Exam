package dk.cphbusiness.sem4.model;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DbFacade {
   
    private EntityManager em;
    private static DbFacade instance = null;
    
    private DbFacade(){
       
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Valgfagsprojekt-PU");
        this.em = emFactory.createEntityManager();
    }
    
    public static DbFacade getInstance() {
        
        if(instance == null) {
        instance = new DbFacade();}
        return instance;
    }
    
    public Collection<Valgfag> listValgfagRunde1(){
        return em.createNamedQuery("Runde1.findAll").getResultList();
    } 
    
    public Collection<Runde2> listValgfagRunde2(){
        return em.createNamedQuery("Runde2.findAll").getResultList();
    }
    
    public Collection<Valgfag> listValgfagRunde1Resultat(){
        return em.createNamedQuery("Runde1res.findAllValgfag").getResultList();
    } 
    
    public Collection<Runde1res> hentRunde1Resultater(){
        return em.createNamedQuery("Runde1res.findAll").getResultList();
    }

    public Studerende findStuderende(String email){
        return em.find(Studerende.class, email);
    }
    
    public void gemStuderende(Studerende s){
        em.getTransaction().begin();
        em.merge(s);
        em.getTransaction().commit();
    } 
    
    public void gemRunde1res (Runde1res r1res){
        em.getTransaction().begin();
        em.merge(r1res);
        em.getTransaction().commit();
    }
    
    public void gemRunde2Liste (Collection<Runde2> runde2Liste){
        em.getTransaction().begin();
        for (Runde2 r2 : runde2Liste) {
            em.persist(r2);
        }
        em.getTransaction().commit();
    }
    
    public int sletRunde2Liste(){
        em.getTransaction().begin();
        System.out.println("1");
        int deleted = em.createNamedQuery("Runde2.deleteAll").setParameter("tal", 0).executeUpdate();
        System.out.println("2");
        em.getTransaction().commit();
        return deleted;
    }
}
