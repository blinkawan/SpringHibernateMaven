/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.springhibernatemaven.controller;

import com.agung.springhibernatemaven.exception.NotFoundException;
import com.agung.springhibernatemaven.model.Lagu;
import com.agung.springhibernatemaven.service.LaguService;
import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

/**
 *
 * @author awanlabs
 */
public class LaguControllerTest {
    
    LaguController laguController;
    View view;
    MockMvc mockMvc;
    LaguService laguService;
    
    @Before
    public void setUp(){
        laguController=new LaguController();
        view=EasyMock.createMock(View.class);
        laguService=EasyMock.createMock(LaguService.class);
        
        laguController.setLaguService(laguService);
        mockMvc=MockMvcBuilders.standaloneSetup(laguController).setSingleView(view).build();
    }
    
    @Test
    public void testIndex() throws Exception{
        List<Lagu> daftarLagu=new ArrayList<Lagu>();
        EasyMock.expect(laguService.getLagus()).andReturn(daftarLagu);
        EasyMock.replay(laguService);
        
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(model().attribute("lagus", daftarLagu));
    }
    
    
    @Test
    public void testAdd() throws Exception{
        mockMvc.perform(get("/tambah")).andExpect(status().isOk()).andExpect(view().name("tambah"));
    }
    
//    @Test
//    public void testAdding(){
//        mockMvc.perform(post(null, urlVariables));
//    }

    @Test
    public void testEdit() throws Exception{
        Lagu lagu=new Lagu();
        EasyMock.expect(laguService.getLagu(1)).andReturn(lagu);
        EasyMock.replay(laguService);
        
        mockMvc.perform(get("/edit/1")).andExpect(status().isOk()).andExpect(view().name("edit"))
                .andExpect(model().attribute("lagu", lagu));
    }
    
    @Test
    public void testEditNotFound() throws Exception{
        EasyMock.expect(laguService.getLagu(1)).andReturn(null);
        EasyMock.replay(laguService);
        
        mockMvc.perform(get("/edit/1")).andExpect(status().is(404));
    }
}