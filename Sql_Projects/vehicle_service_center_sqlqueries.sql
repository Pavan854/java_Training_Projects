create database vehicle_service_center;
use vehicle_service_center;


CREATE TABLE Customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(50),
    phone VARCHAR(15),
    city VARCHAR(30)
);


INSERT INTO Customers(customer_name,phone,city)
VALUES
('Rahul','9876543210','Chennai'),
('Priya','9876543211','Bangalore'),
('Arun','9876543212','Hyderabad'),
('Sneha','9876543213','Coimbatore'),
('Karthik','9876543214','Mumbai');

CREATE TABLE Mechanics (
    mechanic_id INT PRIMARY KEY AUTO_INCREMENT,
    mechanic_name VARCHAR(50),
    specialization VARCHAR(50),
    experience INT
);

INSERT INTO Mechanics(mechanic_name,specialization,experience)
VALUES
('Ramesh','Engine',10),
('Suresh','Electrical',8),
('Mahesh','General Service',6),
('Ganesh','Painting',12);

CREATE TABLE Vehicles (
    vehicle_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    vehicle_number VARCHAR(15),
    vehicle_model VARCHAR(50),
    vehicle_type VARCHAR(30),
    FOREIGN KEY(customer_id)
    REFERENCES Customers(customer_id)
);
INSERT INTO Vehicles(customer_id,vehicle_number,vehicle_model,vehicle_type)
VALUES
(1,'TN10AB1234','Hyundai i20','Car'),
(2,'KA05XY5678','Honda City','Car'),
(3,'TS08PQ4321','Royal Enfield','Bike'),
(4,'TN22KL9090','Maruti Swift','Car'),
(5,'MH12AA1111','TVS Apache','Bike');



CREATE TABLE Service_Records (
    service_id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_id INT,
    mechanic_id INT,
    service_type VARCHAR(50),
    service_date DATE,
    cost DECIMAL(10,2),
    
    FOREIGN KEY(vehicle_id)
    REFERENCES Vehicles(vehicle_id),

    FOREIGN KEY(mechanic_id)
    REFERENCES Mechanics(mechanic_id)
);

INSERT INTO Service_Records(vehicle_id,mechanic_id,service_type,service_date,cost)
VALUES
(1,1,'Engine Repair','2026-07-10',8000),
(1,2,'Electrical Repair','2026-07-20',3000),
(2,3,'General Service','2026-10-8',2500),
(3,1,'Engine Repair','2026-07-15',5000),
(4,3,'General Service','2026-07-18',2200),
(5,2,'Electrical Repair','2026-06-30',1800),
(2,4,'Painting','2026-07-25',7000),
(3,3,'General Service','2026-07-27',2000);

INSERT INTO Service_Records(vehicle_id,mechanic_id,service_type,service_date,cost)
VALUES(4,3,'General Service','2026-10-08',5000);

INSERT INTO Service_Records(vehicle_id,mechanic_id,service_type,service_date,cost)
VALUES(3,4,'painting','2025-10-08',3000);


select*from service_records;

CREATE TABLE Bills (
    bill_id INT PRIMARY KEY AUTO_INCREMENT,
    service_id INT,
    total_amount DECIMAL(10,2),
    payment_status VARCHAR(20),

    FOREIGN KEY(service_id)
    REFERENCES Service_Records(service_id)
);

INSERT INTO Bills(service_id,total_amount,payment_status)
VALUES
(1,8000,'Paid'),
(2,3000,'Paid'),
(3,2500,'Pending'),
(4,5000,'Paid'),
(5,2200,'Paid'),
(6,1800,'Pending'),
(7,7000,'Paid'),
(8,2000,'Pending');
INSERT INTO Bills(service_id,total_amount,payment_status)
VALUES(8,3000,'unpaid');
show tables;

select*from service_records;

-- Display vehicles serviced today

select vehicle_number,vehicle_model,service_type,service_date,cost from vehicles  join service_records on vehicles.vehicle_id=service_records.vehicle_id
 where service_date = curdate();
 
 -- Find the mechanic handling the most services
 
 select mechanic_name,experience,count(service_id) as total_services from mechanics 
 join service_records on mechanics.mechanic_id=service_records.mechanic_id
 group by mechanic_name,experience
 order by count(service_id) desc
 limit 1;
 
-- Calculate customer-wise bill amount.

select 

customer_name,sum(total_amount) as total_bill from customers join vehicles on customers.customer_id= vehicles.customer_id
join service_records on vehicles.vehicle_id=service_records.vehicle_id 
join bills on service_records.service_id=bills.service_id 
group by customer_name;

-- Find vehicles not serviced in the last year
SELECT vehicle_number, vehicle_model
FROM vehicles
WHERE vehicle_id NOT IN (
    SELECT vehicle_id
    FROM service_records
    WHERE service_date >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
);

-- Display service history for a vehicle.

 select vehicle_number,mechanic_name,service_type,service_date,cost from 
 vehicles v join service_records sr on v.vehicle_id=sr.vehicle_id 
 join mechanics ms on sr.mechanic_id=ms.mechanic_id
 where vehicle_number='TN10AB1234'
 order by service_date ;
 
 -- Count services by type.
 
 select service_type,count(*) as total_services from service_records group by service_type;
 
 -- Find customers with more than three visits.
 
 select customer_name,count(service_id) as no_of_visits from 
customers c
join 
 vehicles v on c.customer_id=v.customer_id
 join 
 service_records sr on v.vehicle_id = sr.vehicle_id
 group by customer_name
 having count(service_id)>=2;
 
 -- display pending salary
 
 select c.customer_name,v.vehicle_number,b.total_amount,b.payment_status from customers c 
 join vehicles v on c.customer_id=v.customer_id
 join service_records sr on v.vehicle_id =sr.vehicle_id
 join bills b on sr.service_id=b.service_id 
 where b.payment_status='pending';
 
 -- Find the most common service.
 
 select service_type,count(*) as most_used_service from service_records 
 group by service_type 
 order by most_used_service desc
 limit 1;

-- calculate monthly service revenue

select month(service_date),sum(total_amount) as revenue from service_records sr join bills b
on sr.service_id=b.service_id
group by month(service_date);

-- Find the highest bill.

select *from bills 
order  by total_amount desc
limit 1;
-- Find the mechanic with the highest revenue.
select mechanic_name,sum(total_amount) as revenue from mechanics m join service_records sr on m.mechanic_id=sr.mechanic_id
join bills b on sr.service_id=b.service_id
group by mechanic_name
order by sum(total_amount) desc;

-- Display customers who own more than one vehicle.

select customer_name,count(v.vehicle_id) as no_of_vehicles from customers c join vehicles v on c.customer_id = v.customer_id
group by customer_name
having count(vehicle_id)>=1;

-- average service cost
select avg(cost) from service_records;

-- Find the most experienced mechanic

select mechanic_name,experience from mechanics
order by experience desc
limit 1;

-- select engine repairs

select *from service_records
where service_type='engine repair';

-- find customer from chennai
select*from customers
where city='chennai';

-- Find all services performed by a particular mechanic.
select mechanic_name,service_type,service_date from mechanics m join service_records sr  on m.mechanic_id=sr.mechanic_id
where mechanic_name='suresh';

-- Display unpaid bill details with service information.

select bill_id,customer_name,vehicle_number,service_type,total_amount from bills b join service_records sr
on b.service_id=sr.service_id
join vehicles v on sr.vehicle_id=v.vehicle_id
join customers c on v.customer_id=c.customer_id
where b.payment_status='unpaid';

-- Rank mechanics based on the number of services handled.

select mechanic_name,count(service_id) as total_service,rank() over(order by count(service_id) desc) as ranks
from mechanics m join service_records sr on m.mechanic_id=sr.mechanic_id
group by mechanic_name;










