Create table instructors
(

    instructor_id   serial primary key,
    instructor_name varchar(100) not null,
    email           varchar(100) not null
);

Create table courses
(
    course_id     serial primary key,
    course_name   varchar(100)                                                 not null,
    description   varchar(100)                                                 not null,
    instructor_id int references instructors (instructor_id) on delete cascade not null
);

create table students
(

    student_id   serial primary key,
    student_name varchar(100) not null,
    email        varchar(100) not null,
    phone_number varchar(100) not null

);

create table student_course
(
    student_id int references students (student_id) on delete cascade not null,
    course_id  int references courses (course_id) on delete cascade   not null

);
INSERT INTO instructors (instructor_name, email)
VALUES ('Dara', 'dara@gmail.com'),
       ('Sokha', 'sokha@gmail.com'),
       ('Vireak', 'vireak@gmail.com'),
       ('Sovann', 'sovann@gmail.com'),
       ('Piseth', 'piseth@gmail.com'),
       ('Rithy', 'rithy@gmail.com'),
       ('Chenda', 'chenda@gmail.com'),
       ('Bopha', 'bopha@gmail.com'),
       ('Kosal', 'kosal@gmail.com'),
       ('Sothea', 'sothea@gmail.com'),
       ('Rina', 'rina@gmail.com'),
       ('Mony', 'mony@gmail.com'),
       ('Chhay', 'chhay@gmail.com'),
       ('Sina', 'sina@gmail.com'),
       ('Vanna', 'vanna@gmail.com');



INSERT INTO instructors (instructor_id, instructor_name, email)
VALUES (1, 'Dara', 'dara@gmail.com'),
       (2, 'Sokha', 'sokha@gmail.com'),
       (3, 'Vireak', 'vireak@gmail.com'),
       (4, 'Sovann', 'sovann@gmail.com'),
       (5, 'Piseth', 'piseth@gmail.com'),
       (6, 'Rithy', 'rithy@gmail.com');


INSERT INTO courses (course_name, description, instructor_id)
VALUES ('Java', 'Advance', 2),
       ('Python', 'Intermediate', 3),
       ('JavaScript', 'Beginner', 4),
       ('C++', 'Advanced', 5),
       ('C#', 'Intermediate', 6),
       ('PHP', 'Beginner', 1),
       ('Swift', 'Advanced', 2),
       ('Go', 'Intermediate', 3),
       ('Kotlin', 'Beginner', 4),
       ('Ruby', 'Advanced', 5),
       ('SQL', 'Database Management', 6),
       ('React', 'Frontend Framework', 1),
       ('Spring Boot', 'Backend Framework', 2);

INSERT INTO students (student_name, email, phone_number)
VALUES ('Alice Johnson', 'alice.johnson@example.com', '0123456789'),
       ('Bob Smith', 'bob.smith@example.com', '0123456790'),
       ('Charlie Davis', 'charlie.davis@example.com', '0123456791'),
       ('Diana Roberts', 'diana.roberts@example.com', '0123456792'),
       ('Ethan Brown', 'ethan.brown@example.com', '0123456793'),
       ('Fiona Wilson', 'fiona.wilson@example.com', '0123456794'),
       ('George White', 'george.white@example.com', '0123456795'),
       ('Hannah Moore', 'hannah.moore@example.com', '0123456796'),
       ('Isaac Taylor', 'isaac.taylor@example.com', '0123456797'),
       ('Jessica Hall', 'jessica.hall@example.com', '0123456798'),
       ('Kevin Adams', 'kevin.adams@example.com', '0123456799'),
       ('Laura Thomas', 'laura.thomas@example.com', '0123456800'),
       ('Michael Scott', 'michael.scott@example.com', '0123456801'),
       ('Nancy Lewis', 'nancy.lewis@example.com', '0123456802'),
       ('Oliver Walker', 'oliver.walker@example.com', '0123456803'),
       ('Paula Harris', 'paula.harris@example.com', '0123456804'),
       ('Quinn Martin', 'quinn.martin@example.com', '0123456805'),
       ('Rachel Young', 'rachel.young@example.com', '0123456806'),
       ('Samuel King', 'samuel.king@example.com', '0123456807'),
       ('Tina Turner', 'tina.turner@example.com', '0123456808');




UPDATE students set student_name = 'dara', email= '@gmaim', phone_number = '12345678'
where student_id = '30'
returning student_id;



