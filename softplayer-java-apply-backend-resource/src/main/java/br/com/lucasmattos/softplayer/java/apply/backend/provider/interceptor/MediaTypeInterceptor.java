package br.com.lucasmattos.softplayer.java.apply.backend.provider.interceptor;

import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

@Provider
public class MediaTypeInterceptor implements ContainerResponseFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext req, ContainerResponseContext res) {
        Method resourceMethod = resourceInfo.getResourceMethod();
        Class<?> resourceClass = resourceInfo.getResourceClass();
        if (resourceMethod != null && resourceClass != null){
            Produces producesMethod = resourceInfo.getResourceMethod().getAnnotation(Produces.class);
            Produces producesClass = resourceInfo.getResourceClass().getAnnotation(Produces.class);

            if (producesMethod == null && producesClass == null){
                res.getHeaders().putSingle(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
            } else {
                if (producesMethod != null){
                    res.getHeaders().add("Content-Type", producesMethod);
                } else {
                    res.getHeaders().add("Content-Type", producesClass);
                }
            }
        }
    }
}
