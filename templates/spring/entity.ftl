import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "${nameUtils.getUnderScoreName(entity.name)}")
public class ${entity.name} {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    <#list entity.fields as field>
    @Column(name = "${nameUtils.getUnderScoreName(field.name)}")
    private ${field.type} ${field.name};

    </#list>
}
