DROP DATABASE IF EXISTS `elective`;

CREATE DATABASE `elective` DEFAULT CHARACTER SET utf8;

USE `elective`;

CREATE TABLE `users`
(
    `id`       INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `login`    VARCHAR(255)        NOT NULL UNIQUE,
    `password` VARCHAR(255)        NOT NULL,
    `e-mail`   VARCHAR(255),
    `role`     INTEGER             NOT NULL
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `instructors`
(
    `id`      INTEGER PRIMARY KEY NOT NULL,
    `surname` VARCHAR(100)        NOT NULL,
    `name`    VARCHAR(100)        NOT NULL,
    `rank`    INTEGER             NOT NULL,
    FOREIGN KEY (`id`) REFERENCES `users` (`id`)
        ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `students`
(
    `id`         INTEGER PRIMARY KEY NOT NULL,
    `surname`    VARCHAR(100)        NOT NULL,
    `name`       VARCHAR(100)        NOT NULL,
    `study_year` INTEGER             NOT NULL,
    FOREIGN KEY (`id`) REFERENCES `users` (`id`)
        ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `courses`
(
    `id`            INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name`          VARCHAR(100)        NOT NULL UNIQUE,
    `hours`         INTEGER             NOT NULL,
    `description`   TEXT                NOT NULL,
    `instructor_id` INTEGER             NOT NULL,
    FOREIGN KEY (`instructor_id`) REFERENCES `instructors` (`id`)
        ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `assignments`
(
    `id`         INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `student_id` INTEGER             NOT NULL,
    `course_id`  INTEGER             NOT NULL,
    `begin`      DATE,
    `end`        DATE,
    UNIQUE KEY (`student_id`, `course_id`),
    FOREIGN KEY (`student_id`) REFERENCES `students` (`id`)
        ON DELETE CASCADE ON UPDATE RESTRICT,
    FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`)
        ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `results`
(
    `id`            INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `assignment_id` INTEGER             NOT NULL,
    `mark`          INTEGER             NOT NULL,
    `review`        TEXT                NOT NULL,
    `date`          DATE                NOT NULL,
    UNIQUE KEY (`assignment_id`, `date`),
    FOREIGN KEY (`assignment_id`) REFERENCES `assignments` (`id`)
        ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;


INSERT INTO `users`
    (`login`, `password`, `role`)
VALUES ("admin", "12345", 0),
       ("teacher1", "12345", 1),
       ("teacher2", "12345", 1),
       ("student1", "12345", 2),
       ("student2", "12345", 2);

INSERT INTO `instructors`
    (`id`, `surname`, `name`, `rank`)
VALUES (1, "Иванова", "Татьяна", 4),
       (2, "Макаров", "Павел", 1),
       (3, "Вербицкий", "Андрей", 3);

INSERT INTO `students`
    (`id`, `surname`, `name`, `study_year`)
VALUES (4, "Арамзанцева", "Алиса", 2),
       (5, "Егоров", "Егор", 2);

INSERT INTO `courses`
    (`name`, `hours`, `description`, `instructor_id`)
VALUES ("JAVA Collections", 6,
        "На данном курсе вы познакомитесь с основными типами коллекций JAVA, их методами и особенностями", 2),
       ("JAVA Multithreading", 12, "На данном курсе вы познакомитесь с многопоточном программировании на языке JAVA",
        3);

INSERT INTO `assignments`
    (`student_id`, `course_id`, `begin`, `end`)
VALUES (4, 1, "2021-03-31", "2021-04-14"),
       (5, 2, "2021-02-21", "2021-03-20");

INSERT INTO `results`
    (`assignment_id`, `mark`, `review`, `date`)
VALUES (1, 9, "Успешно ознакомился с курсом", "2021-04-14"),
       (2, 3, "Незачёт. Пересдача!!!", "2021-03-20"),
       (2, 7, "Успешно ознакомился с курсом", "2021-03-24");
