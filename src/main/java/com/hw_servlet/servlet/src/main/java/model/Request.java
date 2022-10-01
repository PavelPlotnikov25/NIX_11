package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Request {

    private String ip;
    private String userAgent;
    private LocalDateTime dateTime;

}
