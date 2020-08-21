package br.com.kaikeventura.restmongodb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {

    private static final long serialVersionUID = -5309850391263326871L;

    @Field("address")
    @JsonProperty("address")
    private String address;

    @Field("postal_code")
    @JsonProperty("postal_code")
    private String postalCode;

    @Field("city")
    @JsonProperty("city")
    private String city;

    @Field("state")
    @JsonProperty("state")
    private String state;
}
