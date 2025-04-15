import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Student_Test_Grades")
public class Student_Test_Grades extends HttpServlet {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/school";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // Get form parameters
            String userIdParam = request.getParameter("user_id");
            String subjectName = request.getParameter("subject_name");
            String grade = request.getParameter("grade");

            // Validate input
            if (userIdParam == null || subjectName == null || grade == null ||
                userIdParam.trim().isEmpty() || subjectName.trim().isEmpty() || grade.trim().isEmpty()) {
                out.println("<script>alert('All fields required!'); window.history.back();</script>");
                return;
            }

            int userId;
            try {
                userId = Integer.parseInt(userIdParam);
            } catch (NumberFormatException e) {
                out.println("<script>alert('Invalid User ID format!'); window.history.back();</script>");
                return;
            }

            // Check if userId exists in the users table
            boolean userExists = false;
            try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String checkUserQuery = "SELECT COUNT(*) FROM users WHERE userId = ?";
                try (PreparedStatement checkUserPs = con.prepareStatement(checkUserQuery)) {
                    checkUserPs.setInt(1, userId);
                    ResultSet rs = checkUserPs.executeQuery();
                    if (rs.next() && rs.getInt(1) > 0) {
                        userExists = true;
                    }
                }
                if (!userExists) {
                    out.println("<script>alert('User ID does not exist! Please enter a valid User ID.'); window.history.back();</script>");
                    return;
                }

                // Check for duplicate subject for the user
                String checkDuplicateQuery = "SELECT COUNT(*) FROM student_subjects_grades WHERE userId = ? AND subject_name = ?";
                try (PreparedStatement checkDuplicatePs = con.prepareStatement(checkDuplicateQuery)) {
                    checkDuplicatePs.setInt(1, userId);
                    checkDuplicatePs.setString(2, subjectName);
                    ResultSet rs = checkDuplicatePs.executeQuery();
                    if (rs.next() && rs.getInt(1) > 0) {
                        out.println("<script>alert('Duplicate subject for this user! Please update the existing grade or choose a different subject.'); window.history.back();</script>");
                        return;
                    }
                }

                // Insert data into the database
                String insertQuery = "INSERT INTO student_subjects_grades (userId, subject_name, grade) VALUES (?, ?, ?)";
                try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
                    ps.setInt(1, userId);
                    ps.setString(2, subjectName);
                    ps.setString(3, grade);

                    int result = ps.executeUpdate();
                    if (result > 0) {
                        out.println("<script>alert('Grade Entered Successfully!'); window.history.back();</script>");
                    } else {
                        out.println("<script>alert('Error! No rows affected.'); window.history.back();</script>");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Check if it's a duplicate entry error (MySQL error code 1062)
                if (e.getErrorCode() == 1062) {
                    out.println("<script>alert('Duplicate subject for this user! Please update the existing grade or choose a different subject.'); window.history.back();</script>");
                } else {
                    out.println("<script>alert('Database Error: ' + '" + e.getMessage() + "'); window.history.back();</script>");
                }
            }
        }
    }
}