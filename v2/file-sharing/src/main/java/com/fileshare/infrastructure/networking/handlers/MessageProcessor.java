package com.fileshare.infrastructure.networking.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fileshare.infrastructure.networking.protocol.NetworkMessage;

public interface MessageProcessor {

    NetworkMessage process(NetworkMessage message) throws JsonMappingException, JsonProcessingException;
}