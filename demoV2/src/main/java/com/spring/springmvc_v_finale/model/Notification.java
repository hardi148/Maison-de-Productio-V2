package com.spring.springmvc_v_finale.model;

import com.spring.springmvc_v_finale.utils.Connex.Connexion;
import com.spring.springmvc_v_finale.utils.DAO.ObjectBDD;
import com.spring.springmvc_v_finale.utils.inter.IdAnnotation;
import com.spring.springmvc_v_finale.utils.inter.KeyAnnotation;
import com.spring.springmvc_v_finale.utils.inter.TableAnnotation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

@TableAnnotation
public class Notification extends ObjectBDD {
    @KeyAnnotation
    @IdAnnotation
    private int id;
    @KeyAnnotation
    private String message;
    @KeyAnnotation
    private Timestamp Datenotif;
    @KeyAnnotation
    private int idaction;

    public Notification() {
    }

    public Notification(int id) {
        this.id = id;
    }

    public Notification(String message, Timestamp datenotif) {
        this.message = message;
        Datenotif = datenotif;
    }

    public Notification(String message, Timestamp datenotif, int idaction) {
        this.message = message;
        Datenotif = datenotif;
        this.idaction = idaction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getDatenotif() {
        return Datenotif;
    }

    public void setDatenotif(Timestamp datenotif) {
        Datenotif = datenotif;
    }
    public void save() throws Exception {
        Connection con = Connexion.getConnection();
        con.setAutoCommit(false);
        try {
            super.save(con);
            con.commit();
        }catch (SQLException e){
            con.rollback();
        }finally {
            con.close();
        }
    }
    public ArrayList<Notification> SelectAll() throws Exception {
        String sql = "select * from "+getNomTable()+" order by id desc";
        return super.SelectAllByQuery(Connexion.getConnection(),sql);
    }

    public int getIdaction() {
        return idaction;
    }

    public void setIdaction(int idaction) {
        this.idaction = idaction;
    }

}
