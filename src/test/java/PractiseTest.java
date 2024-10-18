import org.testng.Assert;
import org.testng.annotations.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PractiseTest {
    String query = "select * from aditya";
   @Test
    public void testAddition(){
       int result = add(2,3);
       Assert.assertEquals(result,5,"Successfull");
       System.out.println(result);
   }

   public int add(int a, int b){
       return a+b;
   }

   @Test(priority = 3)
   public void SimpleTest(){
       System.out.println("This is Test with priority set to 3");
       int a = 0;
       int b = 0;
       int result = a + b;
       Assert.assertEquals(result,2,"Errror or not");
   }

   @Test(priority = 2)
    public void connectionTesting(){
       try(Connection con = DriverManager.getConnection("jdbc:postgresql://10.1.27.41:5432/dummydb","dummyuser","password1")){
           Assert.assertNotNull(con,"Connection Successful");
       } catch (Exception e) {
           Assert.fail("Connection Failed "+ e.getMessage());
       }
   }
   @Test(dependsOnMethods = "connectionTesting",priority = 1)
    public void querytesting(){
       try(Connection con = DriverManager.getConnection("jdbc:postgresql://10.1.27.41:5432/dummydb","dummyuser","password1");
           PreparedStatement st = con.prepareStatement(query);
           ResultSet rs = st.executeQuery();){
           System.out.println("ID\t\tName");
           System.out.println("-------------------------");

           while (rs.next()) {
               String id = rs.getString("account_id");
               String name = rs.getString("account_login");
               System.out.println(id + "\t\t\t\t\t" + name);
           }

           if (!rs.isBeforeFirst()) {
               System.out.println("No data found.");
           }
       } catch (Exception e) {
           Assert.fail("Check the Query again "+ e.getMessage());
       }
   }
}
