create table user
(
    `id`           bigint(11) not null auto_increment,
    `user_id`      bigint(11) not null,
    `username`     varchar(20)     default '',
    `password`     varchar(50)     default '',
    `email`        varchar(50)     default '',
    `phone`        varchar(20)     default '',
    `created_time` timestamp  null default current_timestamp,
    `updated_time` timestamp  null default current_timestamp
        ON UPDATE
                    current_timestamp,
    primary key (`id`),
    unique key (`user_id`),
    unique key (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4;
create table access_token
(
    `id`           bigint(11)  not null auto_increment,
    `user_id`      bigint(11)  not null,
    `token` varchar(50) not null,
    `expires_at`   datetime    not null,
    `created_time` timestamp   null default current_timestamp,
    `updated_time` timestamp   null default current_timestamp
        ON UPDATE
                    current_timestamp,
    primary key (`id`),
    unique key (`user_id`),
    unique key (`token`),
    index (`token`)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4;