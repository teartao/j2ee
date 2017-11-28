-- 所有92年出生的学生
SELECT  s.* FROM db_student s
WHERE s.birth LIKE '1992%';

-- 三年二班所有学生
SELECT s.*,c.* FROM db_student s
LEFT JOIN db_class c on c.id=s.class_id
WHERE c.grade_no=3 AND c.classno=2;

SELECT s.*,c.* FROM db_student s
INNER JOIN db_class c on c.id=s.class_id AND c.grade_no=3 AND c.classno=2;


-- 二年级每班人数
SELECT count(s.id) '人数',c.classno '班级' FROM  db_student s
LEFT JOIN db_class c ON  c.id =s.class_id
WHERE c.grade_no=2
GROUP BY c.id

SELECT count(s.id) '人数',c.classno '班级' FROM  db_student s
INNER JOIN db_class c ON  c.id =s.class_id AND c.grade_no=2
GROUP BY c.id


-- 3年2班所有学生年龄
SELECT s.*,(YEAR(now())-YEAR(s.birth)) '年龄',c.* from db_student s
LEFT JOIN db_class c on c.id=s.class_id
WHERE c.grade_no=3 AND c.classno=2;

SELECT s.*,(YEAR(now())-YEAR(s.birth)) '年龄',c.* from db_student s
INNER JOIN db_class c on c.id=s.class_id AND c.grade_no=3 AND c.classno=2;


-- 2-4年级所有学生平均年龄
SELECT
c.grade_no '年级',avg((YEAR(now())-YEAR(s.birth)))  '平均年龄'
FROM db_student s
INNER JOIN db_class c ON c.id =s.class_id AND (c.grade_no >=2 AND c.grade_no<=4)
GROUP BY c.grade_no

 --ON  WHERE HAVING 区别 https://www.cnblogs.com/strive-study/p/5068158.html


