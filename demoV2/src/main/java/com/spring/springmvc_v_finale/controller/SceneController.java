package com.spring.springmvc_v_finale.controller;

import com.spring.springmvc_v_finale.model.Action;
import com.spring.springmvc_v_finale.model.Calendar;
import com.spring.springmvc_v_finale.model.Notification;
import com.spring.springmvc_v_finale.model.Scene;
import com.spring.springmvc_v_finale.model.suggestion.Suggestion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Controller
public class SceneController {
    public static int page = 4;

    @GetMapping("/")
    public ModelAndView index(HttpServletRequest req) throws Exception {
        return ToList(req);
    }

    //mandefa anle izy any am page de creation scene
    @GetMapping("/create_scene")
    public ModelAndView sendToCreate() {
        ModelAndView mod = new ModelAndView("scene/ajout_scene");
        return mod;
    }

    //    mandefa anle izy any am page de modification scene
// mila pathvariable izay id anle scene izy
    @GetMapping("/modify/{idscene}")
    public ModelAndView sendToModify(@PathVariable("idscene") int idscene) throws Exception {
        ModelAndView mod = new ModelAndView("scene/modification_scene");
        Scene scene = new Scene(idscene);
        scene = scene.findById();
        mod.addObject("scene", scene);
        mod.addObject("mess", "");
        return mod;
    }

    //    code maka anle scene depuis formulaire de modif ou creation scene
    public Scene getScene(HttpServletRequest req, int action) {
        String nom = req.getParameter("nom");
        String debut = req.getParameter("debut");
        String time_debut = req.getParameter("time_debut");
        String fin = req.getParameter("fin");
        String time_fin = req.getParameter("time_fin");
        String datedebut = (action == 0) ? debut + " " + time_debut + ":00" : debut + " " + time_debut;
        String datefin = (action == 0) ? fin + " " + time_fin + ":00" : fin + " " + time_fin;
        System.out.println(datefin + " " + action);
        System.out.println(datedebut + " " + action);
        Timestamp d_debut = null;
        Timestamp fins = null;
        try {
            d_debut = Timestamp.valueOf(datedebut);
        } catch (IllegalArgumentException e) {
            d_debut = Timestamp.valueOf(datedebut + ":00");
        }
        try {
            fins = Timestamp.valueOf(datefin);
        } catch (IllegalArgumentException e) {
            fins = Timestamp.valueOf(datefin + ":00");
        }
        Scene scene = new Scene(nom, d_debut, fins);
        return scene;
    }
// mila pathvariable izay id anle scene izy

    @PostMapping("/update_scene/{idscene}")
    public ModelAndView update_scene(@PathVariable("idscene") int idscene, HttpServletRequest req) throws Exception {
        ModelAndView mod = new ModelAndView("scene/modification_scene");

        Scene scene = getScene(req, 1);
        scene.setIdscene(idscene);
        scene.updateById();
        mod.addObject("scene", scene);
        mod.addObject("mess", "update reussi");
        return mod;
    }

    //    valider l'enreistrement de la scene
    @PostMapping("/validate_scene")
    public ModelAndView validate_scene(HttpServletRequest req) throws Exception {
//        ModelAndView mod = new ModelAndView("scene/ajout_scene");

        Scene scene = getScene(req, 0);
        scene = scene.save();
        System.out.println(scene.getNom() + " id:" + scene.getIdscene());
//        mod.addObject("mess","success lesy");
        return ToList(req);
    }

    //    send list of scene
    @GetMapping("/list_scene")
    public ModelAndView ToList(HttpServletRequest req) throws Exception {
        ModelAndView mod = new ModelAndView("list/list_scene");
        Scene scene = new Scene();
        int pages = (req.getSession().getAttribute("page") == null) ? page : (int) req.getSession().getAttribute("page");
        if (req.getSession().getAttribute("page") == null)
            req.getSession().setAttribute("page", pages);
        int first = (req.getParameter("first") == null) ? 0 : Integer.parseInt(req.getParameter("first"));
        int t = scene.SelectAll().size();
        int size = calculatePage(pages, t);
        mod.addObject("count", size);
        mod.addObject("list", scene.paginateAll(pages, first));
        mod.addObject("action", "list");
        return mod;
    }

    //    parametrage de nombre a afficher pour la pagination
    @PostMapping("/changepage")
    public ModelAndView changePage(@RequestParam("page") int page, HttpServletRequest req) throws Exception {
        req.getSession().setAttribute("page", page);
        return ToList(req);
    }

    //    search with pagination
    @GetMapping("/search")
    public ModelAndView search(HttpServletRequest req) throws Exception {
        ModelAndView mod = new ModelAndView("list/list_scene");
        String search = req.getParameter("search");
        Scene e = new Scene();
        int pages = (req.getSession().getAttribute("page") == null) ? page : (int) req.getSession().getAttribute("page");
        int first = (req.getParameter("first") == null) ? 0 : Integer.parseInt(req.getParameter("first"));
        String sql = "select * from scene where lower(nom) like lower('%" + search + "%') order by idscene asc";

        if (search == null)
            sql = (String) req.getSession().getAttribute("query");
        req.getSession().setAttribute("query", sql);
        ArrayList<Scene> list = e.paginateSearch(sql, pages, first);
        int t = e.SelectAllByQuerry(sql).size();
        int size = calculatePage(pages, t);
        mod.addObject("count", size);
        mod.addObject("list", list);
        mod.addObject("action", "search");
        return mod;
    }

    private int calculatePage(int pages, int t) {
        double size = (double) t / pages;
        if (t % pages != 0)
            size++;
        return (int) size;
    }

    public static String convertTimestampTodate(Timestamp date) {
        Date t = new Date(date.getTime());
        return String.valueOf(t);
    }

    //    show all alert or notification about task
    @GetMapping("/alert")
    public ModelAndView getAlert() throws Exception {
        ModelAndView mod = new ModelAndView("notification/alert");
        ArrayList<Notification> notif = new Notification().SelectAll();
        mod.addObject("notif", notif);
        return mod;
    }

    @GetMapping("/suggest")
    public ModelAndView Suggest(HttpServletRequest req) throws Exception {
        ModelAndView mod = new ModelAndView("scene/suggest");
//        Date debut = Date.valueOf(req.getParameter("debut"));
//        Date fin = Date.valueOf(req.getParameter("fin"));
        ArrayList<Action> list = new Suggestion().suggest();
        list.sort(new Comparator<Action>() {
            public int compare(Action a1, Action a2) {
                return a1.getPlateau().getDateDebutIndisponibilite().compareTo(a2.getPlateau().getDateDebutIndisponibilite());
            }
        });
        ArrayList<Timestamp> listDay = Suggestion.countDayOfWork(list);
        req.getSession().setAttribute("list_suggest", list);
        mod.addObject("listDay", listDay);
        mod.addObject("list", list);
        return mod;
    }

    @GetMapping("/calendar/detail/{key}")
    public ModelAndView getDetailTime(@PathVariable("key") String key, @RequestParam("day") int day, HttpServletRequest req) {
        ArrayList<Action> list = (ArrayList<Action>) req.getSession().getAttribute(key);
        ModelAndView mod = new ModelAndView("scene/Detail_plan");
        mod.addObject("list", Suggestion.getListActionFromDate(day, list));
        return mod;
    }

    @GetMapping("/calendar")
    public ModelAndView ToCalendar(HttpServletRequest req) throws Exception {
        ModelAndView mod = new ModelAndView("Calendar");
        ArrayList<Calendar> cal = new Calendar().SelectAll();
        cal.sort(new Comparator<Calendar>() {
            public int compare(Calendar a1, Calendar a2) {
                return a1.getDateCalendar().compareTo(a2.getDatefin());
            }
        });
        ArrayList<Timestamp> listDay = Calendar.countDayOfWork(cal);
        req.getSession().setAttribute("list_calendar", cal);
        mod.addObject("listDay", listDay);
        return mod;
    }

    @GetMapping("/calendar_plan/detail/{key}")
    public ModelAndView getDetailCalendar(@PathVariable("key") String key, @RequestParam("day") int day,@RequestParam("date") Date date_action,HttpServletRequest req) {
        ArrayList<Calendar> list = (ArrayList<Calendar>) req.getSession().getAttribute(key);
        ModelAndView mod = new ModelAndView("scene/Detail_calendar");
        mod.addObject("list", Calendar.getListActionFromDate(day, list));
        mod.addObject("date_action",date_action);
        return mod;
    }

    @PostMapping("/validate_suggest")
    public ModelAndView validate_suggest(HttpServletRequest req) throws Exception {
        ArrayList<Action> list = new Suggestion().suggest();
        for (Action act : list) {
            Calendar calendar = new Calendar(act.getIdaction(), act.getPlateau().getDateDebutIndisponibilite(), act.getPlateau().getDateFinIndisponibile());
//            eto le misaave calendar de eto
            calendar.save();
        }
        return ToCalendar(req);
    }
    @GetMapping("/to_planification")
    public String Toplanifier(){
       return "scene/SetPlanning";
    }

}
