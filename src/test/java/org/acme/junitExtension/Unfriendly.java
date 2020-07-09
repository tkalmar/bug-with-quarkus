/*
 * Created on 09.07.2020
 *
 * Copyright(c) 1995 - 2020 T-Systems Multimedia Solutions GmbH
 * Riesaer Str. 5, 01129 Dresden
 * All rights reserved.
 */
package org.acme.junitExtension;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

import org.junit.jupiter.api.extension.ExtendWith;

@Target({METHOD, TYPE})
@Retention(RUNTIME)
@ExtendWith(UnfriendlyExtension.class)
public @interface Unfriendly {
}


