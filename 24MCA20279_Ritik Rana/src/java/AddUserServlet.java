import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/School";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Add User</title>");
        out.println("<style>");
        out.println("body { font-family: 'Segoe UI', sans-serif; background: #eef2f3; margin: 0; padding: 0; }");
        out.println(".container { max-width: 600px; margin: 40px auto; background: #fff; padding: 30px 40px; border-radius: 10px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); }");
        out.println("h1 { text-align: center; color: #333; margin-bottom: 20px; }");
        out.println("label { display: block; margin: 10px 0 5px; font-weight: 600; }");
        out.println("input[type='text'], input[type='email'], select { width: 100%; padding: 10px; margin-bottom: 15px; border: 1px solid #ccc; border-radius: 5px; }");
        out.println("input[type='radio'] { margin-right: 8px; }");
        out.println(".gender-group { margin-bottom: 15px; }");
        out.println(".btn { display: block; width: 100%; background-color: #4CAF50; color: white; padding: 12px; border: none; border-radius: 5px; font-size: 16px; cursor: pointer; transition: 0.3s; }");
        out.println(".btn:hover { background-color: #45a049; }");
        out.println("a { text-decoration: none; color: white; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>Add New User</h1>");
        out.println("<form action='AddUserServlet' method='POST'>");

        out.println("<label>User ID</label>");
        out.println("<input type='text' name='userId' required />");

        out.println("<label>Name</label>");
        out.println("<input type='text' name='name' required />");

        out.println("<label>Father's Name</label>");
        out.println("<input type='text' name='fatherName' required />");

        out.println("<label>Mother's Name</label>");
        out.println("<input type='text' name='motherName' required />");

        out.println("<label>Email</label>");
        out.println("<input type='email' name='email' required />");

        out.println("<label>Phone</label>");
        out.println("<input type='text' name='phone' required />");

        out.println("<label>Grade</label>");
        out.println("<input type='text' name='grade' required />");

        out.println("<label>City</label>");
        out.println("<input type='text' name='city' required />");

        out.println("<label>State</label>");
        out.println("<select name='state' required>");
        out.println("<option value='' disabled selected>Select State</option>");
        out.println("<option value='Delhi'>Delhi</option>");
        out.println("<option value='Uttar Pradesh'>Uttar Pradesh</option>");
        out.println("<option value='Maharashtra'>Maharashtra</option>");
        out.println("<option value='Himachal Pradesh'>Himachal Pradesh</option>");
        out.println("<option value='Punjab'>Punjab</option>");
        out.println("<option value='Karnataka'>Karnataka</option>");
        out.println("<option value='Rajasthan'>Rajasthan</option>");
        out.println("<option value='Gujarat'>Gujarat</option>");
        out.println("<option value='Bihar'>Bihar</option>");
        out.println("<option value='Madhya Pradesh'>Madhya Pradesh</option>");
        out.println("<option value='Jharkhand'>Jharkhand</option>");
        out.println("<option value='Tamil Nadu'>Tamil Nadu</option>");
        out.println("<option value='West Bengal'>West Bengal</option>");
        out.println("</select>");

        out.println("<label>Religion</label>");
        out.println("<select name='religion' required>");
        out.println("<option value='' disabled selected>Select Religion</option>");
        out.println("<option value='Hindu'>Hindu</option>");
        out.println("<option value='Muslim'>Muslim</option>");
        out.println("<option value='Christian'>Christian</option>");
        out.println("<option value='Sikh'>Sikh</option>");
        out.println("</select>");

        out.println("<label>Gender</label>");
        out.println("<div class='gender-group'>");
        out.println("<input type='radio' name='gender' value='Male' required /> Male ");
        out.println("<input type='radio' name='gender' value='Female' required /> Female ");
        out.println("<input type='radio' name='gender' value='Other' required /> Other");
        out.println("</div>");

        out.println("<label>Profile Image Path</label>");
        out.println("<input type='text' name='imglink' required />");

        out.println("<button type='submit' class='btn'>Add User</button>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String userId = request.getParameter("userId");
        String name = request.getParameter("name");
        String fatherName = request.getParameter("fatherName");
        String motherName = request.getParameter("motherName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String grade = request.getParameter("grade");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String religion = request.getParameter("religion");
        String gender = request.getParameter("gender");
        String profileImage = request.getParameter("imglink");
        String password = name;

        if (userId.isEmpty() || name.isEmpty() || fatherName.isEmpty() || motherName.isEmpty() ||
            email.isEmpty() || phone.isEmpty() || grade.isEmpty() || city.isEmpty() || state.isEmpty() ||
            religion.isEmpty() || gender.isEmpty() || profileImage.isEmpty() || password.isEmpty()) {
            out.println("<h2 style='color:red; text-align:center;'>All fields are required.</h2>");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String query = "INSERT INTO users (userId, name, fatherName, motherName, email, phone, grade, city, state, religion, gender, profileImage, password) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userId);
            pstmt.setString(2, name);
            pstmt.setString(3, fatherName);
            pstmt.setString(4, motherName);
            pstmt.setString(5, email);
            pstmt.setString(6, phone);
            pstmt.setString(7, grade);
            pstmt.setString(8, city);
            pstmt.setString(9, state);
            pstmt.setString(10, religion);
            pstmt.setString(11, gender);
            pstmt.setString(12, profileImage);
            pstmt.setString(13, password);

            int result = pstmt.executeUpdate();

            if (result > 0) {
                out.println("<html><head><title>User Added</title><style>");
                out.println("body { font-family: 'Segoe UI', sans-serif; background: #eef2f3; }");
                out.println(".box { max-width: 500px; margin: 80px auto; padding: 40px; background: white; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); text-align: center; }");
                out.println(".msg { font-size: 20px; color: green; margin-bottom: 30px; }");
                out.println(".btn { padding: 12px 20px; background: #e91e63; color: white; border: none; border-radius: 5px; font-size: 16px; text-decoration: none; margin: 10px; display: inline-block; }");
                out.println(".btn:hover { background: #d81b60; }");
                out.println("</style></head><body>");
                out.println("<div class='box'>");
                out.println("<div class='msg'>✅ User added successfully!</div>");
                out.println("<a href='AddUserServlet' class='btn'>Add Another User</a>");
                out.println("<a href='Dashboard' class='btn'>Go to Admin Dashboard</a>"); // Changed to /Dashboard
                out.println("</div></body></html>");
            } else {
                out.println("<h2 style='color:red; text-align:center;'>Failed to add user. Try again.</h2>");
            }

            pstmt.close();
            conn.close();

        } catch (Exception e) {
            out.println("<h2 style='color:red; text-align:center;'>Error: " + e.getMessage() + "</h2>");
            e.printStackTrace(out);
        }
    }
}