drop table if exists chr_hotel;
create table chr_hotel
(
    hotel_id       bigint(20) primary key AUTO_INCREMENT,
    province       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  not null,
    city           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  not null,
    district       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  not null,
    detail_address varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci not null,
    hotel_name     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  not null,
    email          varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  not null,
    description    varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci not null,
    longitude      decimal(10, 6)                                                 not null,
    latitude       decimal(10, 6)                                                 not null,
    telephone      varchar(100),
    swimming_pool  boolean                                                        not null,
    gym            boolean                                                        not null,
    dining_room    boolean                                                        not null,
    bar            boolean                                                        not null,
    parking        boolean                                                        not null,
    spa            boolean                                                        not null,
    chess_room     boolean                                                        not null,
    star_level     bigint(20)                                                     not null,
    floors         integer                                                        not null
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

drop table if exists chr_room_type;
create table chr_room_type
(
    type_id     bigint(20) primary key AUTO_INCREMENT,
    hotel_id    bigint(20)                                                     not null,
    price       decimal(18, 2)                                                 not null,
    upper_limit integer                                                        not null,
    breakfast   boolean                                                        not null,
    windows     boolean                                                        not null,
    television  boolean                                                        not null,
    bathtub     boolean                                                        not null,
    thermos     boolean                                                        not null,
    typeName    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  not null,
    description varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci not null,
    area        bigint(20)                                                     not null
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

drop table if exists chr_room;
create table chr_room
(
    room_id       bigint(20) primary key,
    hotel_id      bigint(20) not null,
    room_number   bigint(20) not null,
    type_id       bigint(20) not null,
    layout_id     bigint(20) not null,
    floor_plan_id bigint(20) not null
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

drop table if exists chr_hotel_picture;
create table chr_hotel_picture
(
    hotel_id     bigint(20)   not null,
    picture_path varchar(200) not null,
    cover        boolean
);

drop table if exists chr_room_type_picture;
create table chr_room_type_picture
(
    type_id      bigint(20)   not null,
    picture_path varchar(200) not null,
    cover        boolean
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

drop table if exists chr_layout;
create table chr_layout
(
    layout_id        bigint(20) primary key AUTO_INCREMENT,
    hotel_id         bigint(20) not null,
    floor            bigint(20) not null,
    plane_graph_path varchar(200)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

drop table if exists chr_admin;
create table chr_admin
(
    hotel_id bigint       not null,
    password varchar(100) not null
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;