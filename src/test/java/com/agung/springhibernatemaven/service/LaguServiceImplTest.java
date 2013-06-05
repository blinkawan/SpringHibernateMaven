/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.springhibernatemaven.service;

import com.agung.springhibernatemaven.dao.LaguDao;
import com.agung.springhibernatemaven.model.Lagu;
import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author awanlabs
 */

public class LaguServiceImplTest {
    
    LaguServiceImpl instance;
    LaguDao laguDao;
    Lagu lagu;

    @Before
    public void init() {
        instance=new LaguServiceImpl();
        laguDao= EasyMock.createMock(LaguDao.class);
        instance.setLaguDao(laguDao);
        
        lagu=new Lagu();
        lagu.setId(1);
        lagu.setJudul("Indonesia Raya");
        lagu.setPencipta("WR Supratman");
        lagu.setPenyanyi("Bangsa Indonesia");
    }
    
    @Test
    public void testGetLagus(){
        List<Lagu> daftarLagus=new ArrayList<Lagu>();
        daftarLagus.add(lagu);
        
        EasyMock.expect(laguDao.getLagus()).andReturn(daftarLagus);
        EasyMock.replay(laguDao);
        List<Lagu> lagus=instance.getLagus();
        
        assertEquals(daftarLagus, lagus);
        assertEquals(1, lagus.size());
        assertEquals(lagu.getId(), lagus.get(0).getId());
        assertEquals(lagu.getJudul(), lagus.get(0).getJudul());
        assertEquals(lagu.getPencipta(), lagus.get(0).getPencipta());
        assertEquals(lagu.getPenyanyi(), lagus.get(0).getPenyanyi());
        
        EasyMock.verify(laguDao);
    }
    
    @Test
    public void testGetLagu(){
        
        EasyMock.expect(laguDao.getLagu(1)).andReturn(lagu);
        EasyMock.replay(laguDao);
        Lagu laguDB=instance.getLagu(1);
        
        assertLagu(lagu, laguDB);
        
    }
    
    @Test
    public void testSaveLagu(){
        EasyMock.expect(laguDao.saveLagu(lagu)).andReturn(lagu);
        EasyMock.replay(laguDao);
        
        Lagu laguPersisted=instance.saveLagu(lagu);
        
        assertLagu(lagu, laguPersisted);
       
    }
    
    @Test
    public void testUpdateLagu(){
        Lagu laguUpdated=new Lagu();
        laguUpdated.setId(1);
        laguUpdated.setJudul("Indonesia Raya UPDATE");
        laguUpdated.setPencipta("WR Supratman UPDATE");
        laguUpdated.setPenyanyi("Bangsa Indonesia UPDATE");
        
        EasyMock.expect(laguDao.updateLagu(laguUpdated)).andReturn(laguUpdated);
        EasyMock.replay(laguDao);
        
        Lagu laguBaru=instance.updateLagu(laguUpdated);
        
        assertLagu(laguUpdated, laguBaru);
        
        assertEquals(lagu.getId(), laguBaru.getId());
        assertNotEquals(lagu.getJudul(), laguBaru.getJudul());
        assertNotEquals(lagu.getPencipta(), laguBaru.getPencipta());
        assertNotEquals(lagu.getPenyanyi(), laguBaru.getPenyanyi());
    }
    
    @Test
    public void testDeleteLagu(){
        EasyMock.expect(laguDao.deleteLagu(lagu)).andReturn(lagu);
        EasyMock.replay(laguDao);
        
        Lagu laguDeleted=instance.deleteLagu(lagu);
        
        assertLagu(lagu, laguDeleted);
    }
    
    private void assertLagu(Lagu expected,Lagu actual){
        assertNotNull(actual);
        assertEquals(expected, actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getJudul(), actual.getJudul());
        assertEquals(expected.getPencipta(), actual.getPencipta());
        assertEquals(expected.getPenyanyi(), actual.getPenyanyi());
    }
}