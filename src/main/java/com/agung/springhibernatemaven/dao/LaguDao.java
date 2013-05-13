package com.agung.springhibernatemaven.dao;

import com.agung.springhibernatemaven.model.Lagu;
import java.util.List;

/**
 *
 * @author Agung Setiawan
 */
public interface LaguDao {
    public List<Lagu> getLagus();
    public Lagu getLagu(Integer id);
    public Lagu saveLagu(Lagu lagu);
    public Lagu updateLagu(Lagu lagu);
    public Lagu deleteLagu(Lagu lagu);
}
