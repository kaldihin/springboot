package com.example.demo.daoimplementations;

import com.example.demo.models.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.demo.daointerfaces.OfficeDao;
import com.example.demo.models.Office;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> list(Integer orgId) {
        TypedQuery<Office> query = em.createQuery("select c from Office c where c.orgId = :orgId", Office.class);
        query.setParameter("orgId", orgId);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office byId(Integer id) {
        return em.find(Office.class, id);
    }

    @Override
    public void update(Office office) {
        em.merge(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Office office) {
        em.persist(office);
    }

    @Override
    public void delete(Integer id) {
        Office office = em.find(Office.class, id);
        em.remove(office);
    }
}