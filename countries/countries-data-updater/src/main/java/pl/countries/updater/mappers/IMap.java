package pl.countries.updater.mappers;

public interface IMap<TDto, TEntity> {

    TEntity toEntity(TDto dto);
}
