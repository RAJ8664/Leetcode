select email as Email from Person
group by Email
having count(*) > 1;