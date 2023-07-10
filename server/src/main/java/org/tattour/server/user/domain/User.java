package org.tattour.server.user.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ProductLiked> productLikeds;
}
