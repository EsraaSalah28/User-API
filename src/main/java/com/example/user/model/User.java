package com.example.user.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class User {


    @Id
    @JsonView(Views.MyResponseViews.class)
    @Column(length = 2048)
    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private boolean marketingConsent;
    @JsonView(Views.MyResponseViews.class)
    @Column(length = 2048)
    private String accessToken;
}