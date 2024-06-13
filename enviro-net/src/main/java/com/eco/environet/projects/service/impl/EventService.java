package com.eco.environet.projects.service.impl;

import com.eco.environet.projects.dto.Event;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class EventService {

//    private final EventRepository repository;
//    private final MongoTemplate mongoTemplate;
//    private final EventQuery eventQuery;

//    @Transactional
//    public Event save(Event event) {
//        return repository.save(event);
//    }

    @Transactional
    public void notifyEnding(Event event) {
        event.setCreatedAt(LocalDateTime.now());
//        this.save(event);
        log.info("Order {} with saga notified! TransactionID: {}", event.getDocumentId(), event.getTransactionId());
    }

//    @Transactional(readOnly = true)
//    public List<Event> findAll() {
//        return this.repository.findAllByOrderByCreatedAtDesc();
//    }
//
//    @Transactional(readOnly = true)
//    public Event findByFilters(EventFilters filters) {
//        return Optional.ofNullable(mongoTemplate.findOne(eventQuery.filter(filters), Event.class))
//                .orElseThrow(() -> new ValidationException("Event not found"));
//    }
}
