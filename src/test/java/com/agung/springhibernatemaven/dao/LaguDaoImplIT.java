package com.agung.springhibernatemaven.dao;

import com.agung.springhibernatemaven.init.WebAppConfigTest;
import com.agung.springhibernatemaven.model.Lagu;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author awanlabs
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebAppConfigTest.class)
@TestExecutionListeners({/*DbUnitTestExecutionListener.class,*/ DependencyInjectionTestExecutionListener.class, 
    TransactionDbUnitTestExecutionListener.class})
@Transactional
public class LaguDaoImplIT {
    
    @Autowired
    LaguDao laguDao;
    
    @Test
    @DatabaseSetup("classpath:sampleData.xml")
    public void testGetLagus() {
        List<Lagu> daftarLagu=laguDao.getLagus();
        
        assertNotNull(daftarLagu);
        assertEquals(2, daftarLagu.size());
        assertEquals("Aku dan Bintang", daftarLagu.get(0).getJudul());
    }
    
    @Test
    @DatabaseSetup("classpath:sampleData.xml")
    public void testGetLagu(){
        Lagu lagu=laguDao.getLagu(2);
        
        assertNotNull(lagu);
        assertEquals("Stand By Me", lagu.getJudul());
        assertEquals("Oasis", lagu.getPenyanyi());
    }
    
    @Test
    @DatabaseSetup("classpath:sampleData.xml")
    @ExpectedDatabase("classpath:expectedSave.xml")
    public void testSaveLagu(){
        Lagu lagu=new Lagu();
        lagu.setJudul("Pupus");
        lagu.setPencipta("Ahmad Dhani");
        lagu.setPenyanyi("Dewa");
        
        Lagu laguPersisted=laguDao.saveLagu(lagu);
        
        assertNotNull(laguPersisted);
        assertNotNull(laguPersisted.getId());
    }
    
    @Test
    @DatabaseSetup("classpath:sampleData.xml")
//    @ExpectedDatabase("classpath:expectedUpdate.xml")
    public void testUpdateLagu(){
        Lagu lagu=laguDao.getLagu(2);
        assertEquals("Stand By Me", lagu.getJudul());
        
        lagu.setJudul("Semua Tentang Kita");
        Lagu laguUpdated=laguDao.updateLagu(lagu);
        
        Lagu laguBaru=laguDao.getLagu(2);
        
        assertNotNull(laguUpdated);
        assertEquals("Semua Tentang Kita", laguUpdated.getJudul());
        assertEquals("Semua Tentang Kita", laguBaru.getJudul());
    }
    
    @Test
    @DatabaseSetup("classpath:sampleData.xml")
    @ExpectedDatabase("classpath:expectedDelete.xml")
    public void testDeleteLagu(){
        Lagu lagu=laguDao.getLagu(2);
        Lagu laguDeleted=laguDao.deleteLagu(lagu);
        Lagu laguKosong=laguDao.getLagu(laguDeleted.getId());
        
        assertNull(laguKosong);
        assertEquals(1, laguDao.getLagus().size());
    }
}