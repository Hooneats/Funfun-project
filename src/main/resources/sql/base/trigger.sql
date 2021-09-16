create or replace trigger product_t
    after
        insert on likes
    for each row
begin
    update product set product_like_count=(select count(*) from likes where product_id=:new.product_id)
    where product_id=:new.product_id;
end;
/

create or replace trigger product_t2
    before
        delete on likes
    for each row
begin
    update product set product_like_count=(select count(*) from likes where product_id=:old.product_id)-1
    where product_id=:old.product_id;
end;
/

create or replace trigger product_t3
    after
        insert on funding
    for each row
begin
    update product set funding_count=funding_count+1
    where product_id=:new.product_id;
end;
/

create or replace trigger product_t4
    before
        delete on funding
    for each row
begin
    update product set funding_count=funding_count-1
    where product_id=:old.product_id;
end;
/