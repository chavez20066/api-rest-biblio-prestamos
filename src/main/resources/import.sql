/* Creamos algunos usuarios con sus roles */
INSERT INTO users (id,username, password, enabled) VALUES (1,'user_rest_api','$2a$10$g9kTU1jmhttx9qrB7PWY6u6slv35mPgrM4GmcG77QW6tJqCTEiwpm',1);

INSERT INTO `authorities` (id,user_id, authority) VALUES (1,1,'ROLE_USER');