package testNG;

import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.*;
import reportBase.extendTestManager;
import util.readDataFromExcel;
import util.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
public class apiScenarios {

    // Test Case 1 execution
@Test(priority = 4,dataProvider = "readTestData",dataProviderClass = readDataFromExcel.class)
    public void TestCase_001(Map testData)  {
        try{
                // To invoke util objects
                utils utlility = new utils();

                // To read URL from test data file
                String URI = utlility.getValuefromMap(testData,"URI",0);

                //To read parameters from test data file
                String parameter = utlility.getValuefromMap(testData,"Parameter",0);
                System.out.println("URI : "+URI);

                // Log status in extent report
                    extendTestManager.getTest().log(Status.PASS, "Test Case Description :"+utlility.getValuefromMap(testData,"Test_Case_Description",0));
                extendTestManager.getTest().log(Status.PASS, "URI :"+URI);
                extendTestManager.getTest().log(Status.PASS, "Parameter :"+parameter);

                //Execute GET method to validate API is working as expected
                String outPut = utils.getAPIMethod(URI,parameter);
                String responseCode = outPut.split("\\|")[0].split("\\~")[1];
                String responseString = outPut.split("\\|")[1].split("\\~")[1];

                System.out.println(responseCode);
                Assert.assertEquals(responseCode,"200");
                String expectedResponseCode = "200";

                extendTestManager.getTest().log(Status.PASS, "Response Code  :"+responseCode);
                extendTestManager.getTest().log(Status.PASS, "Response Body  :"+responseString);
        }catch(Exception e){}
}



    @Test(priority=5,dataProvider = "readTestData",dataProviderClass = readDataFromExcel.class)
    public void TestCase_002(Map testData) throws IOException, URISyntaxException {
        utils utlility = new utils();
        String URI = utlility.getValuefromMap(testData,"URI",0);
        String parameter = utlility.getValuefromMap(testData,"Parameter",0);
        System.out.println("URI : "+URI);
        extendTestManager.getTest().log(Status.PASS, "Test Case Description :"+utlility.getValuefromMap(testData,"Test_Case_Description",0));
        extendTestManager.getTest().log(Status.PASS, "URI :"+URI);
        extendTestManager.getTest().log(Status.PASS, "Parameter :"+parameter);

        String outPut = utils.getAPIMethod(URI,parameter);

        String responseCode = outPut.split("\\|")[0].split("\\~")[1];
        String responseString = outPut.split("\\|")[1].split("\\~")[1];

        System.out.println(responseCode);
        Assert.assertEquals(responseCode,"200");
        String expectedResponseCode = "200";
        extendTestManager.getTest().log(Status.PASS, "Response Code  :"+responseCode);
    }


 @Test(priority=1,dataProvider = "readTestData",dataProviderClass = readDataFromExcel.class)
    public void TestCase_003(Map testData) throws IOException, URISyntaxException {
        utils utlility = new utils();
        String URI = utlility.getValuefromMap(testData,"URI",0);
        String parameter = utlility.getValuefromMap(testData,"Parameter",0);
        extendTestManager.getTest().log(Status.PASS, "Test Case Description :"+utlility.getValuefromMap(testData,"Test_Case_Description",0));
        extendTestManager.getTest().log(Status.PASS, "URL :"+URI);
        extendTestManager.getTest().log(Status.PASS, "Parameter :"+parameter);

        String outPut = utils.getAPIMethod(URI,parameter);

        String responseCode = outPut.split("\\|")[0].split("\\~")[1];
        String responseString = outPut.split("\\|")[1].split("\\~")[1];

        String expectedResponseCode = utlility.getValuefromMap(testData,"Expected_ResponseCode",0);;
        String expectedResponseString = utlility.getValuefromMap(testData,"Expected_ResponseString",0);;


        if(Integer.parseInt(expectedResponseCode)==Integer.parseInt(responseCode)){
            extendTestManager.getTest().log(Status.PASS, "Actual Response Code  :"+responseCode+"  Expected Response Code :"+expectedResponseCode);
        }else{
            extendTestManager.getTest().log(Status.FAIL, "Actual Response Code  :"+responseCode+"  Expected Response Code :"+expectedResponseCode);
        }
        if(expectedResponseString.equalsIgnoreCase(responseString)){
            extendTestManager.getTest().log(Status.PASS, "Actual Response String  :"+responseString+"  Expected Response Code :"+expectedResponseString);
        }else{
            extendTestManager.getTest().log(Status.FAIL, "Actual Response String  :"+responseString+"  Expected Response Code :"+expectedResponseString);
        }

    }



    @Test(priority = 3, dataProvider = "readTestData",dataProviderClass = readDataFromExcel.class)
    public void TestCase_004(Map testData) throws IOException, URISyntaxException {
        utils utlility = new utils();
        String URI = utlility.getValuefromMap(testData,"URI",0);
        String parameter = utlility.getValuefromMap(testData,"Parameter",0);
        System.out.println("URI : "+URI);
        extendTestManager.getTest().log(Status.PASS, "Test Case Description :"+utlility.getValuefromMap(testData,"Test_Case_Description",0));
        extendTestManager.getTest().log(Status.PASS, "URL :"+URI);

        extendTestManager.getTest().log(Status.PASS, "Parameter :"+parameter);

        String outPut = utils.getAPIMethod(URI,parameter);

        String responseCode = outPut.split("\\|")[0].split("\\~")[1];
        String responseString = outPut.split("\\|")[1].split("\\~")[1];

        String expectedResponseCode = "200";
        extendTestManager.getTest().log(Status.PASS, "Response Code  :"+responseCode);
        extendTestManager.getTest().log(Status.PASS, "Response Body  :"+responseString);

    }

    @Test(priority = 2, dataProvider = "readTestData",dataProviderClass = readDataFromExcel.class)
    public void TestCase_005(Map testData) throws IOException, URISyntaxException {
        utils utlility = new utils();
        String URI = utlility.getValuefromMap(testData,"URI",0);
        String parameter = utlility.getValuefromMap(testData,"Parameter",0);
        System.out.println("URI : "+URI);
        extendTestManager.getTest().log(Status.PASS, "URL :"+URI);
        extendTestManager.getTest().log(Status.PASS, "Parameter :"+parameter);

        String outPut = utils.getAPIMethod(URI,parameter);

        String responseCode = outPut.split("\\|")[0].split("\\~")[1];
        String responseString = outPut.split("\\|")[1].split("\\~")[1];

        String expectedResponseCode = "200";
        extendTestManager.getTest().log(Status.PASS, "Actual Response Code  :"+responseCode+"  Expected Response Code");
        extendTestManager.getTest().log(Status.PASS, "Response Body  :"+responseString);

    }

}
