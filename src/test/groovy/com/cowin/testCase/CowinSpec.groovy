package com.cowin.testCase

import com.cowin.Pages.CowinHomePage
import com.twilio.Twilio
import com.twilio.rest.api.v2010.account.Message
import com.twilio.type.PhoneNumber
import geb.spock.GebSpec


class CowinSpec extends GebSpec {

    def "check vaccine availability at my PinCode"() {
        when:
        CowinHomePage cp = to CowinHomePage

        and:
        cp.searchByPIn.click()

        and:
        cp.pinTextBox.value(pincode)

        then:
        cp.searchButton.click()


        then:
        int numberOfMessages=cp.searchLocationAndVaccine().size()
        if(numberOfMessages!=0) {
            for (int i = 0; i <numberOfMessages ; i++) {
                        Twilio.init(
                    "AC1ea0abaa384d67b14a8d4fdd6ee9b47e", "41de078223c54a32a102ecf51e05a2c0");
            Message.creator(
                    new PhoneNumber("+91-9871419150"),
                    new PhoneNumber("+15038226838"),
                    cp.searchLocationAndVaccine().get(i))
                    .create();
            }
        }
        else "No Vaccine center found at pincode "+ pincode

        where:
        phoneNumber | pincode
        "9871419150"  | "110065"
        "7755943346"  | "110041"

    }
}
