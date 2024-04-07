package domain;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.List;

import static org.mockito.Mockito.*;


@QuarkusTest
class CandidateServiceTest {
    @Inject
    CandidateService service;

    @InjectMock
    CandidateRepository repository;

    @Test
    void save() {
        Candidate candidate = Instancio.create(Candidate.class);

        service.save(candidate);

        verify(repository).save(candidate);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findAll() {
        List<Candidate> candidates = Instancio.stream(Candidate.class).limit(10).toList();

        when(repository.findAll()).thenReturn(candidates);

        List<Candidate> result = service.findAll();
    }

}