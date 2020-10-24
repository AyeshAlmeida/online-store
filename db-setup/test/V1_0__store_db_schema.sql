CREATE DATABASE IF NOT EXISTS `store_db_test`;

use `store_db_test`;

CREATE TABLE IF NOT EXISTS `product` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(40) NOT NULL,
	`packs_per_carton` SMALLINT(10) NOT NULL,
	`carton_price` FLOAT(10,2) NOT NULL,
	PRIMARY KEY (`id`)
);