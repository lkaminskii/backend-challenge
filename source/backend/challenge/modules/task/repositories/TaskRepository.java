package backend.challenge.modules.task.repositories;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.models.Task;

import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Singleton
public class TaskRepository implements ITaskRepository {

	private final List<Task> tasks = new ArrayList<>();
	private static int idIncreaser = 1;

	@Override
	public Task index(final UUID taskId) {
		for(Task task : tasks) {
			if (existsById(taskId)) {
				return task;
			}
		}

		return null;
	}

	@Override
	public List<Task> show() {
		return tasks;
	}

	@Override
	public Task create(final TaskDTO taskDTO) {
		Task task = new Task();

		task.setId(UUID.fromString("00000000-0000-0000-0000-" + String.format("%012d", idIncreaser++)));
		task.setTitle(TaskDTO.create().getTitle());
		task.setDescription(TaskDTO.create().getDescription());
		task.setProgress(0);
		task.setStatus(TaskStatus.PROGRESS);
		task.setCreatedAt(LocalDateTime.now());

		tasks.add(task);

		return task;
	}

	@Override
	public Task update(final Task task) {
		task.setTitle(task.getTitle());
		task.setDescription(task.getDescription());

		return task;
	}

	@Override
	public void delete(final UUID taskId) {
		Task task = index(taskId);

		tasks.remove(task);
	}

	@Override
	public boolean existsById(final UUID taskId) {
		for (Task task : tasks) {
			if (task.getId().equals(taskId)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Task updateProgress(final TaskProgressDTO taskProgressDTO) {
		for (Task task : tasks) {
			if (task.getId().equals(taskProgressDTO.getId())) {
				task.setProgress(taskProgressDTO.getProgress());
				return task;
			}
		}
		return null;
	}

}
