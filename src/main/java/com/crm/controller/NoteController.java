package com.crm.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.entity.Note;
import com.crm.service.NoteService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/notes")
@SecurityRequirement(name = "bearerAuth")

public class NoteController {
	
	private final NoteService noteService;
	public NoteController(NoteService noteService){
		this.noteService = noteService;
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
	@PostMapping
	public Note saveNote(@RequestBody Note note){
		return noteService.saveNote(note);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
	@GetMapping
	public List<Note> getAllNote(){
		return noteService.getAllNotes();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
	@GetMapping("/{id}")
	public Note getNoteById(@PathVariable Long id) {
		return noteService.getNoteById(id);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
	@PutMapping("/{id}")
	public Note updateNote(@PathVariable Long id,@RequestBody Note note) {
		return noteService.updateNote(id, note);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public void deleteNote(@PathVariable Long id) {
		noteService.deleteNote(id);
	}
	}
