-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2024-03-10 01:02:04.236

-- tables
-- Table: Category
CREATE TABLE Category (
    category_id serial  NOT NULL,
    category_description varchar(500)  NOT NULL,
    category_name varchar(100)  NOT NULL,
    status boolean  NOT NULL,
    CONSTRAINT Category_pk PRIMARY KEY (category_id)
);

-- Table: Content
CREATE TABLE Content (
    content_id serial  NOT NULL,
    content_title varchar(100)  NOT NULL,
    status boolean  NOT NULL,
    Sections_section_id serial  NOT NULL,
    CONSTRAINT Content_pk PRIMARY KEY (content_id)
);

-- Table: course_image
CREATE TABLE course_image (
    course_image_id serial  NOT NULL,
    image varchar(200),
    Course_course_id serial  NOT NULL,
    CONSTRAINT course_image_pk PRIMARY KEY (course_image_id)
);

-- Table: Course
CREATE TABLE Course (
    course_id serial  NOT NULL,
    course_description text  NOT NULL,
    course_standard_price decimal(10,5)  NOT NULL,
    course_name varchar(100)  NOT NULL,
    course_requirements text  NOT NULL,
    status varchar(50)  NOT NULL,
    Category_category_id serial  NOT NULL,
    language_language_id serial  NOT NULL,
    kc_user_kc_uuid varchar(50)  NOT NULL,
    CONSTRAINT Course_pk PRIMARY KEY (course_id)
);

-- Table: Sections
CREATE TABLE Sections (
    section_id serial  NOT NULL,
    section_name varchar(100)  NOT NULL,
    section_description varchar(500)  NOT NULL,
    section_date date  NOT NULL,
    status boolean  NOT NULL,
    Course_course_id serial  NOT NULL,
    CONSTRAINT Sections_pk PRIMARY KEY (section_id)
);

-- Table: attachment
CREATE TABLE attachment (
    attachment_id serial  NOT NULL,
    attachment_name varchar(100)  NOT NULL,
    attachment bytea  NOT NULL,
    status boolean  NOT NULL,
    Content_content_id serial  NOT NULL,
    CONSTRAINT attachment_pk PRIMARY KEY (attachment_id)
);

-- Table: kc_group
CREATE TABLE kc_group (
    kc_group_id serial  NOT NULL,
    kc_group_name varchar(50)  NOT NULL,
    status boolean  NOT NULL,
    tx_date timestamp  NOT NULL,
    tx_user varchar(50)  NOT NULL,
    tx_host varchar(50)  NOT NULL,
    CONSTRAINT kc_group_pk PRIMARY KEY (kc_group_id)
);

-- Table: kc_user
CREATE TABLE kc_user (
    kc_uuid varchar(50)  NOT NULL,
    first_name varchar(100)  NOT NULL,
    last_name varchar(100)  NOT NULL,
    email int  NOT NULL,
    status boolean  NOT NULL,
    tx_date timestamp  NOT NULL,
    tx_user varchar(50)  NOT NULL,
    tx_host varchar(50)  NOT NULL,
    s3_profile_picture int  NOT NULL,
    kc_group_kc_group_id serial  NOT NULL,
    CONSTRAINT kc_user_pk PRIMARY KEY (kc_uuid)
);

-- Table: language
CREATE TABLE language (
    language_id serial  NOT NULL,
    language_name varchar(100)  NOT NULL,
    status boolean  NOT NULL,
    CONSTRAINT language_pk PRIMARY KEY (language_id)
);

-- Table: s3_object
CREATE TABLE s3_object (
    s3_object_id serial  NOT NULL,
    content_type varchar(50)  NOT NULL,
    bucket varchar(50)  NOT NULL,
    filename varchar(100)  NOT NULL,
    status boolean  NOT NULL,
    tx_date timestamp  NOT NULL,
    tx_user varchar(50)  NOT NULL,
    tx_host varchar(50)  NOT NULL,
    CONSTRAINT s3_object_pk PRIMARY KEY (s3_object_id)
);

-- Table: user_social_media
CREATE TABLE user_social_media (
    social_media_id serial NOT NULL,
    kc_user_uuid varchar(50) NOT NULL,
    social_media_url varchar(255) NOT NULL,
    status boolean NOT NULL,
    CONSTRAINT user_social_media_pk PRIMARY KEY (social_media_id)
);

-- Creación de la tabla purchase con todas las columnas necesarias.
CREATE TABLE purchase (
    purchase_id serial NOT NULL,
    date_purchase timestamp NOT NULL,
    amount decimal(10,5) NOT NULL,
    image_comprobant varchar(255) NOT NULL,
    Course_course_id int NOT NULL,
    payment_method_id int NOT NULL,
    kc_user_kc_uuid varchar(50) NOT NULL,
    CONSTRAINT purchase_pk PRIMARY KEY (purchase_id)
);

CREATE TABLE reply (
    reply_id serial  NOT NULL,
    status boolean  NOT NULL,
    date timestamp  NOT NULL,
    coment varchar(500)  NOT NULL,
    purchase_purchase_id int  NOT NULL,
    CONSTRAINT reply_pk PRIMARY KEY (reply_id)
);


-- Agregar la llave foránea que relaciona purchase con Course
ALTER TABLE purchase ADD CONSTRAINT purchase_Course
    FOREIGN KEY (Course_course_id)
    REFERENCES Course (course_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE;

-- Agregar la llave foránea que relaciona purchase con kc_user
ALTER TABLE purchase ADD CONSTRAINT purchase_kc_user
    FOREIGN KEY (kc_user_kc_uuid)
    REFERENCES kc_user (kc_uuid)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE;

-- Agregar la llave foránea que relaciona purchase con payment_method
ALTER TABLE purchase ADD CONSTRAINT purchase_payment_method
    FOREIGN KEY (payment_method_id)
    REFERENCES payment_method (payment_method_id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE;


-- Reference: reply_purchase (table: reply)
ALTER TABLE reply ADD CONSTRAINT reply_purchase
    FOREIGN KEY (purchase_purchase_id)
    REFERENCES purchase (purchase_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Contenido_Secciones (table: Content)
ALTER TABLE Content ADD CONSTRAINT Contenido_Secciones
    FOREIGN KEY (Sections_section_id)
    REFERENCES Sections (section_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Course_kc_user (table: Course)
ALTER TABLE Course ADD CONSTRAINT Course_kc_user
    FOREIGN KEY (kc_user_kc_uuid)
    REFERENCES kc_user (kc_uuid)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Cursos_Categorias (table: Course)
ALTER TABLE Course ADD CONSTRAINT Cursos_Categorias
    FOREIGN KEY (Category_category_id)
    REFERENCES Category (category_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Cursos_Idioma (table: Course)
ALTER TABLE Course ADD CONSTRAINT Cursos_Idioma
    FOREIGN KEY (language_language_id)
    REFERENCES language (language_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Secciones_Cursos (table: Sections)
ALTER TABLE Sections ADD CONSTRAINT Secciones_Cursos
    FOREIGN KEY (Course_course_id)
    REFERENCES Course (course_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: attachment_Content (table: attachment)
ALTER TABLE attachment ADD CONSTRAINT attachment_Content
    FOREIGN KEY (Content_content_id)
    REFERENCES Content (content_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: kc_user_kc_group (table: kc_user)
ALTER TABLE kc_user ADD CONSTRAINT kc_user_kc_group
    FOREIGN KEY (kc_group_kc_group_id)
    REFERENCES kc_group (kc_group_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: user_social_media_kc_user (table: user_social_media)
ALTER TABLE user_social_media ADD CONSTRAINT user_social_media_kc_user_fk
    FOREIGN KEY (kc_user_uuid)
    REFERENCES kc_user (kc_uuid)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.


-- Reference: course_image_Course (table: course_image)
ALTER TABLE course_image ADD CONSTRAINT course_image_Course
    FOREIGN KEY (Course_course_id)
    REFERENCES Course (course_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Table: account_type
CREATE TABLE account_type (
    account_type_id int  NOT NULL,
    description varchar(30)  NOT NULL,
    CONSTRAINT account_type_pk PRIMARY KEY (account_type_id)
);



-- Table: bank
CREATE TABLE bank (
    bank_id int  NOT NULL,
    bank_name varchar(30)  NOT NULL,
    phone_number varchar(30)  NOT NULL,
    webpage varchar(30)  NOT NULL,
    CONSTRAINT bank_pk PRIMARY KEY (bank_id)
);



-- Table: payment_method
CREATE TABLE payment_method (
    payment_method_id serial NOT NULL,
    ci_person varchar(30)  NOT NULL,
    name_owner varchar(30)  NOT NULL,
    phone_number varchar(30)  NOT NULL,
    qr_image varchar(300000)  NOT NULL,
    account_number varchar(50)  NOT NULL,
    bank_bank_id int  NOT NULL,
    kc_user_kc_uuid varchar(50)  NOT NULL,
    account_type_account_type_id int  NOT NULL,
    CONSTRAINT payment_method_pk PRIMARY KEY (payment_method_id)
);


-- foreign keys

-- Reference: payment_method_account_type (table: payment_method)
ALTER TABLE payment_method ADD CONSTRAINT payment_method_account_type
    FOREIGN KEY (account_type_account_type_id)
    REFERENCES account_type (account_type_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: payment_method_bank (table: payment_method)
ALTER TABLE payment_method ADD CONSTRAINT payment_method_bank
    FOREIGN KEY (bank_bank_id)
    REFERENCES bank (bank_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: payment_method_kc_user (table: payment_method)
ALTER TABLE payment_method ADD CONSTRAINT payment_method_kc_user
    FOREIGN KEY (kc_user_kc_uuid)
    REFERENCES kc_user (kc_uuid)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

ALTER TABLE course
ADD COLUMN course_is_public boolean NOT NULL DEFAULT true;
ALTER TABLE kc_user
ALTER COLUMN email TYPE varchar(255);

----------PARA COMENTARIOS: -----------
-- Crear la tabla Comment
CREATE TABLE Comment (
    comment_id serial NOT NULL,
    content text NOT NULL,
    creation_date timestamp NOT NULL,
    status boolean NOT NULL,
    Course_course_id int NOT NULL,  -- Tipo corregido para coincidir con la tabla de destino
    kc_user_kc_uuid varchar(50) NOT NULL,
    Comment_parent_id int NULL,
    CONSTRAINT Comment_pk PRIMARY KEY (comment_id)
);

-- Llaves foráneas
-- Referencia: Comment_Comment (tabla: Comment)
ALTER TABLE Comment ADD CONSTRAINT Comment_Comment
    FOREIGN KEY (Comment_parent_id)
    REFERENCES Comment (comment_id)  -- Referenciar el campo correcto
    NOT DEFERRABLE
    INITIALLY IMMEDIATE;

-- Referencia: Comment_Course (tabla: Course)
ALTER TABLE Comment ADD CONSTRAINT Comment_Course
    FOREIGN KEY (Course_course_id)
    REFERENCES Course (course_id)  -- Asegúrate de que `course_id` sea la llave primaria en `Course`
    NOT DEFERRABLE
    INITIALLY IMMEDIATE;

-- Referencia: Comment_kc_user (tabla: kc_user)
ALTER TABLE Comment ADD CONSTRAINT Comment_kc_user
    FOREIGN KEY (kc_user_kc_uuid)
    REFERENCES kc_user (kc_uuid)  -- Asegúrate de que `kc_uuid` sea la llave primaria en `kc_user`
    NOT DEFERRABLE
    INITIALLY IMMEDIATE;

-- manejo de cupones:
-- Table: cupon
CREATE TABLE cupon (
    cupon_id serial  NOT NULL,
    discount_type varchar(200)  NOT NULL,
    discount_amount decimal(10,5)  NOT NULL,
    cupon_code varchar(500)  NOT NULL,
    start_date timestamp  NOT NULL,
    end_date timestamp  NOT NULL,
    min_amount_purchase decimal(10,5)  NOT NULL,
    description_promotion varchar(200)  NOT NULL,
    CONSTRAINT cupon_pk PRIMARY KEY (cupon_id)
);

-- Table: purchase_cupon
CREATE TABLE purchase_cupon (
    purchase_cupon_id serial  NOT NULL,
    purchase_purchase_id int  NOT NULL,
    cupon_cupon_id serial  NOT NULL,
    CONSTRAINT purchase_cupon_pk PRIMARY KEY (purchase_cupon_id)
);

-- Reference: purchase_cupon_cupon (table: purchase_cupon)
ALTER TABLE purchase_cupon ADD CONSTRAINT purchase_cupon_cupon
    FOREIGN KEY (cupon_cupon_id)
    REFERENCES cupon (cupon_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: purchase_cupon_purchase (table: purchase_cupon)
ALTER TABLE purchase_cupon ADD CONSTRAINT purchase_cupon_purchase
    FOREIGN KEY (purchase_purchase_id)
    REFERENCES purchase (purchase_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;