
INSERT INTO tb_user (name, email, password) VALUES ('Luis Gustavo', 'luisgssf@gmail.com', '$2a$10$jrgW4jrYsLdOd6ZK0gc6PO246u/06aoH/3vvmhhM6qDvAMpozywhi');
INSERT INTO tb_user (name, email, password) VALUES ('Stefany', 'stefany@gmail.com', '$2a$10$jrgW4jrYsLdOd6ZK0gc6PO246u/06aoH/3vvmhhM6qDvAMpozywhi');

INSERT INTO tb_role (role_name) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (role_name) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
