package br.com.lucasmattos.softplayer.java.apply.backend.provider.interceptor;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

@Provider
public class CORSInterceptor implements ContainerResponseFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext req, ContainerResponseContext res) {
        res.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
        res.getHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        String reqHeader = req.getHeaderString("Access-Control-Request-Headers");
        if (reqHeader != null && reqHeader != "") {
            res.getHeaders().putSingle("Access-Control-Allow-Headers", reqHeader);
        }
    }
}
