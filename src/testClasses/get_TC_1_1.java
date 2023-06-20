package testClasses;

import org.testng.annotations.Test;
import java.io.IOException;

import org.testng.Assert;
import commonFunctions.API_Common_Fns;
import io.restassured.path.json.JsonPath;
import requestRepository.getReqRepo.Get_req_repository_1;


public class get_TC_1_1 {
	@Test
	public static void execute() throws IOException 
	{

		for (int i = 0; i < 5; i++) 
		{
			
		   int statusCode = API_Common_Fns.Response_statusCode(Get_req_repository_1.Base_URI(), Get_req_repository_1.Get_Resource());
		   System.out.println(statusCode);
			if (statusCode == 200) 
			{
				
				String responseBody = API_Common_Fns.Response_body(Get_req_repository_1.Base_URI(), Get_req_repository_1.Get_Resource());
				System.out.println(responseBody); 
				//Expected result 
				int id [] = {7, 8, 9, 10, 11, 12};
			    String[] email = {"michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in",
			    		"byron.fields@reqres.in", "george.edwards@reqres.in", "rachel.howell@reqres.in"};

				//Parse the responseBody
				JsonPath jspresponse=new JsonPath(responseBody);
				int count = jspresponse.getList("data").size();
				System.out.println(count);
				
				//Validate objects 
				 for(int j=0;j<count; j++)
				 {

					 int exp_id = id[j];
				     String exp_email = email[j];
					 
					 String res_id=jspresponse.getString("data["+j+"].id");
					 int  res_int_id=Integer.parseInt(res_id);
					 String res_email=jspresponse.getString("data["+j+"].email");
					 
					 //validate the responseBody param
					 Assert.assertEquals(res_int_id, exp_id,"ID at index " + j);
			         Assert.assertEquals(res_email, exp_email, "email at index " + j);
				 }
				 break;
				
			} 
			else 
			{
				System.out.println("Correct StatusCode is not found. Hence retrying the API ");
			}

		}
	}
	
}
