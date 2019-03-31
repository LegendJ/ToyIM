create database if not exists `toyim`;
use `toyim`;


create table if not exists toyim_group
(
    id             bigint(11) auto_increment
        primary key,
    groupName      varchar(256) not null,
    groupAvatarUrl varchar(256) null
);

create table if not exists toyim_user
(
    id        bigint(11) auto_increment
        primary key,
    username  varchar(256) not null,
    password  varchar(64)  not null,
    avatarUrl varchar(64)  null,
    role      varchar(64)  null
);

create table if not exists toyim_friend
(
    id        bigint(11) auto_increment
        primary key,
    user_id   bigint(11) not null,
    friend_id bigint(11) not null,
    constraint toyim_friend_toyim_user_id_fk
        foreign key (user_id) references toyim_user (id),
    constraint toyim_friend_toyim_user_id_fk_2
        foreign key (friend_id) references toyim_user (id)
);

create table if not exists toyim_group_user
(
    id       bigint(11) auto_increment
        primary key,
    group_id bigint(11) not null,
    user_id  bigint(11) not null,
    constraint toyim_group_user_toyim_group_id_fk
        foreign key (group_id) references toyim_group (id),
    constraint toyim_group_user_toyim_user_id_fk
        foreign key (user_id) references toyim_user (id)
);
## insert datas
INSERT INTO toyim.toyim_user (id, username, password, avatarUrl, role) VALUES (1, 'oracle', 'oracle', '../static/img/avatar/admin.jpg', 'admin');
INSERT INTO toyim.toyim_user (id, username, password, avatarUrl, role) VALUES (2, 'just0', '123456', '../static/img/avatar/Member002.jpg', 'user');
INSERT INTO toyim.toyim_user (id, username, password, avatarUrl, role) VALUES (3, 'just1', '123456', '../static/img/avatar/Member003.jpg', 'user');
INSERT INTO toyim.toyim_user (id, username, password, avatarUrl, role) VALUES (4, 'just2', '123456', '../static/img/avatar/Member004.jpg', 'user');
INSERT INTO toyim.toyim_user (id, username, password, avatarUrl, role) VALUES (5, 'just3', '123456', '../static/img/avatar/Member005.jpg', 'user');
INSERT INTO toyim.toyim_user (id, username, password, avatarUrl, role) VALUES (6, 'just4', '123456', '../static/img/avatar/Member006.jpg', 'user');
INSERT INTO toyim.toyim_user (id, username, password, avatarUrl, role) VALUES (7, 'just5', '123456', '../static/img/avatar/Member007.jpg', 'user');
INSERT INTO toyim.toyim_user (id, username, password, avatarUrl, role) VALUES (8, 'just6', '123456', '../static/img/avatar/Member008.jpg', 'user');
INSERT INTO toyim.toyim_user (id, username, password, avatarUrl, role) VALUES (9, 'just7', '123456', '../static/img/avatar/Member009.jpg', 'user');
INSERT INTO toyim.toyim_user (id, username, password, avatarUrl, role) VALUES (10, 'just8', '123456', '../static/img/avatar/Member001.jpg', 'user');

INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (4, 1, 2);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (5, 1, 3);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (6, 2, 3);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (7, 3, 2);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (8, 1, 4);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (9, 1, 5);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (31, 1, 6);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (32, 1, 7);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (33, 1, 8);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (34, 1, 9);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (35, 1, 10);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (36, 2, 1);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (37, 3, 1);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (38, 4, 1);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (39, 5, 1);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (40, 6, 1);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (41, 7, 1);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (10, 8, 1);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (42, 8, 2);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (43, 9, 1);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (44, 10, 1);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (45, 2, 4);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (46, 4, 2);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (47, 2, 5);
INSERT INTO toyim.toyim_friend (id, user_id, friend_id) VALUES (48, 5, 2);

INSERT INTO toyim.toyim_group (id, groupName, groupAvatarUrl) VALUES (1, 'g1', '../static/img/avatar/Group01.jpg');

INSERT INTO toyim.toyim_group_user (id, group_id, user_id) VALUES (1, 1, 2);
INSERT INTO toyim.toyim_group_user (id, group_id, user_id) VALUES (2, 1, 3);
INSERT INTO toyim.toyim_group_user (id, group_id, user_id) VALUES (3, 1, 4);
INSERT INTO toyim.toyim_group_user (id, group_id, user_id) VALUES (4, 1, 5);
INSERT INTO toyim.toyim_group_user (id, group_id, user_id) VALUES (5, 1, 6);
INSERT INTO toyim.toyim_group_user (id, group_id, user_id) VALUES (6, 1, 7);
INSERT INTO toyim.toyim_group_user (id, group_id, user_id) VALUES (7, 1, 8);
INSERT INTO toyim.toyim_group_user (id, group_id, user_id) VALUES (8, 1, 9);
INSERT INTO toyim.toyim_group_user (id, group_id, user_id) VALUES (9, 1, 10);