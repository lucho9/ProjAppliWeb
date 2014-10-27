package m2.project.model.serialization;

import java.io.IOException;

import m2.project.model.Customer;
import m2.project.model.CustomerGroup;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomerSerializer extends JsonSerializer<Customer> {
    @Override
    public void serialize(Customer value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getId());
        jgen.writeStringField("firstName", value.getFirstName());
        jgen.writeStringField("lastName", value.getLastName());
        
        jgen.writeArrayFieldStart("customerGroups");
        for (CustomerGroup group : value.getCustomerGroups()) {
        	jgen.writeNumber(group.getId());
        }
        jgen.writeEndArray();
        //jgen.writeNumberField("customerGroups", 1);
        
        
        //jgen.writeStringField("address", value.getAddress());
        //jgen.writeStringField("phoneNumber", value.getPhoneNumber());
        //jgen.writeStringField("mobileNumber", value.getMobileNumber());
        //jgen.writeStringField("email", value.getEmail());
        
        jgen.writeEndObject();
    }
}