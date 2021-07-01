import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.util.SystemOutLogger;

public class LibraryAPI {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		DataDrivenFromExcel RestAPIData = new DataDrivenFromExcel();
		ArrayList<String> jsonBodyValues = RestAPIData.getDataFromExcel("REST API","AddBook");
		
		System.out.println(jsonBodyValues);
		
		HashMap<String, Object> bodyValue = new HashMap<String, Object>();
		bodyValue.put("name", jsonBodyValues.get(1));
		bodyValue.put("isbn", jsonBodyValues.get(2));
		bodyValue.put("aisle", jsonBodyValues.get(3));
		bodyValue.put("author", jsonBodyValues.get(4));
		
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		//Passing values to json from test
		String addBook = given().header("Content-Type","application/json")
		.body(bodyValue)
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(addBook);
		String bookID = js.get("ID");
		System.out.println("Book Id of the book is : " + bookID);
	}

}
