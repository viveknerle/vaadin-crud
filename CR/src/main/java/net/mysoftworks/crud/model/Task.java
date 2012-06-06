package net.mysoftworks.crud.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue( strategy = GenerationType.AUTO) 
	private Long id;
	
	@Column(name="TASK_NAME")
	private String taskName;
	
	@Column(name="WORKFLOW_NAME")
	private String workflowName;

	@Column(name="WORKFLOW_VERSION")
	private String workflowVersion;

	@Column(name="TASK_ROLE")
	private String taskRole;

	@Column(name="TASK_DESCRIPTION")
	private String taskDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getWorkflowName() {
		return workflowName;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	public String getWorkflowVersion() {
		return workflowVersion;
	}

	public void setWorkflowVersion(String workflowVersion) {
		this.workflowVersion = workflowVersion;
	}

	public String getTaskRole() {
		return taskRole;
	}

	public void setTaskRole(String taskRole) {
		this.taskRole = taskRole;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	
}
