COPY (
    SELECT DISTINCT courseName
    FROM course
    WHERE regexp_matches(courseId, '^[a-z]+4[0-9]{2}[a-z]')
    ORDER BY courseName
) TO 'src/main/resources/data/output/query2.csv' (HEADER);