package com.spring.springmvc_v_finale.model;

import com.spring.springmvc_v_finale.utils.Connex.Connexion;
import com.spring.springmvc_v_finale.utils.DAO.ObjectBDD;
import com.spring.springmvc_v_finale.utils.inter.IdAnnotation;
import com.spring.springmvc_v_finale.utils.inter.KeyAnnotation;
import com.spring.springmvc_v_finale.utils.inter.TableAnnotation;

import java.sql.Timestamp;
import java.util.ArrayList;

@TableAnnotation
public class Plateau extends ObjectBDD {
    @KeyAnnotation
    @IdAnnotation(name = "idplateau")
    private int idplateau;
    @KeyAnnotation
    private String nomplateau;
    @KeyAnnotation
    private Timestamp dateDebutIndisponibilite;
    @KeyAnnotation
    private Timestamp dateFinIndisponibile;

    public Plateau(Timestamp dateFinIndisponibile) {
        this.dateFinIndisponibile = dateFinIndisponibile;
    }

    public Timestamp getDateDebutIndisponibilite() {
        return dateDebutIndisponibilite;
    }

    public void setDateDebutIndisponibilite(Timestamp dateDebutIndisponibilite) {
        this.dateDebutIndisponibilite = dateDebutIndisponibilite;
    }

    public Timestamp getDateFinIndisponibile() {
        return dateFinIndisponibile;
    }

    public void setDateFinIndisponibile(Timestamp dateFinIndisponibile) {
        this.dateFinIndisponibile = dateFinIndisponibile;
    }

    public Plateau() {
    }

    public Plateau(int idplateau, String nomplateau, Timestamp dateDebutIndisponibilite, Timestamp dateFinIndisponibile) {
        this.idplateau = idplateau;
        this.nomplateau = nomplateau;
        this.dateDebutIndisponibilite = dateDebutIndisponibilite;
        this.dateFinIndisponibile = dateFinIndisponibile;
    }

    public Plateau(String nomplateau, Timestamp dateDebutIndisponibilite, Timestamp dateFinIndisponibile) {
        this.nomplateau = nomplateau;
        this.dateDebutIndisponibilite = dateDebutIndisponibilite;
        this.dateFinIndisponibile = dateFinIndisponibile;
    }

    public Plateau(String nomplateau) {
        this.nomplateau = nomplateau;
    }

    public Plateau(int idplateau) {
        this.idplateau = idplateau;
    }

    public Plateau(int idplateau, String nomplateau) {
        this.idplateau = idplateau;
        this.nomplateau = nomplateau;
    }

    public int getIdplateau() {
        return idplateau;
    }

    public void setIdplateau(int idplateau) {
        this.idplateau = idplateau;
    }
    public String getNomplateau() {
        return nomplateau;
    }
    public void setNomplateau(String nomplateau) {
        this.nomplateau = nomplateau;
    }
    public void save() throws Exception {
        super.saveAll(Connexion.getConnection());
    }
    public Plateau findById() throws Exception {
        return super.findById(Connexion.getConnection(),String.valueOf(getIdplateau()));
    }
    public ArrayList<Plateau> SelectAll() throws Exception {
        return super.SelectAll(Connexion.getConnection());
    }
    public void Update() throws Exception {
        super.updateById(Connexion.getConnection());
    }
    public ArrayList<Plateau> SelectAllByQuerry(String sql) throws Exception {
        return SelectAllByQuery(Connexion.getConnection(),sql);
    }
    public boolean testPerso(String valeur)throws Exception{
        String sql="select * from plateau where lower(nomplateau)=lower('"+valeur+"'')";
        ArrayList<Plateau> liste=this.SelectAllByQuerry( sql);
        if((liste!=null)&&(liste.size()!=0))
            return true;
        return false;
    }
}
