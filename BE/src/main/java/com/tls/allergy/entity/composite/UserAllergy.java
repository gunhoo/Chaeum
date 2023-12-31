package com.tls.allergy.entity.composite;

import com.tls.allergy.entity.single.Allergy;
import com.tls.allergy.id.UserAllergyId;
import com.tls.user.entity.User;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_allergy_tb")
@IdClass(UserAllergyId.class)
public class UserAllergy {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userId;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "algy_id")
    private Allergy algyId;
}
