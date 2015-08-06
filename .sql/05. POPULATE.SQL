with datesfrom2015to2030 as (
	select generate_series(DATE '20150101', DATE '20301231', interval '1' day)::timestamp without time zone as d
)
insert into dwh.dim_usage_day(year, month, day, date) (
	select 	EXTRACT(YEAR FROM d), EXTRACT(MONTH FROM d), EXTRACT(DAY FROM d), d::date
	from 	datesfrom2015to2030
)