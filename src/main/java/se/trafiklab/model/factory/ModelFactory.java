package se.trafiklab.model.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import se.trafiklab.model.Model;
import se.trafiklab.model.JourneyPatternPointOnLineModel;
import se.trafiklab.model.StopPointModel;

import java.util.Optional;

public interface ModelFactory {
    Optional<Model<JourneyPatternPointOnLineModel>> createJourneyPatternPointOnLineModel(String json);
    Optional<Model<StopPointModel>> createStopPointModel(String json);
}
