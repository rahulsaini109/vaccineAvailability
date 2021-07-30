package com.cowin.testCase

import com.cowin.Pages.CowinHomePage
import com.twilio.Twilio
import com.twilio.rest.api.v2010.account.Message
import com.twilio.type.PhoneNumber
import geb.spock.GebSpec
import util.Data

class CowinSpec extends GebSpec {
    Data data=new Data()
    def "check vaccine availability at my PinCode"() {
        when:"Open Cowin Homepage"
        CowinHomePage cp = to CowinHomePage


        and:"Select Search PIN option"
        cp.searchByPIn.click()

        and:"Get pinCode from mongo"
        List<String> pinCodes=data.dataFromMongo("pinCodes")
        assert pinCodes != null

        then:"Get Mobile Number from Mongo"
        List<String> mobileNo=data.dataFromMongo("mobileNo")
        assert mobileNo != null

        then:"Get available vaccine slot and send message"
        for(int j=0;j<pinCodes.size();j++)
        {
            cp.pinTextBox.value(pinCodes.get(j))
            cp.searchButton.click()
            ArrayList<String> message = cp.searchLocationAndVaccine()

            if (message != null) {
                int numberOfMessages = message.size()
                for (int i = 0; i < numberOfMessages; i++) {
                    println message.get(i)
                    def mobileNumber = mobileNo.get(j).split(",");
                    for(def mobile:mobileNumber)
                    {
                        try {
                            Twilio.init(
                                    "test", "test1");
                            Message.creator(
                                    new PhoneNumber("+" + mobile),
                                    new PhoneNumber("+19999911112"),
                                    message.get(i))
                                    .create();
                        }
                        catch (Exception e) {
                            println mobile + " not verified"
                        }
                    }
                }

            } else println "No Vaccine center found at pincode " + pinCodes.get(j)
        }

    }
}
