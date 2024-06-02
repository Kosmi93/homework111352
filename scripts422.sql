CREATE TABLE humans(
                       id SERIAL PRIMARY KEY ,
                       surname VARCHAR (30) NOT NULL,
                       age INTEGER CHECK (age >= 0),
                       driving_license BOOLEAN NOT NULL,
                       car_id INTEGER REFERENCES cars (id)
);
CREATE TABLE cars(
                     id SERIAL PRIMARY KEY,
                     make VARCHAR(20) NOT NULL,
                     model VARCHAR(20) NOT NULL,
                     price BIGINT CHECK (price >0)
);