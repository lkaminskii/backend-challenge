package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.infra.http.views.TaskView;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.services.*;
import kikaha.urouting.api.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
@Path("tasks")
public class TaskController {

	private final ICreateTaskService createTaskService;
	private final IDeleteTaskService deleteTaskService;
	private final IRetrieveAllTasksService retrieveAllTasksService;
	private final IRetrieveTaskByIdService retrieveTaskByIdService;
	private final IUpdateTaskService updateTaskService;

	@Inject
	public TaskController(
		final ICreateTaskService createTaskService,
		final IDeleteTaskService deleteTaskService,
		final IRetrieveAllTasksService retrieveAllTasksService
	) {
		this.createTaskService = createTaskService;
		this.deleteTaskService = deleteTaskService;
		this.retrieveAllTasksService = retrieveAllTasksService;
		this.retrieveTaskByIdService = null;
		this.updateTaskService = null;
	}

	@GET
	public Response show() {
		return DefaultResponse.ok().entity(retrieveAllTasksService.execute());
	}

	@GET
	@Path("single/{taskId}")
	public Response index(@PathParam("taskId") UUID taskId) {
		return DefaultResponse.ok().entity(retrieveTaskByIdService.execute(taskId));
	}

	@POST
	public Response create(TaskDTO taskDTO) {
		return DefaultResponse.ok().entity(createTaskService.execute(taskDTO));
	}

	@PUT
	@Path("single/{taskId}")
	public Response update(@PathParam("taskId") Long taskId, Task task) {
		/*
			TODO:  A rota deve alterar apenas o title e description da tarefa
			 			 que possua o id igual ao id correspondente nos parâmetros da rota.
		 */

		return DefaultResponse.ok().entity("Hello world");
	}

	@DELETE
	@Path("single/{taskId}")
	public Response delete(@PathParam("taskId") UUID taskId) {

		return DefaultResponse.ok().entity("");
	}

}
