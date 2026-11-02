-- Per student and department: grade in lowest-level vs highest-level course
COPY (
    WITH parsed AS (
        -- Split courseId into department and numeric course number
        SELECT tr.studentId,
               tr.courseId,
               tr.grade,
               regexp_extract(tr.courseId, '^[a-z]+') AS dept,
               CAST(regexp_extract(tr.courseId, '^[a-z]+([0-9]+)', 1) AS INTEGER) AS courseNum
        FROM transcript tr
    ),
    bestPerNumber AS (
        -- If several courses share the same course number, keep the one with the higher grade
        SELECT studentId, courseId, grade, dept, courseNum
        FROM (
            SELECT p.*,
                   ROW_NUMBER() OVER (PARTITION BY studentId, dept, courseNum
                                      ORDER BY grade DESC, courseId) AS rn
            FROM parsed p
        )
        WHERE rn = 1
    ),
    ranked AS (
        -- Rank each student's courses within a department from lowest and highest level
        SELECT b.*,
               ROW_NUMBER() OVER (PARTITION BY studentId, dept ORDER BY courseNum ASC)  AS lowRank,
               ROW_NUMBER() OVER (PARTITION BY studentId, dept ORDER BY courseNum DESC) AS highRank,
               COUNT(*)     OVER (PARTITION BY studentId, dept)                         AS numLevels
        FROM bestPerNumber b
    )
    SELECT s.firstName || ' ' || s.lastName AS fullName,
           lo.dept AS department,
           '(' || lo.courseId || ', ' || hi.courseId || ')' AS courses,
           '(' || CAST(lo.grade AS VARCHAR) || ', ' || CAST(hi.grade AS VARCHAR) || ')' AS grades
    FROM ranked lo
    JOIN ranked hi  ON lo.studentId = hi.studentId AND lo.dept = hi.dept
    JOIN student s  ON s.studentId = lo.studentId
    WHERE lo.lowRank = 1
      AND hi.highRank = 1
      AND lo.numLevels >= 2
    ORDER BY fullName, department
) TO 'src/main/resources/data/output/query8.csv' (HEADER);