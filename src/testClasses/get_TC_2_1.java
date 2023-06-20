package testClasses;

import org.testng.annotations.Test;
import java.io.IOException;

import org.testng.Assert;

import commonFunctions.API_Common_Fns;

import io.restassured.path.json.JsonPath;
import requestRepository.getReqRepo.Get_req_repository_2;

public class get_TC_2_1 {
	@Test
	public static void execute() throws IOException {

		for (int i = 0; i < 5; i++) 
		{

			int statusCode = API_Common_Fns.Response_statusCode(Get_req_repository_2.Base_URI(),
					Get_req_repository_2.Get_Resource());
			System.out.println(statusCode);

			if (statusCode == 200) 
			{

				String responseBody = API_Common_Fns.Response_body(Get_req_repository_2.Base_URI(),
						Get_req_repository_2.Get_Resource());
				System.out.println(responseBody); 

				// Expected result
				int id[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
				String[] name = { "Google Pixel 6 Pro", "Apple iPhone 12 Mini, 256GB, Blue", "Apple iPhone 12 Pro Max",
						"Apple iPhone 11, 64GB", "Samsung Galaxy Z Fold2", "Apple AirPods", "Apple MacBook Pro 16",
						"Apple Watch Series 8", "Beats Studio3 Wireless", "Apple iPad Mini 5th Gen",
						"Apple iPad Mini 5th Gen", "Apple iPad Air", "Apple iPad Air" };

				// Parse the responseBody
				JsonPath jspresponse = new JsonPath(responseBody);
				int count = jspresponse.getList("data").size();
				System.out.println(count);

				// Validate objects
				for (int j = 0; j < count; j++) {

					int exp_id = id[j];
					String exp_name = name[j];

					String res_id = jspresponse.getString("[" + j + "].id");
					int res_int_id = Integer.parseInt(res_id);
					String res_name = jspresponse.getString("[" + j + "].name");

					// validate the responseBody param
					Assert.assertEquals(res_int_id, exp_id, "ID at index " + j);
					Assert.assertEquals(res_name, exp_name, "name at index " + j);

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
