package m2.project.model.serialization;

import java.io.IOException;
import java.text.SimpleDateFormat;

import m2.project.model.Employee;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class EmployeeSerializer extends JsonSerializer<Employee> {
    @Override
    public void serialize(Employee value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getId());
        
        jgen.writeStringField("firstName", value.getFirstName());
        jgen.writeStringField("lastName", value.getLastName());
        jgen.writeStringField("address", value.getAddress());
        jgen.writeStringField("phoneNumber", value.getPhoneNumber());
        jgen.writeStringField("mobileNumber", value.getMobileNumber());
        jgen.writeStringField("email", value.getEmail());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        jgen.writeStringField("birth", dateFormat.format(value.getBirth()));
        
        jgen.writeStringField("login", value.getLogin());
        //jgen.writeStringField("password", value.getPassword());
        jgen.writeNumberField("role", value.getRole().getId());
        jgen.writeEndObject();
    }
}