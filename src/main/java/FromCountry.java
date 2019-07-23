import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;

import static spark.Spark.get;
import static spark.Spark.post;


//THE {{FromCountry}} works only in the TWILML BIN
//This is different than what the instruction has you do for New app, who dis?
/**
 * <?xml version="1.0" encoding="UTF-8"?>
 * <Response>
 *   <Message>
 *     Hi! It looks like your phone number was born in {{ FromCountry }}
 *   </Message>
 * </Response>
 */

//You can also create a template to include the values of HTTP parameters
//passed-in via the webhook request. Here is an example of creating
// a template that forwards all incoming SMS messages to a specified mobile number.
//<Response>
//<Message to="+12065551212">
//        {{From}}: {{Body}}
//</Message>
//</Response>

public class FromCountry {

    public static void main(String[] args) {

        get("/", (req, res) -> "Website");

        post("/sms", (req, res) -> {
            res.type("application/xml");
            Body body = new Body
                    .Builder("{{FromCountry}}")
                    .build();
            Message sms = new Message
                    .Builder()
                    .body(body)
                    .build();
            MessagingResponse twiml = new MessagingResponse
                    .Builder()
                    .message(sms)
                    .build();
            return twiml.toXml();
        });
    }
}
