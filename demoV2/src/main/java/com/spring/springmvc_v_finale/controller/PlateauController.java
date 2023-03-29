package com.spring.springmvc_v_finale.controller;

import com.spring.springmvc_v_finale.model.Plateau;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;

@Controller
public class PlateauController {
    @GetMapping("/plateau")
    public ModelAndView index() throws Exception {
        ModelAndView mod = new ModelAndView("plateau/Plateau");
        ArrayList<Plateau> list = new Plateau().SelectAll();
        mod.addObject("plateau_list",list);
        return mod;
    }
    @PostMapping("/modify/plateau")
    public ModelAndView modifyPlateau(HttpServletRequest req) throws Exception {
        String idplateau = req.getParameter("idplateau");
        System.out.println(idplateau);
        Date debutIndisponibilite = Date.valueOf(req.getParameter("debut_indisponibilite"));
        Date finIndisponibilite = Date.valueOf(req.getParameter("fin_indisponibilite"));
        Plateau e = new Plateau(Integer.parseInt(idplateau));
        e = e.findById();
//        e.setDateDebutIndisponibilite(debutIndisponibilite);
//        e.setDateFinIndisponibile(finIndisponibilite);
        e.Update();
        return index();
    }
}
