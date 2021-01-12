USE `elective`;

SELECT `id`, `student_id`, `course_id`, `begin`, `end` FROM `assignments` WHERE `id` NOT IN (SELECT `assignment_id` FROM `results`);