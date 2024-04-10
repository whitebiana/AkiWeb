# 数据库初始化

-- 创建库
create database if not exists aki;

-- 切换库
use aki;

-- card table
create table if not exists card
(
    id              bigint unsigned primary key auto_increment comment 'id',
    did             bigint unsigned not null comment 'deck id',
    data            text not null comment '正面',
    ans             text not null comment '答案',
    tags            text comment '标签',
    state           integer not null default 0 comment '0=new, 1=learning, 2=review, 3=relearning',
    difficuty       float not null default 0 comment  '难度',
    stability       float not null default 0 not null default 0 comment  '记忆稳定性',
    reps            integer not null default 0 comment '复习次数',
    lapses          integer not null default 0,
    elapsed_days    float not null default 0 comment '过去的天数',
    scheduled_days  integer unsigned not null default 0 comment '复习间隔时间(天)',
    due             datetime default CURRENT_TIMESTAMP not null comment '过期时间点',
    last_review      datetime default CURRENT_TIMESTAMP not null comment '上一次复习的时间点',
    gmt_create      datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    gmt_modified    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    is_deleted      tinyint unsigned default 0 not null comment '是否删除'
) comment 'card' collate = utf8mb4_unicode_ci;

-- deck table
create table if not exists deck
(
    id              bigint unsigned primary key auto_increment comment 'id',
    name            varchar(255) not null comment '名称',
    gmt_create      datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    gmt_modified    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    is_deleted      tinyint unsigned default 0 not null comment '是否删除'
) comment 'deck' collate = utf8mb4_unicode_ci;
