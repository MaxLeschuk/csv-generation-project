CREATE TABLE t_csv_templates (id integer, template varchar(255));
		insert into t_csv_templates (id, template) values (1, 'two;column');
        insert into t_csv_templates (id, template) values (2, 'col1;col2;col3');
        insert into t_csv_templates (id, template) values (3, 'onecolumn');
        insert into t_csv_templates (id, template) values (4, 'four;column;tem;plate');
select * from t_csv_templates;
