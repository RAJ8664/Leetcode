select name as Employee 
from Employee e1
where (
    select salary from Employee e2 
    where e2.id = e1.managerId
) < e1.salary;

