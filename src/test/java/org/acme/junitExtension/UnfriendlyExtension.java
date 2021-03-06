/*
 * Created on 09.07.2020
 *
 * Copyright(c) 1995 - 2020 T-Systems Multimedia Solutions GmbH
 * Riesaer Str. 5, 01129 Dresden
 * All rights reserved.
 */
package org.acme.junitExtension;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.ext.InterceptorContext;

import io.quarkus.test.junit.callback.QuarkusTestBeforeEachCallback;
import io.quarkus.test.junit.callback.QuarkusTestMethodContext;
import org.acme.StaticProvider;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Interceptor
@Unfriendly
public class UnfriendlyExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback, QuarkusTestBeforeEachCallback {
    Logger LOG = LoggerFactory.getLogger(UnfriendlyExtension.class);
    @Override
    public void beforeTestExecution(final ExtensionContext context) throws Exception {
        setProvider();
    }

    private void setProvider() {
        System.out.println("Inside Extension CL: " + Thread.currentThread().getContextClassLoader().toString());
        StaticProvider.setGreeting("my foe!");
    }

    private void resetProvider() {
        StaticProvider.setGreeting("my friend!");
    }

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        setProvider();
        Object proceed = context.proceed();
        resetProvider();
        return proceed;
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        resetProvider();
    }

    @Override
    public void beforeEach(QuarkusTestMethodContext quarkusTestMethodContext) {
        Unfriendly annotation = quarkusTestMethodContext.getTestMethod().getAnnotation(Unfriendly.class);
        if (annotation == null) {
            LOG.warn("Annotation is null on method: "+ quarkusTestMethodContext.getTestMethod().getName());
        }

    }
}
