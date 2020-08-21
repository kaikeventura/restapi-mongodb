package br.com.kaikeventura.restmongodb.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class AbstractModel implements Serializable {

    private static final long serialVersionUID = -3969611971361997308L;

    @Id
    private String id;

    @CreatedDate
    @Field("created_date")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Field("last_modified_date")
    private LocalDateTime lastModifiedDate;
}
