BEGIN
TRANSACTION;

INSERT INTO parkee_app.check_in_ticket (id, plate_number, parking_spot_id, is_active,
                                        created_by, created_date, updated_by, updated_date, mark_for_delete)
VALUES ('0822b365-c1f5-4b0e-b152-ebb2c817b69b', 'B1236MAX', 'de1dc001-19b7-4bc7-9cc2-d03f1401fc30', 1, 'ADMIN',
        '2025-07-06 19:53:25', 'ADMIN', '2025-07-06 19:53:25', 0),
       ('17837e29-cd9f-4553-abca-9a3b4e1a68c5', 'B 9999 B', '8e3569bb-b751-40a2-9252-2092a4e8a5e6', 1, 'ADMIN',
        '2025-07-06 21:08:46', 'ADMIN', '2025-07-06 21:08:46', 0),
       ('2437b14e-8d01-47b8-b833-54ec3a464089', 'B1235MRA', 'c907efaa-3830-46ef-96ab-718d5fe85386', 1, 'ADMIN',
        '2025-07-06 11:45:38', 'ADMIN', '2025-07-06 11:45:38', 0),
       ('3dc99f57-cad6-4c2e-bb34-796dff4aba76', 'B1234XYZ', 'c907efaa-3830-46ef-96ab-718d5fe85386', 0, 'ADMIN',
        '2025-07-06 10:24:20', 'ADMIN', '2025-07-06 10:24:20', 0),
       ('417769ca-98ce-4353-bc3b-341d031628de', 'B1236MAA', 'ab8c3c3c-2fb6-48dd-abf2-0c9316d5151a', 0, 'ADMIN',
        '2025-07-06 14:08:53', 'ADMIN', '2025-07-06 14:08:53', 0),
       ('47f11150-3aa8-434e-873f-3374d4050caa', 'B1236MAZ', '33d9da8f-a7d0-4c27-a1b5-6c0cfd7997f0', 0, 'ADMIN',
        '2025-07-06 18:51:16', 'ADMIN', '2025-07-06 18:51:16', 0),
       ('48cf0822-a8c7-43d3-a006-a6abfce7d71c', 'B1236MRA', 'ff226cb5-82e9-42a7-9cec-3e92e9ab8ff0', 0, 'ADMIN',
        '2025-07-06 12:54:10', 'ADMIN', '2025-07-06 12:54:10', 0),
       ('569185c5-9c71-4986-8f5b-0263725a8d42', 'B 9999 A', 'd793b83f-4c27-4851-a691-fe5e086c8a5d', 0, 'ADMIN',
        '2025-07-06 20:07:54', 'ADMIN', '2025-07-06 20:07:54', 0),
       ('7dd556b0-3b6a-4814-9d8c-07deabc060fb', 'B1234MRA', 'c907efaa-3830-46ef-96ab-718d5fe85386', 0, 'ADMIN',
        '2025-07-06 11:19:03', 'ADMIN', '2025-07-06 11:19:03', 0),
       ('8b88b993-85d2-4ea9-aed0-c92b7b47e478', 'B1236MAY', 'd6c95e5c-8cb9-4e84-b401-856c0693151b', 1, 'ADMIN',
        '2025-07-06 18:45:31', 'ADMIN', '2025-07-06 18:45:31', 0),
       ('c5b1ab9d-13cc-42f4-980d-3fd36485adf5', 'B 8888 A', '851c1000-795b-4f80-806d-821d06c0ce2f', 1, 'ADMIN',
        '2025-07-06 20:09:26', 'ADMIN', '2025-07-06 20:09:26', 0);


INSERT INTO parkee_app.check_out_ticket (id, price, discount, final_price, check_in_ticket_id, payment_method_id,
                                         created_by, created_date, updated_by, updated_date, mark_for_delete,
                                         voucher_id)
VALUES ('01f04191-cb37-45c4-b20b-74e89044eaad', 0, 0, 0, '569185c5-9c71-4986-8f5b-0263725a8d42', 'FLAZZ', 'ADMIN',
        '2025-07-06 20:39:41', 'ADMIN', '2025-07-06 20:39:41', 0, NULL),
       ('0419c311-caa6-4bc3-9c94-8b657e539f7d', 0, 0, 0, '3dc99f57-cad6-4c2e-bb34-796dff4aba76', 'FLAZZ', 'ADMIN',
        '2025-07-06 14:30:00', 'ADMIN', '2025-07-06 14:30:00', 0, NULL),
       ('6a60a77f-8899-4443-ada4-394571ed550e', 14000, 2000, 12000, '7dd556b0-3b6a-4814-9d8c-07deabc060fb', 'FLAZZ',
        'ADMIN', '2025-07-06 14:30:00', 'ADMIN', '2025-07-06 14:30:00', 0, NULL),
       ('7c2ad6af-a167-49e5-8fab-8e7694617d98', 14000, 2000, 12000, '417769ca-98ce-4353-bc3b-341d031628de', 'FLAZZ',
        'ADMIN', '2025-07-06 14:30:00', 'ADMIN', '2025-07-06 14:30:00', 0, 'V_1000'),
       ('c815768a-e2f9-42b5-9837-dfb5c5b0ed6d', 14000, 2000, 12000, '47f11150-3aa8-434e-873f-3374d4050caa', 'FLAZZ',
        'ADMIN', '2025-07-06 23:30:00', 'ADMIN', '2025-07-06 23:30:00', 0, 'V_1000'),
       ('dbb08976-1dd5-4868-a323-523756c6f660', 14000, 2000, 12000, '48cf0822-a8c7-43d3-a006-a6abfce7d71c', 'FLAZZ',
        'ADMIN', '2025-07-06 14:30:00', 'ADMIN', '2025-07-06 14:30:00', 0, 'V_1000');


INSERT INTO parkee_app.parking_lot (id, name, vehicle_capacity, created_by, created_date, updated_by, updated_date,
                                    mark_for_delete)
VALUES ('8d24318a-17a4-448a-9e2d-f351f1244a33', 'Parking Basement', 200, 'ADMIN', '2025-07-05 12:00:00', 'ADMIN',
        '2025-07-05 12:00:00', 0);


INSERT INTO parkee_app.parking_rate (id, rate_per_hour, created_by, created_date, updated_by, updated_date,
                                     mark_for_delete)
VALUES ('BASIC', 3000, 'ADMIN', '2025-07-05 12:00:00', 'ADMIN', '2025-07-05 12:00:00', 0);


INSERT INTO parkee_app.parking_spot (id, vehicle_type_id, parking_lot_id, created_by, created_date, updated_by,
                                     updated_date, mark_for_delete, parking)
VALUES ('292c6ea1-3be7-4f3b-83dd-5d74a4ef00e4', 'MOTORCYCLE', '8d24318a-17a4-448a-9e2d-f351f1244a33', 'ADMIN',
        '2025-07-05 12:00:00', 'ADMIN', '2025-07-05 12:00:00', 0, 0),
       ('33d9da8f-a7d0-4c27-a1b5-6c0cfd7997f0', 'CAR', '8d24318a-17a4-448a-9e2d-f351f1244a33', 'ADMIN',
        '2025-07-06 18:51:16', 'ADMIN', '2025-07-06 18:51:16', 0, 0),
       ('378b3d9d-0a7f-4755-b64c-6cfc286fe2c5', 'MOTORCYCLE', '8d24318a-17a4-448a-9e2d-f351f1244a33', 'ADMIN',
        '2025-07-05 12:00:00', 'ADMIN', '2025-07-05 12:00:00', 0, 0),
       ('851c1000-795b-4f80-806d-821d06c0ce2f', 'TRUCK', '8d24318a-17a4-448a-9e2d-f351f1244a33', 'ADMIN',
        '2025-07-06 20:09:26', 'ADMIN', '2025-07-06 20:09:26', 0, 1),
       ('8e3569bb-b751-40a2-9252-2092a4e8a5e6', 'TRUCK', '8d24318a-17a4-448a-9e2d-f351f1244a33', 'ADMIN',
        '2025-07-06 21:08:46', 'ADMIN', '2025-07-06 21:08:46', 0, 1),
       ('ab8c3c3c-2fb6-48dd-abf2-0c9316d5151a', 'CAR', '8d24318a-17a4-448a-9e2d-f351f1244a33', 'ADMIN',
        '2025-07-06 14:08:53', 'ADMIN', '2025-07-06 14:08:53', 0, 0),
       ('c907efaa-3830-46ef-96ab-718d5fe85386', 'CAR', '8d24318a-17a4-448a-9e2d-f351f1244a33', 'ADMIN',
        '2025-07-05 12:00:00', 'ADMIN', '2025-07-05 12:00:00', 0, 0),
       ('c907efaa-3830-46ef-99ab-518d5fe85389', 'CAR', '8d24318a-17a4-448a-9e2d-f351f1244a33', 'ADMIN',
        '2025-07-05 12:00:00', 'ADMIN', '2025-07-05 12:00:00', 0, 0),
       ('d6c95e5c-8cb9-4e84-b401-856c0693151b', 'CAR', '8d24318a-17a4-448a-9e2d-f351f1244a33', 'ADMIN',
        '2025-07-06 18:45:31', 'ADMIN', '2025-07-06 18:45:31', 0, 1),
       ('d793b83f-4c27-4851-a691-fe5e086c8a5d', 'TRUCK', '8d24318a-17a4-448a-9e2d-f351f1244a33', 'ADMIN',
        '2025-07-06 20:07:54', 'ADMIN', '2025-07-06 20:07:54', 0, 0),
       ('de1dc001-19b7-4bc7-9cc2-d03f1401fc30', 'CAR', '8d24318a-17a4-448a-9e2d-f351f1244a33', 'ADMIN',
        '2025-07-06 19:53:25', 'ADMIN', '2025-07-06 19:53:25', 0, 1),
       ('ff226cb5-82e9-42a7-9cec-3e92e9ab8ff0', 'CAR', '8d24318a-17a4-448a-9e2d-f351f1244a33', 'ADMIN',
        '2025-07-06 12:54:10', 'ADMIN', '2025-07-06 12:54:10', 0, 0);


INSERT INTO parkee_app.payment_method (id, name, created_by, created_date, updated_by, updated_date, mark_for_delete)
VALUES ('EMONEY', 'eMoney', 'ADMIN', '2025-07-05 12:00:00', 'ADMIN', '2025-07-05 12:00:00', 0),
       ('FLAZZ', 'Flazz', 'ADMIN', '2025-07-05 12:00:00', 'ADMIN', '2025-07-05 12:00:00', 0),
       ('GOPAY', 'GoPay', 'ADMIN', '2025-07-05 12:00:00', 'ADMIN', '2025-07-05 12:00:00', 0),
       ('LINKAJA', 'LinkAja', 'ADMIN', '2025-07-05 12:00:00', 'ADMIN', '2025-07-05 12:00:00', 0),
       ('OVO', 'OVO', 'ADMIN', '2025-07-05 12:00:00', 'ADMIN', '2025-07-05 12:00:00', 0),
       ('QRIS', 'QRIS', 'ADMIN', '2025-07-05 12:00:00', 'ADMIN', '2025-07-05 12:00:00', 0),
       ('TAPCASH', 'Tapcash', 'ADMIN', '2025-07-05 12:00:00', 'ADMIN', '2025-07-05 12:00:00', 0);


INSERT INTO parkee_app.vehicle_type (id, name, created_by, created_date, updated_by, updated_date, mark_for_delete)
VALUES ('CAR', 'Car', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', 0),
       ('MOTORCYCLE', 'Motorcycle', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', 0),
       ('TRUCK', 'Truck', 'ADMIN', '2023-06-16 12:00:00', 'ADMIN', '2023-06-16 12:00:00', 0);


INSERT INTO parkee_app.voucher (id, discount, quantity, expiration_date, created_by, created_date, updated_by,
                                updated_date, mark_for_delete)
VALUES ('V_1000', 1000, 97, '2025-12-31 23:59:59', 'ADMIN', '2025-07-04 12:00:00', 'ADMIN', '2025-07-04 12:00:00', 0),
       ('V_2000', 2000, 50, '2025-12-31 23:59:59', 'ADMIN', '2025-07-04 12:00:00', 'ADMIN', '2025-07-04 12:00:00', 0),
       ('V_3000', 3000, 200, '2025-12-31 23:59:59', 'ADMIN', '2025-07-04 12:00:00', 'ADMIN', '2025-07-04 12:00:00', 0),
       ('V_4000', 4000, 75, '2025-12-31 23:59:59', 'ADMIN', '2025-07-04 12:00:00', 'ADMIN', '2025-07-04 12:00:00', 0),
       ('V_5000', 5000, 150, '2025-12-31 23:59:59', 'ADMIN', '2025-07-04 12:00:00', 'ADMIN', '2025-07-04 12:00:00', 0),
       ('V_EXPIRED', 6000, 150, '2000-01-01 23:59:59', 'ADMIN', '2023-07-04 12:00:00', 'ADMIN', '2023-06-20 12:00:00',
        0);

COMMIT;
