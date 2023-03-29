package com.spring.springmvc_v_finale.model.suggestion;

import com.spring.springmvc_v_finale.model.Action;
import com.spring.springmvc_v_finale.model.Plateau;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

public class Suggestion {
    private ArrayList<Plateau> list_plateau=null;
    private ArrayList<Action> list_action = null;
    public static Time debut_h = new Time(8,0,0);
    public static Time fin_h = new Time(16,0,0);
    public ArrayList<Action> suggest() throws Exception {
        list_action = getList_action();
        list_plateau = getList_plateau();
        ArrayList<Action> list = new ArrayList<>();
        for (Action act:list_action) { //boucle de la list action non_valider
            for (Plateau pl:getList_plateau()) {//boucle de la liste de plateau
                if (pl.getIdplateau()== act.getPlateau().getIdplateau()) {//verifier si le plateau de l'action correspond au plateau de la liste
                    // eo am voloany dia lay if debut et fin indispo plateau =null then maka anle debut anle scene de ny fin = debut+dure action
//                    System.out.println("durre "+act.getDateAction()+" debut "+pl.getDateDebutIndisponibilite()+" fin "+pl.getDateFinIndisponibile()+" action "+act.getIdaction()+" scene ");
                    Timestamp debut_heur = modifyHour(act.getScene().getDateDebut(),debut_h);///eto mi modifier debut
                    if ((pl.getDateDebutIndisponibilite()==null) && (pl.getDateFinIndisponibile()==null))  {
                        boolean checkweek = verifiyWeek(debut_heur);
                        debut_heur = (checkweek)?changeDayTolundi(debut_heur):debut_heur;
                        act.getPlateau().setDateDebutIndisponibilite(debut_heur);
                        act.getPlateau().setDateFinIndisponibile(AddHours(debut_heur,act.getDateAction()));
                    }
                    //        else debut = finIndisponibilite + 1 min
                    else {
                        boolean checkweek = verifiyWeek(pl.getDateDebutIndisponibilite());
                        Timestamp fin_pl = (checkweek)?changeDayTolundi(pl.getDateFinIndisponibile()):pl.getDateFinIndisponibile();
                        if (extractHour(pl.getDateFinIndisponibile()).compareTo(fin_h)>0){///finIndisponibiliter superieur a finh
//                            System.out.println(extractHour(pl.getDateFinIndisponibile()).compareTo(fin_h)+" comparaison "+extractHour(pl.getDateFinIndisponibile())+"  "+fin_h+" ato ny action "+act.getIdaction()+" plateau "+pl.getIdplateau());
//                            System.out.println("act "+act.getIdaction());
                            Timestamp tmp = DebutTravail(fin_pl,debut_h);
                            boolean checkweek_m = verifiyWeek(tmp);
                            Timestamp fintmp = (checkweek_m)?changeDayTolundi(tmp):tmp;
                            act.getPlateau().setDateDebutIndisponibilite(fintmp);
                            act.getPlateau().setDateFinIndisponibile(AddHours(act.getPlateau().getDateDebutIndisponibilite(),act.getDateAction()));
                        }else {
                            act.getPlateau().setDateDebutIndisponibilite(addMinutes(fin_pl));
                            act.getPlateau().setDateFinIndisponibile(AddHours(act.getPlateau().getDateDebutIndisponibilite(),act.getDateAction()));
                        }
                    }
                    pl.setDateDebutIndisponibilite(act.getPlateau().getDateDebutIndisponibilite());
                    pl.setDateFinIndisponibile(act.getPlateau().getDateFinIndisponibile());
                    list.add(act);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(debut_h);
        ArrayList<Action> actions = new Suggestion().suggest();
        System.out.println("planning");
        for (Action act:actions) {
            System.out.println("scene "+act.getScene().getNom());
            System.out.println("--action "+act.getIdaction()+" duration :"+act.getDateAction()+" hours");
            System.out.println(" "+act.getPlateau().getNomplateau()+" debut "+act.getPlateau().getDateDebutIndisponibilite()+" fin "+act.getPlateau().getDateFinIndisponibile());
        }

    }
    public ArrayList<Action> getList_action() throws Exception {
        if (list_action == null) {
            list_action= new Action().SelectAllActionNonValidate();
        }
        return list_action;
    }

    public void setList_action(ArrayList<Action> list_action) {
        this.list_action = list_action;
    }

    public ArrayList<Plateau> getList_plateau() throws Exception {
        if (list_plateau == null) {
            list_plateau = new Plateau().SelectAll();
        }
        return list_plateau;
    }

    public void setList_plateau(ArrayList<Plateau> list_plateau) {
        this.list_plateau = list_plateau;
    }

    private Timestamp AddHours(Timestamp add, int hours){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(add.getTime());
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        add = new Timestamp(calendar.getTimeInMillis());
        return add;
    }
    private Timestamp addMinutes(Timestamp add){
        LocalDateTime localDateTime = add.toLocalDateTime();
        LocalDateTime oneMinuteLater = localDateTime.plusMinutes(1);
        return Timestamp.valueOf(oneMinuteLater);
    }
    public Time extractHour(Timestamp time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time.getTime());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        return new Time(hour,minute,second);
    }
    private Timestamp modifyHour(Timestamp modify,Time time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(modify.getTime());
        Calendar t = Calendar.getInstance();
        t.setTimeInMillis(time.getTime());
        calendar.set(Calendar.HOUR_OF_DAY,t.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE,t.get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND,t.get(Calendar.SECOND));
        return new Timestamp(calendar.getTimeInMillis());
    }
    public Timestamp DebutTravail(Timestamp add,Time debut){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(add.getTime());
        Calendar t = Calendar.getInstance();
        t.setTimeInMillis(debut.getTime());
        int day =calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH,day+1);

        calendar.set(Calendar.HOUR_OF_DAY,t.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE,t.get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND,t.get(Calendar.SECOND));
        return new Timestamp(calendar.getTimeInMillis());
    }
    private boolean verifiyWeek(Timestamp day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(day.getTime());
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // obtenir le jour de la semaine (1 = dimanche, 2 = lundi, ..., 7 = samedi)
//            System.out.println(dayOfWeek+" day of month "+calendar.get(Calendar.DAY_OF_MONTH));
        return (dayOfWeek == Calendar.SATURDAY) || (dayOfWeek == Calendar.SUNDAY);
    }
    private Timestamp changeDayTolundi(Timestamp day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(day.getTime());
        int daym = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        int daymonth = calendar.get(Calendar.DAY_OF_MONTH);
        if (daymonth <= daym) {
            calendar.add(Calendar.DAY_OF_MONTH, 7); // Ajouter 7 jours pour passer à la semaine suivante
//            daymonth = calendar.get(Calendar.DAY_OF_MONTH);
        }
        return new Timestamp(calendar.getTimeInMillis());
    }
    public static String getDayOfDate(Timestamp day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(day.getTime());
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return new DateFormatSymbols().getWeekdays()[dayOfWeek];
    }
    public static ArrayList<Timestamp> countDayOfWork(ArrayList<Action> list){
        ArrayList<Timestamp> listDay = new ArrayList<>();
        if (list.size()<=0) {
            return listDay;
        }
        int first = list.get(0).getPlateau().getDateDebutIndisponibilite().getDate();
        listDay.add(list.get(0).getPlateau().getDateDebutIndisponibilite());
        for (Action act : list) {
            int dates = act.getPlateau().getDateDebutIndisponibilite().getDate();
            if (first!=dates) {
                listDay.add(act.getPlateau().getDateDebutIndisponibilite());
                first = dates;
            }
        }
        return listDay;
    }

    public static ArrayList<Action> getListActionFromDate(int date,ArrayList<Action> list){
        ArrayList<Action> res = new ArrayList<>();
        for (Action act:list) {
            if (act.getPlateau().getDateDebutIndisponibilite().getDate()==date)
                res.add(act);
        }
        return res;
    }
}
