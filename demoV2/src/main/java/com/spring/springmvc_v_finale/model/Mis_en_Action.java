package com.spring.springmvc_v_finale.model;

import com.spring.springmvc_v_finale.utils.Connex.Connexion;
import com.spring.springmvc_v_finale.utils.DAO.ObjectBDD;
import com.spring.springmvc_v_finale.utils.inter.ForeignKeyAnnotation;
import com.spring.springmvc_v_finale.utils.inter.KeyAnnotation;
import com.spring.springmvc_v_finale.utils.inter.TableAnnotation;

import java.sql.Connection;
import java.util.ArrayList;

@TableAnnotation
public class Mis_en_Action extends ObjectBDD {
    @KeyAnnotation
    private int idaction;
    @KeyAnnotation
    private int idperso;

    @ForeignKeyAnnotation(name = "idperso",references = "idperso")
    private Personnage perso;

    public Personnage getPerso() {
        return perso;
    }

    public void setPerso(Personnage perso) {
        this.perso = perso;
    }

    public Mis_en_Action() {
    }

    public Mis_en_Action(int idperso) {
        this.idperso = idperso;
    }

    public Mis_en_Action(int idaction, int idperso) {
        this.idaction = idaction;
        this.idperso = idperso;
    }

    public int getIdaction() {
        return idaction;
    }

    public void setIdaction(int idaction) {
        this.idaction = idaction;
    }

    public int getIdperso() {
        return idperso;
    }

    public void setIdperso(int idperso) {
        this.idperso = idperso;
    }

    public Mis_en_Action save(Connection con) throws Exception {
       return super.save(con);
    }
    public Mis_en_Action save() throws Exception {
       return super.saveAll(Connexion.getConnection());
    }
    public ArrayList<Mis_en_Action> SelectAll() throws Exception {
        return super.SelectAll(Connexion.getConnection());
    }
    public ArrayList<Mis_en_Action> findByAction(int action) throws Exception {
        String sql = "select * from "+getNomTable()+" where idaction="+action;
        return SelectAllByQuery(Connexion.getConnection(),sql);
    }
}
