create database Hotel_booking;
use hotel_booking;

CREATE TABLE Hotels(
    hotel_id INT PRIMARY KEY AUTO_INCREMENT,
    hotel_name VARCHAR(50),
    city VARCHAR(30),
    star_rating INT
);

INSERT INTO Hotels(hotel_name,city,star_rating)
VALUES
('Grand Palace','Chennai',5),
('Royal Inn','Bangalore',4),
('Blue Moon','Hyderabad',3);

CREATE TABLE Rooms(
    room_id INT PRIMARY KEY AUTO_INCREMENT,
    hotel_id INT,
    room_number VARCHAR(10),
    room_type VARCHAR(20),
    price DECIMAL(10,2),
    status VARCHAR(20),

    FOREIGN KEY(hotel_id)
    REFERENCES Hotels(hotel_id)
);

INSERT INTO Rooms(hotel_id,room_number,room_type,price,status)
VALUES
(1,'101','Standard',2500,'Available'),
(1,'102','Deluxe',4000,'Occupied'),
(1,'103','Suite',7000,'Occupied'),
(2,'201','Standard',2200,'Available'),
(2,'202','Deluxe',3800,'Occupied'),
(3,'301','Standard',1800,'Available'),
(3,'302','Suite',6000,'Occupied');

CREATE TABLE Guests(
    guest_id INT PRIMARY KEY AUTO_INCREMENT,
    guest_name VARCHAR(50),
    phone VARCHAR(15),
    city VARCHAR(30)
);

INSERT INTO Guests(guest_name,phone,city)
VALUES
('Rahul','9876543210','Chennai'),
('Priya','9876543211','Bangalore'),
('Arun','9876543212','Hyderabad'),
('Sneha','9876543213','Coimbatore'),
('Karthik','9876543214','Mumbai');


CREATE TABLE Bookings(
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    guest_id INT,
    room_id INT,
    check_in DATE,
    check_out DATE,
    booking_status VARCHAR(20),

    FOREIGN KEY(guest_id)
    REFERENCES Guests(guest_id),

    FOREIGN KEY(room_id)
    REFERENCES Rooms(room_id)
);

INSERT INTO Bookings(guest_id,room_id,check_in,check_out,booking_status)
VALUES
(1,2,'2026-07-25','2026-07-30','Completed'),
(2,3,'2026-07-28','2026-08-02','Active'),
(3,5,'2026-07-29','2026-08-01','Active'),
(4,7,'2026-07-20','2026-07-22','Completed'),
(1,1,'2026-08-05','2026-08-08','Booked'),
(5,4,'2026-07-31','2026-08-03','Cancelled');

INSERT INTO Bookings(guest_id,room_id,check_in,check_out,booking_status)
VALUES (1,1,'2026-10-08','2026-10-16','Booked');


INSERT INTO Bookings(guest_id,room_id,check_in,check_out,booking_status)
VALUES(4,7,'2026-10-06','2026-10-22','Completed'),
(1,1,'2026-10-05','2026-10-15','active'),
(5,4,'2026-10-01','2026-11-01','active');

CREATE TABLE Payments(
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT,
    amount DECIMAL(10,2),
    payment_status VARCHAR(20),

    FOREIGN KEY(booking_id)
    REFERENCES Bookings(booking_id)
);

INSERT INTO Payments(booking_id,amount,payment_status)
VALUES
(1,20000,'Paid'),
(2,35000,'Paid'),
(3,12000,'Pending'),
(4,15000,'Paid'),
(5,7500,'Pending'),
(6,0,'Refunded');

-- Display Available Rooms

select room_id,hotel_name,room_number,room_type,price,status 
from rooms r join hotels h on r.hotel_id=h.hotel_id 
where status='available';

-- Find Guests Staying Today

select guest_name,hotel_name,room_number,check_in,check_out from hotels h join rooms r on h.hotel_id=r.hotel_id
join bookings b on r.room_id=b.room_id 
join guests g on b.guest_id=g.guest_id
where curdate() between b.check_in and b.check_out and b.booking_status='active';

--  Calculate Total Revenue

select sum(amount) as total_revenue from payments where payment_status='paid';

-- display booking between two dates 

select hotel_name,room_number,guest_name,check_in,check_out from hotels h join rooms r on h.hotel_id=r.hotel_id
join bookings b on r.room_id=b.room_id 
join guests g on b.guest_id =g.guest_id 
where check_in between '2026-09-25'
AND '2026-11-10';

-- Find the Most Booked Room Type

select room_type,count(*) as total_bookings from rooms 
group by room_type
order by count(*) desc
limit 1;

-- Calculate Occupancy Rate
SELECT
ROUND((COUNT(CASE WHEN status='Occupied' THEN 1 END)*100.0/COUNT(*)),2)
AS Occupancy_Rate
FROM Rooms;

-- Display Cancelled Bookings

select booking_id,guest_name,hotel_name,room_number,price,booking_status from hotels h join rooms r on h.hotel_id=r.hotel_id
join bookings b on r.room_id =b.room_id
join guests g on b.guest_id=g.guest_id
where b.booking_status='cancelled';

-- Find Customers with Multiple Bookings

select guest_name,count(b.guest_id) as total_visits from guests g join bookings b on g.guest_id=b.guest_id
group by guest_name
having count(b.guest_id)>1
order by count(b.guest_id) desc;

-- Display Average Room Price
select avg(price) as avg_price from rooms;

-- Find Hotels with More Than 100 Rooms
select hotel_name,count(r.hotel_id) as No_Of_Rooms from hotels h join rooms r on h.hotel_id=r.hotel_id
group by hotel_name
having count(r.hotel_id)>100;

-- highest paying guest

select guest_name,sum(amount) as total_spends from guests g join bookings b on g.guest_id=b.guest_id
join payments p on b.booking_id=p.booking_id
group by guest_name
order by sum(amount) desc
limit 1;

-- Hotel-wise revenue
select hotel_name,sum(p.amount) as total_revenue from hotels h join rooms r on h.hotel_id=r.hotel_id
join bookings b on r.room_id=b.room_id
join payments p on b.booking_id=p.booking_id
group by hotel_name
order by sum(p.amount) desc;

-- Most expensive room

select hotel_name,room_number,room_type,price from hotels h join rooms r on h.hotel_id=r.hotel_id
order by r.price desc
limit 1;


-- Guests who have never made a booking

select guest_name from guests g join bookings b on g.guest_id =b.guest_id
where b.booking_id is null;

-- ranking 

select hotel_name,sum(p.amount) as total_revenue,rank() over(order by sum(p.amount) desc) as hotel_ranks
from hotels h join rooms r on h.hotel_id=r.hotel_id
join bookings b on r.room_id=b.room_id
join payments p on b.booking_id=p.booking_id
where payment_status in ('paid','pending')
group by hotel_name;

