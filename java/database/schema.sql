BEGIN TRANSACTION;

DROP TABLE IF EXISTS contacts;

CREATE TABLE contacts (
     contact_id SERIAL,
     "name" varchar(50),
     phone_number varchar (12)
     CONSTRAINT PK_contact_id PRIMARY KEY (contact_id)
);

COMMIT TRANSACTION;
