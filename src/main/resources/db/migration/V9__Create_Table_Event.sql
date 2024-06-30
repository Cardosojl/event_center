CREATE TABLE IF NOT EXISTS `events` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `date` DATETIME NOT NULL,
    `description` varchar(800),
    `status` ENUM('UNDER_ANALYSIS', 'APPROVED', 'REJECTED') NOT NULL DEFAULT 'UNDER_ANALYSIS',
    `name` varchar(45) NOT NULL,
    `event_request` varchar(800) NOT NULL,
    `event_type_id` bigint(20) NOT NULL,
    `organizer_id` bigint(20) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`event_type_id`) REFERENCES `event_types`(`id`),
    FOREIGN KEY (`organizer_id`) REFERENCES `users`(`id`)
);
