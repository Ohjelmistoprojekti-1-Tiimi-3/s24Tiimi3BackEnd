DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS AppUser;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Manufacturer;
DROP TABLE IF EXISTS product_size;
DROP TABLE IF EXISTS product_type;

CREATE TABLE AppUser(
    userid BIGSERIAL PRIMARY KEY,
    username varchar(100) NOT NULL UNIQUE,
    passwordhash varchar(250) NOT NULL,
    user_role varchar(25) NOT NULL
);

INSERT INTO app_user (username, passwordhash, user_role) VALUES 
('admin', '$2a$10$z9TZiBsJv7/1tk5V7TRcVOwJlsNhzgV3DKi4ArViniEPOmaLZ2kbC', 'ROLE_ADMIN');

CREATE TABLE Customer(
    customerid BIGSERIAL PRIMARY KEY,
    customername varchar(100),
    customerlastname varchar(100),
    customeremail varchar(100),
    appUser BIGINT,
    FOREIGN KEY (appUser) REFERENCES AppUser(userid)
);

INSERT INTO Customer (customername, customerlastname, customeremail) VALUES 
('Maija', 'Mehiläinen', 'maijameh@gmail.com'),
('Jorma', 'Jormanen', 'jormajor@gmail.com');


CREATE TABLE Manufacturer(
    manufacturerid BIGSERIAL PRIMARY KEY,
    manufacturername varchar(100) UNIQUE,
    manufacturerinfo varchar(500)
);

INSERT INTO Manufacturer (manufacturername, manufacturerinfo) VALUES
('Rukka', 'erittäin korkea suojaavuus'),
('Basic', 'laadukkaat tuotteet järkevään hintaan'),
('Feel Active', 'käyttöä säässä kuin säässä'),
('Hurtta', 'korkealaatuisia koirantarvikkeita');

CREATE TABLE product_size(
    sizeid BIGSERIAL PRIMARY KEY,
    size_desc varchar(10)
);

INSERT INTO product_size (size_desc) VALUES
('S'),
('M'),
('L');

CREATE TABLE product_type(
    typeid BIGSERIAL PRIMARY KEY,
    typename varchar(100)
);

INSERT INTO product_type (typename) VALUES
('Vaate'),
('Lelu'),
('Ruoka');

CREATE TABLE Product(
    productid BIGSERIAL PRIMARY KEY,
    productname varchar(100) NOT NULL,
    price DECIMAL(30,2),
    color varchar(50),
    info varchar(500),
    quantity INT,
    manufacturerid BIGINT,
    typeid BIGINT,
    sizeid BIGINT,
    FOREIGN KEY (manufacturerid) REFERENCES Manufacturer(manufacturerid),
    FOREIGN KEY (typeid) REFERENCES product_type(typeid),
    FOREIGN KEY (sizeid) REFERENCES product_size(sizeid)
);

INSERT INTO Product (productname, price, color, info, quantity, manufacturerid, typeid, sizeid) VALUES
('Sadetakki', 10.0, 'Punainen', 'Kiva, hyvä ja halpa', 3, 1, 1, 2),
('Kissan ruoka', 29.99, null, 'namnam', 4, 2, 3, null),
('Koiran purulelu', 15, 'sininen', 'kestävä', 2, 3, 2, null);
