DROP DATABASE IF EXISTS `elective`;

CREATE DATABASE `elective` DEFAULT CHARACTER SET utf8;

USE `elective`;

CREATE TABLE `users` (
`id` INTEGER PRIMARY KEY NOT NULL,
`login` VARCHAR(255) NOT NULL,
`password` VARCHAR(255) NOT NULL,
`e-mail` VARCHAR(255),
`role` INTEGER NOT NULL
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `instructors`(
`id` INTEGER PRIMARY KEY NOT NULL,
`surname` VARCHAR(100) NOT NULL,
`name` VARCHAR(100) NOT NULL,
`rank` VARCHAR(150) NOT NULL,
FOREIGN KEY (`id`) REFERENCES `users`(`id`)
ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `students`(
`id` INTEGER PRIMARY KEY NOT NULL,
`surname` VARCHAR(100) NOT NULL,
`name` VARCHAR(100) NOT NULL,
`study_year` INTEGER NOT NULL,
FOREIGN KEY (`id`) REFERENCES `users`(`id`)
ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

/* CREATE TABLE `admins`(
`id` INTEGER PRIMARY KEY NOT NULL,
`surname` VARCHAR(100) NOT NULL,
`name` VARCHAR(100) NOT NULL,
`position` VARCHAR(150) NOT NULL,
FOREIGN KEY (`id`) REFERENCES `users`(`id`)
ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=INNODB DEFAULT CHARACTER SET utf8; */

CREATE TABLE `courses`(
`id` INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
`name` VARCHAR(100) NOT NULL,
`hours` INTEGER NOT NULL,
`description` TEXT NOT NULL,
`instructor_id` INTEGER,
FOREIGN KEY (`instructor_id`) REFERENCES `instructors`(`id`)
ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `assignments`(
`id` INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
`student_id` INTEGER NOT NULL,
`course_id` INTEGER NOT NULL,
`begin`DATE NOT NULL,
`end` DATE NOT NULL,
FOREIGN KEY (`student_id`) REFERENCES `students`(`id`)
ON DELETE CASCADE ON UPDATE RESTRICT,
FOREIGN KEY (`course_id`) REFERENCES `courses`(`id`)
ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `results` (
`id` INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
`assignment_id` INTEGER NOT NULL,
`mark` INTEGER NOT NULL,
`review` TEXT NOT NULL,
`date` DATE NOT NULL,
FOREIGN KEY (`assignment_id`) REFERENCES `assignments`(`id`)
ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

INSERT INTO `users`
(`id`, `login`, `password`, `role`)
VALUES
(0,    "admin", "12345", 0),
(1,    "teacher66", "12345", 1),
(2,    "teacher99", "12345", 1),
(3,    "student66", "12345", 2),
(4,    "student99", "12345", 2);

INSERT INTO `instructors`
(`id`, `surname`, `name`, `rank`)
VALUES
(0,    "Павел", "Павлович", "Другое");
(1,    "Иван", "Иванов", "Профессор"),
(2,    "Андрей", "Андреев", "Доцент");

INSERT INTO `students`
(`id`, `surname`, `name`, `study_year`)
VALUES
(3,    "Сергей", "Сергеев", 2),
(4,    "Егор", "Егоров", 2);

/*
INSERT INTO `admins`
(`id`, `surname`, `name`, `position`)
VALUES
(0,    "Павел", "Павлович", "Лаборант");
 */

INSERT INTO `courses`
(`name`, `hours`, `description`, `instructor_id`)
VALUES
("JAVA Collections", 6, "На данном курсе вы познакомитесь с основными типами коллекций JAVA, их методами и особенностями", 1),
("JAVA Multithreading", 12, "На данном курсе вы познакомитесь с многопоточном программировании на языке JAVA", 2);

INSERT INTO `assignments`
(`student_id`, `course_id`, `begin`, `end`)
VALUES
(3, 1, "2021-03-31", "2021-04-14"),
(4, 2, "2021-02-21", "2021-03-20");

INSERT INTO `results`
(`assignment_id`, `mark`, `review`, `date`)
VALUES
(1, 9, "Успешно ознакомился с курсом", "2021-04-14"),
(2, 3, "Незачёт. Пересдача!!!", "2021-03-20"),
(2, 7, "Успешно ознакомился с курсом", "2021-03-24");