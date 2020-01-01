package pass.salt.code.annotations

import pass.salt.code.modules.server.security.WebSecurityConfigurator

/**
 * Marks a class as a security configurator.
 *
 * Important: The annotated class needs to implement the [WebSecurityConfigurator] interface.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Scan
annotation class WebSecurity()