create database person;
use person;


CREATE TABLE Employee (
    emp_id INT PRIMARY KEY,
    emp_name VARCHAR(50),
    department VARCHAR(30),
    salary DECIMAL(10,2),
    city VARCHAR(30),
    joining_date DATE
);


INSERT INTO Employee VALUES
(101,'John','IT',60000,'Chennai','2022-01-15'),
(102,'David','HR',45000,'Bangalore','2021-03-10'),
(103,'Smith','IT',70000,'Chennai','2020-07-12'),
(104,'Mary','Finance',55000,'Mumbai','2023-01-20'),
(105,'James','HR',48000,'Delhi','2022-05-05'),
(106,'Linda','Finance',65000,'Mumbai','2021-08-18');

-- 1.	Find the total number of employees in each department. 

select department,count(department) as total_employees from employee group by department;

-- 2.	Find the average salary of employees in each department. 

select department,avg(salary) from employee group by department;


-- 3.	Display departments having more than one employee.

select department,count(department) as total_employees from employee group by department having count(department) >1;
 
 
-- 4.	Find the highest salary in each department. 

select department,max(salary) as maximum_salary from employee group by department;


-- 5.	Find the lowest salary in each department. 

select department,min(salary) as minumin_salary from employee group by department;


-- 6.	Find departments whose average salary is greater than 50,000. 

select department,avg(salary) as average_salary from employee group by department having avg(salary)>50000;


-- 7.	Calculate the total salary expenditure for each department. 

select department,sum(salary) as total_salary from employee group by department;


-- 8.	Display all employees sorted by salary in descending order. 

select*from employee order by salary desc;

-- 9.	Display employees sorted first by department and then by salary in descending order. 

select*from employee order by department asc,salary desc;

-- 10.	Find cities that have more than one employee. 

select city,count(city) as total_employees from employee group by city having count(city)>1;

-- 11.	Find the total salary paid in each city.

select city,sum(salary) as total_salary from employee group by city;

-- 12.	Display departments ordered by total salary expenditure from highest to lowest. 

select department,sum(salary) as total_salary from employee  group by department order by sum(salary) desc;

-- 13.	Find the number of employees in each department whose salary is greater than 50,000. 

select department,count(department) as total_employees from employee where salary>50000 group by department;


-- 14.	Find the difference between the highest and lowest salary in each department. 

select department,max(salary) as maximum_salary,min(salary) as minimum_salary,max(salary)-min(salary) as total_difference from employee 
group by department;

-- 15.	Display the top 3 highest-paid employees. 

select*from employee order by salary desc limit 3;


CREATE TABLE Customers (
    customer_id INT PRIMARY KEY,
    customer_name VARCHAR(50),
    city VARCHAR(30)
);

INSERT INTO Customers (customer_id, customer_name, city) VALUES
(1, 'Pavan', 'Hyderabad'),
(2, 'Adithya', 'Chennai'),
(3, 'Nandha', 'Bangalore'),
(4, 'Kishore', 'Hyderabad'),
(5, 'Tharkesh', 'Nellore'),
(6, 'Govardhan', 'Chennai'),
(7, 'Gowthan', 'Bangalore');

CREATE TABLE Orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    amount DECIMAL(10,2),
    order_date DATE,
    FOREIGN KEY(customer_id) REFERENCES Customers(customer_id)
);

INSERT INTO Orders (order_id, customer_id, amount, order_date) VALUES
(101, 1, 3000, '2026-01-05'),
(102, 1, 4500, '2026-01-10'),
(103, 1, 3500, '2026-02-01'),
(104, 1, 2000, '2026-02-15'),
(105, 2, 5000, '2026-01-08'),
(106, 2, 2500, '2026-02-12'),
(107, 3, 1500, '2026-01-11'),
(108, 3, 3000, '2026-02-20'),
(109, 4, 7000, '2026-01-14'),
(110, 4, 4000, '2026-02-18'),
(111, 5, 2500, '2026-01-20'),
(112, 6, 6000, '2026-02-22');

-- 16.	Find the total order amount for each customer. 

select customer_name,sum(amount) as total_amount from customers c join orders o on c.customer_id=o.customer_id group by customer_name;

-- 17.	Find customers who have placed more than 3 orders.

select customer_name,count(o.customer_id) from customers c join orders o on c.customer_id=o.customer_id
 group by customer_name having count(o.customer_id)>3;

-- 18.	Find the average order amount for each customer. 

select customer_name,avg(amount) as average_amount from customers c join orders o on c.customer_id=o.customer_id group by customer_name;


-- 19.	Find the highest order amount placed by each customer. 

select customer_name,max(amount) as highest_amount from customers c join orders o on c.customer_id=o.customer_id group by customer_name;


-- 20.	Display customers sorted by their total purchase amount. 

select customer_name,sum(amount) as total_amount from customers c join orders o on c.customer_id=o.customer_id 
group by customer_name order by sum(amount) desc;

-- 21.	Find customers whose total purchase amount exceeds 10,000. 

select customer_name,sum(amount) as total_amount from 
customers c join orders o on c.customer_id=o.customer_id 
group by customer_name,city having sum(amount)>10000;

-- 22.	Display customer names along with the total number of orders placed.

select customer_name,count(o.customer_id) as total_orders from 
customers c join orders o on c.customer_id=o.customer_id 
group by customer_name;
 
-- 23.	Find the customer who spent the highest amount. 

select customer_name,sum(o.amount) as total_amount from 
customers c join orders o on c.customer_id=o.customer_id 
group by customer_name order by sum(o.amount) desc limit 1;

-- 24.	Find the customer who placed the maximum number of orders. 


select customer_name,count(o.customer_id) as total_orders from 
customers c join orders o on c.customer_id=o.customer_id 
group by customer_name order by count(o.customer_id) desc limit 1;

-- 25.	Find customers whose average order amount is greater than 2,000. 


select customer_name,avg(o.amount) as average_amount from 
customers c join orders o on c.customer_id=o.customer_id 
group by customer_name having avg(o.amount)>2000 ;

select *from customers c where (select avg(amount) from orders o where c.customer_id=o.customer_id)>2000 order by customer_name desc;

-- 26.	Display the top 5 customers based on total purchase amount. 

select customer_name,sum(o.amount) as total_amount from 
customers c join orders o on c.customer_id=o.customer_id 
group by customer_name order by sum(o.amount) desc limit 5;

-- 27.	Find the minimum order amount for each customer. 

select customer_name,min(amount) as total_amount from 
customers c join orders o on c.customer_id=o.customer_id 
group by customer_name;

-- 28.	Find customers who have placed orders worth more than 5,000 in total. 

select*from customers c where (select sum(amount) from orders o where c.customer_id=o.customer_id)>5000;

-- 29.	Display customer-wise total orders and total purchase amount.

select customer_name,city,sum(amount)as total_amount,count(o.customer_id) as total_orders from customers c join orders o on c.customer_id=o.customer_id
group by c.customer_id,c.customer_name,c.city;
 
-- 30.	Find customers who placed more than 2 orders and spent more than 8,000. 

select customer_name,sum(amount)as total_amount,count(o.customer_id) as total_orders from customers c join orders o on c.customer_id=o.customer_id
group by c.customer_id having sum(amount)>8000 and count(o.customer_id)>2;



CREATE TABLE Students (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(50),
    department VARCHAR(30),
    marks INT
);

INSERT INTO Students (student_id, student_name, department, marks) VALUES
(1, 'Pavan', 'CSE', 95),
(2, 'Adithya', 'CSE', 82),
(3, 'Nandha', 'CSE', 76),
(4, 'Kishore', 'ECE', 88),
(5, 'Tharkesh', 'ECE', 72),
(6, 'Govardhan', 'IT', 91),
(7, 'Gowthan', 'IT', 68);

INSERT INTO Students (student_id, student_name, department, marks) VALUES
(8, 'Rahul', 'CSE', 87),
(9, 'praveen', 'CSE', 73),
(10, 'Varun', 'CSE', 91),
(11, 'ganesg', 'Civil', 95),
(12, 'Manoj', 'ECE', 64),
(13, 'mani', 'civil', 82),
(14, 'Ajay', 'IT', 78),
(15, 'karthik', 'IT', 89),
(16, 'Rohit', 'civil', 55),
(17, 'charan', 'Mechanical', 92);


-- 31.	Find the average marks scored by students in each department. 

select department,avg(marks) as average_marks from students group by department;

-- 32.	Find departments whose average marks are above 75. 

select department,avg(marks) as average_marks from students group by department having avg(marks)>75;

-- 33.	Find the highest mark scored in each department.

select department,max(marks) as Highest_marks from students group by department;
 
-- 34.	Find the total number of students in each department. 

select department,count(department) as total_students from students group by department;

-- 35.	Find departments having more than 5 students. 

select department,count(department) as total_students from students group by department having count(department)>5;

-- 36.	Display departments sorted by average marks in descending order. 

select department,avg(marks) as average_marks from students group by department order by avg(marks) desc;

-- 37.	Find the top 3 departments based on average marks. 

select department,avg(marks) as average_marks from students group by department order by avg(marks) desc limit 3;

-- 38.	Find departments whose average marks are between 70 and 90. 

select department,avg(marks) as average_marks from students group by department having avg(marks) between 70 and 90;

-- 39.	Find the total marks scored by students in each department. 

select department,sum(marks) as total_marks from students group by department;

-- 40.	Display departments sorted by the total number of students. 

select department,count(department) as total_student from students group by department order by count(department) desc;

-- 41.	Find the lowest mark scored in each department. 

select department,min(marks) from students group by department;

-- 42.	Find departments where the highest mark is greater than 90. 

select department,max(marks) from students group by department having max(marks)>90;

-- 43.	Find the number of students scoring above 80 in each department. 

select department,count(*) as marks_above_80 from students where marks>80 group by department;

-- 44.	Find departments where more than 3 students scored above 75. 

select department,count(marks) as marks_above_75 from students where marks>75
group by department having count(marks)>3;

-- 45.	Display departments ordered by highest mark in descending order. 

select department,max(marks) from students group by department order by max(marks) desc; 

