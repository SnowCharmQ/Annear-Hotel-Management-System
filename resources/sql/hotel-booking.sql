drop table if exists chb_booking;
CREATE TABLE chb_booking
(                                    -- this table will be updated every day, if end_time passed, the item will be deleted
    user_id    bigint(20)  not null,
    order_id   varchar(30) not null,
    hotel_id bigint(20) not null,
    type_id bigint(20) not null,
    room_id    bigint(20)  not null, -- room_id
    start_date date        not null,
    end_date   date        not null,
    order_state boolean not null
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;
