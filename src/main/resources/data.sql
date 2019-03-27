INSERT INTO course (id,name) VALUES (10001,'Spring Beginner to Guru!')
INSERT INTO course (id,name) VALUES (10002,'Spring Beginner to Expert!')
INSERT INTO course (id,name) VALUES (10003,'Hibernate in 100 steps!')
INSERT INTO course (id,name) VALUES (10004,'Angular in 100 steps!')

INSERT INTO review (id,description, course_id) VALUES (40001,'Very Good!',10001)
INSERT INTO review (id,description, course_id) VALUES (40002,'Very nice!',10001)
INSERT INTO review (id,description, course_id) VALUES (40003,'Bad course!',10002)

INSERT INTO passport (id,number) VALUES (30001,'L8434899')
INSERT INTO passport (id,number) VALUES (30002,'L8435499')
INSERT INTO passport (id,number) VALUES (30003,'L8434459')
INSERT INTO passport (id,number) VALUES (30004,'L8434460')

INSERT INTO student (id,name,passport_id) VALUES (20001,'Sreejith Sreekantan',30001)
INSERT INTO student (id,name,passport_id) VALUES (20002,'Abhilash Nair',30002)
INSERT INTO student (id,name,passport_id) VALUES (20003,'Anuraj Radhakrishnan',30003)
INSERT INTO student (id,name,passport_id) VALUES (20004,'Sreejith Sreekantan',30004)

INSERT INTO student_course(student_id, course_id) VALUES (20001,10001)
INSERT INTO student_course(student_id, course_id) VALUES (20003,10001)
INSERT INTO student_course(student_id, course_id) VALUES (20002,10002)
INSERT INTO student_course(student_id, course_id) VALUES (20003,10003)