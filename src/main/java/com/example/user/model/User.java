package com.example.user.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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