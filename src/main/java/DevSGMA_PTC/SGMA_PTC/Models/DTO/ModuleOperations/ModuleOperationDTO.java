package DevSGMA_PTC.SGMA_PTC.Models.DTO.ModuleOperations;

public class ModuleOperationDTO {
    private Long id;
    private String name;
    private Long moduleId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getModuleId() { return moduleId; }
    public void setModuleId(Long moduleId) { this.moduleId = moduleId; }
}
