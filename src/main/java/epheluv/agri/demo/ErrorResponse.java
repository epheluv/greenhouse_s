package epheluv.agri.demo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

// 创建标准错误响应类
@Data
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
}
