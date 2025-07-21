package DevSGMA_PTC.SGMA_PTC.Entities.Modules;

import jakarta.persistence.*;

@Entity
@Table(name = "tbModules")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moduleId")
    private Long id;

    @Column(name = "moduleName", length = 50, nullable = false)
    private String name;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
