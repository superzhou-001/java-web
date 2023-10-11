DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
    `id` int(11) NOT NULL,
    `name` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
    `age` int(11) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

INSERT INTO `users` VALUES (1, 'xiaoming', 21);
INSERT INTO `users` VALUES (2, 'xiaodong', 22);
INSERT INTO `users` VALUES (3, 'mayun', 28);
INSERT INTO `users` VALUES (4, 'huateng', 21);
INSERT INTO `users` VALUES (5, 'xiaohong', 24);
INSERT INTO `users` VALUES (6, 'liming', 18);
INSERT INTO `users` VALUES (7, 'meiling', 20);

DROP TABLE IF EXISTS `cron`;
CREATE TABLE `cron`  (
    `id` varchar(30) NOT NULL PRIMARY KEY,
    `cron` varchar(30) NOT NULL,
     UNIQUE INDEX `id`(`id`) USING BTREE
);
INSERT INTO `cron` VALUES ('1', '0/5 * * * * ?');
