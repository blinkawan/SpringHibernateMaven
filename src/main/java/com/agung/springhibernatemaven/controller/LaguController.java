package com.agung.springhibernatemaven.controller;

import com.agung.springhibernatemaven.exception.NotFoundException;
import com.agung.springhibernatemaven.model.Lagu;
import com.agung.springhibernatemaven.service.LaguService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 *
 * @author Agung Setiawan
 */
@Controller
public class LaguController {
    
    @Autowired
    LaguService laguService;
    
    public void setLaguService(LaguService laguService){
        this.laguService=laguService;
    }
    
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("lagus", laguService.getLagus());
        return "index";
    }
    
    @RequestMapping(value = "tambah",method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("lagu", new Lagu());
        return "tambah";
    }
    
    @RequestMapping(value = "tambah",method = RequestMethod.POST)
    public String adding(@ModelAttribute("lagu") Lagu lagu){
        laguService.saveLagu(lagu);
        return "redirect:/";
    }
    
    @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
    public String edit(Model model,@PathVariable("id") Integer id){
        Lagu lagu=laguService.getLagu(id);
        if(lagu==null){
            throw new NotFoundException();
        }
        model.addAttribute("lagu", lagu);
        return "edit";
    }
    
    @RequestMapping(value = "edit",method = RequestMethod.POST)
    public String editing(@ModelAttribute("lagu") Lagu lagu){
        laguService.updateLagu(lagu);
        return "redirect:/";
    }
    
    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public String deleting(@PathVariable("id") Integer id){
        Lagu lagu=laguService.getLagu(id);
        if(lagu==null){
            throw new NotFoundException();
        }
        laguService.deleteLagu(lagu);
        return "redirect:/";
    }
    
    @RequestMapping(value = "pdf",method = RequestMethod.GET)
    public String getPdfReport(Model model, HttpServletResponse response){        
        List<Lagu> lagus=laguService.getLagus();
        JRDataSource dataSource=new JRBeanCollectionDataSource(lagus);
        
        model.addAttribute("dataSource", dataSource);
        return "pdfReport";
    }
    
    @RequestMapping(value = "xls",method = RequestMethod.GET)
    public String getXlsReport(Model model, HttpServletResponse response){
        List<Lagu> lagus=laguService.getLagus();
        JRDataSource dataSource=new JRBeanCollectionDataSource(lagus);
        
        model.addAttribute("dataSource", dataSource);
        return "xlsReport";
    }
}
