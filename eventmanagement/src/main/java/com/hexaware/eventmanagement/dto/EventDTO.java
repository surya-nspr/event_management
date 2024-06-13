package com.hexaware.eventmanagement.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class EventDTO {
	private Long id;
    @NotEmpty(message = "Title is required")
    private String title;

    @NotEmpty(message = "Description is required")
    private String description;

    @Future(message = "Date must be in the future")
    private LocalDateTime date;

    @NotEmpty(message = "Location is required")
    private String location;

    @Positive(message = "Maximum attendees must be a positive number")
    private int maxAttendees;

    @PositiveOrZero(message = "Registration fee cannot be negative")
    private double registrationFee;
    

    public EventDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EventDTO(Long id,String title, String description, LocalDateTime date, String location,
			int maxAttendees,double registrationFee) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
		this.location = location;
		this.maxAttendees = maxAttendees;
		this.registrationFee = registrationFee;
	}

	// Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMaxAttendees() {
        return maxAttendees;
    }

    public void setMaxAttendees(int maxAttendees) {
        this.maxAttendees = maxAttendees;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "EventDTO [id=" + id + ", title=" + title + ", description=" + description + ", date=" + date
				+ ", location=" + location + ", maxAttendees=" + maxAttendees + ", registrationFee=" + registrationFee
				+ "]";
	}
    
}

