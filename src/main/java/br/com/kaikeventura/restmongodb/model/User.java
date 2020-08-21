package br.com.kaikeventura.restmongodb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class User extends AbstractModel implements Serializable {

    private static final long serialVersionUID = -950699212736045079L;

    @Field("name")
    @JsonProperty("name")
    private String name;

    @Field("last_name")
    @JsonProperty("last_name")
    private String lastName;

    @Field("age")
    @JsonProperty("age")
    private int age;

    @Field("document_number")
    @JsonProperty("document_number")
    private String documentNumber;

    @Field("address")
    @JsonProperty("address")
    private Address address;
}
