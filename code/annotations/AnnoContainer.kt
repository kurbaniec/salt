package pass.salt.code.annotations
import pass.salt.code.container.*

/**
 * Used to mark classes that should be configured with the Salt.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Scan()

/**
 * Used to mark a function, that returns an object that should be
 * managed by the Salt [Container].
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Bean()

/**
 * Used to mark a dependency injection, a value that should be filled
 * with a corresponding value from the Salt [Container].
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY)
annotation class Autowired()