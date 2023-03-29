package com.spring.springmvc_v_finale.model;

import com.spring.springmvc_v_finale.utils.Connex.Connexion;
import com.spring.springmvc_v_finale.utils.DAO.ObjectBDD;
import com.spring.springmvc_v_finale.utils.inter.ForeignKeyAnnotation;
import com.spring.springmvc_v_finale.utils.inter.IdAnnotation;
import com.spring.springmvc_v_finale.utils.inter.KeyAnnotation;
import com.spring.springmvc_v_finale.utils.inter.TableAnnotation;

import java.util.ArrayList;

@TableAnnotation
public class Action extends ObjectBDD {
    @KeyAnnotation
    @IdAnnotation(name = "idaction")
    private int idaction;
    @KeyAnnotation
    private int idscene;
    @KeyAnnotation
    private int dateAction;
    @KeyAnnotation
    private String description;

    @KeyAnnotation
    private int finished = 0;

    @KeyAnnotation
    private int idplateau;
    @KeyAnnotation
    private int isValidate=0;
    @KeyAnnotation
    private String acteur;



    public int getIsValidate() {
        return isValidate;
    }

    public void setIsValidate(int isValidate) {
        this.isValidate = isValidate;
    }

    public String getActeur() {
        return acteur;
    }

    public void setActeur(String acteur) {
        this.acteur = acteur;
    }


    @ForeignKeyAnnotation(name = "idscene",references = "idscene")
    private Scene scene;

    @ForeignKeyAnnotation(name = "idplateau",references = "idplateau")
    Plateau plateau;

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public int getIdplateau() {
        return idplateau;
    }

    public Action(int idaction, int idscene, int dateAction, String description, int finished, int idplateau) {
        this.idaction = idaction;
        this.idscene = idscene;
        this.dateAction = dateAction;
        this.description = description;
        this.finished = finished;
        this.idplateau = idplateau;
    }

    public Action(int idscene, int dateAction, String description, int finished, int idplateau) {
        this.idscene = idscene;
        this.dateAction = dateAction;
        this.description = description;
        this.finished = finished;
        this.idplateau = idplateau;
    }

    public void setIdplateau(int idplateau) {
        this.idplateau = idplateau;
    }

    private ArrayList<Mis_en_Action> mis_en_actions = null;

    public ArrayList<Mis_en_Action> getMis_en_actions() throws Exception {
        if (mis_en_actions == null) {
            mis_en_actions = new Mis_en_Action().findByAction(getIdaction());
        }
        return mis_en_actions;
    }

    public void setMis_en_actions(ArrayList<Mis_en_Action> mis_en_actions) {
        this.mis_en_actions = mis_en_actions;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    private ArrayList<Mis_en_Action> list_mise_action = new ArrayList<>();
    private  ArrayList<Photos> list_photo = null;

    public ArrayList<Mis_en_Action> getList_mise_action() {
        return list_mise_action;
    }

    public void setList_mise_action(ArrayList<Mis_en_Action> list_mise_action) {
        this.list_mise_action = list_mise_action;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public ArrayList<Photos> getList_photo() throws Exception {
        if (list_photo == null) {
            list_photo = new Photos().findByAction(getIdaction());
        }
        return list_photo;
    }

    public void setList_photo(ArrayList<Photos> list_photo) {
        this.list_photo = list_photo;
    }

    public Action() {
    }
    public Action(int idscene, int dateAction, String description) {
        this.idscene = idscene;
        this.dateAction = dateAction;
        this.description = description;
    }
    public Action(int idscene, int dateAction, String description,int finished) {
        this.idscene = idscene;
        this.dateAction = dateAction;
        this.description = description;
        this.finished = finished;
    }

    public Action(int idaction) {
        this.idaction = idaction;
    }
    public ArrayList<Action> findByScene(int scenes) throws Exception {
        String sql = "select * from "+getNomTable()+" where idscene="+scenes;
        return SelectAllByQuery(Connexion.getConnection(),sql);
    }
    public ArrayList<Action> findByEtat(int etat,int scene) throws Exception {
        String sql = "select * from "+getNomTable()+" where  idscene="+scene+" and isvalidate="+etat;
        return SelectAllByQuery(Connexion.getConnection(),sql);
    }
    public Action(int idaction, int idscene, int dateAction, String description,int finished) {
        this.idaction = idaction;
        this.idscene = idscene;
        this.dateAction = dateAction;
        this.description = description;
        this.finished = finished;
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

    public int getDateAction() {
        return dateAction;
    }

    public void setDateAction(int dateAction) {
        this.dateAction = dateAction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void save() throws Exception {
//            Connection con = Connexion.getConnection();
        try {
//            con.setAutoCommit(false);
            Action act =  super.saveAll(Connexion.getConnection());
//            Action act = findlast(Connexion.getConnection());
            for (Mis_en_Action m:getList_mise_action()) {
                m.setIdaction(act.getIdaction());
//                m.save(con);
                m.saveAll(Connexion.getConnection());
            }
            for (Photos f:getList_photo()) {
                f.setIdaction(act.getIdaction());
//                f.save(con);
                f.saveAll(Connexion.getConnection());
            }
        }catch (Exception e){
            e.printStackTrace();
//            con.rollback();
            throw e;
        }
    }
    public ArrayList<Action> SelectAll() throws Exception {
        return super.SelectAll(Connexion.getConnection());
    }
    public ArrayList<Action> SelectAllByQuerry(String sql) throws Exception {
        return SelectAllByQuery(Connexion.getConnection(),sql);
    }
    public ArrayList<Action> SelectAllActionNonValidate() throws Exception {
        String sql= "select * from "+getNomTable()+" where isvalidate=0 order by idaction asc";
        return SelectAllByQuerry(sql);
    }
    public Action findById() throws Exception {
        return super.findById(Connexion.getConnection(),String.valueOf(getIdaction()));
    }

    public boolean testAction(String valeur)throws Exception{
        String sql="select * from action where lower(description) like lower('%"+valeur+"%')";
        ArrayList<Action> liste=this.SelectAllByQuerry( sql);
        if((liste!=null)&&(liste.size()!=0))
            return true;
        return false;
    }
}
