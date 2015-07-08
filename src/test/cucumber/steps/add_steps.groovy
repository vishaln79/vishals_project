import groovy.json.JsonOutput
import cucumber.api.PendingException

import static cucumber.api.groovy.EN.*

import groovyx.net.http.RESTClient
import groovyx.net.http.ContentType
import com.confluex.mock.http.MockHttpServer
import static com.confluex.mock.http.matchers.HttpMatchers.*
import static groovyx.net.http.ContentType.JSON

def resp
def thirdPartyResp =400
def inputOrder
def error
def client = new RESTClient('http://localhost:9091')
def mockClient


mockClient = new RESTClient('http://localhost:7070')



 //mockServer.respondTo(post('/ftp')).withStatus(400).withBody('Error')
MockHttpServer mockServer = new MockHttpServer(7070)
mockServer.respondTo(post('/ftp')).withStatus(400).withBody('')



Given(~'^a valid order$') { ->
    inputOrder = '{ "orderNumber": "1234", "skuNumber": "Novatel01" }'
}

When(~'^the order is posted to the entry endpoint$') { ->

    try {
        resp = client.post(
                path: '/vendorOrder',
                requestContentType: ContentType.JSON,
                body: inputOrder)
        error = null
    }
    catch (all) {
        resp = null
        error = all
    }

}

Then(~'a success message is returned') { ->
    assert resp.status == 200
}


/*
And(~/the order is placed in the vendor API/) { ->
    //throw new PendingException()
    try {
        resp = mockClient.post(
                path: '/ftp'
        )
        error = null
    }
    catch (all) {
        resp = null
        error = all
    }
   // thirdPartyResp = resp.status
   assert resp.status == 400

}
*/

Given(~'^an invalid order$') { ->
    inputOrder = '{ "foo": "bar" }'


}

When(~'the order is placed in the vendor API$') { ->

    try {
        resp = mockClient.post(
                path: '/ftp'
        )
        error = null
    }
    catch (all) {
        resp = null
        error = all
    }

  assert resp.status == 400


}



Then(~'a validation error message is returned') { ->

    // throw new PendingException()
    assert resp.status == 400

}

/*

VisitorID
CustNumb
Password
PSN
PartNumber
RequestType
ServiceLocationLatitude
ServiceLocationLongitude
ImmediateActivation
ScheduledServiceStartDateTime
CustomerPurchaseOrderReference
IndirectCustomerName
IndirectCustomerReference
CustomerNotes

 */