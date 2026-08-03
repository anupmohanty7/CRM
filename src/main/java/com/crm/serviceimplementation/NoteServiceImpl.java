package com.crm.serviceimplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.entity.Customer;
import com.crm.entity.Note;
import com.crm.exception.CustomerNotFoundException;
import com.crm.exception.NoteNotFoundException;
import com.crm.repository.CustomerRepository;
import com.crm.repository.NoteRepository;
import com.crm.service.NoteService;

@Service
public class NoteServiceImpl implements NoteService {

	private final NoteRepository noteRepository;
	private final CustomerRepository customerRepository;

	public NoteServiceImpl(NoteRepository noteRepository, CustomerRepository customerRepository) {
		this.noteRepository = noteRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public Note saveNote(Note note) {
		Customer customer = customerRepository.findById(note.getCustomer().getId())
				.orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
		note.setCustomer(customer);
		return noteRepository.save(note);
	}

	@Override
	public Note getNoteById(Long id) {
		// TODO Auto-generated method stub
		return noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException("Note not found"));
	}

	@Override
	public List<Note> getAllNotes() {
		// TODO Auto-generated method stub
		return noteRepository.findAll();
	}

	@Override
	public Note updateNote(Long id, Note note) {
		
		Customer customer = customerRepository.findById(note.getCustomer().getId())
				.orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
		note.setCustomer(customer);
		Note existingnote = getNoteById(id);
		existingnote.setContent(note.getContent());
		return noteRepository.save(existingnote);
	}

	@Override
	public void deleteNote(Long id) {
		getNoteById(id);
		noteRepository.deleteById(id);
	}

}
