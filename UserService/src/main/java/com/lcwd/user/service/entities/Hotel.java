package com.lcwd.user.service.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Hotel {
    private String Id;
    private String name;
    private String location;
    private String about;
}
