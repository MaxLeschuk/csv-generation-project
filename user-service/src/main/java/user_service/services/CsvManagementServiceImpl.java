package user_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import user_service.brokers.MessageBroker;
import user_service.dao.CsvTemplatesMapper;
import user_service.entities.CsvFile;

import java.util.List;


@Component
public class CsvManagementServiceImpl implements CsvManagementService {

    @Autowired
    private MessageBroker messageBroker;

    @Autowired
    private CsvTemplatesMapper csvTemplatesMapper;

    @Override
    public void create(Integer id) {
        messageBroker.sendMessage(new String[]{"col1","col2","col3",},"csv_generate");
    }

    @Override
    public List<CsvFile> findAll() {
        return null;
    }

    @Override
    public void saveFileData(String path) {

    }
}
