--Part 1
--Columns in Job table:
-- id: int PK
-- employer: varchar(255)
-- name: varchar(255)
-- skills: varchar(255)

--Part 2
SELECT name
FROM employer
WHERE location = "St. Louis City";

--Part 3
DROP TABLE job;

--Part 4
SELECT skill.name  --select name column from skill table
FROM skill --take from skill table
--join skill and job_skill tables if id column in skill table matches skill_id column in job_skill table
INNER JOIN job_skill ON skill.id = job_skill.skill_id
WHERE job_skill.job_id IS NOT NULL -- only looks at the rows where job-id in job-skill has data
ORDER BY skill.name ASC; -- order results in ascending order

--test not passing because my table is name job_skill not job_skills as is written in the test