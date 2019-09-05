INSERT INTO app_role (id, role_name, description) VALUES (1, 'STANDARD_USER', 'Standard User - Has no admin rights');
INSERT INTO app_role (id, role_name, description) VALUES (3, 'ROLE_PATIENT_ACCESS', 'Standard User - Has no admin rights');
INSERT INTO app_role (id, role_name, description) VALUES (4, 'ROLE_PATIENT_REGISTRATION', 'Standard User - Has no admin rights');
INSERT INTO app_role (id, role_name, description) VALUES (2, 'ADMIN_USER', 'Admin User - Has permission to perform admin tasks');
INSERT INTO app_role (id, role_name, description) VALUES (5, 'ROLE_DOCTOR', 'ROLE_DOCTOR');
INSERT INTO app_role (id, role_name, description) VALUES (6, 'ROLE_CA', 'ROLE_CA');
INSERT INTO app_role (id, role_name, description) VALUES (7, 'ROLE_PATIENT_PAYMENT_CHECK', '');
INSERT INTO app_role (id, role_name, description) VALUES (8, 'ROLE_BILL_PAYMENT', '');

-- USER
-- non-encrypted password: jwtpass

INSERT INTO app_user (
id,
first_name,
last_name,
password,
username,
create_date,
last_update,
email,
status,
old_passwords,
number_of_login_attempts,
context
) VALUES (
1,
'John',
'Doe',
'$2a$10$LOqePml/koRGsk2YAIOFI.1YNKZg7EsQ5BAIuYP1nWOyYRl21dlne',
'john.doe',
'0',
'0',
'chad.sanka@gmail.com',
'ACTIVE',
'[]',
'0',
'{''cms-user-id'': ''D0001''}'
);
INSERT INTO app_user (id, first_name, last_name, password, username) VALUES (2, 'Admin', 'Admin', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a', 'admin.admin');

INSERT INTO user_role(user_id, role_id) VALUES(1,1);
INSERT INTO user_role(user_id, role_id) VALUES(1,3);
INSERT INTO user_role(user_id, role_id) VALUES(1,4);
INSERT INTO user_role(user_id, role_id) VALUES(1,5);
INSERT INTO user_role(user_id, role_id) VALUES(1,6);
INSERT INTO user_role(user_id, role_id) VALUES(1,7);
INSERT INTO user_role(user_id, role_id) VALUES(1,8);
INSERT INTO user_role(user_id, role_id) VALUES(2,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,2);

