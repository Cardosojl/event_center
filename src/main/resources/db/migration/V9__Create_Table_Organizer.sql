CREATE TABLE IF NOT EXISTS `organizers` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(45) NOT NULL,
    `email` varchar(45) NOT NULL,
    `password` varchar(45) NOT NULL,
    `phone` varchar(11) NOT NULL,
    PRIMARY KEY (`id`)
);

