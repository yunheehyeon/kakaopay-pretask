package kakaopay.problem.aipservice.domain.region;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "region")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Region {

    @Id
    @GenericGenerator(name = "region_id_generator", strategy = "kakaopay.problem.aipservice.domain.region.RegionIdGenerator")
    @GeneratedValue(generator = "region_id_generator")
    @Column(name = "region_id")
    private String id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Region(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return Objects.equals(id, region.id) &&
                Objects.equals(name, region.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
