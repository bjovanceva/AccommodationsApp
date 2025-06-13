create materialized view if not exists public.accommodations_per_host as
SELECT h.id as host_id,
       count(a.id) AS acc_per_host
FROM accommodation a
         RIGHT JOIN host h ON a.host_id = h.id
GROUP BY h.id;

alter materialized view public.accommodations_per_host owner to emt;



create materialized view if not exists public.hosts_per_country as
SELECT c.id        AS country_id,
       count(h.id) AS hosts_per_country
FROM host h
         RIGHT JOIN country c ON h.country_id = c.id
GROUP BY c.id;

alter materialized view public.hosts_per_country owner to emt;