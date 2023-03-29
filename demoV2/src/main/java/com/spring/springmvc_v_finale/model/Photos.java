package com.spring.springmvc_v_finale.model;

import com.spring.springmvc_v_finale.utils.Connex.Connexion;
import com.spring.springmvc_v_finale.utils.DAO.ObjectBDD;
import com.spring.springmvc_v_finale.utils.inter.KeyAnnotation;
import com.spring.springmvc_v_finale.utils.inter.TableAnnotation;

import java.sql.Connection;
import java.util.ArrayList;

@TableAnnotation
public class Photos extends ObjectBDD {
    @KeyAnnotation
    private int idaction;
    @KeyAnnotation
    private String image;

    public Photos() {
    }

    public Photos(String image) {
        this.image = image;
    }

    public Photos(int idaction, String image) {
        this.idaction = idaction;
        this.image = image;
    }

    public int getIdaction() {
        return idaction;
    }

    public void setIdaction(int idaction) {
        this.idaction = idaction;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public Photos save(Connection con) throws Exception {
       return super.save(con);
    }
    public void save() throws Exception {
        super.saveAll(Connexion.getConnection());
    }
    public ArrayList<Photos> SelectAll() throws Exception {
        return super.SelectAll(Connexion.getConnection());
    }
    public ArrayList<Photos> findByAction(int action) throws Exception {
        String sql = "select * from "+getNomTable()+" where idaction="+action;
        return SelectAllByQuery(Connexion.getConnection(),sql);
    }
}
