package pass.salt.code.annotations

/**
 * Marks a class as a security configurator.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Scan
annotation class WebSecurity()