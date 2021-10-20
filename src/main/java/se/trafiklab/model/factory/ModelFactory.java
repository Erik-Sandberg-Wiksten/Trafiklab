package se.trafiklab.model.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import se.trafiklab.model.Model;
import se.trafiklab.model.JourneyPatternPointOnLineModel;

public interface ModelFactory {
    Model<JourneyPatternPointOnLineModel> createJourneyPatternPointOnLineModel(String json) throws JsonProcessingException;
}
