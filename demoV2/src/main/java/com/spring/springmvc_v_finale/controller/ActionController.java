package com.spring.springmvc_v_finale.controller;

import com.spring.springmvc_v_finale.model.*;
import com.spring.springmvc_v_finale.utils.Connex.Connexion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class ActionController {
//    send to page ajout action
    @GetMapping("/action/add/{idscene}")
    public ModelAndView toFormAction(@PathVariable("idscene") int idscene) throws Exception {
        ModelAndView mod = new ModelAndView("action/add_action");
        Scene scene = new Scene(idscene);
        scene = scene.findById();
//        mod.addObject("list_date",scene.getMiddleDate());
        mod.addObject("scene",idscene);
        mod.addObject("person",new Personnage().SelectAll());
        mod.addObject("plateau",new Plateau().SelectAll());
        return mod;
    }

//    validate ajout action
    @PostMapping("/validate_action/{idscene}")
    public ModelAndView validate_action(@PathVariable("idscene") int idscene, HttpServletRequest req) throws Exception {
//        String dateAction = req.getParameter("dateAction");
        String[] image = req.getParameterValues("image");
//        String fin = req.getParameter("fin");
//        String d = dateAction+" "+fin+":00";
//        System.out.println(d);
//        Timestamp datefin = Timestamp.valueOf(d);
        int dure = Integer.parseInt(req.getParameter("dure"));
        String description = req.getParameter("description");
        int plateau = Integer.parseInt(req.getParameter("plateau"));
        String[] person = req.getParameterValues("person");
        Action act = new Action(idscene,dure,description);
        act.setIdplateau(plateau);
        for (int i = 0; i < person.length; i++) {
                try {
//                    Mis_en_Action m = new Mis_en_Action(Integer.parseInt(person[i]),plateau);
                    Mis_en_Action m = new Mis_en_Action(Integer.parseInt(person[i]));
                    act.getList_mise_action().add(m);
                }catch (Exception e){e.printStackTrace();}
        }
        for (String img:image) {
            try {
                Photos p = new Photos(img);
                act.getList_photo().add(p);
            }catch (Exception e){e.printStackTrace();}
        }
        act.save();
        return Planning(idscene);
    }
//    set terminate if an action is finish
    @GetMapping("/changestatus/{idaction}")
    public ModelAndView ChangeStatus(@PathVariable("idaction") int idaction) throws Exception {
//        ModelAndView mod = new ModelAndView(/)
        Action act = new Action(idaction);
        act = act.findById();
        act.setFinished(1);
        act.updateById(Connexion.getConnection());
        return Planning(act.getIdscene());
    }
//    show planning by scene
    @GetMapping("/planning/{idscene}")
    public ModelAndView Planning(@PathVariable("idscene") int idscene) throws Exception {
        ModelAndView mod = new ModelAndView("scene/planning");
        mod.addObject("plan",new Action().findByScene(idscene));
        Scene scenes = new Scene(idscene);
        scenes = scenes.findById();
//        mod.addObject("list_date",scenes.getMiddleDate());
        mod.addObject("scene",idscene);
        mod.addObject("person",new Personnage().SelectAll());
        mod.addObject("plateau",new Plateau().SelectAll());
        return mod;
    }

    @PostMapping("/search_is_validate/{idscene}")
    public ModelAndView search(@RequestParam("search") int etat,@PathVariable("idscene") int idscene) throws Exception
    {
        ModelAndView mod = new ModelAndView("action/result");
        ArrayList<Action> result = new Action().findByEtat(etat,idscene);
        mod.addObject("scene",idscene);
        mod.addObject("plan",result);
        mod.addObject("person",new Personnage().SelectAll());
        mod.addObject("plateau",new Plateau().SelectAll());
        return mod;
    }
}
