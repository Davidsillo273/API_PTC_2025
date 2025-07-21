package DevSGMA_PTC.SGMA_PTC.Entities.ModuleOperations;

import jakarta.persistence.*;
import DevSGMA_PTC.SGMA_PTC.Entities.Modules.Module;

@Entity
@Table(name = "tbModuleOperations")
public class ModuleOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operationId")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "route", nullable = false, length = 100)
    private String route;

    @ManyToOne
    @JoinColumn(name = "moduleId", nullable = false)
    private Module module;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRoute() { return route; }
    public void setRoute(String route) { this.route = route; }

    public Module getModule() { return module; }
    public void setModule(Module module) { this.module = module; }
}

