package se.trafiklab.model.factory.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.trafiklab.model.Model;
import se.trafiklab.model.JourneyPatternPointOnLineModel;
import se.trafiklab.model.factory.ModelFactory;

import java.util.Optional;

public class ModelFactoryImpl implements ModelFactory {
    private ObjectMapper mapper;

    public ModelFactoryImpl(ObjectMapper mapper) {
        this.mapper = mapper;
        this.mapper.findAndRegisterModules();
    }

    @Override
    public Optional<Model<JourneyPatternPointOnLineModel>> createJourneyPatternPointOnLineModel(String json) {
        try {
            return mapper.readValue(json, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }
}
