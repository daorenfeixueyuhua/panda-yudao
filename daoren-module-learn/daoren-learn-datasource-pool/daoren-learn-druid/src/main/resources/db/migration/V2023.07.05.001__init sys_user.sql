create table sys_user
(
    id       int          not null auto_increment primary key comment '主键',
    username varchar(32)  not null comment '用户名',
    password varchar(256) not null comment '密码'
) comment '用户信息表';

insert into sys_user (username, password) value ('panda', '123456');