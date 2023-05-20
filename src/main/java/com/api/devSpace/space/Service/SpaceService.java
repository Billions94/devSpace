package com.api.devSpace.space.Service;

import com.api.devSpace.exception.PSQLException;
import com.api.devSpace.response.Failed;
import com.api.devSpace.response.Success;
import com.api.devSpace.space.Entity.Space;
import com.api.devSpace.space.Repository.SpaceRepository;
import com.api.devSpace.space.input.SpaceInput;
import com.api.devSpace.user.entity.User;
import com.api.devSpace.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class SpaceService implements SpaceServiceInterface {
    private final SpaceRepository spaceRepository;
    private final UserRepository userRepository;

    public Object createSpace(SpaceInput input) {
        try {
            if (spaceRepository.existsByName(input.getName()))
                throw new PSQLException("Space name is already taken");

            User user = userRepository.getUserById(input.getUserId());
            Space newSpace = new Space();
            newSpace.setName(input.getName());
            newSpace.getMembers().add(user);
            newSpace.setCreatedAt(LocalDateTime.now());

            spaceRepository.save(newSpace);
            userRepository.save(user);

            return new Success(newSpace.getId());
        } catch (PSQLException e) {
            return new Failed(e.getMessage());
        }
    }

    public List<Space> getSpaces() {
        return spaceRepository.findAll();
    }

    public Object getSpaceById(Long id) {
        try {
            if (!spaceRepository.existsById(id))
                throw new PSQLException("Space with " + id + " not found");

            Space space = spaceRepository.getById(id);
            return new Success(space);
        } catch (PSQLException e) {
            return new Failed(e.getMessage());
        }
    }

    public Object updateSpace(Long id, SpaceInput input) {
        try {
            if (!spaceRepository.existsById(id))
                throw new PSQLException("Space with " + id + " not found");

            Space modifiedSpace = spaceRepository.getById(id);
            modifiedSpace.setName(input.getName());
            modifiedSpace.setUpdatedAt(LocalDateTime.now());
            spaceRepository.save(modifiedSpace);

            return new Success(modifiedSpace);
        } catch (PSQLException e) {
            return new Failed(e.getMessage());
        }
    }

    public Object joinSpace(Long spaceId, Long userId) {
        try {
            User user = userRepository.getUserById(userId);
            Space space = spaceRepository.getById(spaceId);

            if (space.getMembers().contains(user))
                throw new PSQLException(user.getName() + " is already part of this space");

            space.getMembers().add(user);
            spaceRepository.save(space);
            return new Success(user.getName() + " Added to space");
        } catch (PSQLException e) {
            return new Failed(e.getMessage());
        }
    }


    public String deleteSpace(Long id) {
        if (!spaceRepository.existsById(id))
            throw new PSQLException("Space does " + id + " not exist");

        spaceRepository.deleteById(id);
        return "Space successfully deleted";
    }

}
