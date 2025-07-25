-- Week 7
CREATE SCHEMA IF NOT EXISTS projects;

CREATE USER IF NOT EXISTS 'projects'@'localhost' IDENTIFIED BY 'Andr0!ds';

GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE ON projects.* TO 'projects'@'localhost';

-- Week 8
USE projects;

DROP TABLE IF EXISTS project_category;
DROP TABLE IF EXISTS step;
DROP TABLE IF EXISTS material;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS project;

CREATE TABLE project (
    project_id INT NOT NULL AUTO_INCREMENT,
    project_name VARCHAR(128) NOT NULL,
    estimated_hours DECIMAL(7,2),
    actual_hours DECIMAL(7,2),
    difficulty INT,
    notes TEXT,
    PRIMARY KEY (project_id)
);

CREATE TABLE material (
    material_id INT NOT NULL AUTO_INCREMENT,
    project_id INT NOT NULL,
    material_name VARCHAR(128) NOT NULL,
    num_required INT,
    cost DECIMAL(7,2),
    PRIMARY KEY (material_id),
    FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE
);

CREATE TABLE step (
    step_id INT NOT NULL AUTO_INCREMENT,
    project_id INT NOT NULL,
    step_text TEXT NOT NULL,
    step_order INT NOT NULL,
    PRIMARY KEY (step_id),
    FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE
);

CREATE TABLE category (
    category_id INT NOT NULL AUTO_INCREMENT,
    category_name VARCHAR(128) NOT NULL,
    PRIMARY KEY (category_id)
);

CREATE TABLE project_category (
    project_id INT NOT NULL,
    category_id INT NOT NULL,
    FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category(category_id) ON DELETE CASCADE,
    UNIQUE KEY (project_id, category_id)
);

-- Week 10
INSERT INTO project (project_name, estimated_hours, actual_hours, difficulty, notes) 
VALUES ('Hang a closet door', 4.00, 3.00, 3, 'Use the door hangers from Home Depot');

INSERT INTO material (project_id, material_name, num_required, cost) 
VALUES (1, 'Door in frame', 1, NULL),
       (1, 'Package of door hangers from Home Depot', 1, NULL),
       (1, '2-inch screws', 20, NULL);

INSERT INTO step (project_id, step_text, step_order) 
VALUES (1, 'Align hangers on opening side of door vertically on the wall', 1),
       (1, 'Screw hangers into frame', 2);

INSERT INTO category (category_name) 
VALUES ('Doors and Windows'),
       ('Repairs');

INSERT INTO project_category (project_id, category_id) 
VALUES (1, 1),
       (1, 2);

-- Week 11
INSERT INTO category (category_name) 
VALUES ('Gardening');

INSERT INTO project_category (project_id, category_id) 
VALUES (1, 3);
