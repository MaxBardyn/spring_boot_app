package ul.lviv.iot.springjpa.mapper;

public interface Mapper<ENTITY, DTO> {


    DTO toDto(ENTITY entity);

    ENTITY toEntity(DTO dto);
}
