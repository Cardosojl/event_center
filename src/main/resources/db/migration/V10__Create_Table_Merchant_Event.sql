CREATE TABLE IF NOT EXISTS `merchants_events` (
    `id_event` bigint(20) NOT NULL,
    `id_merchant` bigint(20) NOT NULL,
    PRIMARY KEY (`id_event`, `id_merchant`),
    KEY `fk_merchant_event_event` (`id_event`),
    CONSTRAINT `fk_merchant_event` FOREIGN KEY (`id_merchant`) REFERENCES `users`(`id`),
    CONSTRAINT `fk_merchant_event_event` FOREIGN KEY (`id_event`) REFERENCES `events`(`id`)
)ENGINE=InnoDB;

