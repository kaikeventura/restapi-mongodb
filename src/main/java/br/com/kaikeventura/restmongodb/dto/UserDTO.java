package br.com.kaikeventura.restmongodb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 3091956487244452846L;

    @NotNull(message = "ERROR-01")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "ERROR-02")
    @JsonProperty("last_name")
    private String lastName;

    @NotNull(message = "ERROR-03")
    @Positive(message = "ERROR-04")
    @JsonProperty("age")
    private int age;

    @NotNull(message = "ERROR-05")
    @JsonProperty("document_number")
    private String documentNumber;

    @Valid
    @NotNull(message = "ERROR-06")
    @JsonProperty("address")
    private AddressDTO addressDTO;
}
