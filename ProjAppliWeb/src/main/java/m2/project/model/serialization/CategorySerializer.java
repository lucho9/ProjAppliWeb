package m2.project.model.serialization;

import java.io.IOException;

import m2.project.model.Category;
import m2.project.model.Customer;
import m2.project.model.CustomerGroup;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CategorySerializer extends JsonSerializer<Category> {
    @Override
    public void serialize(Category value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getId());
        jgen.writeStringField("name", value.getName());
        jgen.writeNumberField("tvaid", value.getTVA().getId());
        jgen.writeStringField("tva", ""+value.getTVA().getTva());
        
     
   
        jgen.writeEndObject();
    }
}