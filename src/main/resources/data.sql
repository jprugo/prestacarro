INSERT INTO gwtsasPrestaCarro.tbl_active (id_active, internal_code, is_disabled, purchase_date, serial)
VALUES (1, 'C01', false, null, 'C01'),
       (2, 'C02', false, null, 'C02'),
       (3, 'C03', false, null, 'C03'),
       (4, 'C04', false, null, 'C04'),
       (5, 'C05', false, null, 'C05'),
       (6, 'C06', false, null, 'C06'),
       (7, 'C07', false, null, 'C07'),
       (8, 'C08', false, null, 'C08');
       
insert into tbl_users (username, password)
values ('admin', '$2a$12$v5KbZqlO44Bi2K30YV9bxeR7Tvyzh4jwkqch/L2URXx.Wodqsqwpm');

insert into tbl_person (birth_date, document_number, first_name, last_name,
                        person_registration_date, sex)
values (current_timestamp(), '12345678910', 'Admin', 'Gigawatt', current_timestamp(), 'F');