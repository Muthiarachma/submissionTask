BEGIN
TRANSACTION;

CREATE TABLE `check_in_ticket`
(
    `id`              varchar(36)  NOT NULL,
    `plate_number`    varchar(255) NOT NULL,
    `parking_spot_id` varchar(36)  NOT NULL,
    `is_active`       tinyint(1) NOT NULL,
    `created_by`      varchar(255) NOT NULL,
    `created_date`    timestamp    NOT NULL,
    `updated_by`      varchar(255) NOT NULL,
    `updated_date`    timestamp    NOT NULL,
    `mark_for_delete` tinyint(1) NOT NULL,
    PRIMARY KEY (`id`),
    KEY               `parking_spot_id` (`parking_spot_id`),
    CONSTRAINT `check_in_ticket_ibfk_1` FOREIGN KEY (`parking_spot_id`) REFERENCES `parking_spot` (`id`)
);

CREATE TABLE `check_out_ticket`
(
    `id`                 varchar(36)    NOT NULL,
    `price`              decimal(10, 0) NOT NULL,
    `discount`           decimal(10, 0) NOT NULL,
    `final_price`        decimal(10, 0) NOT NULL,
    `check_in_ticket_id` varchar(36)    NOT NULL,
    `payment_method_id`  varchar(255)   NOT NULL,
    `created_by`         varchar(255)   NOT NULL,
    `created_date`       timestamp      NOT NULL,
    `updated_by`         varchar(255)   NOT NULL,
    `updated_date`       timestamp      NOT NULL,
    `mark_for_delete`    tinyint(1) NOT NULL,
    `voucher_id`         varchar(10) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                  `voucher_id` (`voucher_id`),
    KEY                  `check_in_ticket_id` (`check_in_ticket_id`),
    CONSTRAINT `check_out_ticket_ibfk_1` FOREIGN KEY (`voucher_id`) REFERENCES `voucher` (`id`),
    CONSTRAINT `check_out_ticket_ibfk_2` FOREIGN KEY (`check_in_ticket_id`) REFERENCES `check_in_ticket` (`id`)
);

CREATE TABLE `parking_lot`
(
    `id`               varchar(36)  NOT NULL,
    `name`             varchar(255) NOT NULL,
    `vehicle_capacity` int          NOT NULL,
    `created_by`       varchar(255) NOT NULL,
    `created_date`     timestamp    NOT NULL,
    `updated_by`       varchar(255) NOT NULL,
    `updated_date`     timestamp    NOT NULL,
    `mark_for_delete`  tinyint(1) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `parking_lot_payment_method`
(
    `id`                varchar(36)  NOT NULL,
    `parking_lot_id`    varchar(36)  NOT NULL,
    `payment_method_id` varchar(36)  NOT NULL,
    `created_by`        varchar(255) NOT NULL,
    `created_date`      timestamp    NOT NULL,
    `updated_by`        varchar(255) NOT NULL,
    `updated_date`      timestamp    NOT NULL,
    `mark_for_delete`   tinyint(1) NOT NULL,
    PRIMARY KEY (`id`),
    KEY                 `parking_lot_id` (`parking_lot_id`),
    KEY                 `payment_method_id` (`payment_method_id`),
    CONSTRAINT `parking_lot_payment_method_ibfk_1` FOREIGN KEY (`parking_lot_id`) REFERENCES `parking_lot` (`id`),
    CONSTRAINT `parking_lot_payment_method_ibfk_2` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_method` (`id`)
);

CREATE TABLE `parking_lot_vehicle_type`
(
    `id`              varchar(36)  NOT NULL,
    `parking_lot_id`  varchar(36)  NOT NULL,
    `vehicle_type_id` varchar(36)  NOT NULL,
    `created_by`      varchar(255) NOT NULL,
    `created_date`    timestamp    NOT NULL,
    `updated_by`      varchar(255) NOT NULL,
    `updated_date`    timestamp    NOT NULL,
    `mark_for_delete` tinyint(1) NOT NULL,
    PRIMARY KEY (`id`),
    KEY               `parking_lot_id` (`parking_lot_id`),
    KEY               `vehicle_type_id` (`vehicle_type_id`),
    CONSTRAINT `parking_lot_vehicle_type_ibfk_1` FOREIGN KEY (`parking_lot_id`) REFERENCES `parking_lot` (`id`),
    CONSTRAINT `parking_lot_vehicle_type_ibfk_2` FOREIGN KEY (`vehicle_type_id`) REFERENCES `vehicle_type` (`id`)
);

CREATE TABLE `parking_rate`
(
    `id`              varchar(36)    NOT NULL,
    `rate_per_hour`   decimal(10, 0) NOT NULL,
    `created_by`      varchar(255)   NOT NULL,
    `created_date`    timestamp      NOT NULL,
    `updated_by`      varchar(255)   NOT NULL,
    `updated_date`    timestamp      NOT NULL,
    `mark_for_delete` tinyint(1) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `parking_spot`
(
    `id`              varchar(36)  NOT NULL,
    `vehicle_type_id` varchar(20)  NOT NULL,
    `parking_lot_id`  varchar(36)  NOT NULL,
    `created_by`      varchar(255) NOT NULL,
    `created_date`    timestamp    NOT NULL,
    `updated_by`      varchar(255) NOT NULL,
    `updated_date`    timestamp    NOT NULL,
    `mark_for_delete` tinyint(1) NOT NULL,
    `parking`         tinyint(1) NOT NULL,
    PRIMARY KEY (`id`),
    KEY               `vehicle_type_id` (`vehicle_type_id`),
    KEY               `parking_lot_id` (`parking_lot_id`),
    CONSTRAINT `parking_spot_ibfk_1` FOREIGN KEY (`vehicle_type_id`) REFERENCES `vehicle_type` (`id`),
    CONSTRAINT `parking_spot_ibfk_2` FOREIGN KEY (`parking_lot_id`) REFERENCES `parking_lot` (`id`)
);


CREATE TABLE `payment_method`
(
    `id`              varchar(36)  NOT NULL,
    `name`            varchar(36)  NOT NULL,
    `created_by`      varchar(255) NOT NULL,
    `created_date`    timestamp    NOT NULL,
    `updated_by`      varchar(255) NOT NULL,
    `updated_date`    timestamp    NOT NULL,
    `mark_for_delete` tinyint(1) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `vehicle_type`
(
    `id`              varchar(36)  NOT NULL,
    `name`            varchar(255) NOT NULL,
    `created_by`      varchar(255) NOT NULL,
    `created_date`    timestamp    NOT NULL,
    `updated_by`      varchar(255) NOT NULL,
    `updated_date`    timestamp    NOT NULL,
    `mark_for_delete` tinyint(1) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `voucher`
(
    `id`              varchar(10)    NOT NULL,
    `discount`        decimal(10, 0) NOT NULL,
    `quantity`        int            NOT NULL,
    `expiration_date` timestamp      NOT NULL,
    `created_by`      varchar(255)   NOT NULL,
    `created_date`    timestamp      NOT NULL,
    `updated_by`      varchar(255)   NOT NULL,
    `updated_date`    timestamp      NOT NULL,
    `mark_for_delete` tinyint(1) NOT NULL,
    PRIMARY KEY (`id`)
);

COMMIT;
