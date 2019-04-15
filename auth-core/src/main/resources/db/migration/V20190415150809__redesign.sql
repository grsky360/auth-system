drop table user;
create table user (
    `id`           bigint(11)  not null auto_increment,
    `user_id`      bigint(11)  not null,
    `platform`     varchar(20) not null,
    `platform_id`  bigint(11)  not null,
    `created_time` timestamp  null default current_timestamp,
    `updated_time` timestamp  null default current_timestamp ON UPDATE current_timestamp,
    primary key (`id`),
    unique key (`user_id`)
) ENGINE = InnoDB DEFAULT CHARSET = UTF8MB4;
