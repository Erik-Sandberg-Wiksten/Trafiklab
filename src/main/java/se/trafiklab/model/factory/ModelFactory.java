package se.trafiklab.model.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import se.trafiklab.model.Model;
import se.trafiklab.model.JourneyPatternPointOnLineModel;

import java.util.Optional;

public interface ModelFactory {
    Optional<Model<JourneyPatternPointOnLineModel>> createJourneyPatternPointOnLineModel(String json);
}
