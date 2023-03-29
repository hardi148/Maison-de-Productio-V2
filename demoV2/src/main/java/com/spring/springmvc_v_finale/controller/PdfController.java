package com.spring.springmvc_v_finale.controller;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.spring.springmvc_v_finale.model.Scene;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Controller
public class PdfController {

    @GetMapping("/sendMail")
    public ResponseEntity<byte[]> generatePdf(@RequestParam("idperso") int idperso, @RequestParam("nomperso") String nomperso, @RequestParam("description") String description, @RequestParam("duree") float duree, @RequestParam("nomplateau") String nomplateau, @RequestParam("nom") String scene, @RequestParam("idaction") int idaction, @RequestParam("idscene") int idscene, @RequestParam("dateaction") Date datedebut, @RequestParam("acteur") String acteur, @RequestParam("datefin") Date datefin, @RequestParam("heure_debut") Time heure_debut,@RequestParam("heure_fin") Time heure_fin) throws Exception {

        // Création d'un document PDF
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);

        // Ajout du contenu
        document.open();
        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD);
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

        Paragraph title = new Paragraph("Nouvelle action sur le plateau " + nomplateau, titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        Chunk sceneTitle = new Chunk("Nom de la scène : ", boldFont);
        Chunk sceneText = new Chunk(scene, normalFont);
        Paragraph sceneParagraph = new Paragraph();
        sceneParagraph.add(sceneTitle);
        sceneParagraph.add(sceneText);
        document.add(sceneParagraph);

        Chunk auteurTitle = new Chunk("Auteur : ", boldFont);
        Chunk auteurText = new Chunk("Hardi", normalFont);
        Paragraph auteurParagraph = new Paragraph();
        auteurParagraph.add(auteurTitle);
        auteurParagraph.add(auteurText);
        document.add(auteurParagraph);

        Chunk acteurTitle = new Chunk("Acteurs participants : ", boldFont);
        Chunk acteurText = new Chunk(acteur, normalFont);
        Paragraph acteurParagraph = new Paragraph();
        acteurParagraph.add(acteurTitle);
        acteurParagraph.add(acteurText);
        document.add(acteurParagraph);

        Chunk debutTitle = new Chunk("Date de début de l'action: ", boldFont);
        Chunk debutText = new Chunk(new SimpleDateFormat("dd/MM/yyyy").format(datedebut), normalFont);
        Paragraph debutParagraph = new Paragraph();
        debutParagraph.add(debutTitle);
        debutParagraph.add(debutText);
        document.add(debutParagraph);

        Chunk finTitle = new Chunk("Date fin de l'action: ", boldFont);
        Chunk finText = new Chunk(new SimpleDateFormat("dd/MM/yyyy").format(datefin), normalFont);
        Paragraph finParagraph = new Paragraph();
        finParagraph.add(finTitle);
        finParagraph.add(finText);
        document.add(finParagraph);

        Chunk heuredebutTitle = new Chunk("Veuillez vous rendre a ce plateau a  cette heure : ", boldFont);
        Chunk heuredebutText = new Chunk(String.valueOf(heure_debut), normalFont);
        Paragraph heuredebutParagraph = new Paragraph();
        heuredebutParagraph.add(heuredebutTitle);
        heuredebutParagraph.add(heuredebutText);
        document.add(heuredebutParagraph);


        Chunk heurefinTitle = new Chunk("L'action se terminera a cette heure : ", boldFont);
        Chunk heurefinText = new Chunk(String.valueOf(heure_fin), normalFont);
        Paragraph heurefinParagraph = new Paragraph();
        heurefinParagraph.add(heurefinTitle);
        heurefinParagraph.add(heurefinText);
        document.add(heurefinParagraph);

        Chunk dialogueTitle = new Chunk("Dialogue : ", boldFont);
        Chunk dialogueText = new Chunk(description, normalFont);
        Paragraph dialogueParagraph = new Paragraph();
        dialogueParagraph.add(dialogueTitle);
        dialogueParagraph.add(dialogueText);
        document.add(dialogueParagraph);


        Chunk dureeTitle = new Chunk("Durée de l'action (en minutes): ", boldFont);
        Chunk dureeText = new Chunk(Float.toString(duree), normalFont);
        Paragraph dureeParagraph = new Paragraph();
        dureeParagraph.add(dureeTitle);
        dureeParagraph.add(dureeText);
        document.add(dureeParagraph);


        document.close();

        // Configuration de la réponse HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "nouvelle_action.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(baos.toByteArray());
    }


}
