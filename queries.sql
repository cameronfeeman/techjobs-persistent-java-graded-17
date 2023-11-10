--Part 1
--Columns in Job table:
-- id: int PK
-- employer: varchar(255)
-- name: varchar(255)
-- skills: varchar(255)

--Part 2
SELECT name
FROM employer
WHERE location = 'St. Louis, City';

--Part 3
DROP TABLE job;

--Part 4
SELECT DISTINCT skill.name
FROM skill
INNER JOIN job_skill ON skill.id = job_skill.skill_id
ORDER BY skill.name ASC;