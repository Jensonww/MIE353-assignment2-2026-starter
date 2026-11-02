package org.example;

// General Helper Imports
import manifold.sql.rt.api.Dependencies.*;
import java.sql.SQLException;
import java.util.List;
import static java.lang.System.out;
import java.nio.file.*;


// Import all defined queries
import org.example.queries.*;

// Import schema classes
import org.example.schema.CMS;
import org.example.schema.CMS.*;



public class Main {
    public static void main(String[] args) {

      try {
        // Load in all the data from CSV files in to in-memory DuckDB
        loadCMSData();

        // Example: fetch a student by ID and print their email
        Student s = Student.fetch(9002);
        out.println(s.getEmail());
        // Example: fetch the same student using a parameterized SQL query
        Student s2 = FindStudent.fetchOne(9002);
        out.println(s2.getEmail());

        //Example: saving a table to CSV
        CMS.addSqlChange(ctx -> {
          SaveStudents.execute(ctx);
        });
        CMS.commit();
        
        // EXTRA MAIN FUNCTION CALLS CAN GO BELOW:
        saveGradCourses();
        query1CountHighGradesMie301();
        query2FourthYearCourseNames();
        query3ProfsTeachingOneCourse();
        query4ProfsOfRebeccaWard();
        query5ProfsWithNoFailingGrades();
        query6DataModellingHighAchievers();
        query7StudentPairsWithCommonCourses();
        query8LowestVsHighestLevelGrades();
        
      }
      catch (Exception e) {
        e.printStackTrace();
      }


  }

  public static void loadCMSData() throws SQLException {
    loadStudentData();
    loadProfessorData();
    loadCourseData();
    loadTeachesData();
    loadTranscriptData();
  }

  public static void loadStudentData() throws SQLException {
    CMS.addSqlChange(ctx -> {
      LoadStudents.execute(ctx);
    });
    CMS.commit();
  }

  public static void loadProfessorData() throws SQLException {
    CMS.addSqlChange(ctx -> {
      LoadProfessors.execute(ctx);
    });
    CMS.commit();
  }

  public static void loadCourseData() throws SQLException {
    CMS.addSqlChange(ctx -> {
      LoadCourses.execute(ctx);
    });
    CMS.commit();
  }

  public static void loadTeachesData() throws SQLException {
    CMS.addSqlChange(ctx -> {
      LoadTeaches.execute(ctx);
    });
    CMS.commit();
  }

  public static void loadTranscriptData() throws SQLException {
    CMS.addSqlChange(ctx -> {
      LoadTranscript.execute(ctx);
    });
    CMS.commit();
  }

  public static void saveGradCourses() throws SQLException {
    CMS.addSqlChange(ctx -> {
      SaveGradCourses.execute(ctx);
    });
    CMS.commit();
  }

  public static void query1CountHighGradesMie301() throws SQLException {
    CMS.addSqlChange(ctx -> {
      Query1.execute(ctx);
    });
    CMS.commit();
  }

  public static void query2FourthYearCourseNames() throws SQLException {
    CMS.addSqlChange(ctx -> {
      Query2.execute(ctx);
    });
    CMS.commit();
  }

  public static void query3ProfsTeachingOneCourse() throws SQLException {
    CMS.addSqlChange(ctx -> {
      Query3.execute(ctx);
    });
    CMS.commit();
  }

  public static void query4ProfsOfRebeccaWard() throws SQLException {
    CMS.addSqlChange(ctx -> {
      Query4.execute(ctx);
    });
    CMS.commit();
  }

  public static void query5ProfsWithNoFailingGrades() throws SQLException {
    CMS.addSqlChange(ctx -> {
      Query5.execute(ctx);
    });
    CMS.commit();
  }

  public static void query6DataModellingHighAchievers() throws SQLException {
    CMS.addSqlChange(ctx -> {
      Query6.execute(ctx);
    });
    CMS.commit();
  }

  public static void query7StudentPairsWithCommonCourses() throws SQLException {
    CMS.addSqlChange(ctx -> {
      Query7.execute(ctx);
    });
    CMS.commit();
  }

  public static void query8LowestVsHighestLevelGrades() throws SQLException {
    CMS.addSqlChange(ctx -> {
      Query8.execute(ctx);
    });
    CMS.commit();
  }
}