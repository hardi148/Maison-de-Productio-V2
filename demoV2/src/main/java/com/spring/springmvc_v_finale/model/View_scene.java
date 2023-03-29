package com.spring.springmvc_v_finale.model;

import com.spring.springmvc_v_finale.utils.Connex.Connexion;
import com.spring.springmvc_v_finale.utils.DAO.ObjectBDD;
import com.spring.springmvc_v_finale.utils.inter.KeyAnnotation;
import com.spring.springmvc_v_finale.utils.inter.TableAnnotation;

import java.sql.Timestamp;
import java.util.ArrayList;


@TableAnnotation
public class View_scene extends ObjectBDD{
    @KeyAnnotation
    String personnage;
    @KeyAnnotation
    int idperso;
    @KeyAnnotation(column = "idplateau")
    int idplateu;
    @KeyAnnotation
    String plateau;
    @KeyAnnotation
    String nom;
    @KeyAnnotation
    Timestamp dateaction;
    @KeyAnnotation
    Timestamp datedebut;
    @KeyAnnotation
    Timestamp datefin;
    @KeyAnnotation
    int idaction;
    @KeyAnnotation
    int idscene;
    @KeyAnnotation
    String description; 
    public String getPersonnage() {
        return personnage;
    }
    public void setPersonnage(String personnage) {
        this.personnage = personnage;
    }
    public int getIdperso() {
        return idperso;
    }
    public void setIdperso(int idperso) {
        this.idperso = idperso;
    }
    public int getIdplateu() {
        return idplateu;
    }
    public void setIdplateu(int idplateu) {
        this.idplateu = idplateu;
    }
    public String getPlateau() {
        return plateau;
    }
    public void setPlateau(String plateau) {
        this.plateau = plateau;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Timestamp getDateaction() {
        return dateaction;
    }
    public void setDateaction(Timestamp dateaction) {
        this.dateaction = dateaction;
    }
    public Timestamp getDatedebut() {
        return datedebut;
    }
    public void setDatedebut(Timestamp datedebut) {
        this.datedebut = datedebut;
    }
    public Timestamp getDatefin() {
        return datefin;
    }
    public void setDatefin(Timestamp datefin) {
        this.datefin = datefin;
    }
    public int getIdaction() {
        return idaction;
    }
    public void setIdaction(int idaction) {
        this.idaction = idaction;
    }
    public int getIdscene() {
        return idscene;
    }
    public void setIdscene(int idscene) {
        this.idscene = idscene;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public View_scene() {
    }
    public View_scene(String personnage, int idperso, int idplateu, String plateau, String nom, Timestamp dateaction,

                      Timestamp datedebut, Timestamp datefin, int idaction, int idscene, String description) {
        this.personnage = personnage;
        this.idperso = idperso;
        this.idplateu = idplateu;
        this.plateau = plateau;
        this.nom = nom;
        this.dateaction = dateaction;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.idaction = idaction;
        this.idscene = idscene;
        this.description = description;
    }

    public ArrayList<View_scene> SelectAll() throws Exception {
        return super.SelectAll(Connexion.getConnection());
    }
    public ArrayList<View_scene> paginateAll(int maxRow,int firstRow) throws Exception {
        return paginateSelectAll(maxRow,firstRow,Connexion.getConnection());
    }
    public ArrayList<View_scene>Recherch(String sql) throws Exception {
        return super.SelectAllByQuery(Connexion.getConnection(),sql);
    }
    public String sql(String valeur)throws Exception{
        String sql="";
        String [] liste=sql.split(" ");
        Personnage perso=new Personnage();
        Action action =new Action();
        Plateau plateau=new Plateau();
        Scene scene=new Scene();
        for(int i=0;i<liste.length;i++){
            if(scene.testScene(liste[i])){
                sql=sql+"and lower(nom)=lower('"+liste[i]+"')";
            }
            if(action.testAction(liste[i])){
                sql=sql+"and lower(description) like lower('%"+liste[i]+"%')";
            }
            if(plateau.testPerso(liste[i])){
                sql=sql+"and lower(plateau) = lower('"+liste[i]+"')";
            }
            if(perso.testPerso(liste[i])){
                sql=sql+"and lower(personnage) = lower('"+liste[i]+"')";
            }
        }
        return sql;
    }
    public String sql(String valeur1,String datedebut,String datefin)throws Exception{
        String sql=this.sql(valeur1);
        if(datedebut!=null){
            sql=sql+" and datedebut ='"+datedebut+"'";
        } if(datefin!=null){
            sql=sql+" and datefin ='"+datefin+"'";
        }
        return sql;
    }
    public ArrayList<View_scene> Recherche(String valeur1,String datedebut,String datefin) throws Exception{
        String sql=this.sql(valeur1,datedebut,datefin);
        return this.Recherch(sql);
    }
    public static void main (String [] args)throws Exception{
        Action act = new Action();
        act.setDescription("myra");
        ArrayList<Action> l = act.search(Connexion.getConnection());
    }

}
