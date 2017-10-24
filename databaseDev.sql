CREATE TABLE Availability
  (
    Availability        BIGINT NOT NULL Primary Key
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
    Room_Hotel_Hotel_ID Numeric (5) NOT NULL ,
    Room_Room_Numeric    Numeric (5) NOT NULL ,
    Availability_Date   DATE NOT NULL
  ) ;


CREATE TABLE Booking
  (
    Booking_Reference Numeric(5) NOT NULL,
    Room_Room_Numeric         Numeric (5) NOT NULL ,
    Room_Hotel_Hotel_ID      Numeric (5) NOT NULL ,
    Booking_Customer_Name    VARCHAR (30) NOT NULL ,
    Booking_Check_In_Date    DATE NOT NULL ,
    Booking_Check_Out_Date   DATE NOT NULL ,
    Booking_Total_Cost       Numeric (10,2) NOT NULL ,
    Booking_Credit_Card      VARCHAR (20) NOT NULL
  ) ;
ALTER TABLE Booking ADD CONSTRAINT Booking_PK PRIMARY KEY ( Booking_Reference ) ;


CREATE TABLE Hotel
  (
    Hotel_ID      numeric(5) NOT NULL,
    Hotel_Name    VARCHAR (25) UNIQUE NOT NULL ,
    Hotel_Address VARCHAR (50) NOT NULL ,
    Hotel_City    VARCHAR (15) NOT NULL ,
    Hotel_Country VARCHAR (55) NOT NULL
  ) ;
ALTER TABLE Hotel ADD CONSTRAINT Hotel_PK PRIMARY KEY ( Hotel_ID ) ;

CREATE TABLE Room
  (
    Room_Numeric    numeric(5) NOT NULL,
    Hotel_Hotel_ID Numeric (5) NOT NULL ,
    Room_Price     Numeric (7,2) NOT NULL,
    room_size      Numeric (4) NOT NULL,
	room_available  numeric(2) Not null
  );
ALTER TABLE Room ADD CONSTRAINT Room_PK PRIMARY KEY ( Room_Numeric, Hotel_Hotel_ID ) ;

ALTER TABLE Availability ADD CONSTRAINT Availability_Room_FK FOREIGN KEY ( Room_Room_Numeric, Room_Hotel_Hotel_ID ) REFERENCES Room ( Room_Numeric, Hotel_Hotel_ID ) ;
ALTER TABLE Booking ADD CONSTRAINT Booking_Room_FK FOREIGN KEY ( Room_Room_Numeric, Room_Hotel_Hotel_ID ) REFERENCES Room ( Room_Numeric, Hotel_Hotel_ID ) ;
ALTER TABLE Room ADD CONSTRAINT Room_Hotel_FK FOREIGN KEY ( Hotel_Hotel_ID ) REFERENCES Hotel ( Hotel_ID ) ;

insert into Hotel values(1,'HiltonS','488 George Street','Sydney','Australia');
insert into Hotel values(2,'FourSeasons','199 George St','Sydney','Australia');
insert into Hotel values(3,'HiltonM','South Wharf, 2 Convention Centre Pl','Melbourne','Australia');
insert into Hotel values(4,'DoubleTreeM','270 Flinders St','Melbourne','Australia');
insert into Hotel values(5,'HiltonP','14 Mill Street','Perth','Australia');
insert into Hotel values(6,'Crowne Plaza','54 Terrace Rd','Perth','Australia');

insert into room values(1,1,60,50,1);
insert into room values(1,2,70,55,1);
insert into room values(1,3,80,45,1);
insert into room values(1,4,90,60,1);
insert into room values(1,5,100,70,1);
insert into room values(1,6,110,75,1);

insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-01');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-02');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-03');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-04');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-05');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-06');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-07');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-08');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-09');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-10');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-11');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-12');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-13');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-14');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-15');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-16');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-17');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-18');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-19');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-20');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-21');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-22');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-23');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-24');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-25');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-26');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-27');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-28');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-29');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-30');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (1,1,'2017-07-31');

insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-01');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-02');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-03');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-04');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-05');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-06');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-07');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-08');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-09');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-10');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-11');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-12');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-13');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-14');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-15');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-16');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-17');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-18');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-19');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-20');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-21');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-22');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-23');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-24');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-25');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-26');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-27');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-28');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-29');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-30');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (2,1,'2017-07-31');

insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-01');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-02');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-03');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-04');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-05');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-06');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-07');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-08');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-09');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-10');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-11');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-12');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-13');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-14');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-15');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-16');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-17');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-18');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-19');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-20');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-21');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-22');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-23');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-24');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-25');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-26');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-27');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-28');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-29');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-30');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (3,1,'2017-07-31');

insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-01');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-02');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-03');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-04');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-05');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-06');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-07');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-08');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-09');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-10');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-11');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-12');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-13');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-14');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-15');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-16');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-17');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-18');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-19');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-20');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-21');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-22');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-23');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-24');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-25');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-26');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-27');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-28');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-29');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-30');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (4,1,'2017-07-31');

insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-01');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-02');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-03');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-04');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-05');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-06');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-07');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-08');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-09');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-10');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-11');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-12');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-13');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-14');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-15');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-16');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-17');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-18');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-19');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-20');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-21');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-22');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-23');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-24');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-25');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-26');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-27');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-28');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-29');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-30');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (5,1,'2017-07-31');

insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-01');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-02');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-03');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-04');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-05');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-06');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-07');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-08');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-09');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-10');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-11');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-12');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-13');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-14');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-15');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-16');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-17');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-18');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-19');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-20');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-21');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-22');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-23');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-24');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-25');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-26');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-27');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-28');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-29');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-30');
insert into Availability (Room_Hotel_hotel_id,room_room_numeric,availability_date) values (6,1,'2017-07-31');