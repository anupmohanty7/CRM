package com.crm.controller;

import java.util.List;

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

@RestController
@RequestMapping("/api/notes")
public class NoteController {
	
	private final NoteService noteService;
	public NoteController(NoteService noteService){
		this.noteService = noteService;
	}
	@PostMapping
	public Note saveNote(@RequestBody Note note){
		return noteService.saveNote(note);
	}
	@GetMapping
	public List<Note> getAllNote(){
		return noteService.getAllNotes();
	}
	@GetMapping("/{id}")
	public Note getNoteById(@PathVariable Long id) {
		return noteService.getNoteById(id);
	}
	@PutMapping("/{id}")
	public Note updateNote(@PathVariable Long id,@RequestBody Note note) {
		return noteService.updateNote(id, note);
	}
	@DeleteMapping("/{id}")
	public void deleteNote(@PathVariable Long id) {
		noteService.deleteNote(id);
	}
	}
