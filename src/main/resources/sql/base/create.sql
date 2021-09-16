
create table member (
                        member_id number constraint member_pk primary key,
                        email varchar2(30 char) constraint member_uk unique,
                        nic_name varchar2(20 char),
                        password varchar2(255 char),
                        login_api varchar2(30 char),
                        city varchar2(50 char),
                        street varchar2(50 char),
                        zipcode varchar2(30 char),
                        phone_number varchar2(30 char),
                        role varchar2(20 char),
                        created timestamp,
                        updated timestamp
);

CREATE SEQUENCE MEMBER_SEQ START WITH 1 MINVALUE 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE PROFILE_IMG_SEQ START WITH 1 MINVALUE 1 INCREMENT BY 1 NOCACHE;

create table profile_img(
                            profile_img_id number constraint profile_img_pk primary key,
                            file_src varchar2(255 char),
                            file_name varchar2(255 char),
                            member_id number
);

ALTER TABLE profile_img ADD CONSTRAINT profile_img_fk FOREIGN KEY (member_id) REFERENCES member (member_id);


create table friend_list(
                            friend_list_id number constraint friend_list_pk primary key,
                            member_id number,
                            friend_id number
);

CREATE SEQUENCE FRIEND_LIST_SEQ START WITH 1 MINVALUE 1 INCREMENT BY 1 NOCACHE;
ALTER TABLE friend_list ADD CONSTRAINT friend_list_fk FOREIGN KEY (member_id) REFERENCES member (member_id);
ALTER TABLE friend_list ADD CONSTRAINT friend_list_fk2 FOREIGN KEY (friend_id) REFERENCES member (member_id);

create table event(
                      event_id number constraint event_pk primary key,
                      event_title varchar2(255 char)
);

CREATE SEQUENCE EVENT_SEQ START WITH 1 MINVALUE 1 INCREMENT BY 1 NOCACHE;

create table event_img(
                          event_img_id number constraint event_img_pk primary key,
                          event_id number,
                          file_src varchar2(255 char),
                          file_name varchar2(255 char),
                          img_code varchar2(20 char)
);

CREATE SEQUENCE EVENT_IMG_SEQ START WITH 1 MINVALUE 1 INCREMENT BY 1 NOCACHE;
ALTER TABLE event_img ADD CONSTRAINT event_img_fk FOREIGN KEY (event_id) REFERENCES event (event_id);

create table category(
                         category_id number constraint category_pk primary key,
                         category_name varchar2(50 char)
);

CREATE SEQUENCE CATEGORY_SEQ START WITH 1 MINVALUE 1 INCREMENT BY 1 NOCACHE;

create table product(
                        product_id number constraint product_pk primary key,
                        product_name varchar2(255 char),
                        product_stock number,
                        product_detail varchar2(255 char),
                        product_price number,
                        product_like_count number,
                        funding_count number,
                        created timestamp,
                        updated timestamp,
                        category_id number,
                        product_brand varchar2(50 char),
                        event_id number
);

CREATE SEQUENCE PRODUCT_SEQ START WITH 1 MINVALUE 1 INCREMENT BY 1 NOCACHE;
ALTER TABLE product ADD CONSTRAINT product_fk FOREIGN KEY (event_id) REFERENCES event (event_id);
ALTER TABLE product ADD CONSTRAINT product_fk2 FOREIGN KEY (category_id) REFERENCES category (category_id);

create table product_img(
                            product_img_id number constraint product_img_pk primary key,
                            file_src varchar2(255 char),
                            file_name varchar2(255 char),
                            product_id number,
                            img_code varchar2(20 char)
);

CREATE SEQUENCE PRODUCT_IMG_SEQ START WITH 1 MINVALUE 1 INCREMENT BY 1 NOCACHE;
ALTER TABLE product_img ADD CONSTRAINT product_img_fk FOREIGN KEY (product_id) REFERENCES product (product_id);

create table likes(
                      likes_id number constraint likes_pk primary key,
                      product_id number,
                      member_id number
);

CREATE SEQUENCE LIKES_SEQ START WITH 1 MINVALUE 1 INCREMENT BY 1 NOCACHE;
ALTER TABLE likes ADD CONSTRAINT likes_fk FOREIGN KEY (product_id) REFERENCES product (product_id);
ALTER TABLE likes ADD CONSTRAINT likes_fk2 FOREIGN KEY (member_id) REFERENCES member (member_id);

create table funding(
                        funding_id number constraint funding_pk primary key,
                        product_id number,
                        member_id number,
                        funding_title varchar2(255 char),
                        funding_expired_time timestamp,
                        funding_create_time timestamp,
                        funding_people_count number,
                        funding_collected_money number,
                        funding_target_money number,
                        funding_type varchar2(20 char)
);

CREATE SEQUENCE FUNDING_SEQ START WITH 1 MINVALUE 1 INCREMENT BY 1 NOCACHE;
ALTER TABLE funding ADD CONSTRAINT funding_fk FOREIGN KEY (product_id) REFERENCES product (product_id);
ALTER TABLE funding ADD CONSTRAINT funding_fk2 FOREIGN KEY (member_id) REFERENCES member (member_id);

create table orders(
                       orders_id number constraint orders_pk primary key,
                       member_id number,
                       product_id number,
                       funding_id number,
                       total_payment number,
                       order_date timestamp,
                       memo varchar2(255 char),
                       i_port_id varchar2(50 char),
                       pg_id varchar2(50 char),
                       status varchar2(30 char)
);

CREATE SEQUENCE ORDERS_SEQ START WITH 1 MINVALUE 1 INCREMENT BY 1 NOCACHE;
ALTER TABLE orders ADD CONSTRAINT orders_fk FOREIGN KEY (member_id) REFERENCES member (member_id);
ALTER TABLE orders ADD CONSTRAINT orders_fk2 FOREIGN KEY (product_id) REFERENCES product (product_id);
ALTER TABLE orders ADD CONSTRAINT orders_fk3 FOREIGN KEY (funding_id) REFERENCES funding (funding_id);

create table product_payment(
                                product_payment_id number constraint product_payment_pk primary key,
                                funding_id number,
                                product_id number,
                                status varchar2(20 char),
                                excess_cost number,
                                w_product varchar2(10 char),
                                account varchar2(30 char),
                                phone_number varchar2(30 char),
                                city varchar2(50 char),
                                street varchar2(50 char),
                                zipcode varchar2(30 char)
);

CREATE SEQUENCE PRODUCT_PAYMENT_SEQ START WITH 1 MINVALUE 1 INCREMENT BY 1 NOCACHE;
ALTER TABLE product_payment ADD CONSTRAINT product_payment_fk FOREIGN KEY (product_id) REFERENCES product (product_id);
ALTER TABLE product_payment ADD CONSTRAINT product_payment_fk2 FOREIGN KEY (funding_id) REFERENCES funding (funding_id);

create table delivery(
                         delivery_id number constraint delivery_pk primary key,
                         delivery_number number,
                         delivery_company varchar2(20 char),
                         city varchar2(50 char),
                         street varchar2(50 char),
                         zipcode varchar2(30 char),
                         phone_number varchar2(30 char),
                         status varchar2(20 char),
                         product_payment_id number,
                         member_id number
);

CREATE SEQUENCE DELIVERY_SEQ START WITH 1 MINVALUE 1 INCREMENT BY 1 NOCACHE;
ALTER TABLE delivery ADD CONSTRAINT delivery_fk FOREIGN KEY (product_payment_id) REFERENCES product_payment (product_payment_id);
ALTER TABLE delivery ADD CONSTRAINT delivery_fk2 FOREIGN KEY (member_id) REFERENCES  member (member_id);
