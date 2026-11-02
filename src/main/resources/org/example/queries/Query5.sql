-- Professors who teach at least one course and have never given a failing grade
COPY (
    SELECT p.firstName || ' ' || p.lastName AS fullName
    FROM professor p
    WHERE p.profId IN (SELECT profId FROM teaches)
      AND NOT EXISTS (
          SELECT 1
          FROM teaches t
          JOIN transcript tr ON t.courseId = tr.courseId
          WHERE t.profId = p.profId
            AND tr.grade <= 49
      )
    ORDER BY fullName
) TO 'src/main/resources/data/output/query5.csv' (HEADER);