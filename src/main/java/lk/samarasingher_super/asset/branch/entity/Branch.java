package lk.samarasingher_super.asset.branch.entity;


import lk.samarasingher_super.asset.common_asset.model.enums.ActiveOrInactive;
import lk.samarasingher_super.asset.employee.entity.Employee;
import lk.samarasingher_super.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Branch extends AuditEntity {

    @Size( min = 5, message = "Your name cannot be accepted" )
    private String name;

    @Size( max = 10, min = 9, message = "Land number length should be contained 10 and 9" )
    @Column( unique = true )
    private String land;

    @Column( columnDefinition = "VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin NULL", length = 255 )
    private String address;

    @Column( unique = true )
    private String email;

    @Enumerated(EnumType.STRING)
    private ActiveOrInactive activeOrInactive;

   @OneToMany( mappedBy = "branch" )
    private List<Employee> employees;
}