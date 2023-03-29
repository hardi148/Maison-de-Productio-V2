package com.spring.springmvc_v_finale.service;

import com.spring.springmvc_v_finale.ExceptionTime.TimeAlertException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AlertService {
    public void CalculateTime(Timestamp tocalculate,int minute) throws TimeAlertException {
        Timestamp current = new Timestamp(System.currentTimeMillis());
        long t = tocalculate.getTime()-current.getTime();
        if ((t/minute)<=(minute/1000))
            throw new TimeAlertException("alert ! action non terminer a "+minute/1000+" min de fin ");
    }
//    public void checkAction() throws Exception {
//        ArrayList<Action> list = new Action().SelectAllByQuery(Connexion.getConnection(),"select * from action where finished=0");
//        int minute = 60000;
//        for (Action act:list) {
//            try {
//                CalculateTime(act.getDateAction(),minute);
//            }catch (TimeAlertException e){
//                Timestamp current = new Timestamp(System.currentTimeMillis());
//                System.out.println(act.getDateAction()+" "+current);
//                System.out.println(act.getDateAction().getTime()-current.getTime());
//                Notification alert = new Notification(e.getMessage()+" scene :"+act.getScene().getNom(),new Timestamp(System.currentTimeMillis()),act.getIdaction());
//                alert.save();
//            }
//        }
//    }
    @Scheduled(fixedRate = 60_000)
    public void runTask() {
        try {
//            checkAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        Timestamp timestamp1 = new Timestamp(System.currentTimeMillis() - 1000000);
//        System.out.println(timestamp1);
//        new AlertService().CalculateTime(timestamp1);
//    }
}
