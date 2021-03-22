CREATE TABLE IF NOT EXISTS `Meter` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `description` varchar(50),
    `locationId` bigint NOT NULL,
    CONSTRAINT `fk_Location` FOREIGN KEY (locationId) REFERENCES Location (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;