/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fciencias.is2.adminaula.modelo.manejador;

import fciencias.is2.adminaula.modelo.Academico;
import fciencias.is2.adminaula.modelo.manejador.exceptions.NonexistentEntityException;
import fciencias.is2.adminaula.modelo.manejador.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author jrivera
 */
public class AcademicoJpaController implements Serializable {

    public AcademicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Academico academico) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(academico);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAcademico(academico.getRfc()) != null) {
                throw new PreexistingEntityException("Academico " + academico + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Academico academico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            academico = em.merge(academico);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = academico.getRfc();
                if (findAcademico(id) == null) {
                    throw new NonexistentEntityException("The academico with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Academico academico;
            try {
                academico = em.getReference(Academico.class, id);
                academico.getRfc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The academico with id " + id + " no longer exists.", enfe);
            }
            em.remove(academico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Academico> findAcademicoEntities() {
        return findAcademicoEntities(true, -1, -1);
    }

    public List<Academico> findAcademicoEntities(int maxResults, int firstResult) {
        return findAcademicoEntities(false, maxResults, firstResult);
    }

    private List<Academico> findAcademicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Academico.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Academico findAcademico(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Academico.class, id);
        } finally {
            em.close();
        }
    }

    public int getAcademicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Academico> rt = cq.from(Academico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
