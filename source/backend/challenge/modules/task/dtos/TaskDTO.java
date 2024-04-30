package backend.challenge.modules.task.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(staticName = "create")
public class TaskDTO {

	private String title;
	private String description;

	public TaskDTO(String title, String description) {
		this.title = title;
		this.description = description;
	}

}
