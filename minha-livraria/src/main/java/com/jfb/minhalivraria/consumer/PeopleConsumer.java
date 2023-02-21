package com.jfb.minhalivraria.consumer;

import com.jfb.minhalivraria.People;
import com.jfb.minhalivraria.domain.Book;
import com.jfb.minhalivraria.repository.PeopleRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Slf4j
public class PeopleConsumer {

    private final PeopleRepository repository;

    public PeopleConsumer(PeopleRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "${topic.name}")
    public void consumer(ConsumerRecord<String, People> record, Acknowledgment acknowledgment) {
        var people = record.value();

        log.info("People vindo de mensageria: " + people.toString());

        var peopleEntity = com.jfb.minhalivraria.domain.People.builder().build();
        peopleEntity.setId(people.getId().toString());
        peopleEntity.setCpf(people.getCpf().toString());
        peopleEntity.setName(people.getName().toString());
        peopleEntity.setBooks(people.getBooks().stream().map(book -> Book.builder()
                .people(peopleEntity)
                .name(book.toString())
                .build()).collect(Collectors.toList()));

        acknowledgment.acknowledge();

        repository.save(peopleEntity);
    }
}
