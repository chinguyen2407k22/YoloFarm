USE yolo_farm;
GO

CREATE TABLE farm (
    id INT IDENTITY(1,1) PRIMARY KEY,
    farm_size FLOAT,
    farm_location NVARCHAR(255),
    farm_name NVARCHAR(100)
);

CREATE TABLE user_account (
    username NVARCHAR(50) PRIMARY KEY,
    firstname NVARCHAR(50),
    lastname NVARCHAR(50),
    hash_password NVARCHAR(255),
    farm_id INT,
	FOREIGN KEY (farm_id) REFERENCES farm (id)
);

CREATE TABLE reminder (
    id INT IDENTITY(1,1),
    username NVARCHAR(50),
    title NVARCHAR(255),
    reminder_description NVARCHAR(MAX),
    reminder_time DATETIME,
    is_done BIT,
    FOREIGN KEY (username) REFERENCES user_account (username),
	PRIMARY KEY (id,username) 
);


CREATE TABLE activity_log (
    id INT IDENTITY(1,1) PRIMARY KEY,
    category NVARCHAR(50),
    title NVARCHAR(255),
    mode NVARCHAR(50),
    log_time DATETIME,
    farm_id INT,
    FOREIGN KEY (farm_id) REFERENCES farm(id) 
);

CREATE TABLE record (
    id INT IDENTITY(1,1) PRIMARY KEY,
    record_time DATETIME,
    farm_id INT,
    FOREIGN KEY (farm_id) REFERENCES farm(id) 
);

CREATE TABLE temperature_record (
	id INT,
    record_value FLOAT,
	FOREIGN KEY (id) REFERENCES record(id) 
);

CREATE TABLE light_record (
    record_value FLOAT,
    id INT,
    FOREIGN KEY (id) REFERENCES record(id)
);

CREATE TABLE amount_of_water_record (
    record_value FLOAT,
    id INT,
    FOREIGN KEY (id) REFERENCES record(id)
);


CREATE TABLE humidity_record (
    record_value FLOAT,
    id INT,
    FOREIGN KEY (id) REFERENCES record(id) 
);

CREATE TABLE moisture_record (
    record_value FLOAT,
    id INT,
    FOREIGN KEY (id) REFERENCES record(id)
);
