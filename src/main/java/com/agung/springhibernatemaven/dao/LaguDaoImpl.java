package com.agung.springhibernatemaven.dao;

import com.agung.springhibernatemaven.model.Lagu;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Agung Setiawan
 */
@Repository
public class LaguDaoImpl implements LaguDao{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Lagu> getLagus() {
        return sessionFactory.getCurrentSession().createQuery("select l from Lagu l").list();
    }

    @Override
    public Lagu getLagu(Integer id) {
        return (Lagu) sessionFactory.getCurrentSession().get(Lagu.class, id);
    }

    @Override
    public Lagu saveLagu(Lagu lagu) {
        sessionFactory.getCurrentSession().save(lagu);
        return lagu;
    }

    @Override
    public Lagu updateLagu(Lagu lagu) {
        sessionFactory.getCurrentSession().update(lagu);
        return lagu;
    }

    @Override
    public Lagu deleteLagu(Lagu lagu) {
        sessionFactory.getCurrentSession().delete(lagu);
        return lagu;
    }
    
}
