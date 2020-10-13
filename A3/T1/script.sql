CREATE TABLE `staff`
(
    `id`          int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `first_name`  varchar(255)     NOT NULL,
    `middle_name` varchar(255) DEFAULT NULL,
    `last_name`   varchar(255)     NOT NULL,
    `address`     varchar(255)     NOT NULL,
    `city`        varchar(255)     NOT NULL,
    `state`       varchar(16)      NOT NULL,
    `telephone`   int(11)          NOT NULL,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;