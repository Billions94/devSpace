package com.api.devSpace.space.Service;

import com.api.devSpace.space.Entity.Space;
import com.api.devSpace.space.input.SpaceInput;

import java.util.List;

public interface SpaceServiceInterface {
    Object createSpace(SpaceInput input);
    List<Space> getSpaces();
    Object getSpaceById(Long id);
    Object updateSpace(Long id, SpaceInput input);
    Object joinSpace(Long spaceId, Long userId);
    String deleteSpace(Long id);
}
