package com.spring.springmvc_v_finale.controller;

import com.spring.springmvc_v_finale.model.Acteur;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ActeurController {

    @GetMapping("/versActeur")
    public ModelAndView versAuteur()
    {
        ModelAndView mod = new ModelAndView("acteur/Login");
        return mod;
    }

    @PostMapping("Login")
    public ModelAndView Login(HttpServletRequest request) throws  Exception
    {
        ModelAndView mod = new ModelAndView("acteur/email");
        String pseudo = request.getParameter("nom");
        int id = Integer.parseInt(request.getParameter("id"));
        List<Object[]> list = new Acteur().getResults(id);
        mod.addObject("id",id);
        mod.addObject("nom",pseudo);
        mod.addObject("list",list);
        mod.addObject("acteur",new Acteur().findByID(id));
        return mod;
    }
}
