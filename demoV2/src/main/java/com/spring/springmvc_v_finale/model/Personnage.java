package com.spring.springmvc_v_finale.model;

import com.spring.springmvc_v_finale.utils.Connex.Connexion;
import com.spring.springmvc_v_finale.utils.DAO.ObjectBDD;
import com.spring.springmvc_v_finale.utils.inter.IdAnnotation;
import com.spring.springmvc_v_finale.utils.inter.KeyAnnotation;
import com.spring.springmvc_v_finale.utils.inter.TableAnnotation;

import java.sql.Date;
import java.util.ArrayList;

@TableAnnotation
public class Personnage extends ObjectBDD {
    @KeyAnnotation
    @IdAnnotation(name = "idperso")
    private int idperso;
    @KeyAnnotation
    private String nomperso;
    @KeyAnnotation
    private Date DateNaissance;

    public Personnage(String nomperso, Date dateNaissance) {
        this.nomperso = nomperso;
        DateNaissance = dateNaissance;
    }

    public Personnage(int idperso) {
        this.idperso = idperso;
    }

    public Personnage() {
    }

    public Personnage(int idperso, String nomperso, Date dateNaissance) {
        this.idperso = idperso;
        this.nomperso = nomperso;
        DateNaissance = dateNaissance;
    }

    public int getIdperso() {
        return idperso;
    }

    public void setIdperso(int idperso) {
        this.idperso = idperso;
    }

    public String getNomperso() {
        return nomperso;
    }

    public void setNomperso(String nomperso) {
        this.nomperso = nomperso;
    }

    public Date getDateNaissance() {
        return DateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        DateNaissance = dateNaissance;
    }
    public void save() throws Exception {
        super.saveAll(Connexion.getConnection());
    }
    public ArrayList<Personnage> SelectAll() throws Exception {
        return super.SelectAll(Connexion.getConnection());
    }
    public ArrayList<Personnage> SelectAllByQuerry(String sql) throws Exception {
        return SelectAllByQuery(Connexion.getConnection(),sql);
    }
    public boolean testPerso(String valeur)throws Exception{
        String sql="select * from personnage where lower(nomperso)=lower('"+valeur+"'')";
        ArrayList<Personnage> liste=this.SelectAllByQuerry( sql);
        if((liste!=null)&&(liste.size()!=0))
            return true;
        return false;
    }
}
