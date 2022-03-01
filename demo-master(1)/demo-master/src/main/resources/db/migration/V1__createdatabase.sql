




  CREATE TABLE usser (
    userid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    username varchar(24) NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    role boolean DEFAULT false,
    groups int,
    enabled boolean DEFAULT false
  );
 
  Create TABLE missatges(
    missatgesid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    missatges varchar(255) NOT NULL,
    traduccio varchar(255),
    iduserenviat uuid REFERENCES usser(userid) ON DELETE CASCADE,
    iduserremitent uuid REFERENCES usser(userid) ON DELETE CASCADE
  );



  Create TABLE productes(
    producteid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    nom varchar,
    cant int
  );
  create TABLE pedidos(
    pedidosuuid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    data varchar,
    entregat boolean  DEFAULT false
  );
  
  
CREATE TABLE favorite (
    userid uuid REFERENCES usser(userid) ON DELETE CASCADE,
    pedidosid uuid REFERENCES pedidos(pedidosuuid) ON DELETE CASCADE,
    PRIMARY KEY(userid, pedidosid)
);


create TABLE pedidos_productes(
    pedidosid uuid REFERENCES pedidos(pedidosuuid) ON DELETE CASCADE,
    producteid uuid REFERENCES productes(producteid) ON DELETE CASCADE,
    PRIMARY KEY (pedidosid,producteid));



  
  INSERT INTO usser (username, password, role, groups) VALUES ('user', crypt('pass', gen_salt('bf')), true,0);
  INSERT INTO usser (username, password, role, groups) VALUES ('user2', crypt('1324', gen_salt('bf')), true,0);
  INSERT INTO usser (username, password, role, groups) VALUES ('user3', crypt('1234', gen_salt('bf')), false ,1);
  INSERT INTO usser (username, password, role, groups) VALUES ('user4', crypt('1234', gen_salt('bf')), false ,1);
  
  INSERT INTO pedidos(data,entregat) values('2020-08-12',false);
  insert into productes(nom,cant) values('tuerca',10);

  insert into pedidos_productes values((select pedidosuuid from pedidos where data='2020-12-12'),(select producteid from productes where cant =10));

  
  insert into favorite values((select userid from usser where username='user'),(select pedidosuuid from pedidos where entregat ='f'));
  insert into favorite values((select userid from usser where username='user'),(select pedidosuuid from pedidos where data ='2020-08-12'));



CREATE TABLE usser (
  userid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
  username varchar(24) NOT NULL UNIQUE,
  password varchar(255) NOT NULL,
  role varchar(10),
  enabled boolean DEFAULT true);



