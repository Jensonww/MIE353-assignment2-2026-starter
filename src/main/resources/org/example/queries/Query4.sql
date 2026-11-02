-- Full names of professors who have taught at least one course taken by Rebecca Ward
COPY (
    SELECT DISTINCT p.firstName || ' ' || p.lastName AS fullName
    FROM student s
    JOIN transcript tr ON s.studentId = tr.studentId
    JOIN teaches t     ON tr.courseId = t.courseId
    JOIN professor p   ON t.profId = p.profId
    WHERE s.firstName = 'Rebecca'
      AND s.lastName = 'Ward'
    ORDER BY fullName
) TO 'src/main/resources/data/output/query4.csv' (HEADER);