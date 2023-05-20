package com.api.devSpace.space.resolver;

import com.api.devSpace.space.Entity.Space;
import com.api.devSpace.space.Service.SpaceService;
import com.api.devSpace.space.input.SpaceInput;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class SpaceResolver {
    private final SpaceService spaceService;

    @MutationMapping
    public Object createSpace(@Argument("spaceInput") SpaceInput spaceInput) {
        return spaceService.createSpace(spaceInput);
    }

    @QueryMapping
    public List<Space> spaces() {
        return spaceService.getSpaces();
    }

    @QueryMapping
    public Object space(@Argument("spaceId") Long spaceId) {
        return spaceService.getSpaceById(spaceId);
    }

    @MutationMapping
    public Object updateSpace(@Argument("spaceId") Long spaceId, @Argument("spaceInput") SpaceInput spaceInput) {
        return spaceService.updateSpace(spaceId, spaceInput);
    }

    @MutationMapping
    public Object joinSpace(@Argument("spaceId") Long spaceId, @Argument("userId") Long userId) {
        return spaceService.joinSpace(spaceId, userId);
    }

    @MutationMapping
    public String deleteSpace(@Argument("spaceId") Long spaceId) {
        return spaceService.deleteSpace(spaceId);
    }
}
