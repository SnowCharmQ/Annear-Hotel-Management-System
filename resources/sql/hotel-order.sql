drop table if exists cho_order;
CREATE TABLE cho_order
(
    order_id       varchar(30) primary key,
    user_id        bigint(20) not null,
    room_id        bigint(20) not null,     -- room
    order_status   integer            not null, -- 0 booking, 1 isPayed, 2 isCheckIn, 3 isLeaved, 4 isCancelled
    start_date     date           not null,
    end_date       date           not null,
    origin_money   decimal(18, 2) not null,
    after_discount decimal(18, 2) not null,
    additional     varchar(1000),
    score integer

) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

drop table if exists cho_order_info;
CREATE TABLE cho_order_info
(
    order_id      varchar(30) not null,
    tenant_name   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    identity_card char(18)    not null,
    telephone     char(11)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

drop table if exists cho_order_operation;
CREATE TABLE cho_order_operation
(
    order_id       varchar(30) not null,
    operation      int         not null, -- 0 booking, 1 payed, 2 checkIn, 3 leave, 4 cancel
    operation_time datetime    not null,
    primary key (order_id, operation)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

drop table if exists cho_order_comments;
CREATE TABLE cho_order_comments
(
    order_id     varchar(30) not null,
    comments     text        not null,
    comment_time datetime    not null,
    picture      varchar(1000),
    video        varchar(1000)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

drop table if exists cho_income_refund;
CREATE TABLE cho_income_refund
(
    order_id varchar(30)    not null,
    income   decimal(18, 2) not null, -- positive means income, negative means outcome
    time     datetime       not null
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;
