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