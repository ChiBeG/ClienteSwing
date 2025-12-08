package services.domain.persistence;

import model.Carro;

public class CarroRepository extends Repository<Carro, CarroDTO> {

    private final ICarroDAO dao = DAOFactory.create(DAOType.CARRO);

    public Carro findByPlaca(String placa) {
        var dto = dao.findByPlaca(placa);
        Carro carro = null;

        if (dto != null) {
            carro = Carro.of(dto.placa, dto.modelo, dto.anoFabricacao, dto.km);
            carro.setId(dto.id);
        }

        return carro;
    }

    @Override
    protected IDAO<CarroDTO> getDAO() {
        return dao;
    }

    @Override
    protected CarroDTO toDTO(Carro entity) {
        return new CarroDTO(entity.getId(), 
                            entity.getPlaca(), 
                            entity.getModelo(),
                            entity.getAnoFabricacao(), 
                            entity.getKm());
    }

    @Override
    protected Carro toEntity(CarroDTO dto) {
        Carro carro = Carro.of(dto.placa, 
                               dto.modelo, 
                               dto.anoFabricacao, 
                               dto.km);
        carro.setId(dto.id);
        return carro;
    }
}
