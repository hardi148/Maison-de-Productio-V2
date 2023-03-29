package com.spring.springmvc_v_finale.model;

import com.spring.springmvc_v_finale.utils.Connex.Connexion;
import com.spring.springmvc_v_finale.utils.DAO.ObjectBDD;
import com.spring.springmvc_v_finale.utils.inter.IdAnnotation;
import com.spring.springmvc_v_finale.utils.inter.KeyAnnotation;
import com.spring.springmvc_v_finale.utils.inter.TableAnnotation;

import java.sql.Timestamp;
import java.util.ArrayList;

@TableAnnotation
public class Scene extends ObjectBDD {
    @KeyAnnotation
    @IdAnnotation(name = "idscene")
    private int idscene;
    @KeyAnnotation
    private String nom;
    @KeyAnnotation
    private Timestamp dateDebut;

    public Scene() {
    }

    public Scene(int idscene) {
        this.idscene = idscene;
    }

    public Scene(String nom, Timestamp dateDebut, Timestamp dateFin) {
        this.nom = nom;
        this.dateDebut = dateDebut;
    }

    public Scene(int idscene, String nom, Timestamp dateDebut, Timestamp dateFin) {
        this.idscene = idscene;
        this.nom = nom;
        this.dateDebut = dateDebut;
    }

    public int getIdscene() {
        return idscene;
    }

    public void setIdscene(int idscene) {
        this.idscene = idscene;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }


    public Scene save() throws Exception {
       return super.saveAll(Connexion.getConnection());
    }
    public ArrayList<Scene> SelectAll() throws Exception {
        return super.SelectAll(Connexion.getConnection());
    }
    public Scene findById() throws Exception {
        return super.findById(Connexion.getConnection(),String.valueOf(getIdscene()));
    }
    public void updateById() throws Exception {
        super.updateById(Connexion.getConnection());
    }
//    public ArrayList<Date> getMiddleDate() throws Exception {
//        ArrayList<Date> list = new ArrayList<>();
//        Date debut = new Date(getDateDebut().getTime());
////        Date debutfin =new Date(getDateFin().getTime());
//        long startMillis = debut.getTime();
//        long endMillis = debutfin.getTime();
//        for (long millis = startMillis; millis <= endMillis; millis += 86400000) {
//            // Add 1 day in milliseconds (86400000 = 24 hours * 60 minutes * 60 seconds * 1000 milliseconds)
//            Date date = new Date(millis);
//            list.add(date);
//        }
//        if (endMillis % 86400000 == 0) {
//            Date lastDay = new Date(endMillis);
//            list.add(lastDay);
//        }
//
//        return list;
//    }
    public ArrayList<Scene> SelectAllByQuerry(String sql) throws Exception {
        return SelectAllByQuery(Connexion.getConnection(),sql);
    }
    public ArrayList<Scene> paginateAll(int maxRow,int firstRow) throws Exception {
        return paginateSelectAll(maxRow,firstRow,Connexion.getConnection());
    }
    public ArrayList<Scene> paginateSearch(String sql,int maxRow,int firstRow) throws Exception {
        return paginateNativeSql(sql,maxRow,firstRow,Connexion.getConnection());
    }
    public boolean testScene(String valeur)throws Exception{
        String sql="select * from scene where lower(nom) = lower('"+valeur+"'')";
        ArrayList<Scene> liste=this.SelectAllByQuerry( sql);
        if((liste!=null)&&(liste.size()!=0))
            return true;
        return false;
    }
}
