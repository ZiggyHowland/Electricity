CREATE TABLE IF NOT EXISTS `Location` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `street` varchar(100),
    `house_number` varchar(10),
    `house_section` varchar(10),
    `post_code` varchar(10)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;