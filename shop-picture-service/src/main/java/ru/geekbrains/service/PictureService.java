package ru.geekbrains.service;


import ru.geekbrains.entities.PictureData;

import java.io.IOException;
import java.util.Optional;

public interface PictureService {

    Optional<String> getPictureContentTypeById(long id);

    Optional<byte[]> getPictureDataById(long id) throws IOException;

    PictureData createPictureData(byte[] picture) throws IOException;
}