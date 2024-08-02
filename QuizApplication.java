import java.sql.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mysql://127.0.0.1/quizdb";
        String username = "root";
        String password = "";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        int score = 0;
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected ");
            
            for (int i = 1; i <= 4; i++) {
                String query = "SELECT * FROM data WHERE question_id = ?";
                
                try (PreparedStatement pstmt = con.prepareStatement(query)) {
                    pstmt.setInt(1, i);
                    
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                    String ques = rs.getString("question");
                    String option1 = rs.getString("option1");
                    String option2 = rs.getString("option2");
                    String option3 = rs.getString("option3");
                    String option4 = rs.getString("option4");
                    String answer = rs.getString("answer");
                    System.out.println();
                    System.out.println("#####################");
                    System.out.println(ques);
                    System.out.println("1." + option1 + " 2." + option2 + " 3." + option3 + " 4." + option4);
                    Timer timer = new Timer();
                    TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                    System.out.println("\nTime is up! Moving to the next question.");
                    timer.cancel();
                        }
                 };
                timer.schedule(task, 5000);
                String usr = sc.nextLine();
                timer.cancel(); 
                if (usr.equalsIgnoreCase(answer)) {
                score++;
                System.out.println("Correct answer");
             } else {
                System.out.println("Wrong answer ");
                System.out.println("Correct answer is " + answer);
                            }
                        }
                    }
                }
            }

            System.out.println("You guessed " + score + " right answers out of 4");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            sc.close();
        }
    }
}
