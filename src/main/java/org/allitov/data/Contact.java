package org.allitov.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Contact {
    private final String name;
    private final String phoneNumber;
    private final String email;

    public String toString() {
        return String.format("| %s | %s | %s |", name, phoneNumber, email);
    }
}
