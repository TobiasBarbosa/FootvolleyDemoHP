package org.example.footvolleydemohp.service;

import lombok.RequiredArgsConstructor;
import org.example.footvolleydemohp.model.Event;
import org.example.footvolleydemohp.repository.TrainingEventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TrainingEventService {

    //***ACCESS ATTRIBUTES***-------------------------------------------------------------------------------------------
    private final TrainingEventRepository trainingEventRepository;

    //***BUSINESS LOGIC & CRUD***---------------------------------------------------------------------------------------
    //                           --------------------------------------------------------------------------------------C
    public Event createTrainingEvent(Event event){
        return trainingEventRepository.save(event);
    }

    //-----------------------------------------------------------------------------------------------------------------R
    public Event getTrainingEventById(Long id){
        return trainingEventRepository.findById(id)
                                      .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Event> getAllTrainingEvents() {
        return trainingEventRepository.findAll();
    }

    //-----------------------------------------------------------------------------------------------------------------U
    public Event updateTrainingEvent(Long id, Event updatedEvent){
        Event existingEvent = getTrainingEventById(id);

        existingEvent.setTitle(updatedEvent.getTitle());
        existingEvent.setEventType(updatedEvent.getEventType());
        existingEvent.setTrainingLevel(updatedEvent.getTrainingLevel());
        existingEvent.setLocationName(updatedEvent.getLocationName());
        existingEvent.setLocationAddress(updatedEvent.getLocationAddress());
        existingEvent.setDate(updatedEvent.getDate());
        existingEvent.setStartTime(updatedEvent.getStartTime());
        existingEvent.setEndTime(updatedEvent.getEndTime());
        existingEvent.setPrice(updatedEvent.getPrice());
        existingEvent.setMaxParticipants(updatedEvent.getMaxParticipants());
        existingEvent.setDescription(updatedEvent.getDescription());

        return trainingEventRepository.save(existingEvent);

    }

    //-----------------------------------------------------------------------------------------------------------------D
    public void deleteTrainingEvent(Long id){
        trainingEventRepository.delete(getTrainingEventById(id));
    }


}
