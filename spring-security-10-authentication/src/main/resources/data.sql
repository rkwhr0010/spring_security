INSERT IGNORE INTO `spring`.`user` (`id`, `username`, `password`, `algorithm`) VALUES ('1', 'john', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'BCRYPT');
INSERT IGNORE INTO `spring`.`user` (`id`, `username`, `password`, `algorithm`) VALUES ('2', 'scott', '$100801$wrZ7+bA1n+YTdPdRhjFGYQ==$qZzYBmpejdVm7hC+AkimN5t5y9StgUwhY4K72JdSJXU=', 'SCRYPT');

INSERT IGNORE INTO `spring`.`authority` (`id`, `name`, `user`) VALUES ('1', 'READ', '1');
INSERT IGNORE INTO `spring`.`authority` (`id`, `name`, `user`) VALUES ('2', 'WRITE', '1');
INSERT IGNORE INTO `spring`.`authority` (`id`, `name`, `user`) VALUES ('3', 'READ', '2');
INSERT IGNORE INTO `spring`.`authority` (`id`, `name`, `user`) VALUES ('4', 'WRITE', '2');

INSERT IGNORE INTO `spring`.`product` (`id`, `name`, `price`, `currency`) VALUES ('1', 'Chocolate', '10', 'USD');
INSERT IGNORE INTO `spring`.`product` (`id`, `name`, `price`, `currency`) VALUES ('2', 'Apple', '5', 'EUR');
INSERT IGNORE INTO `spring`.`product` (`id`, `name`, `price`, `currency`) VALUES ('3', 'Orange', '4', 'USD');
INSERT IGNORE INTO `spring`.`product` (`id`, `name`, `price`, `currency`) VALUES ('4', 'Banana', '4', 'EUR');
