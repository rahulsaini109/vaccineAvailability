package util

import io.restassured.RestAssured
import io.restassured.path.json.JsonPath
import io.restassured.response.Response
import io.restassured.response.ResponseBody
import io.restassured.specification.RequestSpecification

class Data {
public List<String> dataFromMongo(String attribute) {
        RestAssured.baseURI = "https://ap-south-1.aws.webhooks.mongodb-realm.com";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/api/client/v2.0/app/test_google_sheet-mejsg/service/getCowinSlotData/incoming_webhook/webhook0");
        ResponseBody body = response.getBody();
        JsonPath jsonPathEvaluator = response.jsonPath()
        return response.jsonPath().getList(attribute);
    }
}
