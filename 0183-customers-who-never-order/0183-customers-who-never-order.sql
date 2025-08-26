select  c1.name as Customers from Customers c1
left join Orders o1 on o1.customerId = c1.id
where o1.customerId is null;