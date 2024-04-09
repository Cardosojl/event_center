CREATE TABLE IF NOT EXISTS `merchants_events` (
    `events_id` bigint(20) NOT NULL,
    `merchants_id` bigint(20) NOT NULL,
    FOREIGN KEY (`events_id`) REFERENCES `events`(`id`),
    FOREIGN KEY (`merchants_id`) REFERENCES `merchants`(`id`)
);

