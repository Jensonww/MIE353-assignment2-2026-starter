-- Number of students who received a grade of 80 or above in mie301h1f
COPY (
    SELECT COUNT(*) AS numStudents
    FROM transcript
    WHERE courseId = 'mie301h1f'
      AND grade >= 80
) TO 'src/main/resources/data/output/query1.csv' (HEADER);