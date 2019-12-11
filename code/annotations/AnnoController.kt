package pass.salt.code.annotations

/**
 * Marks a class as a Controller configurator for requests.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Scan
annotation class Controller()

/**
 * Mapping for GET-Request endpoint.
 * Only viable in classes marked with [Controller].
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Get(val path: String)

/**
 * Mapping for POST-Request endpoint.
 * Only viable in classes marked with [Controller].
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Post(val path: String)

/**
 * Mapping for Parameters that are expected from the request.
 * Can be used on functions mapped with [Get] or [Post] annotations.
 * Only viable in classes marked with [Controller].
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class Param(val name: String)


