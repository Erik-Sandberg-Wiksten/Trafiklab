package se.trafiklab.model.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import se.trafiklab.model.BaseModel;
import se.trafiklab.model.JourneyPatternPointOnLineModel;

public interface ModelFactory {
    BaseModel<JourneyPatternPointOnLineModel> createJourneyPatternPointOnLineModel(String json) throws JsonProcessingException;
}
