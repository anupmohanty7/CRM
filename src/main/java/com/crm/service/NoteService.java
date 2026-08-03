package com.crm.service;

import java.util.List;

import com.crm.entity.Note;

public interface NoteService {
	
	public Note saveNote(Note note);
	public Note getNoteById(Long id);
	public List<Note> getAllNotes();
	public Note updateNote(Long id,Note note);
	public void deleteNote(Long id);
}
