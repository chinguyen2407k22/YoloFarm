-- Insert into farm table
INSERT INTO farm (farm_size, farm_location, farm_name)
VALUES 
(50.5, 'Location A', 'Farm Alpha'),
(75.2, 'Location B', 'Farm Beta'),
(100.8, 'Location C', 'Farm Gamma');

-- Tạo biến bảng để lưu ID đã chèn
DECLARE @InsertedIDs TABLE (id INT IDENTITY(1,1), record_id INT);

-- Insert into record table
INSERT INTO record (record_time, farm_id)
OUTPUT INSERTED.id INTO @InsertedIDs(record_id)
VALUES 
('2025-02-28 08:00:00', 1),
('2025-02-28 08:10:00', 2),
('2025-02-28 08:20:00', 3),
('2025-02-28 08:30:00', 1),
('2025-02-28 08:40:00', 2),
('2025-02-28 08:50:00', 3),
('2025-02-28 09:00:00', 1),
('2025-02-28 09:10:00', 2),
('2025-02-28 09:20:00', 3),
('2025-02-28 09:30:00', 1),
('2025-02-28 09:40:00', 2),
('2025-02-28 09:50:00', 3),
('2025-02-28 10:00:00', 1),
('2025-02-28 10:10:00', 2),
('2025-02-28 10:20:00', 3),
('2025-02-28 10:30:00', 1),
('2025-02-28 10:40:00', 2),
('2025-02-28 10:50:00', 3),
('2025-02-28 11:00:00', 1),
('2025-02-28 11:10:00', 2),
('2025-02-28 11:20:00', 3),
('2025-02-28 11:30:00', 1),
('2025-02-28 11:40:00', 2),
('2025-02-28 11:50:00', 3),
('2025-02-28 12:00:00', 1),
('2025-02-28 12:10:00', 2),
('2025-02-28 12:20:00', 3),
('2025-02-28 12:30:00', 1),
('2025-02-28 12:40:00', 2),
('2025-02-28 12:50:00', 3),
('2025-02-28 13:00:00', 1),
('2025-02-28 13:10:00', 2),
('2025-02-28 13:20:00', 3),
('2025-02-28 13:30:00', 1),
('2025-02-28 13:40:00', 2),
('2025-02-28 13:50:00', 3),
('2025-02-28 14:00:00', 1),
('2025-02-28 14:10:00', 2),
('2025-02-28 14:20:00', 3),
('2025-02-28 14:30:00', 1),
('2025-02-28 14:40:00', 2),
('2025-02-28 14:50:00', 3),
('2025-02-28 15:00:00', 1),
('2025-02-28 15:10:00', 2),
('2025-02-28 15:20:00', 3),
('2025-02-28 15:30:00', 1),
('2025-02-28 15:40:00', 2),
('2025-02-28 15:50:00', 3);

-- Chèn dữ liệu vào từng bảng con theo thứ tự
INSERT INTO temperature_record (id, record_value)
SELECT record_id, RAND() * 10 + 20 FROM @InsertedIDs WHERE id % 5 = 1;

INSERT INTO light_record (id, record_value)
SELECT record_id, RAND() * 500 + 100 FROM @InsertedIDs WHERE id % 5 = 2;

INSERT INTO amount_of_water_record (id, record_value)
SELECT record_id, RAND() * 5 + 1 FROM @InsertedIDs WHERE id % 5 = 3;

INSERT INTO humidity_record (id, record_value)
SELECT record_id, RAND() * 40 + 30 FROM @InsertedIDs WHERE id % 5 = 4;

INSERT INTO moisture_record (id, record_value)
SELECT record_id, RAND() * 50 + 10 FROM @InsertedIDs WHERE id % 5 = 0;

select * from moisture_record