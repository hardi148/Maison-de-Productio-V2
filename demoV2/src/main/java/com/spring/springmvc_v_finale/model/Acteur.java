package com.spring.springmvc_v_finale.model;

import com.spring.springmvc_v_finale.utils.Connex.Connexion;
import com.spring.springmvc_v_finale.utils.DAO.ObjectBDD;
import com.spring.springmvc_v_finale.utils.inter.IdAnnotation;
import com.spring.springmvc_v_finale.utils.inter.KeyAnnotation;
import com.spring.springmvc_v_finale.utils.inter.TableAnnotation;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@TableAnnotation(nameTable = "personnage")
public class Acteur extends ObjectBDD{

    @KeyAnnotation
    @IdAnnotation(name = "idperso")
    private int idperso;
    @KeyAnnotation
    private String nomperso;
    @KeyAnnotation
    private Date datenaissance;
    @KeyAnnotation
    private Date dateDebutIndisponibilite;
    @KeyAnnotation
    private Date dateFinIndisponibile;



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

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }
    public Date getDateDebutIndisponibilite() {
        return dateDebutIndisponibilite;
    }

    public void setDateDebutIndisponibilite(Date dateDebutIndisponibilite) {
        this.dateDebutIndisponibilite = dateDebutIndisponibilite;
    }

    public Date getDateFinIndisponibile() {
        return dateFinIndisponibile;
    }

    public void setDateFinIndisponibile(Date dateFinIndisponibile) {
        this.dateFinIndisponibile = dateFinIndisponibile;
    }

    public ArrayList<Acteur> findByID(int idperso) throws Exception {
        String sql = "select * from personnage where  idperso="+idperso;
        return SelectAllByQuery(Connexion.getConnection(),sql);
    }

    public List<Object[]> getResults(int idperso) throws Exception {
        List<Object[]> results = new ArrayList<>();
        try {
            // Exécuter la requête SQL et récupérer les résultats
            String sql = "select * from v_action_perso where idperso=? and isvalidate=1";
            PreparedStatement statement = Connexion.getConnection().prepareStatement(sql);
            statement.setInt(1, idperso);
            ResultSet resultSet = statement.executeQuery();

            // Parcourir les résultats et les ajouter à la liste
            while (resultSet.next()) {
                Object[] row = new Object[15];
                row[0] = resultSet.getInt("idperso");
                row[1] = resultSet.getString("nomperso");
                row[2] = resultSet.getDate("dateaction");
                row[3] = resultSet.getString("description");
                row[4] = resultSet.getFloat("duree");
                row[5] = resultSet.getBoolean("isvalidate");
                row[6] = resultSet.getString("nomplateau");
                row[7] = resultSet.getString("nom");
                row[8] = resultSet.getTimestamp("datedebut");
                row[9] = resultSet.getInt("idaction");
                row[10] = resultSet.getInt("idscene");
                row[11] = resultSet.getString("acteur");
                row[12] = resultSet.getDate("datefin");
                row[13] = resultSet.getTime("heure_debut");
                row[14] = resultSet.getTime("heure_fin");
                results.add(row);
            }

            // Fermer la connexion et le statement
            resultSet.close();
            statement.close();
            Connexion.getConnection().close();
        } catch (ClassNotFoundException e) {
            System.out.println("Le driver JDBC n'a pas pu être chargé.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Une erreur est survenue lors de l'exécution de la requête SQL.");
            e.printStackTrace();
        }

        return results;
    }
}
