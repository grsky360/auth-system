create table user (
    `id` int(11) not null auto_increment,
    `username` varchar(20) default '',
    `created_time` timestamp null default current_timestamp,
    `updated_time` timestamp null default current_timestamp ON UPDATE current_timestamp,
    primary key (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;