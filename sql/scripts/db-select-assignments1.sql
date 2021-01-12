USE `elective`;

SELECT `id`, `student_id`, `course_id`, `begin`, `end` FROM `assignments` WHERE `begin` >= "2021-03-01" AND `begin` <= "2021-03-30";