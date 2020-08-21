package br.com.kaikeventura.restmongodb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO implements Serializable {

    private static final long serialVersionUID = 3289885294184520854L;

    @JsonProperty("address")
    private String address;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("city")
    private String city;

    @JsonProperty("state")
    private String state;
}
