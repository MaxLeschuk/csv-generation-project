CREATE TABLE t_csv_templates (id SERIAL, template varchar(255));
		insert into t_csv_templates (template) values ( 'two;column');
        insert into t_csv_templates (template) values ( 'col1;col2;col3');
        insert into t_csv_templates (template) values ( 'onecolumn');
        insert into t_csv_templates (template) values ('four;column;tem;plate');
select * from t_csv_templates;
CREATE TABLE t_csv_files(id SERIAL,userId varchar(300),path varchar(255));
