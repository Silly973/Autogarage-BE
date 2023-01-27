INSERT INTO users (username, password, enabled, email)
VALUES ('monteur', '$2y$10$pn4mvxgRMiFSelR/8LbTE.VgPVF4eYQFR0bd.vmkznvnesWfnUwxK', TRUE, 'monteur@gmail.com');
INSERT INTO users (username, password, enabled, email)
VALUES('administratief_medewerker', '$2y$10$pn4mvxgRMiFSelR/8LbTE.VgPVF4eYQFR0bd.vmkznvnesWfnUwxK', TRUE, 'medewerker@gmail.com');

INSERT INTO authorities(username, authority)
VALUES('monteur', 'ROLE_MONTEUR');
INSERT INTO authorities(username, authority)
VALUES('administratief_medewerker', 'ROLE_ADMINISTRATIEFMEDEWERKER');


INSERT INTO customers (first_name, last_name, phone_number, email)
VALUES('Silvia', 'van Lingen', '0612345678', 'emailtje@gmail.com');
INSERT INTO customers(first_name, last_name, phone_number, email)
VALUES ('John', 'van de Heuvel', '0655555555', 'heuvel@gmail.com');
INSERT INTO customers (first_name, last_name, phone_number, email)
VALUES('Nikki', 'van de Rode', '06114785962', 'rodeduivel@gmail.com');


INSERT INTO cars(license_plate, customer_id, brand, model, construction_year)
VALUES('G707NX', 1, 'Mitsubishi', 'Spacestar',2019);
INSERT INTO cars(license_plate, customer_id, brand, model, construction_year)
VALUES('288XX8', 2, 'Mitsubishi', 'Spacestar',2021);
INSERT INTO cars(license_plate, customer_id, brand, model, construction_year)
VALUES('HNFG47', 3,  'Ford', 'Fiesta',2017);

INSERT INTO inspections(appointment_date, appointment_status, car_id)
VALUES ('2022-12-19','CUSTOMER_APPROVED', 1);
INSERT INTO inspections(appointment_date, appointment_status, car_id)
VALUES ('2022-12-21','REPAIR_COMPLETED', 2);
INSERT INTO inspections(appointment_date, appointment_status, car_id)
VALUES ('2022-12-20','CUSTOMER_DISAPPROVED', 3);


INSERT INTO repairs(appointment_date, appointment_status, car_id)
VALUES ('2022-12-19', 'APPOINTMENT_SCHEDULED', 1);
INSERT INTO repairs(appointment_date, appointment_status, car_id)
VALUES ('2022-12-21', 'REPAIR_COMPLETED', 2);
INSERT INTO repairs(appointment_date, appointment_status, car_id)
VALUES ('2022-12-20', 'REPAIR_COMPLETED', 3);


INSERT INTO deficiencies(name, inspection_id)
VALUES ('Accu vervangen', 1);
INSERT INTO deficiencies(name, inspection_id)
VALUES ('Remmen schoonmaken', 1);
INSERT INTO deficiencies(name, inspection_id)
VALUES ('Winterbanden verwisselen', 1);
INSERT INTO deficiencies(name, inspection_id)
VALUES ('Zomerbanden verwisselen', 2);
INSERT INTO deficiencies(name, inspection_id)
VALUES ('Ruitenwisservloeistof bijvullen (winter)', 2);
INSERT INTO deficiencies(name, inspection_id)
VALUES ('Niet akkoord na inspectie', 3);


INSERT INTO parts(name, price, repair_id)
VALUES ('Accu Bosch S4 028', 110.87, 1);
INSERT INTO parts(name, price, repair_id)
VALUES ('Remschijven schoongemaakt', 42.95, 1);
INSERT INTO parts(name, price, repair_id)
VALUES ('Koelvloeistof bijgevuld', 8.25, 1);
INSERT INTO parts(name, price, repair_id)
VALUES ('Wissel banden zomer/winter', 85.00, 1);
INSERT INTO parts(name, price, repair_id)
VALUES ('Luchtfilter vervangen', 25.00, 2);
INSERT INTO parts(name, price, repair_id)
VALUES ('Wissel banden zomer/winter', 85.00, 2);
INSERT INTO parts(name, price, repair_id)
VALUES ('Ruitenwisservloeistof per 500 ml', 25.00, 2);
INSERT INTO parts(name, price, repair_id)
VALUES ('Oliefilter vervangen', 25.00, 2);
INSERT INTO parts(name, price, repair_id)
VALUES ('Algehele controle', 50.00, 3);


INSERT INTO invoices(license_plate, customer_id)
VALUES('G707NX', 1);
INSERT INTO invoices(license_plate, customer_id)
VALUES('288XX8', 2);
INSERT INTO invoices(license_plate, customer_id)
VALUES('HNFG47', 3);

