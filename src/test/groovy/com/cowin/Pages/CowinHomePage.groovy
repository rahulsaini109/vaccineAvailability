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


package com.cowin.Pages

import geb.Page


class CowinHomePage extends Page{
    static url="https://www.cowin.gov.in/"

    static content =
            {
                searchByPIn(wait: true, required: false) {$("div", class:"mat-tab-label-content",text:contains("PIN"))}
                pinTextBox(wait: true, required: false){$("input",class:"mat-input-element")}
                searchButton(wait:true,required: false){$("button",class:"pin-search-btn")}
                numberOfAddressLine(wait:true,required: false){$("div",class:"row ng-star-inserted")}
                addressLabel(wait:true,required: false){int index->$("div",class:"row ng-star-inserted",index).find("div",class:"main-slider-wrap col").find("h5",class:"center-name-title")}
                numberOfSlots(wait:true,required:false){int index->$("div",class:"row ng-star-inserted",index).find("li",class:"ng-star-inserted")}
                slotAvailable(wait:true,required: false){int index->$("ul",class:"slot-available-wrap",index).find("li",class:"ng-star-inserted")}
                vaccineAvailable(wait:true,required: false){int index,int index2->$("ul",class:"slot-available-wrap",index).find("li",class:"ng-star-inserted",index2).find("div",class:"vaccine-cnt",0).find("h5")}
                dose1(wait:true,required: false){int index,int index2->$("ul",class:"slot-available-wrap",index).find("li",class:"ng-star-inserted",index2).find("div",class:"vaccine-box",0).find("span",title:"Dose 1")}
                dose2(wait:true,required: false){int index,int index2->$("ul",class:"slot-available-wrap",index).find("li",class:"ng-star-inserted",index2).find("div",class:"vaccine-box",0).find("span",title:"Dose 2")}
                registerLink(wait:true,required: false){$("a",class:"yellowbtn",0)}
                date(wait:true,required: false){ int index->$("ul",class:"availability-date-ul").find("li",class:"availability-date",index).find("p")}


            }


    ArrayList<String> searchLocationAndVaccine()
    {
        ArrayList<String> message = new ArrayList<String>()
        for(int i=0;i<numberOfAddressLine.size();i++)
        {
                for(int d=0;d<=1;d++)
                {
                    if(!addressLabel(i).text().contains("PAID"))
                    {
                        if (dose1(i, d).isDisplayed()) {

                            message.add(vaccineAvailable(i, d).text() + " is available at " + addressLabel(i).text() + " center with " + dose1(i, d).text() + " and " + dose2(i, d).text() + " on date " + date(d).text())

                        }
                        if(message.size()==2){
                            return message
                        }
                    }

                }


        }
        if(message.size()!=null){
            return message
        }

    }



}
