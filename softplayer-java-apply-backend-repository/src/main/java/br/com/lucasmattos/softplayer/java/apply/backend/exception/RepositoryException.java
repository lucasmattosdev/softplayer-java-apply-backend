package br.com.lucasmattos.softplayer.java.apply.backend.exception;

import lombok.Getter;

import javax.ws.rs.core.Response;

@Getter
public class RepositoryException extends Exception{
        private Response.Status responseStatus;
        private String message;

        public RepositoryException(Response.Status responseStatus){
            super(responseStatus.getReasonPhrase());
            this.responseStatus = responseStatus;
        }

        public RepositoryException(Response.Status responseStatus, String message){
            super(message);
            this.responseStatus = responseStatus;
            this.message = message;
        }
}
