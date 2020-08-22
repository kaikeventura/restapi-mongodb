package br.com.kaikeventura.restmongodb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO implements Serializable {

    private static final long serialVersionUID = 3289885294184520854L;

    @NotNull(message = "ERROR-07")
    @JsonProperty("address")
    private String address;

    @NotNull(message = "ERROR-08")
    @JsonProperty("postal_code")
    private String postalCode;

    @NotNull(message = "ERROR-09")
    @JsonProperty("city")
    private String city;

    @NotNull(message = "ERROR-10")
    @JsonProperty("state")
    private String state;
}
