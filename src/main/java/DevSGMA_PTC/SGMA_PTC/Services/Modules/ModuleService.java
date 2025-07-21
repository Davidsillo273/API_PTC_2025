package DevSGMA_PTC.SGMA_PTC.Services.Modules;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import DevSGMA_PTC.SGMA_PTC.Entities.Modules.Module;
import DevSGMA_PTC.SGMA_PTC.Repositories.Modules.ModuleRepository;

@Service
public class ModuleService {

    private final ModuleRepository moduleRepository;

    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    public Optional<Module> getModuleById(Long id) {
        return moduleRepository.findById(id);
    }

    public Module createModule(Module module) {
        return moduleRepository.save(module);
    }

    public Module updateModule(Long id, Module updatedModule) {
        return moduleRepository.findById(id)
                .map(m -> {
                    m.setName(updatedModule.getName());
                    return moduleRepository.save(m);
                }).orElse(null);
    }

    public boolean deleteModule(Long id) {
        if(moduleRepository.existsById(id)) {
            moduleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
