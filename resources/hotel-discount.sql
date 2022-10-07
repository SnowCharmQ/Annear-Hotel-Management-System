DROP TABLE IF EXISTS chd_vip_price;
CREATE TABLE chd_vip_price
(
    vip_level int            not null,
    type_id   int            not null,
    price     decimal(18, 2) not null
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci
    ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS chd_coupon;
CREATE TABLE chd_coupon
(
    coupon_id         int primary key AUTO_INCREMENT,
    type_id           int,
    reduction         decimal(18, 2)                                                not null,
    coupon_name       varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    enable_start_time date                                                          null default null,
    enable_end_time   date                                                          null default null,
    use_start_time    date                                                          null default null,
    use_end_time      date                                                          null default null
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci
    ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS chd_promotion;
CREATE TABLE chd_promotion
(
    promotion_id      int primary key AUTO_INCREMENT,
    type_id           int,
    reduction         decimal(18, 2)                                                not null,
    promotion_name    varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    enable_start_time date                                                          null default null,
    enable_end_time   date                                                          null default null,
    use_start_time    date                                                          null default null,
    use_end_time      date                                                          null default null
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci
    ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS chd_seckill;
CREATE TABLE chd_seckill
(
    seckill_id        int primary key AUTO_INCREMENT,
    type_id           int,
    reduction         decimal(18, 2)                                                not null,
    seckill_name      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    enable_start_time date                                                          null default null,
    enable_end_time   date                                                          null default null,
    use_start_time    date                                                          null default null,
    use_end_time      date                                                          null default null
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci
    ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS chd_own_coupon;
CREATE TABLE chd_own_coupon
(
    user_id    bigint(20) not null,
    coupon_id  int        not null,
    use_status boolean,
    order_id   varchar(30) default null
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci
    ROW_FORMAT = DYNAMIC;
