DROP DATABASE IF EXISTS campaign;
CREATE DATABASE campaign DEFAULT CHARACTER SET utf8mb4;
USE campaign;

drop table if exists exam;

drop table if exists speciality;

drop table if exists speciality_subject;

drop table if exists subject;

drop table if exists university;

drop table if exists university_speciality;

drop table if exists user;

drop table if exists user_speciality;


create table speciality
(
  id                bigint auto_increment
    primary key,
  max_student_count int                          not null,
  title             varchar(100)                 not null,
  deleted           bit default b'0'             not null,
  passmark          int                          not null,
  status            varchar(20) default 'ACTIVE' not null
)
  collate = utf8_general_mysql500_ci;

create table subject
(
  id    bigint auto_increment
    primary key,
  title varchar(100) not null
)
  collate = utf8_general_mysql500_ci;

create table exam
(
  id         bigint auto_increment
    primary key,
  user_id    bigint          not null,
  subject_id bigint          not null,
  mark       int default '0' not null,
  title      varchar(100)    not null,
  constraint exam_subject_id_fk
  foreign key (subject_id) references subject (id)
    on update cascade
    on delete cascade
)
  collate = utf8_general_mysql500_ci;

create table speciality_subject
(
  id            bigint auto_increment
    primary key,
  speciality_id bigint not null,
  subject_id    bigint not null,
  constraint required_exam_speciality_id_fk
  foreign key (speciality_id) references speciality (id)
    on update cascade
    on delete cascade,
  constraint required_exam_subject_id_fk
  foreign key (subject_id) references subject (id)
    on update cascade
    on delete cascade
)
  collate = utf8_general_mysql500_ci;

create table university
(
  id      bigint auto_increment
    primary key,
  title   varchar(100)     not null,
  deleted bit default b'0' not null,
  constraint university_title_uindex
  unique (title)
)
  collate = utf8_general_mysql500_ci;

create table university_speciality
(
  id            bigint auto_increment
    primary key,
  university_id bigint not null,
  speciality_id bigint not null,
  constraint university_speciality_speciality_id_fk
  foreign key (speciality_id) references speciality (id)
    on update cascade
    on delete cascade,
  constraint university_speciality_university_id_fk
  foreign key (university_id) references university (id)
    on update cascade
    on delete cascade
)
  collate = utf8_general_mysql500_ci;

create table user
(
  id          bigint auto_increment
    primary key,
  first_name  varchar(50)  not null,
  second_name varchar(50)  not null,
  middle_name varchar(50)  not null,
  role        varchar(10)  not null,
  password    varchar(100) not null,
  email       varchar(100) not null,
  status      varchar(10)  not null,
  constraint user_email_uindex
  unique (email)
)
  charset = utf8mb4;

create table user_speciality
(
  id            bigint auto_increment
    primary key,
  user_id       bigint                   not null,
  speciality_id bigint                   not null,
  university_id bigint                   not null,
  enter_status  varchar(20) default 'NO' not null,
  constraint user_speciality_speciality_id_fk
  foreign key (speciality_id) references speciality (id)
    on update cascade
    on delete cascade,
  constraint user_speciality_university_id_fk
  foreign key (university_id) references university (id)
    on update cascade
    on delete cascade,
  constraint user_speciality_user_id_fk
  foreign key (user_id) references user (id)
    on update cascade
    on delete cascade
)
  collate = utf8_general_mysql500_ci;

