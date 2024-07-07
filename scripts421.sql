ALTER TABLE student
ADD CONSTRAINT age_unique CHECK ( age >= 16 ),
ALTER COLUMN age SET DEFAULT 20,
ALTER COLUMN name  SET NOT NULL,
ADD CONSTRAINT name_unique UNIQUE (name);

ALTER TABLE faculty
ADD CONSTRAINT name_add_color_unique UNIQUE (name,color);


