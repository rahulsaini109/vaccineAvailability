# vaccineAvailability
This is the project which helps to notify people in India for the aavilability of the slot for the vaccines. This uses Cowin application (https://www.cowin.gov.in) though selenium script to find the slot from the website and sends SMS notifications for the slots.
The phone numbers and pin codes of the people are captured in a Google sheet in offline mode throuh restricted access and using Google AppScript the data is submitted to MongoDB Atlas database. From there REST API service is exposed at Realm application layer to share data which is used to send the notifactions by a groovy program integrated with selenium for finding slot and sending SMS to the people.
This will help for people who can't browse the CoWin site or people not having smart phone or laptop. This way they can get notifications on finding the slot.

The application is tested from local environment only for now. There is a plan to install the code on AWS infrastructure and run a daily scheduler to check the availability and send the SMS notification. For SMS Twilio's free account has been used, this is just for POC purpose. Another solution can be used for sending bulk SMS. 
