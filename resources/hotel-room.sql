drop table if exists chr_hotel;
create table chr_hotel
(
    hotel_id      int primary key AUTO_INCREMENT,
    province      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci not null,
    city          varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci not null,
    district      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci not null,
    hotel_name    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci not null,
    longitude     decimal(10, 6)                                                not null,
    latitude      decimal(10, 6)                                                not null,
    telephone     varchar(100),
    swimming_pool boolean                                                       not null,
    gym           boolean                                                       not null,
    dining_room   boolean                                                       not null,
    bar           boolean                                                       not null,
    parking       boolean                                                       not null,
    spa           boolean                                                       not null,
    chess_room    boolean                                                       not null,
    star_level    int                                                           not null
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci
    ROW_FORMAT = DYNAMIC;

drop table if exists chr_room_type;
create table chr_room_type
(
    type_id    int primary key AUTO_INCREMENT,
    hotel_id   int            not null,
    price      decimal(18, 2) not null,
    breakfast  boolean        not null,
    windows    boolean        not null,
    television boolean        not null,
    bathtub    boolean        not null,
    thermos    boolean        not null
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci
    ROW_FORMAT = DYNAMIC;

drop table if exists chr_room;
create table chr_room
(
    room_id     int not null,
    hotel_id    int not null,
    room_number int not null,
    type_id     int not null,
    layout_id   int not null
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci
    ROW_FORMAT = DYNAMIC;

drop table if exists chr_hotel_picture;
create table chr_hotel_picture
(
    hotel_id     int primary key,
    picture_path varchar(200)
);

drop table chr_room_type_picture;
create table chr_room_type_picture
(
    type_id      int primary key,
    picture_path varchar(200)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci
    ROW_FORMAT = DYNAMIC;

drop table if exists chr_layout;
create table chr_layout
(
    layout_id        int primary key AUTO_INCREMENT,
    hotel_id         int not null,
    floor            int not null,
    plane_graph_path varchar(200)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci
    ROW_FORMAT = DYNAMIC;