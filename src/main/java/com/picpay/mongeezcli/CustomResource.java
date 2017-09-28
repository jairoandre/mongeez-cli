package com.picpay.mongeezcli;

import java.io.IOException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class CustomResource extends ByteArrayResource {
    CustomResource(byte[] bytes) {
        super(bytes);
    }

    @Override public Resource createRelative(String relativePath) throws IOException {
        return new FileSystemResource(relativePath);
    }
}
