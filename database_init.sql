CREATE SCHEMA `checkout_test` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `checkout_test`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(30) NULL,
  `name` VARCHAR(250) NULL,
  `value` DOUBLE NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `checkout_test`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(20) NULL,
  `ORDER_DATE` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `checkout_test`.`product_orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `order_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
