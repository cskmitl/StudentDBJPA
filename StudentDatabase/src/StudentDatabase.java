/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package studentdatabase;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 *
 * @author DELL
 */
public class StudentDatabase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    /**
     *
     */
    public EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Student student) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void update(Student student) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(student);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void delete(int id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Student student = em.find(Student.class, id);
            em.remove(student);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Student> findAll() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT s FROM Student s");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Student findById(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Student.class, id);
        } finally {
            em.close();
        }
    }

    public List<Student> findByName(String name) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT s FROM Student s WHERE s.name = :name");
            query.setParameter("name", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
