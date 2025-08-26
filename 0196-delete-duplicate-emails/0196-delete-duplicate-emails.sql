DELETE p FROM Person p
JOIN Person p2 
ON p.Email = p2.Email AND p.Id > p2.Id;