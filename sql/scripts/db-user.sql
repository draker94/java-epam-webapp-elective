CREATE USER `training_user`@`localhost` IDENTIFIED BY '12345';
GRANT ALL PRIVILEGES ON elective.* TO `training_user`@`localhost`;
FLUSH PRIVILEGES;