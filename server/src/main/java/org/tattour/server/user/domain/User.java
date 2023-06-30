package org.tattour.server.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Integer id;
    private String name;
    private String email;
    private Integer point;
    private String phoneNumber;
    private String loginType;
    @Column(columnDefinition = "Timestamp")
    private String created_at;
    @Column(columnDefinition = "Timestamp")
    private String last_updated_at;
    @Column(columnDefinition = "tinyint")
    private Boolean state;
}
