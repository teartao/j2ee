-- 查出所有 94年出生的学生
SELECT  s.* FROM db_student s
WHERE s.birth LIKE '1992%';

-- 查出三年二班所有学生
select s.*,c.* from db_student s
LEFT JOIN db_class c on c.id=s.class_id
WHERE c.grade_no=3 AND c.classno=2;

