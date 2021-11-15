package csv_generation.resource;

import com.jlefebure.spring.boot.minio.MinioException;
import com.jlefebure.spring.boot.minio.MinioService;
import csv_generation.exceptions.TechnicalException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class StorageServiceImplTest {


    @Mock
    private MinioService minioService;

    @InjectMocks
    private StorageServiceImpl storageService;


    @Test
    void test_save() throws MinioException {
        InputStream inputStream = Mockito.mock(InputStream.class);
        String s = storageService.save(inputStream);
        assertTrue(s != null);
        verify(minioService, times(1)).upload(Path.of(s), inputStream, "text/csv");
    }

    @Test
    void test_throwing_exception() throws MinioException {
        InputStream inputStream = Mockito.mock(InputStream.class);
        doThrow(MinioException.class).when(minioService).upload(any(Path.class),any(InputStream.class),any(String.class));
        assertThrows(TechnicalException.class, () -> storageService.save(inputStream));
    }

}