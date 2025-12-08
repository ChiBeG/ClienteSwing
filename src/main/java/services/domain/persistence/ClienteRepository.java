package services.domain.persistence;

import model.Cliente;
import model.ClienteBuilder;

public class ClienteRepository extends Repository<Cliente, ClienteDTO> {

    private final IClienteDAO dao = DAOFactory.create(DAOType.CLIENTE);

    public Cliente findByCPF(Long cpf) {
        var dto = dao.findByCPF(cpf);
        return dto == null ? null : ClienteBuilder.buildFromDTO(dto);
    }
    
    @Override
    protected IDAO<ClienteDTO> getDAO() {
        return dao;
    }

    @Override
    protected ClienteDTO toDTO(Cliente entity) {
        return ClienteDTO.fromEntity(entity);
    }

    @Override
    protected Cliente toEntity(ClienteDTO dto) {
        return ClienteBuilder.buildFromDTO(dto);
    }
}
