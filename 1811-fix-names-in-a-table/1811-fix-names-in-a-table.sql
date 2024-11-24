select user_id, concat(upper(substring(name, 1, 1)) , lower(substring(name, 2))) as name from Users
        order by user_id;