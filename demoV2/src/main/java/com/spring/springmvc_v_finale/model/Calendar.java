package com.spring.springmvc_v_finale.model;

import com.spring.springmvc_v_finale.utils.Connex.Connexion;
import com.spring.springmvc_v_finale.utils.DAO.ObjectBDD;
import com.spring.springmvc_v_finale.utils.inter.ForeignKeyAnnotation;
import com.spring.springmvc_v_finale.utils.inter.IdAnnotation;
import com.spring.springmvc_v_finale.utils.inter.KeyAnnotation;
import com.spring.springmvc_v_finale.utils.inter.TableAnnotation;

import java.sql.Timestamp;
import java.util.ArrayList;

@TableAnnotation
public class Calendar extends ObjectBDD {
    @KeyAnnotation
    @IdAnnotation(name = "idCalendar")
    private int idCalendar;
    @KeyAnnotation
    private int idaction;
    @KeyAnnotation
    private Timestamp dateCalendar;
    @KeyAnnotation
    private Timestamp datefin;

    @ForeignKeyAnnotation(name = "idaction",references = "idaction")
    private Action action;

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Timestamp getDatefin() {
        return datefin;
    }

    public void setDatefin(Timestamp datefin) {
        this.datefin = datefin;
    }

public Calendar(int idaction, Timestamp dateCalendar, Timestamp datefin) {
        this.idaction = idaction;
        this.dateCalendar = dateCalendar;
        this.datefin = datefin;
    }

    public Calendar() {
    }

    public Calendar(int idaction) {
        this.idaction = idaction;
    }

    public Calendar(int idaction, Timestamp dateCalendar) {
        this.idaction = idaction;
        this.dateCalendar = dateCalendar;
    }

    public Calendar(int idCalendar, int idaction, Timestamp dateCalendar) {
        this.idCalendar = idCalendar;
        this.idaction = idaction;
        this.dateCalendar = dateCalendar;
    }

    public int getIdCalendar() {
        return idCalendar;
    }

    public void setIdCalendar(int idCalendar) {
        this.idCalendar = idCalendar;
    }

    public int getIdaction() {
        return idaction;
    }

    public void setIdaction(int idaction) {
        this.idaction = idaction;
    }

    public Timestamp getDateCalendar() {
        return dateCalendar;
    }

    public void setDateCalendar(Timestamp dateCalendar) {
        this.dateCalendar = dateCalendar;
    }
    public void save() throws Exception {
        super.saveAll(Connexion.getConnection());
    }
    public ArrayList<Calendar> SelectAll() throws Exception {
        return super.SelectAll(Connexion.getConnection());
    }
    public ArrayList<Calendar> SelectAllByQuerry(String sql) throws Exception {
        return SelectAllByQuery(Connexion.getConnection(),sql);
    }
    public static ArrayList<Timestamp> countDayOfWork(ArrayList<Calendar> list){
        ArrayList<Timestamp> listDay = new ArrayList<>();
        if (list.size()<=0) {
            return listDay;
        }
        int first = list.get(0).getDateCalendar().getDate();
        listDay.add(list.get(0).getDateCalendar());
        for (Calendar act : list) {
            int dates = act.getDateCalendar().getDate();
            if (first!=dates) {
                listDay.add(act.getDateCalendar());
                first = dates;
            }
        }
        return listDay;
    }
    public static ArrayList<Calendar> getListActionFromDate(int date,ArrayList<Calendar> list){
        ArrayList<Calendar> res = new ArrayList<>();
        for (Calendar act:list) {
            if (act.getDateCalendar().getDate()==date)
                res.add(act);
        }
        return res;
    }
}
