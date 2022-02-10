CREATE TABLE movie (
    movieid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    title text,
    synopsis text,
    imageurl text);

CREATE TABLE actor (
    actorid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    name text,
    imageurl text);

CREATE TABLE genre (
    genreid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    label text);

CREATE TABLE movie_actor (
    movieid uuid REFERENCES movie(movieid) ON DELETE CASCADE,
    actorid uuid REFERENCES actor(actorid) ON DELETE CASCADE,
    PRIMARY KEY (movieid, actorid));

CREATE TABLE movie_genre (
    movieid uuid REFERENCES movie(movieid) ON DELETE CASCADE,
    genreid uuid REFERENCES genre(genreid) ON DELETE CASCADE,
    PRIMARY KEY (movieid, genreid));

CREATE TABLE file (
    fileid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    contenttype TEXT,
    data bytea);

CREATE TABLE usser (
  userid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
  username varchar(24) NOT NULL UNIQUE,
  password varchar(255) NOT NULL,
  role varchar(10),
  enabled boolean DEFAULT true);

CREATE TABLE favorite (
    userid uuid REFERENCES usser(userid) ON DELETE CASCADE,
    pedidosid uuid REFERENCES pedidos(pedidosuuid) ON DELETE CASCADE,
    PRIMARY KEY(userid, pedidosid)
);

insert into favorite values((select userid from usser where username='user'),(select pedidosuuid from pedidos where entregat ='f'));
insert into favorite values((select userid from usser where username='user'),(select pedidosuuid from pedidos where data ='2020-08-12'));

create TABLE pedidos_productes(
    pedidosid uuid REFERENCES pedidos(pedidosuuid) ON DELETE CASCADE,
    producteid uuid REFERENCES productes(producteid) ON DELETE CASCADE,
    PRIMARY KEY (pedidosid,producteid));

    insert into pedidos_productes values((select pedidosuuid from pedidos where data='2020-12-12'),(select producteid from productes where cant =10));





  CREATE TABLE usser (
    userid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    username varchar(24) NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    role boolean DEFAULT false,
    groups int,
    enabled boolean DEFAULT false
  );
  INSERT INTO usser (username, password, role, groups) VALUES ('user', crypt('pass', gen_salt('bf')), true,0);
  INSERT INTO usser (username, password, role, groups) VALUES ('user2', crypt('1324', gen_salt('bf')), true,0);
  INSERT INTO usser (username, password, role, groups) VALUES ('user3', crypt('1234', gen_salt('bf')), false ,1);
  INSERT INTO usser (username, password, role, groups) VALUES ('user4', crypt('1234', gen_salt('bf')), false ,1);
--------
  Create TABLE missatges(
    missatgesid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    missatges varchar(255) NOT NULL,
    traduccio varchar(255),
    iduserenviat uuid REFERENCES usser(userid) ON DELETE CASCADE,
    iduserremitent uuid REFERENCES usser(userid) ON DELETE CASCADE
  );
-----------
  Create TABLE missatges(
    missatgesid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    missatges varchar(255) NOT NULL,
    traduccio varchar(255),
  );
  insert into missatges(missatges, iduserenviat,iduserremitent)values('hola com estas',(select userid from usser where username='user'),(select userid from usser where username='user3'));

  create table missatgesremitent(

   userid uuid REFERENCES usser(userid) ON DELETE CASCADE,
   misatgesid uuid REFERENCES missatges(missatgesid) ON DELETE CASCADE,
   PRIMARY KEY (userid, misatgesid));
  );

  insert into missatgesremitent values((select userid from usser where username='user'),(select missatgesid from missatges where missatges='hola com estas'));


  create table missatgesdesti(
   misatgesid uuid REFERENCES missatges(missatgesid) ON DELETE CASCADE,
   userid uuid REFERENCES usser(userid) ON DELETE CASCADE,
   PRIMARY KEY (userid, misatgesid));
  );

  insert into missatgesdesti values((select missatgesid from missatges where missatges='hola com estas'),(select userid from usser where username='user3'));

  Create TABLE productes(
    producteid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    nom varchar,
    cant int
  );
  insert into productes(nom,cant) values('tuerca',10);
  create TABLE pedidos(
    pedidosuuid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    data varchar,
    entregat boolean  DEFAULT false
  );
INSERT INTO pedidos(data,entregat) values('2020-08-12',false);

CREATE EXTENSION IF NOT EXISTS pgcrypto;
INSERT INTO usser (username, password) VALUES ('user', crypt('pass', gen_salt('bf')));
INSERT INTO usser (username, password) VALUES ('pepe', crypt('pass', gen_salt('bf')));


INSERT INTO movie(title, synopsis, imageurl) VALUES
    ('Movie One','This is the One Movie','movie1.jpg'),
    ('Movie Two','The Two Movie is the next','movie2.jpg'),
    ('Movie Three','The Trilogy','movie3.jpg'),
    ('Movie Four','Four movies is too much','movie4.jpg');

INSERT INTO actor(name, imageurl) VALUES
    ('Actor One','actor1.jpg'),
    ('Actor Two','actor2.jpg'),
    ('Actor Three','actor3.jpg'),
    ('Actor Four','actor4.jpg'),
    ('Actor Five','actor5.jpg');

INSERT INTO genre(label) VALUES
    ('Genre One'),
    ('Genre Two'),
    ('Genre Three');

INSERT INTO movie_actor VALUES
    ((SELECT movieid FROM movie WHERE title='Movie One'),(SELECT actorid FROM actor WHERE name='Actor One')),
    ((SELECT movieid FROM movie WHERE title='Movie One'),(SELECT actorid FROM actor WHERE name='Actor Two')),
    ((SELECT movieid FROM movie WHERE title='Movie Two'),(SELECT actorid FROM actor WHERE name='Actor Three')),
    ((SELECT movieid FROM movie WHERE title='Movie Two'),(SELECT actorid FROM actor WHERE name='Actor Four')),
    ((SELECT movieid FROM movie WHERE title='Movie Three'),(SELECT actorid FROM actor WHERE name='Actor Four')),
    ((SELECT movieid FROM movie WHERE title='Movie Three'),(SELECT actorid FROM actor WHERE name='Actor Five')),
    ((SELECT movieid FROM movie WHERE title='Movie Four'),(SELECT actorid FROM actor WHERE name='Actor One')),
    ((SELECT movieid FROM movie WHERE title='Movie Four'),(SELECT actorid FROM actor WHERE name='Actor Four'));

INSERT INTO movie_genre VALUES
    ((SELECT movieid FROM movie WHERE title='Movie One'),(SELECT genreid FROM genre WHERE label='Genre One')),
    ((SELECT movieid FROM movie WHERE title='Movie One'),(SELECT genreid FROM genre WHERE label='Genre Two')),
    ((SELECT movieid FROM movie WHERE title='Movie Two'),(SELECT genreid FROM genre WHERE label='Genre One')),
    ((SELECT movieid FROM movie WHERE title='Movie Three'),(SELECT genreid FROM genre WHERE label='Genre One')),
    ((SELECT movieid FROM movie WHERE title='Movie Three'),(SELECT genreid FROM genre WHERE label='Genre Two')),
    ((SELECT movieid FROM movie WHERE title='Movie Three'),(SELECT genreid FROM genre WHERE label='Genre Three'));

INSERT INTO favorite VALUES
    ((SELECT userid FROM usser WHERE username = 'user'),(SELECT movieid FROM movie WHERE title='Movie One'));