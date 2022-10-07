DROP TABLE IF EXISTS chm_user_info;
CREATE TABLE chm_user_info
(
    user_id        bigint(20)                                                    not null AUTO_INCREMENT,
    username       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL,
    password       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL,
    telephone      char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     NULL     DEFAULT NULL,
    email          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL,
    avatar         varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL,
    gender         tinyint(4)                                                    NULL     DEFAULT NULL,
    birthday       date                                                          null     default null,
    province       varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL,
    city           varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL,
    detail_address varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL,
    job            varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL,
    is_blocked     boolean                                                                default false,
    social_name    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL,
    access_token   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL,
    growth         bigint(20)                                                             default 0,
    create_time    date                                                          null     default null,
    balance        decimal(18, 2)                                                not null,
    vip_level      int                                                           not null default 0,
    identity_card  char(18)                                                      null     default null,
    consume_amount decimal(18, 2)                                                         default 0,
    coupon_amount  decimal(18, 2)                                                         default 0,
    order_count    bigint(20)                                                             default 0,
    comment_count  bigint(20)                                                             default 0,
    login_count    bigint(20)                                                             default 0,
    primary key (user_id) using BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS chm_vip_level;
CREATE TABLE chm_vip_level
(
    vip_level     int primary key,
    vip_name      varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    growth_needed bigint(20)                                                   not null
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS chm_collect_hotel;
CREATE TABLE chm_collect_hotel
(
    user_id  bigint(20) not null,
    hotel_id int        not null
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;