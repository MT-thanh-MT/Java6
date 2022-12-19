package thi.app.model.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thi.app.model.dto.StatusDTO;
import thi.app.model.entity.Status;
import thi.app.model.mapper.StatusMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//@Service
public class StatusMapperImpl implements StatusMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Status toEntity(StatusDTO dto) {
        Status status = modelMapper.map(dto, Status.class);
        return status;
    }

    @Override
    public StatusDTO toDto(Status entity) {
        StatusDTO statusDTO = modelMapper.map(entity, StatusDTO.class);
        return statusDTO;
    }

    @Override
    public List<Status> toEntity(List<StatusDTO> dtoList) {
        return dtoList.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<StatusDTO> toDto(List<Status> entityList) {
        return entityList.stream()
                .filter(Objects::nonNull)
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
