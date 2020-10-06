CREATE TABLE IF NOT EXISTS `a3t1`.`staff`
(
    `id`          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `first_name`  VARCHAR(255) NOT NULL,
    `middle_name` VARCHAR(255) NULL DEFAULT NULL,
    `last_name`   VARCHAR(255) NOT NULL,
    `address`     VARCHAR(255) NOT NULL,
    `state`       VARCHAR(16)  NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;