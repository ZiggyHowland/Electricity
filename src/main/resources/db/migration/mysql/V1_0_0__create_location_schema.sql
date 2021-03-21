CREATE TABLE IF NOT EXISTS `Location` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `street` varchar(100),
    `houseNumber` varchar(10),
    `houseSection` varchar(10),
    `postCode` varchar(10)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;