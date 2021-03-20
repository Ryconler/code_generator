import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/${nameUtils.getUnderScoreName(entity.name)}")
public class ${entity.name}Controller {

	@Autowired
	private ${entity.name}Repository repository;

	@GetMapping("/list")
	public List<${entity.name}> list() {
		return this.repository.findAll();
	}

	@GetMapping("/{id:\\d+}")
	public ${entity.name} getById(@PathVariable("id") Long id) {
		return this.repository.getOne(id);
	}

	@PostMapping
	public ${entity.name} create(@RequestBody ${entity.name} dto) {
		this.repository.save(dto);
		return dto;
	}

	@PutMapping("/{id:\\d+}")
	public ${entity.name} update(@RequestBody ${entity.name} dto, @PathVariable("id") Long id) {
		${entity.name} entity = this.repository.getOne(id);
		return entity;
	}

	@DeleteMapping("/{id:\\d+}")
	public String delete(@PathVariable Long id) {
		this.repository.deleteById(id);
		return "ok";
	}
	
}
