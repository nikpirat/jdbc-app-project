use test;
create table if not exists `skills`
(
    `skill_id`   bigint primary key auto_increment,
    `skill_name` varchar(40) not null
);

create table if not exists `developers`
(
    `developer_id` bigint primary key auto_increment,
    `name`         varchar(64) not null
);

create table if not exists `accounts`
(
    `account_id`     bigint primary key auto_increment,
    `developer_id`   bigint unique not null,
    `username`       varchar(40)   not null,
    `account_status` enum ('ACTIVE','BANNED','DELETED') DEFAULT 'ACTIVE',

    foreign key (`developer_id`) references `developers` (`developer_id`) on delete cascade
);

create table if not exists `developer_skills`
(
    `developer_id` bigint not null,
    `skill_id`     bigint not null,

    foreign key (`developer_id`) references `developers` (`developer_id`)
);