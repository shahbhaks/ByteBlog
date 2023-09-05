package com.example.byteblog.exception;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {
   public ResourceNotFoundException(String message, Long userId) {
   }

   public ResourceNotFoundException(String message) {
      super(message);
   }

   public ResourceNotFoundException(String message, Throwable cause) {
      super(message, cause);
   }

   public ResourceNotFoundException(Throwable cause) {
      super(cause);
   }

   public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}

