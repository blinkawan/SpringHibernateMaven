package com.agung.springhibernatemaven.service;

import com.agung.springhibernatemaven.dao.LaguDao;
import com.agung.springhibernatemaven.model.Lagu;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Agung Setiawan
 */
@Service
@Transactional
public class LaguServiceImpl implements  LaguService{

    @Autowired
    LaguDao laguDao;
    
    public void setLaguDao(LaguDao laguDao){
        this.laguDao=laguDao;
    }
    
    @Override
    public List<Lagu> getLagus() {
        return laguDao.getLagus();
    }

    @Override
    public Lagu getLagu(Integer id) {
        return laguDao.getLagu(id);
    }

    @Override
    public Lagu saveLagu(Lagu lagu) {
        return laguDao.saveLagu(lagu);
    }

    @Override
    public Lagu updateLagu(Lagu lagu) {
        return laguDao.updateLagu(lagu);
    }

    @Override
    public Lagu deleteLagu(Lagu lagu) {
        return laguDao.deleteLagu(lagu);
    }
    
}
