-- create database production;
-- \c production

CREATE SEQUENCE action_idaction_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE calendar_idcalendar_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE notification_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE personnage_idperso_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE plateau_idplateau_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE scene_idscene_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE personnage
(
    idperso                  integer DEFAULT nextval('personnage_idperso_seq'::regclass) NOT NULL,
    nomperso                 varchar(30)                                                 NOT NULL,
    datenaissance            date                                                        NOT NULL,
    datedebutindisponibilite date,
    datefinindisponibile     date,
    CONSTRAINT personnage_pkey PRIMARY KEY (idperso)
);

ALTER TABLE personnage
    ADD CONSTRAINT personnage_datenaissance_check CHECK ( (datenaissance < CURRENT_DATE) );

CREATE TABLE plateau
(
    idplateau                integer DEFAULT nextval('plateau_idplateau_seq'::regclass) NOT NULL,
    nomplateau               varchar(30)                                                NOT NULL,
    datedebutindisponibilite timestamp,
    datefinindisponibile     timestamp,
    CONSTRAINT plateau_pkey PRIMARY KEY (idplateau)
);

CREATE TABLE regleaction
(
    durree integer
);

CREATE TABLE scene
(
    idscene   integer   DEFAULT nextval('scene_idscene_seq'::regclass) NOT NULL,
    nom       varchar(40)                                              NOT NULL,
    datedebut timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT scene_pkey PRIMARY KEY (idscene)
);

CREATE TABLE "action"
(
    idaction    integer DEFAULT nextval('action_idaction_seq'::regclass) NOT NULL,
    idscene     integer                                                  NOT NULL,
    dateaction  integer DEFAULT 1,
    idplateau   integer                                                  NOT NULL,
    description text,
    finished    integer DEFAULT 0,
    isvalidate  integer                                                  NOT NULL,
    CONSTRAINT action_pkey PRIMARY KEY (idaction),
    CONSTRAINT action_idplateau_fkey FOREIGN KEY (idplateau) REFERENCES plateau (idplateau),
    CONSTRAINT action_idscene_fkey FOREIGN KEY (idscene) REFERENCES scene (idscene)
);

ALTER TABLE "action"
    ADD CONSTRAINT action_isvalidate_check CHECK ( (isvalidate = ANY (ARRAY[0, 1])) );

ALTER TABLE "action"
    ADD CONSTRAINT action_finished_check CHECK ( (finished = ANY (ARRAY[0, 1])) );

CREATE TABLE calendar
(
    idcalendar   integer   DEFAULT nextval('calendar_idcalendar_seq'::regclass) NOT NULL,
    idaction     integer                                                        NOT NULL,
    datecalendar timestamp DEFAULT CURRENT_TIMESTAMP                            NOT NULL,
    datefin      timestamp                                                      NOT NULL,
    CONSTRAINT calendar_pkey PRIMARY KEY (idcalendar),
    CONSTRAINT calendar_idaction_key UNIQUE (idaction),
    CONSTRAINT calendar_idaction_fkey FOREIGN KEY (idaction) REFERENCES "action" (idaction)
);

ALTER TABLE calendar
    ADD CONSTRAINT calendar_check CHECK ( (datefin > datecalendar) );

CREATE TABLE mis_en_action
(
    idaction integer NOT NULL,
    idperso  integer NOT NULL,
    CONSTRAINT mis_en_action_idaction_fkey FOREIGN KEY (idaction) REFERENCES "action" (idaction),
    CONSTRAINT mis_en_action_idperso_fkey FOREIGN KEY (idperso) REFERENCES personnage (idperso)
);


CREATE TABLE notification
(
    id        integer   DEFAULT nextval('notification_id_seq'::regclass) NOT NULL,
    message   text                                                       NOT NULL,
    idaction  integer,
    datenotif timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT notification_pkey PRIMARY KEY (id),
    CONSTRAINT notification_idaction_key UNIQUE (idaction),
    CONSTRAINT notification_idaction_fkey FOREIGN KEY (idaction) REFERENCES "action" (idaction)
);

CREATE TABLE photos
(
    idaction integer NOT NULL,
    image    text,
    CONSTRAINT photos_idaction_fkey FOREIGN KEY (idaction) REFERENCES "action" (idaction)
);

CREATE
OR REPLACE FUNCTION update_disponibility_action()
 RETURNS trigger
 LANGUAGE plpgsql
AS $function$
declare
    idplat integer;
    BEGIN

    select a.idplateau
            into idplat
            from action a
            where a.idaction = new.idAction;
            update plateau
            set dateDebutIndisponibilite=new.dateCalendar,
                dateFinIndisponibile=new.datefin
            where idplateau = idplat;
            update action a2 set isvalidate=1 where a2.idaction = new.idAction;
    return new;
end;
$function$
;

CREATE TRIGGER updateaction
    BEFORE INSERT
    ON calendar
    FOR EACH ROW EXECUTE FUNCTION update_disponibility_action();

select * from action;

insert into action(idscene, idplateau, description, isvalidate,dateaction) values (1,1,'apparition d un mysterieux personnage',0,3);
insert into action(idscene, idplateau, description, isvalidate,dateaction) values (1,1,'apparition d un autre personnage ',0,4);


create or replace view v_action_perso as 
select p.idperso , p.nomperso ,DATE(datecalendar) as dateaction,a.description , EXTRACT(EPOCH FROM (c.datefin - c.datecalendar))/60 as duree , a.isvalidate , pl.nomplateau , s.nom , s.datedebut,a.idaction,s.idscene,a.acteur,DATE(datefin) as datefin , TO_CHAR(datecalendar, 'HH24:MI:SS') as heure_debut , TO_CHAR(datefin,'HH24:MI:SS') as heure_fin from personnage p join mis_en_action m using(idperso) join action a using(idaction) join scene s using(idscene) join plateau pl using(idplateau) join calendar c using(idaction);





SELECT idcalendar, idaction, TO_CHAR(datecalendar, 'HH24:MI:SS') AS time
FROM calendar;
