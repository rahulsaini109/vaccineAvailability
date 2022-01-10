/*Copyright 2021 Rahul Saini, Bikram Bhusan Sinha, Monil Singhal

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. */

package com.cowin.testCase

import com.cowin.Pages.CowinHomePage

import geb.junit4.GebReportingTest
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

class CowinTest extends GebReportingTest implements Job{

    public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {
        CowinHomePage cp=to CowinHomePage
        cp.searchByPIn.click()
        cp.pinTextBox.value("110041")
        cp.searchButton.click()
        if (cp.searchLocationAndVaccine("COVISHIELD") != null) {
            Twilio.init(
                    "AC1ea0abaa384d67b14a8d4fdd6ee9b47e", "264414d67df60651b2cb8fc82240cf12");
            Message.creator(
                    new PhoneNumber("+91-9871419150"),
                    new PhoneNumber("+15038226838"),
                    "Available Vaccine center :" + cp.searchLocationAndVaccine("COVISHIELD"))
                    .create();
        } else println "No Vaccine Found"



    }

}
