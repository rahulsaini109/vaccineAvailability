package com.cowin.testCase

import com.cowin.Pages.CowinHomePage
import geb.spock.GebSpec


class CowinSpec extends GebSpec {

    def "check vaccine availabilty at my PinCode"() {
        when:
        CowinHomePage cp = to CowinHomePage

        and:
        cp.searchByPIn.click()

        and:
        cp.pinTextBox.value("110065")

        then:
        cp.searchButton.click()


        then:
        if (cp.searchLocationAndVaccine("COVISHIELD") != null) {
            println cp.searchLocationAndVaccine("COVISHIELD")
        } else println "No Vaccine Found"


    }
}
