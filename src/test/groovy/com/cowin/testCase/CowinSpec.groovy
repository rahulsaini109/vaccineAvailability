package com.cowin.testCase

import com.cowin.Pages.CowinHomePage
import com.twilio.Twilio
import com.twilio.rest.api.v2010.account.Message
import com.twilio.type.PhoneNumber
import geb.spock.GebSpec


class CowinSpec extends GebSpec {

    def "check vaccine availabilty at my PinCode"() {
        when:
        CowinHomePage cp = to CowinHomePage

        and:
        cp.searchByPIn.click()

        and:
        cp.pinTextBox.value("110041")

        then:
        cp.searchButton.click()


        then:
        if (cp.searchLocationAndVaccine("COVISHIELD") != null) {
            Twilio.init(
                    "AC1ea0abaa384d67b14a8d4fdd6ee9b47e", "41de078223c54a32a102ecf51e05a2c0");
            Message.creator(
                    new PhoneNumber("+91-9871419150"),
                    new PhoneNumber("+15038226838"),
                    "Available Vaccine center :" + cp.searchLocationAndVaccine("COVISHIELD"))
                    .create();
        } else println "No Vaccine Found"

    }
}
