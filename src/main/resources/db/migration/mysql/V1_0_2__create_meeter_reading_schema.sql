# CREATE TABLE IF NOT EXISTS `MeterReading` (
#     `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
#     `description` varchar(50),
#     `location_id` bigint NOT NULL,
#     CONSTRAINT `fk_Location` FOREIGN KEY (location_id) REFERENCES Location (id)
# )ENGINE=InnoDB DEFAULT CHARSET=UTF8;